/* 表示 */
var name ="";
var school_year="";
var school_class="";
var categoraiz = "";

function ShowModal(){
	console.log("##############");
	 name = $('#student_information option:selected').text();
	 school_year = $('#studen_year option:selected').text();
	 school_class = $('#student_class option:selected').text();
	 categoraiz = 0;
 
 $(function() {
  // Modalオープンボタン
  $("#modal").modal("show");
  
   // p要素にテキストを出力
    $('#school_year').text(school_year);
    $('#school_class').text(school_class);
    $('#name').text(name);

 });
 
}

function ShowModal2(){
	console.log("##############");
	 name = $('#student_information option:selected').text();
	 school_year = $('#studen_year option:selected').text();
	 school_class = $('#student_class option:selected').text();
	 categoraiz = 1;
 
 $(function() {
  // Modalオープンボタン
  $("#modal2").modal("show");
  
   // p要素にテキストを出力
    $('#school_year1').text(school_year);
    $('#school_class1').text(school_class);
    $('#name1').text(name);

 });
 
}

//転校ボタンを押したときの処理
function Category(data_parameter1){
	console.log(data_parameter1);
	
	// CSRFトークンの取得
 	const token = $("meta[name='_csrf']").attr("content");
	
	 name = $('#student_information option:selected').text();
	 school_year = $('#studen_year option:selected').text();
	 school_class = $('#student_class option:selected').text();
	
		console.log("転校")
		$.ajax({
		  type : "POST",
		  url : "/rest/return_transfer",
		  headers : {
		   "X-CSRF-TOKEN" : token
		  },
		  data : {
		   name : name,
		   school_year : school_year,
		   school_class : school_class,
		   categoraiz : categoraiz
		  }
		 })
		 .then(function(response_data){
		  //成功
		  console.log("success")
		 }
		 ,function(){
		  //失敗
		  console.log("error")
		 });
			
}

//退学ボタンを押したときの処理
function Category1(data_parameter2){
	console.log(data_parameter2);
	
	// CSRFトークンの取得
 	const token = $("meta[name='_csrf']").attr("content");
	
	 name = $('#student_information option:selected').text();
	 school_year = $('#studen_year option:selected').text();
	 school_class = $('#student_class option:selected').text();
	 
	 console.log("退学")
	 
	 $.ajax({
		type : "POST",
		url : "/rest/return_transfer",
		headers : {
			"X-CSRF-TOKEN" : token
			},
		data : {
			name : name,
			school_year : school_year,
			school_class : school_class,
			categoraiz : categoraiz
			}
		 })
		 .then(function(response_data){
		  //成功
		  console.log("success")
		 }
		 ,function(){
		  //失敗
		  console.log("error")
		 });
	
}

//　pull選択クラスの学生のデータを取得
function AjaxJson(year_student,class_student){
	
	// 表示している学生の消去
	$("#student_information").empty();
	
	//ajax処理
	$.ajax({
		type : "GET",
		url : "/rest/return_jsonsend",
		contentType: 'application/json; charset=utf-8',
		data : {
			year_student : year_student,
			class_student : class_student
		}
	})
	.then(function(response_data){
		//成功
		console.log("success : ",response_data)
		// 表示する場所の取得
		const select = document.getElementById("student_information");
		//ループカウント変数
		var count = 0;
		// 動的表示
		for(i = response_data.length; i > 0; i--) {
			count += 1;
			var js_classno = null;
			var js_studentname = null;
			js_classno = response_data[response_data.length-count]["classno"];
			js_studentname  = response_data[response_data.length-count]["studentname"];
			console.log("js_response_data",js_classno + js_studentname);
			
			// optionタグを作成する
			var option = document.createElement("option");
			// optionタグのテキストを4に設定する
			option.text = js_classno + js_studentname;
			// optionタグのvalueを4に設定する
			option.value = js_studentname;
			// selectタグの子要素にoptionタグを追加する
			select.appendChild(option);
		}
	}
	,function(e){
		//失敗
		console.log("error",e)
	});
		
}