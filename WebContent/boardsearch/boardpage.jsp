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
  
  
  <script src="../js/boardsearch.js"></script>
  
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
    background : #f2f2f2;
    color:#444;
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
 .navbar {
 	background:#BEEFFF;
 }
 #stype{
 	width:100px;
 }
 .rep{
 	border : 1px solid gray;
 	background:#ffb399;
 	margin:1px;
 	padding:3px;
 }
 .p1{
  width : 65%;
  float : left;
  border :none;
 }
 .p2{
    width : 33%;
    float : right;
    text-align : right;
    border :none;
 }
 hr{
   clear : both ;
 }
 #modifyform {
 	display:none;
 }
 #modifyModal label {
 	display:inline-block;
 }
</style>  
  
<script>
reply = {};
$(function(){
	//js/boardsearch.js에 구현(실행)
	listServer(1);
	
	
	//이벤트
	//수정, 삭제, 등록 버튼에 대한 이벤트
	$('.box').on('click', '.action', function(){ //delegate
		actionName = $(this).attr('name');
		actionIdx = $(this).attr('idx');
		
		if(actionName=="modify"){
			alert(actionIdx+"번 글을 수정합니다.");
			
			modi = this;
			
			card = $(this).parents('.card');
			
			title = card.find('a').text();
			writer = card.find('.bodyp').find('.wr').text();
			email = card.find('.bodyp').find('.em').text();
			cont = card.find('.p3').html(); //br태그 포함
			cont = cont.replace(/<br>/g,"\n")
			
			$('#modifyModal #subject').val(title);
			$('#modifyModal #writer').val(writer);
			$('#modifyModal #writer').prop('readonly',true);
			
			
			$('#modifyModal #mail').val(email);
			$('#modifyModal #area').val(cont);
			
		}else if(actionName=="delete"){
			alert(actionIdx+"번 글을 삭제합니다.");
			
			//서버로 요청
			type = $('#stype').val(); //subject, writer, content 
			word = $('#sword').val(); //1, hello
			boardDelete();
			
		}else if(actionName=="reply"){
			alert(actionIdx+"번 글에 댓글을 답니다.");	
			
			//입력한 내용 가져오기
			cont = $(this).prev().val();
			
			//저장할 요소를 만들기 - renum, bonum, name, cont, redate
			reply.bonum = actionIdx;
			name1 = String.fromCharCode(Math.random()*26+65) //알파벳대문자 65~90 
			name2 = String.fromCharCode(Math.random()*26+97) //알파벳소문자 97~122
			name3 = parseInt(Math.random()*100+1);
			
			reply.name = name1+name2+name3;
			reply.cont = cont;
			
			$(this).prev().val("");
			
			//boardsearch.js의 replyInsert()실행
			replyInsert(this); //댓글저장
						
		}else if(actionName == "r_modify"){
			alert(actionIdx+"번 댓글을 수정합니다");
			
			//댓글 수정폼(modivyform)의 display 스타일 속성값을 가져온다
			//none가 아니면 - 보여지고 있는 상태 - 보여지고있는 수정폼을 닫는다
			//다른곳에 이미 수정폼이 보여지고있다면 수정폼을 닫는다
			if($('#modifyform').css('display') != "none"){
				replyReset();	
			}
			
			//수정할 내용
			modifycont = $(this).parents('.rep').find('.p3').html();
			//<br>를 \n으로 바꾼다
			modifycont = modifycont.replace(/<br>/g,"\n");
			
			//수정할 내용(원래글)을 modifyform에 
			$('#modifyform #text').val(modifycont);
			
			//수정폼 보이기
			$(this).parents('.rep').find('.p3').empty().append($('#modifyform'));
			//$('#modifyform').css('display','block');
			$('#modifyform').show();
			
		}else if(actionName == "r_delete"){
			alert(actionIdx+"번 댓글을 삭제합니다");	
			
			//화면에서 삭제
			//$(this).parents('.rep').remove();
			
			//db삭제
			replyDelete(this);
			
		}else if(actionName == "list"){
			//alert(actionIdx+"번 댓글을 출력합니다");
			
			replyList(this);
			
			vclass = $(this).parents('.card').find('.show').attr('class')
			///alert(vclass);
			//닫혀있는 상태에서 클릭하면 undefined
			//열려있는 상태에서 클릭하면 callapse show 클래스가 출력
			//Number, String undefined 도 자료형(type)
			
			if(typeof vclass=="undefined"){
				//조회수 증가
				readHit(this);
			}
			
		}
		
	});
	
	//댓글 수정창 닫기
	replyReset = function(){
		//p3 찾기
		cp3 = $('#modifyform').parent();
		
		//수정폼의 내용을 가져오기
		cp3data = $('#modifyform #text').val(); //\n 포함
		
		//수정폼을 body로 이동 - 감추기
		$('body').append($('#modifyform'));
		$('#modifyform').hide();
		
		//수정폼의 내용중 \n을 br태그로 바꾸기
		cp3data = modifycont.replace(/\n/g,"<br>");
		
		//
		cp3.html(cp3data);
		
	}
	
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
	
	//검색버튼
	$('#search').on('click', function(){
		currentPage = 1;
		listServer(currentPage);
	})
	
	//댓글수정창에서 취소버튼 클릭
	$('#btnreset').on('click', function(){
		replyReset();
	})
	
	//댓글 수정창에서 확인버튼 클릭
	$('#btnok').on('click', function(){
		
		//수정내용 가져오기
		modifycont = $('#modifyform #text').val(); //\n 포함
		
		cp3 = $('#modifyform').parent();
		
		//수정폼을 body로 이동 - 감추기
		$('body').append($('#modifyform'));
		$('#modifyform').hide();
		
		
		//------화면으로 확인-----
		//수정내용의 \n을 <br>태그로 변경
		modifycont2 = modifycont.replace(/\n/g, "<br>");
		
		//p3에 수정내용을 출력 - 화면에서 수정됨
		//cp3.html(modifycont2);
		//-------------------
		
		//수정항목을 서버로 보내기 - js
		replyUpdate(); //renum, cont
	});
	
	///수정modal창에서 각 항목 수정후 수정버튼 클릭
	$('#modibut').on('click',function(){
		
		
		subject = $('#subject ').val();
		mail = $('#mail').val();
		pass = $('#pass').val();
		area = $('#area').val();
		num = actionIdx;
		
		//update board1 set ~~~~~~~~ where num = 34 // 
		//서버로 전송 - boardsearch.js - boardModify
		boardModify(modi); //modi - 게시판글의 수정버튼
		
		//모달창닫고, 모달창의 내용 지우고, 화면수정
		 $('#modifyModal').modal('hide');
		//$('#.text').val(""); */
		
	});
	
	//모달창닫기 이벤트
	$('#modifyModal').on('hide.bs.modal', function(){
		$('.text').val("");
		
	});
	$('#writeModal').on('hide.bs.modal', function(){
		$('.text').val("");
		
	});
	
	//글쓰기 이벤트
	$('#write').on('click',function(){
		//모달창 띄우기
		$('#writeModal').modal('show');
		
	});
	
	//글쓰기 모달창에서 전송버튼 클릭
	$('#wrbut').on('click',function(){
		//입력한 모든값을 가져온다
		console.log($('#inputform').serializeArray());
		
		boardInsert();
		
		$('#writeModal').modal('hide');
		
	})
	
});
</script>
</head>
<body>
<div id="modifyform">
	<textarea id="text" rows="5" cols="50"></textarea>
	<input type="button" value="확인" id="btnok">
	<input type="button" value="취소" id="btnreset">
