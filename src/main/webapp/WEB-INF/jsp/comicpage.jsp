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
<meta name="description" content="">
<meta name="author" content="aewnfin">

<title>comic001</title>

<!-- Bootstrap core CSS -->
<link href="/comic001/dist/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/comic001/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<!-- <link href="/comic001/css/login.css" rel="stylesheet"> -->

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

	<div class="row">
		<c:forEach items="${chapters}" var="Chapter">
			<div class="col-xs-3 col-md-2">
				<span onclick="paypries(${Chapter.id},${Chapter.part},${Chapter.cost})"
					class="label label-default">${Chapter.cost}点&emsp;${Chapter.title}</span>
				<span class="label label-primary"
					onclick="share(${Chapter.id},${Chapter.part})">分享</span> <input
					class="share_input" type="text" id="share_part${Chapter.part}" />
			</div>
		</c:forEach>
	</div>

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

					<div class="radio">
						<label class="text-success"> <input type="radio"
							name="optionsRadios" id="optionsRadios1" value="option1" checked>
							使用有效余额(余额：<span id="price"></span>)
						</label>
					</div>
					<div class="radio">
						<label class="text-warning"> <input type="radio"
							name="optionsRadios" id="optionsRadios2" value="option2">
							使用免费额度(余额：<span id="freeprice"></span>)
						</label>
					</div>
					<span id="comicid_pay" style="display: none;"></span>&emsp;<span id="chapterid_pay" style="display: none;"></span>&emsp;cost:<span id="needpay"></span>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="pay_button">支付</button>
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
		window.jQuery || document.write('<script src="/comic001/assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/comic001/dist/js/bootstrap.min.js"></script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/comic001/assets/js/ie10-viewport-bug-workaround.js"></script>

	<script type="text/javascript" src="/comic001/scripts/basevalue.js"></script>
	<script src="/comic001/assets/js/vendor/jquery.cookie.js"></script>
	<script src="/comic001/scripts/post.js"></script>
	
	<script type="text/javascript">
		function share(comicid,part){
			var id = "share_part"+part;
			$("#"+id).toggle();
			
			//获取输入框当前属性
			var style=$("#"+id).attr("style");
			//console.log(style);
			
			//不可见变为可见
			if(style=="display: inline-block;"){
				//显示分享的信息ajax
				//$("#"+id).val("123");
			}else{//可见变为不可见
				//alert($("#"+id).val().trim());
				var shareUser=$("#"+id).val().trim();
				//提交修改的数据ajax
				$.ajax({url : path + "for/share.do",
						type : "post",
						data : {
						"comicid" : comicid,
						"part" : part,
						"shareUser":shareUser},
						
					dataType : "json",
					success : function(result) {
							// result是服务器返回结果
							console.log(result);
							if (result.status == 0) {
								//没有权限
								alert(result.msg);
								$("#"+id).val("");
							} else if (result.status == 1) {
								//完成分享
								alert(result.msg);
							} else if (result.status == 3) {
								//请先登陆
								alert(result.msg);
								window.location.href="http://localhost:8080/comic001/login";
							}
					},
					error : function(XMLHttpRequest,textStatus,errorThrown) {
						console.log("XML请求状态码："+XMLHttpRequest.status);
						console.log("XML读取状态码："+XMLHttpRequest.readyState);
						console.log("XML状态码："+textStatus);
					}
				});
				//ajax请求结束
			}
		}
	</script>
	<script type="text/javascript">
		function paypries(comicid,part,cost){
			//提交修改的数据ajax
			$.ajax({url : path + "for/pay",
					type : "post",
					data : {
					"comicid" : comicid,
					"part" : part},
					
				dataType : "json",
				success : function(result) {
						// result是服务器返回结果
						console.log(result);
						if (result.status == 0) {
							//没有支付记录，显示模态框
							$('#myModal').modal('show');
							$("#price").html(result.data.price);
							$("#freeprice").html(result.data.freeprice);
							$("#comicid_pay").html(comicid);
							$("#chapterid_pay").html(part);
							$("#needpay").html(cost);
						} else if (result.status == 1) {
							//有支付记录，跳转至阅读
							window.location.href="/comic001/for/read/"+comicid+"/"+part;
						} else if (result.status == 3) {
							//没有用户信息，请先登陆
							alert(result.msg);
							window.location.href="http://localhost:8080/comic001/login";
						}
				},
				error : function(XMLHttpRequest,textStatus,errorThrown) {
					console.log("XML请求状态码："+XMLHttpRequest.status);
					console.log("XML读取状态码："+XMLHttpRequest.readyState);
					console.log("XML状态码："+textStatus);
				}
			});
			//ajax请求结束
		}
	</script>
	<script type="text/javascript">
		function pay(){
			//获悉选择的支付方式
			var chose=$("input[name='optionsRadios']:checked").val();
			console.log(chose);
			//获悉需要付费的段落
			var comic=$("#comicid_pay").html();
			var chapter=$("#chapterid_pay").html();
			console.log(comic);
			console.log(chapter);
			
			//确认支付方式
			var url;
			if(chose=="option1"){
				url="/comic001/for/pay.do";
			}else if(chose=="option2"){
				url="/comic001/for/freepay.do";
			}
			
			var params={comicid:comic,chapterid:chapter};
			//发送请求
			post(url,params);
		}
	</script>
	<script type="text/javascript">
		$(function(){
				$("input.share_input").toggle();
				//添加模态框支付事件
				$("#pay_button").click(pay);
				//显示模态框
				//$('#myModal').modal('show');
				//显示数据
				//$('#myModal').on('show.bs.modal', function (e) {
					//$("price").html($.cookie('price'));
					//$("freeprice").html($.cookie('freeprice'));
				//})
				
		});
	</script>
	
</body>
</html>