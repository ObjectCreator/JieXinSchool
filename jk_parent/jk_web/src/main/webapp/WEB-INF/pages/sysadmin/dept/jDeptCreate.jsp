<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	
	
	<!-- 用css设置提示信息的样式-->
	<style type="text/css">
	/* label标签下 class =error的标签 */	
	 label.error
		{
		color:Red;
		font-size:13px;
		margin-left:5px;
		padding-left:16px;
		
		}
	</style>
<script type="text/javascript">
	//自动校验
	$(function(){
	
		//校验用户填写的部门是否为空
		$("#formId").validate({
			rules:{
				deptName:{
					/* 必须填写，以及长度 */
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
	//表单提交校验
	function tosubmit(){
		//执行表单验证
		if($("#formId").valid())
			//表单提交
			formSubmit('deptAction_insert','_self');
		else{
		return false;
		}
	}
	
</script>
</head>

<body>
<form name="icform" method="post" id="formId" > 

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<!--onclick="formSubmit('deptAction_insert','_self');this.blur();"  -->
										
<li id="save">
<a href="#"  onclick="javascript:tosubmit()">保存</a></li> 

<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">上级部门：
	            
	         
	            </td>
	            <td class="tableContent">
	            	<s:select name="parent.id" list="#request.deptList" headerKey="" headerValue="--请选择--" listKey="id" listValue="deptName"></s:select>
	  
	             </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">部门名称：</td>
	            <td class="tableContent"><input  type="text" name="deptName" /></td>
	        </tr>	
	         <tr>
	           <td class="columnTitle">管理领导：</td>
	            <td class="tableContent">
	            	   <s:select name="manager.userId" list="#request.userList"
	            		listKey="id" listValue="userInfo.name"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>   
	            </td>
	        </tr>	
		</table>
	</div>
 </form>
</body>
</html>