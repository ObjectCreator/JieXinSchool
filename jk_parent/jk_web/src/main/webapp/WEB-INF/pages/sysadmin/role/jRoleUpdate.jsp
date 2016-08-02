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
				remark:{
					required:true
				}
			},messages:{
				name:{
					required:"角色名称必须填写 "
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
			formSubmit("roleAction_update","_self");
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
<li id="save"><!-- <a href="#" onclick="formSubmit('roleAction_update','_self');this.blur();">保存</a> -->
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
   修改角色
       <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">名称：</td>
	            <td class="tableContent"><input type="text" name="name" value="${name}"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	        </tr>		
		</table>
	</div>
 
 
</form>
</body>
</html>

