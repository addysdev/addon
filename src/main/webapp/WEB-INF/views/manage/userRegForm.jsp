<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<!DOCTYPE html>
<html>
 <head>
	<script>
		function goUserSave1(){
			alert('a');
		}
		
		// 삭제
		function goUserSave2(){
		    commonDim(true);
		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "/cs/ADUserAuthListDelAuth",
		           data:{"con_userId":userId,"con_userDeptCode":userDeptCode,"con_funcAuthCode":funcAuthCode},
		           success: function(result) {
		               commonDim(false);
		               fnAdUserAuthList_listSearch();
		           },
		           error:function(){
		               commonDim(false);
		           }
		    });
		}
		
		//리스트 조회
		function goUserSave(){
		    //commonDim(true);
		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/manage/userreg",
		           data:$("#userRegistForm").serialize(),
		           success: function(result) {
		               
		        	   alert('1');
		        	   alert(result);
		               //commonDim(false);
		           },
		           error:function(){
		              // commonDim(false);
		           }
		    });
		}
	</script>
  </head>
  <body>
   <div class="container">
      <form:form commandName="userVO" id="userRegistForm" name="userRegistForm" method="post" action="">
	    <div class="form-group">
	    <th>
   		  <td>
			  아이디 : <input type="text" class="form-control" id="userId" name="userId" tabindex="1" value="${userVO.userId}">
			  패스워드: <input type="text" class="form-control" id="password" name="password" tabindex="2" >
	      </td>
          <td>
                              사용자명: <input  type="text" class="form-control" id="userName"  name="userName" tabindex="3">
	    	  생성자 : <input type="text" class="form-control" id="createUserId" name="createUserId" tabindex="4">
          </td>
          <td>
                              조직: <input  type="text" class="form-control" id="groupId"  name="groupId" tabindex="3">
	    	  권한 : <input type="text" class="form-control" id="authId" name="authId" tabindex="4">
          </td>
			</th>
			<br>
            <td><button type="button" class="btn btn-primary" onClick="javascript:goUserSave()">저장</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>

 

