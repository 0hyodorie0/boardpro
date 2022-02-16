<%@page import="com.google.gson.Gson"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//서블릿에서 저장된  list를 꺼내게 

List<BoardVO>  list = 
           (List<BoardVO>)request.getAttribute("sdfsdfqwedd");

  //json데이터 자동생성 - vo의 속성(멤버변수)와 일치 하는 속성들만을 대상으로 한다
  Gson   gson = new Gson();
  String  result  = gson.toJson(list);
  
  out.print(result);
%>
