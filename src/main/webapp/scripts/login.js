function userLogin() {
	
	//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
	$.ajaxSetup({
		cache : false
	});
	
	//取值
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	
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
	
	// 发送请求
	if (ok) {
		$.ajax({
			url : path + "login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
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

