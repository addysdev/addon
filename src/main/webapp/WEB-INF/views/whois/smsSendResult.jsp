<%@ include file="/WEB-INF/views/addys/base.jsp" %>

<body>
<div class="container-fluid">
<h5><strong><font style="color:#428bca">
받는사람번호 sms_to = ${sms_to}<br>
보내는사람번호 sms_from =${sms_from}<br>
발송요청일자 sms_date = ${sms_date}<br>
보내는 메세지 sms_msg = ${sms_msg}<br>
=============================================<br>
결과 코드 retCode = ${retCode}<br>
결과 메세지 retMessage = ${retMessage}<br>
잔여포인트 retLastPoint = ${retLastPoint}<br>
</font></strong></h5>
</div>
</body>