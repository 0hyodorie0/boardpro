<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   //서블릿에서 저장된  list를 꺼내게 
   
   List<BoardVO>  list = 
              (List<BoardVO>)request.getAttribute("sdfsdfqwedd");
 %>
 [
   <%
      for(int i=0; i<list.size(); i++){
    	  BoardVO  vo = list.get(i);
    	  if(i > 0) out.print(",");
 %>
  
    	{
    	  "num"      : "<%= vo.getNum() %>",
    	  "writer"   : "<%= vo.getWriter() %>",
    	  "subject"  : "<%= vo.getSubject() %>",
    	  "mail"     : "<%= vo.getMail() %>",
    	  "hit"      : "<%= vo.getHit() %>",
    	  "wdate"    : "<%= vo.getWdate() %>",
    	  "content"  : "<%= vo.getContent().replaceAll("\r"	, "").replaceAll("\n","<br>") %>"
    	}  
    	  
 <%   	  
    }
 %>
 
 ]
 
 
 
 