<!DOCTYPE HTML> 
<html> 
<head> 
<meta charset="utf-8"> 
<title></title> 
    <meta name="viewport" content="width=device-width, initial-scale=1">  
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css" /> 
<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script> 
<script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script> 
<script>

function goMap(){
	document.offactForm.submit();
}

</script>
</head> 

<body> 
<form  id="offactForm" name="offactForm"  role="form" action="<%= request.getContextPath() %>/offactmap">
<div data-role="page" id="page" data-fullscreen="true"> 
  <div data-role="header" data-position="fixed"> 
    <a href="#" data-icon="back">Back</a> 
    <a href="#" data-icon="home" data-iconpos="notext" data-theme="b">홈</a>          
    <h1>Offact Hybrid</h1> 
    <div data-role="navbar"> 
        <ul> 
            <li><a href="javascript:goMap()" class="ui-btn-active">Map</a></li> 
            <li><a href="javascript:alert('이미지')" >이미지</a></li> 
            <li><a href="javascript:alert('동영상')" >동영상</a></li> 
        </ul> 
    </div>  <!-- navbar 끝 -->           
  </div><!-- header 끝 --> 
  <div data-role="content"> 
      <img src="<%= request.getContextPath() %>/images/body_bgAll.gif" alt="" /> 
  </div> 
  <div data-role="footer" data-position="fixed"> 
    <div data-role="navbar" > 
        <ul> 
            <li><a href="javascript:alert('설정')" class="ui-btn-active" data-icon="gear" >설정</a></li> 
            <li><a href="javascript:alert('메뉴')" data-icon="grid" >메뉴</a></li> 
            <li><a href="javascript:alert('검색')" data-icon="search">검색</a></li> 
            <li><a href="javascript:alert('도움말')" data-icon="info">도움말</a></li>             
        </ul> 
    </div>  <!-- navbar 끝 -->    
  </div> <!-- footer 끝 --> 
</div> 
</form>
</body> 