/* 授業選択 */

//組データ削除判別フラグ
var flag = "0";

// 学年ごとの組データの取得
function clickGrade(school_year) {
	// CSRFトークンの取得
	const token = $("meta[name='_csrf']").attr("content");

	console.log("学年",school_year);
	
	// 非同期通信
	$.ajax({
		type : "POST",
		url : "/rest/room_select",
		headers : {
			"X-CSRF-TOKEN" : token
		},
		data : {
			js_schoolyear : school_year,
		},
		dataType : "json"
	})
	.then(function(response_data) {
		// 成功時
		console.log("response_data", response_data);

		//既に表示されている「組」の要素を全て消す
		$(".bigsmall_list_selectclass").remove();

		/*-- 表示処理 --*/
		for (var i = 0; i < response_data.length; i++){
			//表示させるエレメントを取得
			var addPlace = document.getElementById("small_list_selectclass");
			//ボタンを生成
			var btn = document.createElement('button');
			btn.textContent = response_data[i]["schoolclass"] + '組';
			btn.setAttribute("type", "button");
			btn.setAttribute("name", i);
			btn.setAttribute("value", response_data[i]["schoolclass"]);
			btn.setAttribute("data-parameter1", response_data[i]["classid"]);
			btn.setAttribute('onclick', "GetAction_Class(this.getAttribute('data-parameter1'))");
			btn.classList.add("gradel_btn");
			btn.classList.add("bigsmall_list_selectclass");
			//要素の追加
			addPlace.appendChild(btn);
		}
	}, function() {
		// 失敗時
		//既に表示されている「組」の要素を全て消す
		$("#small_list_selectclass").empty();
		console.log('[$.ajax]"/rest/room_select" Fail');
	});
}

//授業画面に遷移
function GetAction_Class(classid){
	window.location.href = '/class/teacherclass?classid=' +  encodeURIComponent(classid);
}