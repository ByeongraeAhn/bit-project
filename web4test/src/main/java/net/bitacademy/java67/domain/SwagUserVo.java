package net.bitacademy.java67.domain;

import java.io.Serializable;
import java.sql.Date;

/* - USERS 테이블의 데이터와 대응 
 * - WAS 사이에 데이터 이전이 가능하도록 직렬화를 허용한다.
 *   => 보통 값 객체는 Serializable 인터페이스를 구현한다. 
 */
public class SwagUserVo implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String      email;
  protected String      username;
  protected Date        birthday;
  protected String      theme;
  protected String      language;
  protected int         countmod;
  
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  public String getTheme() {
    return theme;
  }
  public void setTheme(String theme) {
    this.theme = theme;
  }
  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }
  public int getCountmod() {
    return countmod;
  }
  public void setCountmod(int countmod) {
    this.countmod = countmod;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  
}
