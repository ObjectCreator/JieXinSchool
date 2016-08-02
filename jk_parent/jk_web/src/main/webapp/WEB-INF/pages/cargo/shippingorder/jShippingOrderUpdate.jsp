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
						orderType:{
							required:true
							
						},
						shipper:{
							required:true
						},
						consignee:{
							required:true
						},
						
						notifyParty:{
							required:true
						},
						lcNo:{
							required:true
						},
						portOfLoading:{
							required:true
						},
						portOfDischarge:{
							required:true
						},
						loadingDate:{
							required:true
						},
						limitDate:{
							required:true
						},
						isBatch:{
							required:true
						},
					
						isTrans:{
							required:true
						},
						
						specialCondition:{
							required:true
						},
						
						clId:{
							required:true
						},
						
						freight:{
							required:true
						},
						checkBy:{
							required:true
						}
						
						
						
					},
					messages:{
						orderType:{
							required:"运输方式必须选择 "
							
						},
						shipper:{
							required:"货主必须填写 "
						},
						consignee:{
							required:"提单抬头必须填写 "
						},
						notifyParty:{
							required:"正本通知必须填写"
						},
						lcNo:{
							required:"信用证号必须填写 "
						},
						portOfLoading:{
							required:"装运港必须填写 "
						},
						portOfDischarge:{
							required:"卸货港必须填写 "
						},
						loadingDate:{
							required:"装期必须填写 "
						},
						limitDate:{
							required:"效期必须填写 "
						},
						isBatch:{
							required:"是否分期选择 "
						},
					
						isTrans:{
							required:"是否分批选择 "
						},
						
						specialCondition:{
							required:"运输要求必须填写 "
						},
						
						clId:{
							required:"装箱单必须选择 "
						},
						
						freight:{
							required:"运费说明必须填写 "
						},
						checkBy:{
							required:"复核人必须填写 "
						}
					},
					errorLabelContainer: "#errorId"
				});
			});
		
			function tosave(){
			
				if($("#formId").valid()){
					formSubmit('shippingOrderAction_update','_self');
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

<!-- <a href="#" onclick="formSubmit('shippingOrderAction_update','_self');this.blur();">保存</a> -->
 <a href="#" onclick="javascript:tosave()">保存</a>
</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改委托单
    <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  
    <div>
		<table class="commonTable" cellspacing="1">
	       	
	        <tr>
	            <td class="columnTitle">海运/空运：</td>
	            <td class="tableContentAuto">
	            <input type="radio" name="orderType" value="海运" checked class="input" ${orderType=='海运'?'checked':'' }/>SEA
	            	<input type="radio" name="orderType" value="空运" class="input" ${orderType=='空运'?'checked':'' }/>AIR
	            
	            
	            </td>
	        
	            <td class="columnTitle">货主：</td>
	            <td class="tableContent"><input type="text" name="shipper" value="${shipper}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">提单抬头：</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${consignee}"/></td>
	        
	            <td class="columnTitle">正本通知人：</td>
	            <td class="tableContent"><input type="text" name="notifyParty" value="${notifyParty}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">信用证：</td>
	            <td class="tableContent"><input type="text" name="lcNo" value="${lcNo}"/></td>
	        
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent"><input type="text" name="portOfLoading" value="${portOfLoading}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">转船港：</td>
	            <td class="tableContent"><input type="text" name="portOfTrans" value="${portOfTrans}"/></td>
	       
	            <td class="columnTitle">卸货港：</td>
	            <td class="tableContent"><input type="text" name="portOfDischarge" value="${portOfDischarge}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装期：</td>
	            <td class="tableContent">
	            	<%-- <input type="text" name="loadingDate" value="${loadingDate}"/> --%>
	            	<input type="text" style="width:90px;" name="loadingDate"
	            	 value="<fmt:formatDate value='${loadingDate }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	       
	            <td class="columnTitle">效期：</td>
	            <td class="tableContent">
	            	<%-- <input type="text" name="limitDate" value="${limitDate}"/> --%>
	            	<input type="text" style="width:90px;" name="limitDate"
	            	 value="<fmt:formatDate value='${limitDate }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">是否分批 ：</td>
	            <td class="tableContentAuto">
	            	<%-- <input type="text" name="isBatch" value="${isBatch}"/> --%>
	            	<input type="radio" name="isBatch" value="1" ${isBatch=='1'?"checked":"" }  class="input">是
	            	<input type="radio" name="isBatch" value="0" ${isBatch=='0'?"checked":"" } class="input">否
	            </td>
	        
	            <td class="columnTitle">是否转船：</td>
	            <td class="tableContentAuto">
	            	<%-- <input type="text" name="isTrans" value="${isTrans}"/> --%>
	            	<input type="radio" name="isTrans" value="1" ${isTrans=='1'?"checked":"" }  class="input">是
	            	<input type="radio" name="isTrans" value="0" ${isTrans=='0'?"checked":"" } class="input">否
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">份数：</td>
	            <td class="tableContent"><input type="text" name="copyNum" value="${copyNum}"/></td>
	       
	            <td class="columnTitle">扼要说明：</td>
	            <td class="tableContent">
	            	<%-- <input type="text" name="remark" value="${remark}"/> --%>
	            	<textarea name="remark" style="height:120px;">${remark}</textarea>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">运输要求：</td>
	            <td class="tableContent">
	           		 <%-- <input type="text" name="specialCondition" value="${specialCondition}"/> --%>
	           		 <textarea name="specialCondition" style="height:120px;">${specialCondition}</textarea>
	            </td>
	        
	            <td class="columnTitle">运费说明：</td>
	            <td class="tableContent">
	           		 <%-- <input type="text" name="freight" value="${freight}"/> --%>
	           		 <textarea name="freight" style="height:120px;">${freight}</textarea>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">复核人：</td>
	            <td class="tableContent"><input type="text" name="checkBy" value="${checkBy}"/></td>
	        </tr>	
	        
		</table>
	</div>
 
 
</form>
</body>
</html>