</div>

<input type="button" value="글쓰기" id="write">
<br>
<br>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<select id="stype" class="form-control">
		<option value="">전체</option>
		<option value="writer">이름</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
	</select>
  <form class="form-inline" action="">
    <input id="sword" class="form-control mr-sm-2" type="text" placeholder="Search"> 
    <button class="btn btn-success" type="button" id="search">Search</button>
  </form>
</nav>
<div class="box"></div>
<div id="btngroup1"></div>

<!-- The Modal -->
<div class="modal" id="modifyModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="inputform">
        	<label>제목</label>
        	<input type="text" class="text" id="subject" name="subject"><br>
        	<label>이름</label>
        	<input type="text" class="text" id="writer" name="writer"><br>
        	<label>메일</label>
        	<input type="text" class="text" id="mail" name="mail"><br>
        	<label>비밀번호</label>
        	<input type="password" class="text" id="pass" name="password"><br>
        	<label>내용</label>
        	<textarea rows="10" cols="50" id="area" name="content"></textarea>
        	<br><br>
        	<input type="button" value="수정" id="modibut">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<!-- 글쓰기 The Modal -->
<div class="modal" id="writeModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="wrform">
        	<label>제목</label>
        	<input type="text" class="text" id="subject" name="subject"><br>
        	<label>이름</label>
        	<input type="text" class="text" id="writer" name="writer"><br>
        	<label>메일</label>
        	<input type="text" class="text" id="mail" name="mail"><br>
        	<label>비밀번호</label>
        	<input type="password" class="text" id="pass" name="password"><br>
        	<label>내용</label>
        	<textarea rows="10" cols="50" class="text" id="area" name="content"></textarea>
        	<br><br>
        	<input type="button" value="전송" id="wrbut">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

</body>
</html>





 
    