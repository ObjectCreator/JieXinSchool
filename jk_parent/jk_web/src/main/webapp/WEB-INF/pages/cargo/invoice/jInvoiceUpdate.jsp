<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		
			$(function(){
				
				$("#formId").validate({
					rules:{
						
						scNo:{
							required:true
						},
						blNo:{
							required:true
						},
						tradeTerms:{
							required:true
						}
						
					},
					messages:{
						
						scNo:{
							required:"发票号必须填写 "
						},
						blNo:{
							required:"提单号必须填写 "
						},
						tradeTerms:{
							required:"贸易条款必须填写 "
						}
					},
					errorLabelContainer: "#errorId"
				});
			});
		
			function tosave(){
			
				if($("#formId").valid()){
					formSubmit('invoiceAction_update','_self');
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

<!-- <a href="#" onclick="formSubmit('invoiceAction_update','_self');this.blur();">保存</a>
 -->
  <a href="#" onclick="javascript:tosave()">保存</a>
</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx}/skin/default/images/icon/currency_yen.png"/>
   修改发票
    <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="scNo" value="${scNo}"/></td>
	        
	            <td class="columnTitle">提单号：</td>
	            <td class="tableContent"><input type="text" name="blNo" value="${blNo}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">贸易条款:</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value="${tradeTerms}"/></td>
	        
	            <td class="columnTitle">状态:</td>
	            <td class="tableContent">
	            	<!-- 草稿<input type="radio" name="state" value="0"/>
	            	已上报<input type="radio" name="state" value="1" /> -->
	            </td>
	        </tr>	
	       
		</table>
	</div>
	
 
</form>
</body>
</html>

