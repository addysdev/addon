	var oEditors = [];	

	function createSEditor(txtAreaId) {
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: txtAreaId,
			sSkinURI: "/cs/se/SmartEditor2Skin.html",	
			htParams : {bUseToolbar : true,
				fOnBeforeUnload : function(){
					//alert("!");	
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});			
	}
	
	function registerContent(form) {
		
		var seqNo = form.seqNo.value;
		var title = form.title.value;
		
		if (title == null || title == "" ) {
			alert("제목을 입력해주세요");
			return;
		}
		
		toTextArea();
		
		var postedContent = form.postedContent.value;
		if (postedContent == null || postedContent == "" ) {
			alert("내용을 입력해 주세요");
			return;
		}
		
		if (form.eventStartDate != null && form.eventStartDate != undefined) {
			var eventStartDate = form.eventStartDate.value;
			if (eventStartDate == null || eventStartDate == "" ) {
				alert("행사 시작일을 입력해 주세요");
				return;
			}	
		}
		
		if (form.eventEndDate != null && form.eventEndDate != undefined) {
			var eventEndDate = form.eventEndDate.value;
			if (eventEndDate == null || eventEndDate == "" ) {
				alert("행사 종료일을 입력해 주세요");
				return;
			}		
		}
		
		if (form.eventLocation != null && form.eventLocation != undefined) {
			var eventLocation = form.eventLocation.value;
			if (eventLocation == null || eventLocation == "" ) {
				alert("행사 장소를 입력해 주세요");
				return;
			}
		}
		
		if (form.sido != null && form.sido != undefined) {
			var sidoCode = $("#sido").val();
			var sportCode = $("#sport").val();
			if (sidoCode == 'NONE' && sportCode == 'NONE') {
				alert("행사 조직을 선택해 주세요");
				return;
			}
		}
		
		if (seqNo == null || seqNo == "0" ) {
			form.action = "/cs/board/registerContent";
		}
		else {
			form.action = "/cs/board/modifyContent";
		}
		
		form.submit();
	}
	
	function toTextArea() {
		// 에디터의 내용이 textarea에 적용됩니다.
		oEditors.getById["postedContent"].exec("UPDATE_CONTENTS_FIELD", []);		
	}
	
	function toSEditor(content) {
		// content = '';
		oEditors.getById["postedContent"].exec("PASTE_HTML", [content]);
	}
	
	function postUpload(hostAddr, boardId, seqNo, attSeqNo, thumbnail) {
		
		var imgUrl = hostAddr+"/cs/board/getAttachedFile?boardId="
						+boardId+"&seqNo="+seqNo+"&attSeqNo="+attSeqNo+"&thumbnail="+thumbnail;
		imgUrl = "<div class=\"tbl_img\"><img src='"+imgUrl+"'  /></div>";

		toSEditor(imgUrl);
	}
	
	function postGeneralUpload(hostAddr, boardId, seqNo, attSeqNo, fileName) {
		
		var url = hostAddr+"/cs/board/getAttachedFile?boardId="
					+boardId+"&seqNo="+seqNo+"&attSeqNo="+attSeqNo;
		
		$(function(){
            
			var html = "<strong id="+attSeqNo+">"+fileName;
			html += "<button type=\"button\" class=\"btn_glay2\" onclick=\"javascript:deleteAttachFile("+attSeqNo+");\">";
			html += "<span class=\"btn_bul bul_dot\">삭제</span></button>";
			html += "<input type=\"hidden\" id=\"arrBoardId\" name=\"arrBoardId\" value="+boardId+ " />";
			html += "<input type=\"hidden\" id=\"arrSeqNo\" name=\"arrSeqNo\" value="+seqNo+" />";
			html += "<input type=\"hidden\" id=\"arrAttSeqNo\" name=\"arrAttSeqNo\" value="+attSeqNo+" />";
			html += "<input type=\"hidden\" id=\"arrAttachFileYn\" name=\"arrAttachFileYn\" value=Y />";
			html += "<input type=\"hidden\" id=\"command\" name=\"command\" value=\"C\" />";				
			html += "</strong>";

			$("div[id='fileList']").append(html);
		});
	}
	
	function popupAttachFileWindow(form, w, h, scroll) {
		
		var LeftPosition=(screen.width)?(screen.width-w)/2:100;
		var TopPosition=(screen.height)?(screen.height-h)/2:100;
		
		var settings='width='+w+',height='+h+
		 ',top='+TopPosition+
		 ',left='+LeftPosition+
		 ',scrollbars='+scroll+
		 ',location=no,directories=no,status=yes,menubar=no,toolbar=no,resizable=no';

		var url = form.action+"?boardId="+form.boardId.value;
		
		openedPop = window.open( url, '_blank', settings);
	}
	
	function getContentPage(form) {
		
		form.method = "post";
		form.action = "/cs/board/getContentPage";
		
		form.submit(); 
	}
	// isNew : true 이면 신규 글쓰기임. 아니면 게시글 수정.
	function viewWriteContent(form, isNew) {
		
		form.action = "/cs/board/viewWriteContent";
		
		if (isNew != null && isNew != undefined 
				&& isNew != "" && isNew == "true") {
//			form.seqNo.value = '0';
			form.action = "/cs/board/viewNewWritePage";
		}
		form.method = "post";
		
		form.submit(); 
	}
	
