function generateState() {
	var oDate = new Date();
	return oDate.getTime();
}

function saveState(state) {
	$.removeCookie("state_token");
	$.cookie("state_token", state);
	//console.log("show cookies to JSON : " + JSON.stringify($.cookie));
}

var naver = NaverAuthorize({
	client_id : "m749lyf8DGsMVqtvRIFF",
	client_secret : "x7XNbitBMo",
	redirect_uri : "http://192.168.10.25:9999/web4test/login/brain.html"
});

window.onload = function() {
	checkLoginState();
}

var tokenInfo = {
		access_token : "",
		refresh_token : ""
};

//console.log(tokenInfo);

function checkLoginState() {
	var state = $.cookie("state_token");

/*	console
	.log("show $.cookie 'state_token' ", $
			.cookie("state_token"));*/

	if (naver.checkAuthorizeState(state) === "connected") {

/*		console
		.log("naver.checkAuthorizeState(state) === 'connected' = true");*/

		naver.getAccessToken(function(data) {
/*			console.log("getAccessToken's data info " + data);*/
			var response = data._response.responseJSON;

			if (response.error === "fail") {
/*				console.log("response.error is fail")*/
				return;
			}

			naver.api("/me", response.access_token, function(a) {
				/*console.log(JSON.stringify(a._response.responseJSON))*/
			})
			tokenInfo.access_token = response.access_token;
			tokenInfo.refresh_token = response.refresh_token;

			naver.updateAccessToken(tokenInfo.refresh_token, function(
					a, b, c) {
				/*console.log("params", a);*/
				/*console.log("params",
						a._response.responseJSON.access_token);*/

				naver.api("/me", a._response.responseJSON.access_token,
						function(a) {
					/*console.log("my info Object : ", a)*/
/*					console.log(a._response.responseJSON.response["email"]);
					console.log(a._response.responseJSON.response["nickname"]);*/
					naverajax(a);
				})
			})

			/*console.log("success to get access token", response);*/
		}); 
	} else {
		console
		.log("naver.checkAuthorizeState(state) === 'connected' : false")
		return;
	}
}

$("#NaverIdLoginBTN").click(function() {
	var state = generateState();
	saveState(state);
	naver.login(state);
});

function naverajax(a) {
		$.ajax('http://localhost:9999/web4test/board/ajax/test.do', {
			method: 'POST',
			data: {
				name: a._response.responseJSON.response["nickname"],
				email: a._response.responseJSON.response["email"]
			},
			success: function(result) {
				console.log("ajax성공");
				location.replace("http://localhost:9999/web4test/main/main.html");
			},
			error: function(xhr, textStatus, errorThrown) {
				alert('작업을 완료할 수 없습니다.\n' + 
					  '잠시 후 다시 시도하세요.\n' +
					  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
			}
		});
		console.log(a._response.responseJSON.response["nickname"]);
}