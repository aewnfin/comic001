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
<title>我是管理员</title>
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
					<h4 class="modal-title">作者认证</h4>
				</div>
				<div class="modal-body">

					<div role="tabpanel" class="tab-pane active" id="home">
						<div>
							<label>作者id</label> <input type="text" id="authorid"
								placeholder="author id"> <span id="authorid_span"></span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="pass" class="btn btn-primary">认证通过</button>
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
function passverify(){

	//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
	$.ajaxSetup({
		cache : false
	});
	
	//取值
	var authorid = $("#authorid").val().trim();
	
	//清除输入提示
	$("#authorid_span").html("");
	
	// 输入验证
	var ok = true;
	if (authorid == "") {
		$("#authorid_span").html("作者id不能为空");
		ok = false;
	}
	
	// 发送请求
	if (ok) {
		$.ajax({
			url : path + "user/pass.do",
			type : "post",
			data : {
				"authorid" : authorid
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				// result是服务器返回结果
				if (result.status == 0) {
					//登陆验证通过
					alert(result.msg);
					window.location.href = "/comic001/login";
				} else if (result.status == 1) {
					$("#authorid_span").html(result.msg);
				} else if (result.status == 2) {
					$("#authorid_span").html(result.msg);
				} else if (result.status == 3) {
					alert(result.msg);
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
			//$("#login").click(userLogin);
			//绑定通过认证事件
			$("#pass").click(passverify);
			//显示模态框
			$('#myModal').modal('show');
			//模态框隐藏后
			$('#myModal').on('hidden.bs.modal', function(e) {
				window.location.href = "/comic001"
			})
		});
	</script>
</body>
</html>