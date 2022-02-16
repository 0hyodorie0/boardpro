<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//서블릿에서 공유한 데이터 꺼내기
int res = (Integer)request.getAttribute("resnum");
if(res>0){
%>
	 {
	 	"sw" : "수정성공"
	 }
<% }else{%>
	{
		"sw" : "수정실패"
	}
<%
}
%>
