package net.bitacademy.java67.web.ajax;

import java.util.HashMap;

import net.bitacademy.java67.dao.SwagUserDao;
import net.bitacademy.java67.domain.SwagUserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/* 실습 목표: JSON 데이터 출력 */

@Controller("/board/ajax")
@RequestMapping("/board/ajax")
public class BoardController {
  
  @Autowired
  SwagUserDao swagUserDao;
  
  @RequestMapping("/test")
  public ResponseEntity<String> list(String email, String name) throws Exception {
    
    HashMap<String,Object> sqlParams = new HashMap<String,Object>();
    sqlParams.put("email", email);
    sqlParams.put("username", name);
    
    SwagUserVo swagUserVo = swagUserDao.selectOne(email);
    System.out.println(swagUserVo.getCount());
    
    if(swagUserVo.getCount() == 1) {
      System.out.println("이미 등록되어있음");
    } else {
      swagUserDao.insert(sqlParams);
    }
    
    HashMap<String,Object> responseData = new HashMap<String,Object>();
    responseData.put("status", "success");
    responseData.put("email", email);
    responseData.put("username", name);
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/plain;charset=UTF-8");
    headers.add("Access-Control-Allow-Origin", "*");
    
    return new ResponseEntity<String>(
        new Gson().toJson(responseData),
        headers,
        HttpStatus.OK);
  }
  
  @RequestMapping("/test2")
  public ResponseEntity<String> list2(String email, String name) throws Exception {
    
    HashMap<String,Object> sqlParams = new HashMap<String,Object>();
    sqlParams.put("email", email);
    sqlParams.put("username", name);
    
    SwagUserVo swagUserVo = swagUserDao.selectOne(email);
    System.out.println(swagUserVo.getCount());
    
    if(swagUserVo.getCount() == 1) {
      System.out.println("이미 등록되어있음");
    } else {
      swagUserDao.insert(sqlParams);
    }
    
    HashMap<String,Object> responseData = new HashMap<String,Object>();
    responseData.put("status", "success");
    responseData.put("email", email);
    responseData.put("username", name);
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/plain;charset=UTF-8");
    headers.add("Access-Control-Allow-Origin", "*");
    
    return new ResponseEntity<String>(
        new Gson().toJson(responseData),
        headers,
        HttpStatus.OK);
  }
}










