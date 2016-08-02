<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<!--表单进行验证 -->
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
					},
					"userInfo.name":{
						required:true
					},
					
					"userInfo.joinDate":{
						required:true
					},
					"userInfo.salary":{
						required:true,
						number:true
					},
					"userInfo.degree":{
						required:true
					},
					"userInfo.gender":{
						required:true
					},
					"userInfo.station":{
						required:true
					},
					"userInfo.telephone":{
						required:true,
						number:true,
						minlength:11,
						maxlength:11
					},
					"userInfo.email":{
						required:true,
						email:true
					},
					"userInfo.birthday":{
						required:true
					},
					"userInfo.orderNo":{
						required:true,
						number:true
					},
					"userInfo.remark":{
						required:true
					}	
				},
				messages:{
					"dept.id":{
						required:"所在部门必须选择 "
					},
					userName:{
						required:"登录名不能为空  "
					},
					state:{
						required:"必须选择用户状态  "
					},
					"userInfo.name":{
						required:"用户名必须填写  "
					},
					
					"userInfo.joinDate":{
						required:"入职日期必须选择 "
					},
					"userInfo.salary":{
						required:"薪资必须填写  ",
						number:"薪资必须为数字  "
					},
					"userInfo.degree":{
						required:"等级必须选择 "
					},
					"userInfo.gender":{
						required:"性别必须选择 "
					},
					"userInfo.station":{
						required:"职位必须选择 "
					},
					"userInfo.telephone":{
						required:"电话必须填写",
						number:"电话必须为数字 ",
						minlength:"电话必须为11位 ",
						maxlength:"电话必须为11位 "
					},
					"userInfo.email":{
						required:"邮箱必须填写 ",
						email:"请检查邮箱格式 "
					},
					"userInfo.birthday":{
						required:"生日必须选择 "
					},
					"userInfo.orderNo":{
						required:"排序号必须填写 ",
						number:"排序号必须为数字 "
					},
					"userInfo.remark":{
						required:"说明必须填写 "
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
<li id="save"><%-- <a href="#" onclick="formSubmit('${ctx }/sysadmin/userAction_save','_self');this.blur();">保存</a></li>
 --%>

<a href="#"  onclick="javascript:tosave()">保存</a></li>


<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
更新用户
    <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  <!-- 用于显示所有表单验证错误信息 -->
 
  </div>
  </div>
  

 	
    <div>
		<table class="commonTable" cellspacing="2">
       		<tr>
	            <td class="columnTitle">所在部门：</td>
          
           <td class="tableContent">
           	<s:select name="dept.id" list="#request.deptList" 
           		listKey="id" listValue="deptName"
           		headerKey="" headerValue="--请选择--"
           	></s:select>
           </td>
            <td class="columnTitle">管理领导：</td>
	            <td class="tableContent">
	            	   <s:select name="manager.userId" list="#request.userList"
	            		listKey="id" listValue="userInfo.name"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>   
	            	<input type="hidden" name="manager.id" value="${manager.id }"/>
	            </td>
           
	        </tr>
        	<tr>
	            <td class="columnTitle">登录名：</td>
	            <td class="tableContent"><input type="text" name="userName" value="${userName }"/></td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" checked class="input" ${state==1?'checked':'' }/>启用
	            	<input type="radio" name="state" value="0" class="input" ${state==0?'checked':'' }/>停用
	            </td>
	        </tr>
        	<tr>
	            <td class="columnTitle">姓名：</td>
	            <td class="tableContent"><input type="text" name="userInfo.name" value="${userInfo.name }" /></td>
	            <td class="columnTitle">直属领导：</td>
	            <td class="tableContent">
	            	   <s:select name="userInfo.manager.id" list="#request.userList"
	            		listKey="id" listValue="userInfo.name"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>   
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">入职时间：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="userInfo.joinDate"
	            	  value="<fmt:formatDate value='${userInfo.joinDate }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
				<td class="columnTitle">薪水：</td>
	            <td class="tableContent"><input type="text" name="userInfo.salary" value="${userInfo.salary }"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">等级：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="userInfo.degree" value="0" class="input" ${userInfo.degree==0?'checked':'' }/>超级管理员
	            	<input type="radio" name="userInfo.degree" value="1" class="input" ${userInfo.degree==1?'checked':'' }/>跨部门跨人员
	            	<input type="radio" name="userInfo.degree" value="2" class="input" ${userInfo.degree==2?'checked':'' }/>管理所有下属部门和人员
	            	<input type="radio" name="userInfo.degree" value="3" class="input" ${userInfo.degree==3?'checked':'' }/>管理本部门
	            	<input type="radio" name="userInfo.degree" value="4" class="input" ${userInfo.degree==4?'checked':'' }/>普通员工
	            </td>
				<td class="columnTitle">性别：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="userInfo.gender" value="1" class="input" ${userInfo.gender==1?'checked':'' }/>男
	            	<input type="radio" name="userInfo.gender" value="0" class="input" ${userInfo.gender==0?'checked':'' }/>女
	            </td>
	        </tr>	
        	<tr>
	            <td class="columnTitle">岗位：</td>
	            <td class="tableContent"><input type="text" name="userInfo.station" value="${userInfo.station }"/></td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="userInfo.telephone" value="${userInfo.telephone }"/></td>
	        </tr>	
        	<tr>
        	    <td class="columnTitle">邮箱：</td>
	            <td class="tableContent"><input type="text" name="userInfo.email" value="${userInfo.email }"/></td>
	            <td class="columnTitle">出生年月：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="userInfo.birthday"
	            	 value="<fmt:formatDate value='${userInfo.birthday }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"
	           
	             	/>
				</td>
	        </tr>	
        	<tr>
        	    <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="userInfo.orderNo" value="${userInfo.orderNo }"/></td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="userInfo.remark" style="height:120px;" >${userInfo.remark }</textarea>
	            </td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

