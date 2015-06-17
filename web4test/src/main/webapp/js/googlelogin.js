(function() {
  var po = document.createElement('script');
  po.type = 'text/javascript'; po.async = true;
  po.src = 'https://apis.google.com/js/client:plusone.js?onload=render';
  var s = document.getElementsByTagName('script')[0];
  s.parentNode.insertBefore(po, s);
})();

function render() {
  gapi.signin.render('customBtn', {
    'class': 'g-signin',
    'callback': 'signinCallback',
    'approvalprompt': 'force',
    'clientid': '822199243108-pj0f8lp7v5rm3l04j3h0saessl0258uf.apps.googleusercontent.com',
    'cookiepolicy': 'single_host_origin',
    'requestvisibleactions': 'http://schemas.google.com/AddActivity',
    'height': "short",
    'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email'
  });
}

(function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();
   
   function signinCallback(authResult) {
 	  if (authResult['access_token']) {
 	    // 승인 성공
 	    // 사용자가 승인되었으므로 로그인 버튼 숨김. 예:
 	    gapi.auth.setToken(authResult); // 반환된 토큰을 저장합니다.
 	    getEmail();                     // 이메일 주소 가져오기 요청을 실행합니다.
 	  } else if (authResult['error']) {
 	    // 오류가 발생했습니다.
 	    // 가능한 오류 코드:
 	    //   "access_denied" - 사용자가 앱에 대한 액세스 거부
 	    //   "immediate_failed" - 사용자가 자동으로 로그인할 수 없음
 	    // console.log('오류 발생: ' + authResult['error']);
 	  }
 	}
   
   function getEmail(){
 	    // userinfo 메소드를 사용할 수 있도록 oauth2 라이브러리를 로드합니다.
 	    gapi.client.load('oauth2', 'v2', function() {
 	          var request = gapi.client.oauth2.userinfo.get();
 	          request.execute(getEmailCallback);
 	        });
 	  }

  function getEmailCallback(obj){
    var el = document.getElementById('email');
    var email = '';

    if (obj['email']) {
      email = 'Email: ' + obj['email'];
    }
    googleajax(obj);

    console.log(obj);   // 전체 개체를 검사하려면 주석을 해제합니다.

    el.innerHTML = email;
    toggleElement('email');
  }
 	
 	function googleajax(obj) {
 		$.ajax('http://localhost:9999/web4test/board/ajax/test.do', {
 			method: 'POST',
 			data: {
 				name: obj['name'],
 				email: obj['email']
 			},
 			success: function(result) {
 				console.log("ajax성공");
 				/*var rows = result.data;
 				console.log(email);
 				console.log(result.username);
 				console.log(rows[0]);
 				console.log(rows[0].email);*/
 				location.replace("http://localhost:9999/web4test/main/main.html");
 			},
 			error: function(xhr, textStatus, errorThrown) {
 				alert('작업을 완료할 수 없습니다.\n' + 
 					  '잠시 후 다시 시도하세요.\n' +
 					  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
 			}
 		});
 	}
 	
 	