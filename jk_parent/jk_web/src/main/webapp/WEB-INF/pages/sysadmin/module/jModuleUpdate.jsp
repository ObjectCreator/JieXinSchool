<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		$(function(){
			$("#formId").validate({
				rules:{
					name:{
						required:true
					},
					layerNum:{
						required:true,
						number:true
					},
					cpermission:{
						required:true
					},
					curl:{
						required:true
					},
					ctype:{
						required:true
					},
					state:{
						required:true
					},
					cwhich:{
						required:true
					},
					orderNo:{
						required:true,
						number:true
					},
					remark:{
						required:true
					}	
				},
				messages:{
					name:{
						required:"模块名必须填写 "
					},
					layerNum:{
						required:"层数必须填写 ",
						number:"层数必须为数字 "
					},
					cpermission:{
						required:"权限标识必须填写 "
					},
					curl:{
						required:"链接必须填写 "
					},
					ctype:{
						required:"类型必须选择 "
					},
					state:{
						required:"状态必须选择 "
					},
					cwhich:{
						required:"复用标识必须填写 "
					},
					orderNo:{
						required:"排序号必须选择 ",
						number:"排序号必须为数字 "
					},
					remark:{
						required:"说明必须填写 "
					}	
				}, 
				errorLabelContainer: "#errorId"
			});
		});
		
		function tosave(){
			if($("#formId").valid()){
				formSubmit('${ctx }/sysadmin/moduleAction_update','_self');
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
<li id="save">
<!-- <a href="#" onclick="formSubmit('moduleAction_update','_self');this.blur();">保存</a> -->
<a href="#" onclick="javascript:tosave()">保存</a>
</li>
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
   修改模块
     <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">模块名：</td>
	            <td class="tableContent"><input type="text" name="name" value="${name}"/></td>
	            <td class="columnTitle">层数：</td>
	            <td class="tableContent"><input type="text" name="layerNum" value="${layerNum}"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">权限标识：</td>
	            <td class="tableContent"><input type="text" name="cpermission" value="${cpermission}"/></td>
	            <td class="columnTitle">链接：</td>
	            <td class="tableContent"><input type="text" name="curl" value="${curl}"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">类型：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="ctype" value="0" <c:if test="${ctype==0}">checked</c:if> class="input"/>主菜单
	            	<input type="radio" name="ctype" value="1" <c:if test="${ctype==1}">checked</c:if> class="input"/>左侧菜单
	            	<input type="radio" name="ctype" value="2" <c:if test="${ctype==2}">checked</c:if> class="input"/>按钮
	            	<input type="radio" name="ctype" value="3" <c:if test="${ctype==3}">checked</c:if> class="input"/>链接
	            	<input type="radio" name="ctype" value="4" <c:if test="${ctype==4}">checked</c:if> class="input"/>状态
	            </td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" <c:if test="${state==1}">checked</c:if> class="input"/>启用
	            	<input type="radio" name="state" value="0" <c:if test="${state==0}">checked</c:if> class="input"/>停用
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">父模块：</td>
	            <td class="tableContent"><%-- <input type="text" name="belong" value="${parentName}"/></td> --%>
	            
	            <s:select name="parentId" list="#request.moduleList" headerKey="" headerValue="--请选择--" listKey="id" listValue="name"></s:select>
	   

	            <td class="columnTitle">复用标识：</td>
	            <td class="tableContent"><input type="text" name="cwhich" value="${cwhich}"/></td>
	        </tr>			
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="remark" style="height:120px;">${remark}</textarea>
	            </td>
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value="${orderNo}"/></td>
	        </tr>			
		</table>
	</div>
 
 
</form>
</body>
</html>

