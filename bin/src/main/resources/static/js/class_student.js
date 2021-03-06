/*　生徒側　*/
var stompClient = null; // Websocket接続用変数
var notice_flag = 0; //出席送信カウント

/* ロード処理 */
$(document).ready(() => {
	const session_id =  $("#student_sessionid").val();
  
	// アクセスするエンドポイントを設定
	var socket = new SockJS('/socket_endpoint');
	stompClient = Stomp.over(socket);
	// エンドポイントに対して接続
	stompClient.connect({}, function (frame) {
		
		// 先生のセッションidの取得
		const class_id = $("#classid").val();
		//送信
		stompClient.send("/socket_prefix/get_sessionid", {}, JSON.stringify({'class_id':class_id}));
		//受信
	    stompClient.subscribe('/user/queue/get_sessionid', function (response_data) {
	    	let element_teacher_sessionid = document.getElementById('teacher_sessionid');
	    	element_teacher_sessionid.value = JSON.parse(response_data.body).session_id;
	    	// 出席処理
	    	SendToNotice(JSON.parse(response_data.body).session_id);
	    });
	    
	    // 授業内問題の取得
		stompClient.subscribe('/user/queue/send_answer', function(response_message) {
			//modal表示処理
			ShowModal(JSON.parse(response_message.body).issue,JSON.parse(response_message.body).answer);
		});
		
		// 一括退出
		stompClient.subscribe('/user/queue/bulkexit', function(response_message) {
			//ホームページに遷移
			window.location.href = '/home/studenthome';
		});
	}) 
})

/* 授業画面退出時 */
window.onload = function(){
	window.onunload = function(){
		//退出ログ
		ExitRog();
	}
}


/* 処理 */
//退出ログの保存、一時保存のセッションidのデータ削除
function ExitRog(){
	const enter_id =  $("#enterid").val();
	
	// CSRFトークンの取得
	const token = $("meta[name='_csrf']").attr("content");
	// 非同期通信
	$.ajax({
		type : "POST",
		url : "/rest/exit",
		headers : {
			"X-CSRF-TOKEN" : token
		},
		data : {
			js_enter_id : enter_id
		},
		dataType : "json"
	})
	.then(function(response_data) {
		// 成功時
		console.log("response_data",response_data);
		if(response_data == 1){
			//登録成功
		}
		else{
			//登録失敗
		}
	}, function() {
		// 失敗時
		console.log('[$.ajax]"/rest/exit" Fail');
	});
	
	
}

// 出席処理
function SendToNotice(teacher_sessionid){
	//出席処理を行うのを1回だけにする
		const student_name = document.getElementById('student_name').value;
		const student_classno = document.getElementById('student_classno').value;
		
		// アクセスするエンドポイントを設定
		var socket = new SockJS('/socket_endpoint');
		stompClient = null;
		stompClient = Stomp.over(socket);
		// エンドポイントに対して接続
		stompClient.connect({}, function (frame) {
			//1回のみ送信する
			if(notice_flag == 0){
				stompClient.send("/socket_prefix/send_notice", {}, JSON.stringify({'student_name':student_name,'student_classno':student_classno,'teacher_sessionid':teacher_sessionid}));
				notice_flag += 1;
			}
		})
}

// 音声認識取得処理
function GetVoiceRecog(flag){
	
	//接続処理
	if(flag == 1){
		var socket = new SockJS('/socket_endpoint');
		stompClient = Stomp.over(socket);
		
		// エンドポイントに対して接続
		stompClient.connect({}, function (frame) {
			// 受信
			stompClient.subscribe('/user/queue/voice_recog', function (response_data) {
				// 表示
				ShowVoiceRecognition(JSON.parse(response_data.body).voicetext);
			});
		});
	}
	//切断処理
	else if(flag == 0){
		stompClient.disconnect();
	}
}

// 問題の解答送信処理
function SendAnswer() {
	//生徒名の取得
	const studentname = document.getElementById('student_name').value;
	//出席番号の取得
	const class_no = document.getElementById('student_classno').value;
	//解答の取得
	const answer_value = document.getElementById('answer').value;
	//先生のセッションidの取得
	const teacher_sessionid =  $("#teacher_sessionid").val();
	
	//先生側に解答を送信
	var socket = new SockJS('/socket_endpoint');
	stompClient = Stomp.over(socket);
	// エンドポイントに対して接続
	stompClient.connect({}, function (frame) {
		stompClient.send("/socket_prefix/send_student_answer", {}, JSON.stringify({'studentname':studentname,'class_no':class_no,'answer':answer_value,'teacher_sessionid':teacher_sessionid}));
	});
	
	// CSRFトークンの取得
	const token = $("meta[name='_csrf']").attr("content");
	// 非同期通信
	$.ajax({
		type : "POST",
		url : "/rest/issue_answer",
		headers : {
			"X-CSRF-TOKEN" : token
		},
		data : {
			js_answer : answer_value,
		},
		dataType : "json"
	})
	.then(function(response_data) {
		// 成功時
		console.log("response_data",response_data);
		if(response_data == 1){
			//登録成功
		}
		else{
			//登録失敗
		}
	}, function() {
		// 失敗時
		console.log('[$.ajax]"/rest/issue_answer" Fail');
	});
	
	//解答正解チェック
	if(answer_value == issue_answer){
		$("#answer_result").empty();
		$("#result_value").empty();
		
		$("#answer_result").append("正解");
	}
	else{
		$("#answer_result").empty();
		$("#result_value").empty();
		
		$("#answer_result").append("不正解");
		$("#result_value").append("正解は" + issue_answer + "です。");
	}
}

/* 表示 */

// 字幕表示
function ShowVoiceRecognition(message) {
	$("#subtitle").append("<p>" + message + "</p>");
}

//modal表示
function ShowModal(issue,answer) {
	//問題と解答を空にする
	$("#show").empty();
	var input = document.getElementById("answer");
	input.value = '';
	
	//問題をセット
	$("#show").append(issue);
	issue_answer = answer
	// Modalオープンボタン
	// 表示中のページと最終ページ番号
	var page, max = 2;
	
	$(function() {
		// Modalオープンボタン
		page = 1;
		drawModal();
		$("#myModal").modal("show");

		// 次へボタン
		$(".btnNext").click(function() {
			page++;
			drawModal();
		});

		// 前へボタン
		$(".btnPrev").click(function() {
			page--;
			drawModal();
		});

		// Modal内表示
		function drawModal() {
			for (var i = 1; i <= max; i++) {
				if (i == page){
					$("#modal-page" + i).show()
				}else{
					$("#modal-page" + i).hide()
				}
			}
		}
	});
}