<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
	<script type="text/javascript">
		$(function(){
	
			$("#formId").validate({
				
				rules:{
					customName:{
						required:true
					},
					printStyle:{
						required:true

					},
					contractNo:{
						required:true
					},
					offeror:{
						required:true
					},
					inputBy:{
						required:true
					},
					checkBy:{
						required:true
					},
					inspector:{
						required:true
					},
					signingDate:{
						required:true
					},
					importNum:{
						required:true
	
					},
					shipTime:{
						required:true
					
					},
					tradeTerms:{
						required:true
					
					},
					deliveryPeriod:{
						required:true
					
					},
					crequest:{
						required:true
					
					},
					remark:{
						required:true
					}	
				},
				messages:{
					customName:{
						required:"客户名称必须填写 "
					},
					printStyle:{
						required:"打印版式必须填写 "

					},
					contractNo:{
						required:"合同号必须填写 "
					},
					offeror:{
						required:"收购方必须填写 "
					},
					inputBy:{
						required:"制单人必须填写 "
					},
					checkBy:{
						required:"审单人必须填写 "
					},
					inspector:{
						required:"验货员必须填写 "
					},
					signingDate:{
						required:"签单日期必须选择 "
					},
					importNum:{
						required:"重要程度必须选择 "
	
					},
					shipTime:{
						required:"船期必须选择 "
					
					},
					tradeTerms:{
						required:"贸易条款必须填写 "
					
					},
					deliveryPeriod:{
						required:"交期必须填写  "
				
					},
					crequest:{
						required:"要求必须填写 "
					
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
				
				formSubmit('contractAction_update','_self');
			}else{
				return false;
			}
		}
	</script>
	
</head>

<body>
<form name="icform" method="post" id="formId">
     <input type="hidden" name="id" value="${id }">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">
<!-- <a href="#" onclick="formSubmit('contractAction_update','_self');this.blur();">保存</a> -->
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
   更新购销合同
      <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">客户名称：</td>
	            <td class="tableContent"><input type="text" name="customName" value="${customName }"/></td>
	            <td class="columnTitle">打印版式：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="printStyle" value="2" ${printStyle=='2'?"checked":"" }  class="input">两款
	            	<input type="radio" name="printStyle" value="1" ${printStyle=='1'?"checked":"" } class="input">一款
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">合同号：</td>
	            <td class="tableContent"><input type="text" name="contractNo" value="${contractNo }"/></td>
	            <td class="columnTitle">收购方：</td>
	            <td class="tableContent"><input type="text" name="offeror" value="${offeror }"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">制单人：</td>
	            <td class="tableContent"><input type="text" name="inputBy" value="${inputBy }"/></td>
	            <td class="columnTitle">审单人：</td>
	            <td class="tableContent"><input type="text" name="checkBy" value="${checkBy }"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent"><input type="text" name="inspector" value="${inspector }"/></td>
	            <td class="columnTitle">签单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="signingDate"
	            	 value="<fmt:formatDate value='${signingDate }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">重要程度：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="importNum" value="3" ${importNum==3?"checked":"" } class="input">★★★
	            	<input type="radio" name="importNum" value="2" ${importNum==2?"checked":"" } class="input">★★
	            	<input type="radio" name="importNum" value="1" ${importNum==1?"checked":"" } class="input">★
	            </td>
	            <td class="columnTitle">船期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="shipTime"
	            	 value="<fmt:formatDate value='${shipTime }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value="${tradeTerms }"/></td>
	            <td class="columnTitle">交货期限：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="deliveryPeriod"
	            	 value="<fmt:formatDate value='${deliveryPeriod }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">要求：</td>
	            <td class="tableContent"><textarea name="crequest" style="height:150px;">${crequest }</textarea>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><textarea name="remark" style="height:150px;">${remark }</textarea>
	        </tr>		
		</table>
	</div>
 
 
</form>
</body>
</html>

