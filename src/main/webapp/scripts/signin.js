function userLogin() {
	$.ajaxSetup({
		cache : false
	});
	//ie获取Ajax缓存，代替请求，访问旧数据问题，解决。
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	$("#count_span").html("");
	$("#password_span").html("");
	// 验证输入
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
			url : path + "user/login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				// result是服务器返回结果
				if (result.status == 0) {
					// 将用户信息保存到cookie
					var userId = result.data.name;
					addCookie("userId", userId, 2);
					window.location.href = "edit.html";
				} else if (result.status == 1) {
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {
					$("#password_span").html(result.msg);
				}
			},
			error : function() {
				alert("登陆失败！");
			}
		});
	}
}

