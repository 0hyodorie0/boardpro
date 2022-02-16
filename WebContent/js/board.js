/**
 * 
 */
currentPage=1;
var listServer = function(cpage){
	$.ajax({
		 url : '/boardpro/ListPage.do',
		 method : 'get',
		 data : {"page": cpage }, 
		 success : function(res){
			
		   code = '<div id="accordion">'	 ; 
			 
		   $.each(res.datas, function(i,v){
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
				code += '    <input type="button" class="action reply" name="reply" idx="' + v.num + '" value="등록">  '
				code += '   </p>'
				code += '  </div>'
				code += ' </div>'
				code += '</div>'
			})  //반복문
		   
		    code += '</div>'
		   
		    $('.box').html(code); 
		   
		   //이전버튼 만들기
		   pager="";
		   if(res.startpage>1){
			   pager += '<ul class="pagination">'
			   pager += '	<li class="page-item previous"><a href="#" class="page-link">Previous</a></li>'
			   pager += '</ul>'
		   }

		   //페이지 번호
		   pager += '<ul class="pagination pager">';
		   for(i=res.startpage; i<=res.endpage; i++){
			   if(currentPage==i){
				   pager += '<li class="page-item active"><a href="#" class="page-link pnum">'+i+'</a></li>';				   
			   }else{
				   pager += '<li class="page-item"><a href="#" class="page-link pnum">'+i+'</a></li>';				   
			   }
		   }
		   pager += '</ul>';
			 
		   
		   //다음버튼
		   if(res.endpage<res.totalpage){
			    pager += '<ul class="pagination">'
				pager += '	<li class="page-item next"><a href="#" class="page-link">Next</a></li>'
				pager += '</ul>'
		   }
		   
		   $('#btngroup1').empty().append(pager);
			 
		 },
		 error : function(xhr){
			 alert("상태 :" + xhr.status)
		 },
		 dataType : 'json'
		 
	})
}


