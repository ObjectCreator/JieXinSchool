<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
	$(function(){
		$("#formId").validate({
			rules:{
				"dept.id":{
					required:true
				},
				userName:{
					required:true
				},
				state:{
					required:true
				}
			
			},
			messages:{
				"dept.id":{
					required:"所在部门必须选择  "
				},
				userName:{
					required:"用户名不能为空  "
				},
				state:{
					required:"必须选择用户状态  "
				}
				
			}, 
			errorLabelContainer: "#errorId"
		});
	});
	
	function tosave(){
		if($("#formId").valid()){
			formSubmit('${ctx }/sysadmin/userAction_update','_self');
		}else{
			return false;
		}
	}
	</script>
</head>

<body>
<form name="icform" method="post" id="formId">
      <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<!-- <li id="save"><a href="#" onclick="formSubmit('userAction_update','_self');this.blur();">保存</a></li> -->
<li id="save"><a href="#"  onclick="javascript:tosave()">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
 更新用户
    <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">所在部门：</td>
	             <td class="tableContent">
	            	<s:select name="dept.id" list="#request.deptList"
	            		listKey="id" listValue="deptName"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="tableContent"><input type="text" name="userName" value="${userName }"/></td>
	        </tr>	
	         <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	              <input type="radio" name="state" class="input" ${state==0?'checked':'' } value="0">停用 
	              <input type="radio" name="state" class="input"  ${state==1?'checked':'' } value="1">启用 
	            </td>
	        </tr>		
		</table>
	</div>
 </form>
</body>
</html>