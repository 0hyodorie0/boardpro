<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//서블릿에서 공유 데이터 꺼내기
	int rn = (int)request.getAttribute("renum");
	if(rn > 0){
%>
		{
			"sw" : "저장성공"	
		}		
<%	}else{%>
		{
			"sw" : "저장실패"
		}		
<%		
	}
%>