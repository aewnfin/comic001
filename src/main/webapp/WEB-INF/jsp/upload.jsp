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
<title>上传</title>
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
</head>
<body>

	<!-- Nav tabs -->
	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#newcomic"
			aria-controls="newcomic" role="tab" data-toggle="tab">新增漫画</a></li>
		<li role="presentation"><a href="#uploadcomic"
			aria-controls="uploadcomic" role="tab" data-toggle="tab">更新漫画</a></li>
		<li role="presentation" class="navbar-right"><a href="/comic001">退出</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="newcomic">
			<!-- <form method="post" action="comic/newcomic.do"
				enctype="multipart/form-data">
				<input type="file" name="file" value="file"> <input
					type="submit" value="确定">
			</form> -->
			<div>
				<label>笔名：</label> <input type="text" id="pename" placeholder="">
				<span id="pename_span"></span>
			</div>
			<div>
				<label>新漫画名称：</label> <input type="text" id="title" placeholder="">
				<span id="title_span"></span>
			</div>
			<div>
				<label>封&emsp;面</label> <input type="file" id="hoverpage"
					placeholder="210×277"> <span id="hoverpage_span"></span>
			</div>
			<div>
				<br>
				<button id="new" class="btn btn-primary">提交</button>
			</div>
		</div>

		<div role="tabpanel" class="tab-pane" id="uploadcomic">
			<div>
				<label>漫画id</label> <input type="text" id="comicid" placeholder="">
				<span id="comicid_span"></span>
			</div>
			<div>
				<label>章节part</label> <input type="text" id="part" placeholder="">
				<span id="part_span"></span>
			</div>
			<div>
				<label>标题title</label> <input type="text" id="chaptertitle" placeholder="">
				<span id="chaptertitle_span"></span>
			</div>
			<div>
				<label>设置价格</label> <input type="text" id="cost" placeholder="">
				<span id="cost_span"></span>
			</div>
			<div>
				<label>漫画页选择</label> <input type="file" id="pages" multiple="multiple"
					placeholder=""> <span id="pages_span"></span>
			</div>
			<div>
				<br>
				<button id="newchapter" class="btn btn-primary">提交</button>
			</div>
			
		</div>
	</div>





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
		function newcomic() {
			//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
			$.ajaxSetup({
				cache : false
			});

			//取值
			var form = new FormData();
			var title = $("#title").val().trim();
			var pename = $("#pename").val().trim();
			form.append("title", title);
			form.append("pename", pename);
			form.append("hoverpage", $("#hoverpage")[0].files[0]);

			//清除输入提示
			$("#title_span").html("");
			$("#hoverpage_span").html("");
			$("#pename_span").html("");

			// 输入验证
			var ok = true;
			if (pename == "") {
				$("#pename_span").html("笔名不能为空");
				ok = false;
			}
			if (title == "") {
				$("#title_span").html("标题不能为空");
				ok = false;
			}

			// 发送请求
			if (ok) {
				$.ajax({
					url : path + "comic/newcomic.do",
					type : "post",
					data : form,
					async : false,//同步锁
					enctype : "multipart/form-data",//必须
					contentType : false,//取消jqurey间隔操作，防止与"multipart/form-data"冲突
					processData : false,//不序列化返回结果
					dataType : "json",
					success : function(result) {
						console.log(result);
						// result是服务器返回结果
						if (result.status == 0) {
							//申请成功
							alert(result.msg);
						} else if (result.status == 1) {
							$("#title_span").html(result.msg);
						} else if (result.status == 2) {
							$("#hoverpage_span").html(result.msg);
						} else if (result.status == 3) {
							alert(result.msg);
							window.location.href = "/login";
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("XML请求状态码：" + XMLHttpRequest.status);
						console.log("XML读取状态码：" + XMLHttpRequest.readyState);
						console.log("XML状态码：" + textStatus);
					}
				});
			}
		}
	</script>
	<script type="text/javascript">
		function newchapter() {
			//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
			$.ajaxSetup({
				cache : false
			});

			//取值
			var form2 = new FormData();
			var comicid = $("#comicid").val().trim();
			var part = $("#part").val().trim();
			var title = $("#chaptertitle").val().trim();
			var cost = $("#cost").val().trim();
			var files =$("#pages")[0].files;
			
			form2.append("comicid", comicid);
			form2.append("part", part);
			form2.append("title", title);
			form2.append("cost", cost);
			for(var i =0;i<files.length;i++){
				form2.append("pages", files[i]);
			}
			
			
			
			//清除输入提示
			$("#comicid_span").html("");
			$("#part_span").html("");
			$("#chaptertitle_span").html("");
			$("#cost_span").html("");
			$("#pages_span").html("");

			// 输入验证
			var ok = true;
			if (comicid == "") {
				$("#comicid_span").html("漫画序号不能为空");
				ok = false;
			}
			if (part == "") {
				$("#part_span").html("漫画章节序号不能为空");
				ok = false;
			}
			if (title == "") {
				$("#chaptertitle_span").html("章节标题不能为空");
				ok = false;
			}
			if (cost == "") {
				$("#cost_span").html("需要注明价格");
				ok = false;
			}
			

			// 发送请求
			if (ok) {
				$.ajax({
					url : path + "comic/uploadcomic.do",
					type : "post",
					data : form2,
					async : false,//同步锁
					enctype : "multipart/form-data",//必须
					contentType : false,//取消jqurey间隔操作，防止与"multipart/form-data"冲突
					processData : false,//不序列化返回结果
					dataType : "json",
					success : function(result) {
						console.log(result);
						// result是服务器返回结果
						if (result.status == 0) {
							//请先登录
							alert(result.msg);
							window.location.href = "/comic001/login";
						} else if (result.status == 2) {
							//空文件
							$("#pages_span").html(result.msg);
						} else if (result.status == 3) {
							//无权更新
							$("#comicid_span").html(result.msg);
						} else if (result.status == 4) {
							//章节已经存在
							$("#part_span").html(result.msg);
						}else if (result.status == 5) {
							//失败
							alert(result.msg);
						}else if (result.status == 6) {
							//成功
							alert(result.msg);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("XML请求状态码：" + XMLHttpRequest.status);
						console.log("XML读取状态码：" + XMLHttpRequest.readyState);
						console.log("XML状态码：" + textStatus);
					}
				});
			}
		}
	</script>
	<script type="text/javascript">
		$(function() {
			//绑定新增漫画事件
			$("#new").click(newcomic);
			//绑定新增章节事件
			$("#newchapter").click(newchapter);

		});
	</script>


</body>
</html>