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
				formSubmit('${ctx }/sysadmin/moduleAction_save','_self');
			}else{
				return false;
			}
		}
	</script>
</head>

<body>
<form name="icform" method="post" id="formId">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">
<!-- <a href="#" onclick="formSubmit('moduleAction_save','_self');this.blur();">保存</a> -->
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
   新增模块
       <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">模块名：</td>
	            <td class="tableContent"><input type="text" name="name" value=""/></td>
	            <td class="columnTitle">层数：</td>
	            <td class="tableContent"><input type="text" name="layerNum" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">权限标识：</td>
	            <td class="tableContent"><input type="text" name="cpermission" value=""/></td>
	            <td class="columnTitle">链接：</td>
	            <td class="tableContent"><input type="text" name="curl" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">类型：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="ctype" value="0" class="input"/>主菜单
	            	<input type="radio" name="ctype" value="1" class="input"/>左侧菜单
	            	<input type="radio" name="ctype" value="2" class="input"/>按钮
	            	<input type="radio" name="ctype" value="3" class="input"/>链接
	            	<input type="radio" name="ctype" value="4" class="input"/>状态
	            </td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" checked class="input"/>启用
	            	<input type="radio" name="state" value="0" class="input"/>停用
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">父模块：</td>
	            <td class="tableContent"><!-- <input                                 ="text" name="belong" value=""/></td> -->
	            	<!--回显实现是，当栈顶对象的parentId的属性域listKeyId的值一致时进行回显  -->
	             	<s:select name="parentId" list="#request.moduleList" headerKey="" headerValue="--请选择--" listKey="id" listValue="name"></s:select>
					
	            <td class="columnTitle">复用标识：</td>
	            <td class="tableContent"><input type="text" name="cwhich" value=""/></td>
	        </tr>			
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="remark" style="height:120px;"></textarea>
	            </td>
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value=""/></td>
	        </tr>			
		</table>
	</div>
 
 
</form>
</body>
</html>

