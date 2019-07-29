<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="aewnfin">
<title>认证作者</title>
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
<body>

	<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">

					<div>
						<label>身份证</label> <input type="text" id="new_count"
							placeholder="ID card"> <span id="new_count_span"></span>
					</div>
					<div>
						<label>&emsp;&emsp;姓</label> <input type="text" id="new_password"
							placeholder="firstname"> <span id="new_password_span"></span>
					</div>
					<div>
						<label>&emsp;&emsp;名</label> <input type="text" id="new_password_re"
							placeholder="secondname"> <span id="new_password_re_span"></span>
					</div>
					<br>

					<button id="register" class="btn btn-primary">提交申请</button>

				</div>
				<div class="modal-footer">
				<samp style="color:red;">请联系管理员确认！&emsp;QQ：897729797</samp>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->









	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="dist/js/bootstrap.min.js"></script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>

	<script type="text/javascript" src="scripts/basevalue.js"></script>
	<script type="text/javascript" src="scripts/cookie_util.js"></script>

<script type="text/javascript">
function userRegister(){
	//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
	$.ajaxSetup({
		cache : false
	});
	
	//取值
	var idcard = $("#new_count").val().trim();
	var firstname = $("#new_password").val().trim();
	var secondname = $("#new_password_re").val().trim();
	
	//清除输入提示
	$("#new_count_span").html("");
	$("#new_password_span").html("");
	$("#new_password_re_span").html("");
	
	// 输入验证-空
	var ex = true;
	if (idcard == "") {
		$("#new_count_span").html("身份证不能为空");
		ex = false;
	}
	if (firstname == "") {
		$("#new_password_span").html("姓 不能为空");
		ex = false;
	}
	if (secondname == "") {
		$("#new_password_re_span").html("名 不能为空");
		ex = false;
	}
	
	
	//验证输入-重复
	var ok = false;
	if(ex){
		if(idcard.length == 18){
			ok = true;
		}else{
			$("#new_password_re_span").html("身份证格式错误");
		}
	}
	
	
	if (ok) {
		$.ajax({
			url : path + "user/beAuthor.do",
			type : "post",
			data : {
				"IDcard" : idcard,
				"firstName" : firstname,
				"secondName" : secondname,
				"type" : 2
			},
			dataType : "json",
			success : function(result) {
				// result是服务器返回结果
				console.log(result);
				
				if (result.status == 0) {
					//请先登录
					alert(result.msg);
					window.location.href = "/comic001/login";
				} else if (result.status == 1) {
					alert(result.msg);
					window.location.href = "/comic001";
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