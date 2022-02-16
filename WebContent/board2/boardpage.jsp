<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="../js/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  
  
  <script src="../js/board.js"></script>
  
<style>
.box{
  padding : 20px;
}
.card{
 margin : 2px;
}
.card-header{
    background : #BEEFFF;
}
.card-header:hover{
    background : blue;
}
.pagination{
	float:left;
}
#btngroup1{
	margin-left:50%;
}
 input[name=reply]{
    height : 55px;
    vertical-align:  top;
 }
</style>  
  
<script>
$(function(){
	listServer(1);
	
	
	//이벤트
	//수정, 삭제, 등록 버튼에 대한 이벤트
	$('.box').on('click', '.action', function(){ //delegate
		actionName = $(this).attr('name');
		actionIdx = $(this).attr('idx');
		
		if(actionName=="modify"){
			alert(actionIdx+"번 글을 수정합니다.");
		}else if(actionName=="delete"){
			alert(actionIdx+"번 글을 삭제합니다.");
		}else if(actionName=="reply"){
			alert(actionIdx+"번 글에 댓글을 답니다.");			
		}
		
	});
	
	//page번호 - pnum 이벤트
	$('#btngroup1').on('click', '.pnum', function(){
		numtext = $(this).text().trim();
		currentPage = numtext;
		listServer(currentPage); 
	});
	//다음
	$('#btngroup1').on('click', '.next', function(){
		nextnum = parseInt($('.pager a').last().text().trim())+1;
		currentPage = nextnum;
		listServer(currentPage);
	});
	//이전
	$('#btngroup1').on('click', '.previous', function(){
		prevnum = parseInt($('.pager a').first().text().trim())-1;
		currentPage = prevnum;
		listServer(currentPage);
	});
	
})
</script>
</head>
<body>
<div class="box"></div>
<div id="btngroup1"></div>
</body>
</html>





 
    