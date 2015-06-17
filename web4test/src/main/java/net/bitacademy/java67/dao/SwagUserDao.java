package net.bitacademy.java67.dao;

import java.util.Map;

import net.bitacademy.java67.domain.SwagUserVo;

/* 실습 내용: DAO를 인터페이스로 정의하기 */

public interface SwagUserDao {
  
  int insert(Map<String,Object> paramMap);
  
  SwagUserVo selectOne(String email);

}











