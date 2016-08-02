<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">

	$(function(){
	
		$("#formId").validate({
			
			rules:{
				inputBy:{
					required:true
				},
				title:{
					required:true

				},
				content:{
					required:true
				}
			},
			messages:{
				inputBy:{
					required:"反馈人必须填写  "
				},
				title:{
					required:"标题必须填写  "

				},
				content:{
					required:"内容必须填写  "
				}
			}, 
			errorLabelContainer: "#errorId"
		});
	});
	
	function tosave(){
	
		if($("#formId").valid()){
			
			formSubmit('feedbackAction_insert','_self');
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
<!-- <a href="#" onclick="formSubmit('feedbackAction_insert','_self');this.blur();">保存</a> -->
<a href="#" onclick="javascript:tosave()">保存</a>
</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
反馈意见
   <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	          
	            <td class="columnTitle">反馈人：</td>
	            <td class="tableContent"><input type="text" name="inputBy" value=""/></td>  
	        
	            <td class="columnTitle">标题：</td>
	            <td class="tableContent"><input type="text" name="title" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">联系电话：</td>
	            <td class="tableContent"><input type="text" name="tel" value=""/></td>
	            <td class="columnTitle">反馈类型：</td>
	            <td class="tableContent">
	            
	          
	             <s:select name="classType" list="typeList" 
	            	
	            		listKey="typeName" listValue="typeName"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select> 
	            </td>
	        </tr>	
	        <tr>
	       
	      		
	            <td class="columnTitle">反馈内容：</td>
	            <td class="" >
	            <textarea  name="content" style="height: 100px"></textarea></td>   
	             <td class="columnTitle">是否公开：</td>
	            <td class="tableContentAuto">
	            <input type="radio" name="isShare" value="1" checked class="input"/>公开
	            <input type="radio" name="isShare" value=" " class="input"/>不公开</td>
	             
	        </tr>	
	   	 <tr>
	           
	      		
	             
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

