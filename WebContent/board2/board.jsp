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

</style>  
  
<script>
$(function(){
	$.ajax({
		 url : '/boardpro/List2.do',
		 method : 'get',
		 success : function(res){
			
		   code = '<div id="accordion">'	 ; 
			 
		   $.each(res, function(i,v){
				code += '<div class="card">'
				code += '  <div class="card-header">'
				code += '    <a class="card-link" data-toggle="collapse" href="#collapse' + v.num + '"> '
				code +=  v.subject ;
				code += '    </a>'
				code += '  </div>'
				code += '  <div id="collapse' + v.num + '" class="collapse" data-parent="#accordion"> '
				code += '   <div class="card-body"> '
				code += '    <p class="p1">'
				code += '	 작성자 : <span class="wr">' + v.writer + '</span> &nbsp;&nbsp;&nbsp;&nbsp; '
				code += '	 이메일 <span class="em">' + v.mail + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
				code += '	조회수 <span class="hit">' + v.hit  + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
				code += '	 날짜  <span class="da">' + v.wdate + '</span> '
				code += '   </p> '
				code += '    <p class="p2"> '
				code += '     <input type="button" class="action"  name="modify" idx="' + v.num + '" value="수정">'
				code += '     <input type="button" class="action"  name="delete" idx="' + v.num + '" value="삭제">'
				code += '    </p>'
				code += '  <hr>'
				code += '   <p class="p3">  '
				code += '	 내용보기 내용출력<br> '
				code += v.content 
			   code +=  '   </p>'
				code += '   <p class="p4">'
				code += '    <textarea  cols="100"></textarea>'
				code += '    <input type="button" class="action" name="reply" idx="' + v.num + '" value="등록">  '
				code += '   </p>'
				code += '  </div>'
				code += ' </div>'
				code += '</div>'
			})  //반복문
		   
		    code += '</div>'
		   
		    $('.box').html(code); 
			 
			 
		 },
		 error : function(xhr){
			 alert("상태 :" + xhr.status)
		 },
		 dataType : 'json'
		 
	})
})
</script>
</head>
<body>
<div class="box"></div>
</body>
</html>





 
