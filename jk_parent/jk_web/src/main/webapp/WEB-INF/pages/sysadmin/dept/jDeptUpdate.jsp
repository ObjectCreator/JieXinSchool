<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	
	<!-- 设置提示信息样式 -->
	<style type="text/css">
		label.error{
			color:Red;
		}
	</style>
	<!--自动校验  -->
	<script type="text/javascript">
		$(function(){
		
			$("#formId").validate({
				rules:{
					deptName:{
						required:true,
						minlength:2,
						maxlength:10
					}
				},
				messages:{
					deptName:{
						required:"部门名称必须填写，且名称字数在2-10个字"
					}
				}
			});
		});
	
		function tosave(){
			//执行表单校验
			if($("#formId").valid()){
				formSubmit('${pageContext.request.contextPath}/sysadmin/deptAction_update','_self');
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
<li id="save"><%-- <a href="#" onclick="formSubmit('${pageContext.request.contextPath}/sysadmin/deptAction_update','_self');this.blur();">保存</a></li>
 --%>
<a href="#" onclick="javascript:tosave()">保存</a></li>

<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div> 
  

 	<!--  -->
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">上级部门：</td>
	            <td class="tableContent">
	            	<%-- <s:select name="parent.id" list="#request.deptList"
	            		listKey="id" listValue="deptName" 
	            	 value="parent.id"
	            	></s:select>
	            		           --%>  	
	            	<s:select name="parent.id" list="#request.deptList"
 	            		listKey="id" listValue="deptName" 
 	            		headerKey="" headerValue="--请选择--" 
 	            	></s:select> 
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">部门名称：</td>
	            <td class="tableContent"><input type="text" name="deptName" value="${model.deptName }"/>
	            </td>
	        </tr>		
	         <tr>
	           <td class="columnTitle">管理领导：</td>
	            <td class="tableContent">
	            	   <s:select name="manager.userId" list="#request.userList"
	            		listKey="id" listValue="userInfo.name"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>   
	            	<input type="hidden" name="manager.id" value="${manager.id}"/>
	           
	            </td>
	        </tr>	
		</table>
	</div>
 </form>
</body>
</html>