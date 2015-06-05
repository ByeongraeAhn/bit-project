package net.bitacademy.java67.web.ex4;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/* 실습 목표: 파일 업로드 처리
 */
@Controller
@RequestMapping("/ex4")
public class FileUploadController {
  @Autowired
  ServletContext ctx;
  
  @RequestMapping("/fileUpload") 
  public String fileUpload (
      String name,
      int age,
      MultipartFile file,
      Model model
      ) throws Exception {
    
    // 업로드 파일의 이름을 생성한다. (오리지널 파일이름이면 덮어 쓸수가 있기 떄문이다.)
    String originalFileName = file.getOriginalFilename();
    int lastIndexForDot = originalFileName.lastIndexOf("."); //마지막 점(.)의 위치
    String filename = System.currentTimeMillis() + "-" 
                      + count() 
                      + originalFileName.substring(lastIndexForDot); //마지막 점부터 끝까지 (즉, 확장자)
    
    model.addAttribute("name",name);
    model.addAttribute("age",age);
    model.addAttribute("file",filename);
    
    file.transferTo(new File(ctx.getRealPath("/upload") + "/" + filename));
    
    return "ex4/FileUpload";
  }
  
  
  int count = 0;
  
  // 오직 한 번에 한 스레드 만이 카운트 값을 얻을 수 있다. 중복 불가!
  synchronized private int count() {
    if (count == 100) {
      count = 1;
    }
    return ++count;
  }

}