// 덧글 , 댓글 관련
	// 답글 창이 화면에 표시되었는지 여부, 다른 답글 버튼을 눌렀을 때 이전 답글 창은 사라져야 함 --%>
	var isShowReplyWin = false;
	function showSubReplyPanel(parentReplySeqNo, isClubBoard) {
		
		if (isShowReplyWin == true) {
			hideReplyPannel();	
		}
		
		isShowReplyWin = true;
		
		$(function(){
			
		    var html = "<dd class=\"dot_line\" id=\"replyPanel\">";
		    
//			클럽 게시판은 다른 css 를 사용하므로 : btn_glay0 를 사용.
			if (isClubBoard == 'true') {
			  	html += "<input type=\"hidden\" id=\"parentReplySeqNo\" name=\"parentReplySeqNo\" value=\'\' />";
			  	html += "<textarea id=\"subReplyContent\" name=\"subReplyContent\" style=\"width:530px; height:30px\"></textarea>";
			  	html += "<button type=\"button\" class=\"btn_glay0 floatR\" onclick=\"registerSubReply(document.all.form, document.all.replyForm);\"><span class=\"btn_bul bul_dot\">답글입력</span></button>";
			  	html += "</dd>";
			}
			else {
			  	html += "<input type=\"hidden\" id=\"parentReplySeqNo\" name=\"parentReplySeqNo\" value=\'\' />";
			  	html += "<textarea id=\"subReplyContent\" name=\"subReplyContent\" style=\"width:530px; height:30px\"></textarea>";
			  	html += "<button type=\"button\" class=\"btn_glay floatR\" onclick=\"registerSubReply(document.all.form, document.all.replyForm);\"><span class=\"btn_bul bul_dot\">답글입력</span></button>";
			  	html += "</dd>";				
			}
		  	
		  	$("dt[id='"+parentReplySeqNo+"'],dd[id='"+parentReplySeqNo+"']").after(html);
		 // 새로운 panel(input:hidden) 에 ID 값을 설정한다.
		  	$("form[id='replyForm'] #parentReplySeqNo").val(parentReplySeqNo);
		});
	}

	// 답글 입력 창을 숨긴다 --%>
	function hideReplyPannel() {
		$("#replyPanel").remove();
		cancelModifyReply();
		isShowReplyWin = false;
	}

	var curReplySeqNo;
	function showReplyModifyPanel(replySeqNo, content, isClubBoard) {
		
		if (isShowReplyWin == true) {
			hideReplyPannel();	
		}
		
		isShowReplyWin = true;
		// 수정 취소에서 사용하게 된다. 
		curReplySeqNo = replySeqNo;
		// 수정 취소를 위해 기존 덧답글을 백업 --%>
		var replyHtml = $("dt[id='"+replySeqNo+"'],dd[id='"+replySeqNo+"']").html();

		storeReply(replyHtml);
		// 수정 창을 보여줌 --%>
		var html = "";
//		클럽 게시판은 다른 css 를 사용하므로 : btn_glay0 를 사용.
		if (isClubBoard == 'true') {
		  	html += "<input type=\"hidden\" id=\"replySeqNo\" name=\"replySeqNo\" value=\'\' />";
		  	html += "<textarea id=\"taReplyContent\" name=\"taReplyContent\" style=\"width:450px; height:30px\"></textarea>";
		  	html += "<button type=\"button\" class=\"btn_glay0 floatR\" onclick=\"cancelModifyReply();\"><span class=\"btn_bul bul_dot\">취소</span></button>";
		  	html += "<button type=\"button\" class=\"btn_glay0 floatR\" onclick=\"modifyReply("+replySeqNo+", document.all.form, document.all.replyForm);\"><span class=\"btn_bul bul_dot\">수정</span></button>";
		} else {
		  	html += "<input type=\"hidden\" id=\"replySeqNo\" name=\"replySeqNo\" value=\'\' />";
		  	html += "<textarea id=\"taReplyContent\" name=\"taReplyContent\" style=\"width:450px; height:30px\"></textarea>";
//		  	html += "<button type=\"button\" class=\"btn_glay floatR\" onclick=\"cancelModifyReply();\"><span class=\"btn_bul bul_dot\">취소</span></button>";
//		  	html += "<button type=\"button\" class=\"btn_glay floatR\" onclick=\"modifyReply("+replySeqNo+", document.all.form, document.all.replyForm);\"><span class=\"btn_bul bul_dot\">수정</span></button>";
		  	
            
            html += "<div class=\"comment_box2\"><ul><li><a href='javascript:modifyReply("+replySeqNo+", document.all.form, document.all.replyForm);'>수정</a></li>";
            html += "<li><a href='javascript:cancelModifyReply();'>취소</a></li></ul></div>";
            
		}
		
	  	
	  	$("dt[id='"+replySeqNo+"'],dd[id='"+replySeqNo+"']").html(html);
	  	// 새로운 panel(input:hidden) 에 ID 값을 설정한다.
	  	$("form[id='replyForm'] #replySeqNo").val(replySeqNo);
	  	// 수정할 덧답글 내용을 textarea 에 표시한다.
	  	$("#taReplyContent").val(content);
	}


	function removeReply(replySeqNo,conForm, repForm) {
		
		if (!confirm("게시글을 삭제하시겠습니까")) {
			return;
		}
		
		repForm.boardId.value = conForm.boardId.value;
		repForm.seqNo.value = conForm.seqNo.value;
		repForm.replySeqNo.value = replySeqNo;
		
		repForm.action = "/cs/board/removeReply";
		repForm.method = "post";
		repForm.submit();
	}

	// 수정 버튼을 눌렀을 경우 기존 덧글/답글을 백업해 둔다 --%>
	var gReplyHtml;
	function storeReply(replyHtml) {
		gReplyHtml = replyHtml;
	}

	function loadReply() {
		return gReplyHtml;
	}

	function modifyReply(replySeqNo, conForm, repForm) {
		
		repForm.boardId.value = conForm.boardId.value;
		repForm.seqNo.value = conForm.seqNo.value;
		repForm.replySeqNo.value = replySeqNo;
		repForm.content.value = repForm.taReplyContent.value;
		
		repForm.action = "/cs/board/modifyReply";
		repForm.method = "post";
		repForm.submit();
	}

	function cancelModifyReply() {
		
		var html = loadReply();
		$("dt[id='"+curReplySeqNo+"'],dd[id='"+curReplySeqNo+"']").html(html);
	}

	function registerSubReply(conForm, repForm) {
		
		repForm.method = "post";
		repForm.action = "/cs/board/registerReply";
		
		repForm.boardId.value = conForm.boardId.value;
		repForm.seqNo.value = conForm.seqNo.value;
		
		repForm.content.value = repForm.subReplyContent.value;
		
		if (repForm.content.value == "") {
			alert("내용을 입력하세요");
			return;
		}
		
		// isShowReplyWin = false;
		repForm.submit(); 
	}

	function registerReply(conForm, repForm) {
		
		repForm.method = "post";
		repForm.action = "/cs/board/registerReply";
		
		repForm.boardId.value = conForm.boardId.value;
		repForm.seqNo.value = conForm.seqNo.value;
		repForm.parentReplySeqNo.value = 0;
		repForm.content.value = repForm.replyContent.value;
		
		if (repForm.content.value == "") {
			alert("내용을 입력하세요");
			return;
		}
		
		// isShowReplyWin = false;
			
		repForm.submit(); 
	}	
	
	function invalidTerm(form) {
//		날짜 조건이 없을 경우 invalid 가 아니다.
		if (form.fromDate.value == undefined
				|| form.toDate.value == undefined) {
			return false;
		}
		
		var from = form.fromDate.value;
		var to = form.toDate.value;
		
		if(from != null && from != "" 
			&& to != null && to != "" ) { 
		
			if(from > to){
				alert("시작일이 종료일보다 나중입니다.");
				return true;
			}
		}
		return false;
	}
