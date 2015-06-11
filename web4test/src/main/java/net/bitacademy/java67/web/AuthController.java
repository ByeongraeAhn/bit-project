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

/* 실습 목표: 이메일 저장을 쿠키로 처리하기 */

@Controller
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  UserDao userDao;
  
  @RequestMapping(value="/login",method=RequestMethod.GET)
  public void loginForm() {
  }
  
  @RequestMapping(value="/login",method=RequestMethod.POST)
  public String login(String email, String password, String saveEmail,
      HttpServletResponse response, HttpSession session) {
    
    if (saveEmail != null) { // 체크 상자를 체크 했으면,
      // 클라이언트로 사용자 이메일을 쿠키에 담아 보낸다.
      Cookie emailCookie = new Cookie("email", email);
      emailCookie.setMaxAge(60 * 60 * 24 * 30); //30일간 쿠키 유지할 것!
      response.addCookie(emailCookie);
    } else {
      // 기존의 이메일을 지운다.
      Cookie emailCookie = new Cookie("email", null);
      emailCookie.setMaxAge(0); // 쿠키를 지울 것!
      response.addCookie(emailCookie);
    }
    
    // 사용자 검증
    HashMap<String,String> sqlParams = new HashMap<String,String>();
    sqlParams.put("email", email);
    sqlParams.put("password", password);
    
    UserVo user = userDao.selectOne(sqlParams);
    
    if (user != null) { // 이메일과 암호가 일치하는 사용자를 찾았다면,
      session.setAttribute("user", user); // 로그인 성공 => 사용자 정보를  세션에 보관한다.
      return "redirect:../board/list.do";
      
    } else { // 찾지 못했다면, 다시 로그인 폼을 요청하라고 응답한다.
      session.invalidate(); // 기존 세션을 무효화시킨다. => 세션을 새로 만든다.
      return "redirect:login.do";
    }
  }
  
  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:login.do";
  }
}












