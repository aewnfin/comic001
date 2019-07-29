<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="aewnfin">
<title>登陆/注册</title>
<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- Custom styles for this template -->
<link href="css/login.css" rel="stylesheet">
</head>
<body >

<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">登录/注册</h4>
      </div>
      <div class="modal-body">

		<div>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist" id="myTabs">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">登录</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">注册</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
			<div>
		<label>用户名</label>
		<input type="text" id="count" placeholder="user name">
		<span id="count_span"></span>
	</div>
	<div>
		<label>密&emsp;码</label>
		<input type="password" id="password" placeholder="password">
		<span id="password_span"></span>
	</div>
	<div>
		<br>
		<input type="checkbox" name="checkbox_remember" value="true">Remember me
		<p class="help-block">你是否希望保存登陆到本地？</p>
	</div>
		<button id="login" class="btn btn-primary">登陆</button>
	</div>
    <div role="tabpanel" class="tab-pane" id="profile">
		<div>
		<label>新用户名</label>
		<input type="text" id="new_count" placeholder="new user name">
		<span id="new_count_span"></span>
	</div>
	<div>
		<label>密&emsp;&emsp;码</label>
		<input type="password" id="new_password" placeholder="password">
		<span id="new_password_span"></span>
	</div>
	<div>
		<label>确认密码</label>
		<input type="password" id="new_password_re" placeholder="password again">
		<span id="new_password_re_span"></span>
	</div>
	<br>
	<div>
		<label>邮&emsp;&emsp;箱</label>
		<input type="email" id="new_eamil" placeholder="E-mail@">
		<span id="new_eamil_span"></span>
	</div>
	<button id="register" class="btn btn-primary">注册</button>
	</div>

  </div>

</div>

      </div>
      <div class="modal-footer">
        <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


	
	
	
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script>
		window.jQuery || document.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="dist/js/bootstrap.min.js"></script>
	
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
	
<script type="text/javascript" src="scripts/basevalue.js"></script>
<script type="text/javascript" src="scripts/cookie_util.js"></script>

<script type="text/javascript">
function userLogin() {
	
	//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
	$.ajaxSetup({
		cache : false
	});
	
	//取值
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	var remember = $("input:checkbox:checked").val();
	
	//清除输入提示
	$("#count_span").html("");
	$("#password_span").html("");
	
	// 输入验证
	var ok = true;
	if (name == "") {
		$("#count_span").html("用户名不能为空");
		ok = false;
	}
	if (password == "") {
		$("#password_span").html("密码不能为空");
		ok = false;
	}
	
	//确认是否记住登陆
	if(remember != "true"){
		remember = "false";
	}
	
	
	// 发送请求
	if (ok) {
		$.ajax({
			url : path + "user/login.do",
			type : "post",
			data : {
				"username" : name,
				"password" : password,
				"savecookie" : remember
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				// result是服务器返回结果
				if (result.status == 0) {
					//登陆验证通过
					window.location.href = "/comic001";
				} else if (result.status == 1) {
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {
					$("#password_span").html(result.msg);
				}
			},
			error : function(XMLHttpRequest,textStatus,errorThrown) {
				console.log("XML请求状态码："+XMLHttpRequest.status);
				console.log("XML读取状态码："+XMLHttpRequest.readyState);
				console.log("XML状态码："+textStatus);
			}
		});
	}
}

</script>
<script type="text/javascript">
function userRegister(){
	//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
	$.ajaxSetup({
		cache : false
	});
	
	//取值
	var name = $("#new_count").val().trim();
	var password = $("#new_password").val().trim();
	var repassword = $("#new_password_re").val().trim();
	var email = $("#new_eamil").val().trim();
	
	//清除输入提示
	$("#new_count_span").html("");
	$("#new_password_span").html("");
	$("#new_password_re_span").html("");
	$("#new_eamil_span").html("");
	
	// 输入验证-空
	var ex = true;
	if (name == "") {
		$("#new_count_span").html("用户名不能为空");
		ex = false;
	}
	if (password == "") {
		$("#new_password_span").html("密码不能为空");
		ex = false;
	}
	if (repassword == "") {
		$("#new_password_re_span").html("再次输入密码不能为空");
		ex = false;
	}
	if (email == "") {
		$("#new_eamil_span").html("邮箱不能为空");
		ex = false;
	}
	
	//验证输入-重复
	var ok = false;
	if(ex){
		if(password == repassword){
			ok = true;
		}else{
			$("#new_password_re_span").html("密码错误");
		}
	}
	
	
	if (ok) {
		$.ajax({
			url : path + "user/register.do",
			type : "post",
			data : {
				"username" : name,
				"password" : password,
				"email" : email,
				"type" : 1
			},
			dataType : "json",
			success : function(result) {
				// result是服务器返回结果
				console.log(result);
				
				if (result.status == 0) {
					//注册验证通过
					alert(result.msg);
					//window.location.href = "/comic001";
					$('#myTabs a:first').tab('show');
				} else if (result.status == 1) {
					//提示用户名是否重复
					$("#new_count_span").html(result.msg);
					//用户名重复->清空输入
					$("#new_count").html("");
				}
			},
			error : function(XMLHttpRequest,textStatus,errorThrown) {
				console.log("XML请求状态码："+XMLHttpRequest.status);
				console.log("XML读取状态码："+XMLHttpRequest.readyState);
				console.log("XML状态码："+textStatus);
			}
		});
	}
}
</script>
<script type="text/javascript">
	$(function() {
		//绑定登陆事件
		$("#login").click(userLogin);
		//绑定注册事件
		$("#register").click(userRegister);
		//显示模态框
		$('#myModal').modal('show');
		//模态框隐藏后
		$('#myModal').on('hidden.bs.modal', function (e) {
			window.location.href = "/comic001"
			})
	});
</script>


	
</body>
</html>