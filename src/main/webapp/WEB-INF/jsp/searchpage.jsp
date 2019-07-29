<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="漫画">
<meta name="author" content="aewnfin">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="/comic001/dist/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/comic001/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/comic001/css/comic001.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/comic001/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="container"><h1 class="col-sm-4 col-xs-4">Comic001<small><span class="label label-default cm-label label-success">搜索</span></small></h1></div>
<nav class="navbar navbar-inverse cm-navbar">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" type="button" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
             </button>
             <a class="navbar-brand" href="#" >漫画001</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li>
					<a href="http://localhost:8080/comic001/" >首页</a>
				</li>
				<li class="active">
					<a href="http://localhost:8080/comic001/search/forcomic.do" >搜索</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
	 			<li><a href="http://localhost:8080/comic001/login">${hello}</a></li>
	 		</ul>
		</div>
	</div>
	 
	</nav>
<div class="container">
		<form class="form-inline col-md-4 col-md-offset-4 col-xs-6 col-xs-offset-4" action="forcomic.do" method="post">
		<div class="form-group">
			<input class="form-control cm-search " type="text" placeholder="Search..." name="keyword" />
		</div>
		<input class="btn btn-success" type="submit" value="搜索">
		</form>
	</div>
<hr>
<div class="container theme-showcase" role="main">
<div class="row">
	<c:forEach items="${comics}" var="Comic">
	<div class="col-xs-4 col-md-2">
		<div>
			<a href="/comic001/comic/page/${Comic.id}" class="thumbnail">
				<img src="/comic001/comic/${Comic.id}/${Comic.img_uri}" alt="《${Comic.title}》">
				<span class="text-center cm-title">${Comic.title}&emsp;${Comic.update}</span>
			</a>
		</div>
	</div>
	</c:forEach>
</div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script>
		window.jQuery || document.write('<script src="/comic001/assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/comic001/dist/js/bootstrap.min.js"></script>
	
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/comic001/assets/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>