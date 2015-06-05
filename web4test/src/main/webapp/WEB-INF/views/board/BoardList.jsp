<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <jsp:include page="/CommonStyle.jsp"/>
</head>
<body>
  <jsp:include page="/header.jsp"/>
  
  <h1>게시물 목록</h1>
  <p>
  	<form action="list.do" method='get'> <!-- 검색 기능 추가 -->
  	  <input type='text' name='word' value='${param.word}'> <!-- 검색한 키워드를  확인할 수 있도록 value를 지정한다. -->
  	  <button>검색</button>
  	</form>
  </p>
  
  <table border='1'>
    <tr> 
      <!-- 누르면 해당사항으로 정렬시킨다. -->
      <!-- 누른 사항에 *를 붙인다. -->
      <th><a href='list.do?order=no&word=${param.word}'>번호
      	  <c:if test="${param.order=='no'}">*</c:if></a></th>
      <th><a href='list.do?order=title&word=${param.word}'>제목
      	  <c:if test="${param.order=='title'}">*</c:if></a></th>
      <th><a href='list.do?order=createDate&word=${param.word}'>작성일
      	  <c:if test="${param.order=='createDate'}">*</c:if></a></th>
      <th><a href='list.do?order=views&word=${param.word}'>조회수
      	  <c:if test="${param.order=='views'}">*</c:if></a></th>
    </tr> 
<c:forEach items="${list}" var="board">
    <tr> 
       <td>${board.no}</td> 
       <td><a href='detail.do?no=${board.no}'>${board.title}</a></td> 
       <td>${board.createDate}</td> 
       <td>${board.views}</td> 
    </tr>
</c:forEach>
  </table>
  
  <!-- 페이지가 1이면 [이전]에 링크를 걸지 않는다, -->
  <div>
    <a href='form.html'>새 글</a>
    <c:choose>
      <c:when test="${pageNo > 1 }">
        <a href='list.do?pageNo=${pageNo-1}&pageSize=${pageSize}&word=${param.word}&order=${param.order}'>[이전]</a>
      </c:when> 
      <c:otherwise>[이전]</c:otherwise>
   </c:choose>
   
   ${pageNo} 
   
   <!-- 마지막 페이지에서는 [다음]에 링크를 걸지 않는다 -->
   <c:choose>
     <c:when test="${pageNo < maxPage }">
       <a href='list.do?pageNo=${pageNo+1}&pageSize=${pageSize}&word=${param.word}&order=${param.order}'>[다음]</a>
     </c:when> 
     <c:otherwise>[다음]</c:otherwise>
   </c:choose>
  </div>
  <jsp:include page="/Footer.jsp"/>
</body>
</html>













