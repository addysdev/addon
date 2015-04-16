<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<HTML>
 <HEAD>

<script language="JavaScript">

	/*******************************************************************************
	★ 설명
	   크롬 브라우저로 팝업띄우기
	★ Parameter 
	   1. 팝업 URL
	*****************************************************************************/
	
	function func_OpenExploerPopup(strParm) 
	{
		try {
			var objWSH = new ActiveXObject("WScript.Shell");
			var retval = objWSH.Run("iexplore.exe " + strParm, 2, true);
			if (retval != 0) {
				alert('팝업 호출에 실패했습니다.');
			}
	
		} catch (e) {
			alert("도구 > 인터넷 옵션 > 보안 > 사용자 지정 수준 클릭 > 스크립팅하기 안전하지 않은 것으로 표시된 ActiveX컨트롤 초기화 및 스크립팅을 확인으로 변경하세요.\n");
		}
	}
	
    function tmt_winLaunch(theURL,winName,targetName,features) {
		
		eval(winName+"=window.open('"+theURL+"','"+targetName+"','"+features+"')");

	}
    
	function init(){
		
		var h=560;
		var s=400;
	
	    tmt_winLaunch('<%= request.getContextPath()%>/gmroiclc' , 'qaz', 'qaz', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
		
	}

	/*
	*팝업 부모창 종료함수
	*/
	function selfClose(){
		qaz.firstInput();
		window.open('about:blank','_self').close();
	}

</script>
</HEAD>
 <body onLoad = "init();">
</body>
</HTML>