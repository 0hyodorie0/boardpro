/**
 * 
 */
currentPage=1;

boardInsert = function(){
	$.ajax({
		url : '/boardpro/BoardInsert.do',
		type : 'post',
		data : $('#wrform').serializeArray(), 
		success : function(res){
//			alert(res.sw);
			if(res.sw=='저장성공'){
				listServer(1);
			}
		},
		error : function(xhr){
			alert("상태 : "+xhr.status);
		},
		dataType:'json'
	});
}

readHit = function(title){
	//update board1 set hit = hit+1 where num = 
	$.ajax({
		url : '/boardpro/BoardHit.do',
		data : {
			"num" : actionIdx
		},
		type : 'get',
		success : function(res){
			//alert(res.sw);
			if(res.sw=="수정성공"){
				//화면수정
				card = $(title).parents('.card');
				hit = parseInt(card.find('.hit').text())+1;
				card.find('.hit').text(hit);
			}
		},
		error : function(xhr){
			alert("상태 : "+xhr.status)
		},
		dataType : 'json'
	})
}

boardModify = function(but){ //but - 게시판글의 수정버튼
	$.ajax({
		url : '/boardpro/BoardModify.do',
		data : {
			"num" : num,
			"subject" : subject,
			"mail" : mail,
			"pass" : pass,
			"content" : area //\n포함
		},
		type : 'post',
		success : function(res){
			alert(res.sw);
			if(res.sw=='수정성공'){
				//화면수정
				card = $(but).parents('.card');
				
				card.find('a').text(subject);
				card.find('.bodyp').find('.em').text(mail);
				
				area = area.replace(/\r/g, "").replace(/\n/g,"<br>");
				card.find('.bcont').html(area);
				
				today = new Date();
				date = today.toLocaleDateString();
				card.find('.bodyp').find('.da').text(date);
				
			}
		},
		error : function(xhr){
			alert('상태:'+xhr.status);
		},
		dataType : 'json'
	});
}

boardDelete = function(){
	$.ajax({
		url : '/boardpro/BoardDelete.do',
		data : {
			"num" : actionIdx,
			"type" : type,
			"word" : word
			},
		type : 'get',
		success : function(res){
			if(res.sw == "삭제성공"){
				if(currentPage > res.tp ){
					//리스트 재호출 - 해당 페이지 - 
					currentPage = res.tp;
				}
				listServer(currentPage);					
			}
		},
		error : function(xhr){
			alert('상태 : '+xhr.status);
		},
		dataType : 'json'
	});
}

replyUpdate = function(){
	$.ajax({
		url : '/boardpro/ReplyUpdate.do',
		type : 'post',
		data : {
			"renum" : actionIdx,
			"cont" : modifycont
		},
		success : function(res){
			if(res.sw == "수정성공"){
				//db수정이 성공하면 화면수정되도록 한다
				cp3.html(modifycont2);				
			}
		},
		error : function(xhr){
			alert('상태:'+xhr.status);
		},
		dataType : 'json'
	});
}

replyDelete = function(but){
	$.ajax({
		url : '/boardpro/ReplyDelete.do',
		method: 'get',
		data:{"renum":actionIdx},
		success:function(res){
			if(res.sw == "삭제성공"){
				//화면의 댓글 지우기
				$(but).parents('.rep').remove();				
			}
		},
		error:function(xhr){
			alert('상태 : '+xhr.status);
		},
		dataType : 'json'
	})
}

replyList = function(but){ //but : 등록버튼 이거나 제목
	
	$.ajax({
		url : '/boardpro/ReplyList.do',
		type : 'get',
		data : {'bonum' : actionIdx},
		success : function(res){
			rcode='';
			$.each(res, function(i,v){
				

				cont = v.cont;
				cont = cont.replaceAll("\r","").replaceAll("\n", "<br>");
				
				rcode += '   <div class="rep"> '
				rcode += '    <p class="p1 bodyr">'
				rcode += '	 <span class="wr">' + v.name + '</span> &nbsp;&nbsp;&nbsp;&nbsp; '
				rcode += '	  <span class="da">' + v.redate + '</span> '
				rcode += '   </p> '
				rcode += '    <p class="p2"> '
				rcode += '     <input type="button" class="action"  name="r_modify" idx="' + v.renum + '" value="댓글수정">'
				rcode += '     <input type="button" class="action"  name="r_delete" idx="' + v.renum + '" value="댓글삭제">'
				rcode += '    </p>'
				rcode += '  <hr>'
				rcode += '   <p class="p3">  '
				rcode += cont; 
			    rcode +=  '   </p>'
				rcode += '  </div>'
			});
			$(but).parents('.card').find('.rep').remove();
			$(but).parents('.card').find('.card-body').append(rcode);
		},
		error : function(xhr){
			alert('상태 : '+xhr.status);
		},
		dataType:'json'
	});
}

replyInsert=function(rebut){
	$.ajax({
		url : '/boardpro/ReplyInsert.do',
		type : 'post',
		data : reply, //bonum, cont, name
		success : function(res){
			//댓글 리스트 가져오기
			replyList(rebut);
			
			//저장한 댓글을 화면에 출력
		},
		error : function(xhr){
			alert("상태 : "+xhr.status);
		},
		dataType:'json'
	});
}
listServer = function(cpage){
	
	type = $('#stype option:selected').val().trim();
	word = $('#sword').val().trim();
	
	$.ajax({
		 url : '/boardpro/ListSearch.do',
		 method : 'get',
		 data : {
			 "page" : cpage ,
			 "type" : type ,
			 "word" : word
			 }, 
		 success : function(res){
			
		   code = '<div id="accordion">'	 ; 
			 
		   $.each(res.datas, function(i,v){
				code += '<div class="card">'
				code += '  <div class="card-header">'
				code += '    <a name="list" class="action card-link" href="#collapse' + v.num + '" data-toggle="collapse" idx="' + v.num + '"> '
				code +=  v.subject ;
				code += '    </a>'
				code += '  </div>'
				code += '  <div id="collapse' + v.num + '" class="collapse" data-parent="#accordion"> '
				code += '   <div class="card-body cbody"> '
				code += '    <p class="p1 bodyp">'
				code += '	 작성자 : <span class="wr">' + v.writer + '</span> &nbsp;&nbsp;&nbsp;&nbsp; '
				code += '	 이메일 <span class="em">' + v.mail + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
				code += '	조회수 <span class="hit">' + v.hit  + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
				code += '	 날짜  <span class="da">' + v.wdate + '</span> '
				code += '   </p> '
				code += '    <p class="p2"> '
				code += '     <input type="button" class="action"  name="modify" idx="' + v.num + '" value="수정" data-toggle="modal" data-target="#modifyModal">'
				code += '     <input type="button" class="action"  name="delete" idx="' + v.num + '" value="삭제">'
				code += '    </p>'
				code += '  <hr>'
				code += '   <p class="p3 bcont">  '
				code += v.content.replace(/\r/g,"").replace(/\n/g, "<br>");
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
		 
	});
}