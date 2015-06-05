package net.bitacademy.java67.web;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java67.dao.UserDao;
import net.bitacademy.java67.domain.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  UserDao userDao;

  @RequestMapping(value="/login",method=RequestMethod.GET)
  public void loginForm() {
    // view URL 주소를 리턴하지 않으면,
    // 요청 URL을 리턴한다.
    // 요청 URL? 
    // 만약 클라이이언트가 요청한 전체 URL이 다음과 같다면,
    // http://localhost:9999/web4t/auth/login.do
    // => 웹 애플리케이션 주소까지 제외한다. => auth/login.do
    // => 접미사를 제외한다. => auth/login
    // => 따라서 요청 URL은 "auth/login"이 된다.
    // 
    // InternalResouceViewResolver가 요청 URL을 받아서 실제 JSP URL을 만들면,
    // => /WEB-INF/views/auth/login.jsp
    
    // 결론, 같은 경로에 요청 URL의 이름과 같은 JSP 파일을 만들면,
    // 굳이 JSP URL을 리턴하지 않아도 된다.
    
  }
  
  @RequestMapping(value="/login",method=RequestMethod.POST)
  public String login(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) {
    if (saveEmail != null) { //체크박스에 체크가 되어 있으면 on이 넘어온다. 즉 null이 아니다.
      Cookie emailCookie = new Cookie("email", email);
      emailCookie.setMaxAge(60 * 60 * 24 * 30); //30일동안 쿠키를 유지할 것!
      response.addCookie(emailCookie);
    } else { //체크박스에 체크가 되지 않아을떄 null이 넘어온다.
      //기존의 이메일을 지운다.
      Cookie emailCookie = new Cookie("email", "");
      emailCookie.setMaxAge(0); // 쿠키를 지울 것!
      response.addCookie(emailCookie);
    }
    
    // 사용자 검증
    HashMap<String, String> sqlParams = new HashMap<String, String>();
    sqlParams.put("email",email);
    sqlParams.put("password",password);
    
    UserVo user = userDao.selectOne(sqlParams);
    
        
    if (user != null) { //이메일과 암호가 일치하는 사용자를 찾았다면,
      session.setAttribute("user", user); // 로그인 성공 => 사용자 정보를 세션에 보관한다.
      return "redirect:../board/list.do";
      
    } else { // 찾지 못했다면, 다시 로그인 폼을 요청하라고 응답한다.
      session.invalidate(); // 기존 세션을 무효화 시킨다. => 세션을 새로 만든다
      return "redirect:login.do";
    }
  }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:login.do";
  }
}












