<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		
			$(function(){
				
				$("#formId").validate({
					rules:{
						id:{
							required:true
							
						},
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
						id:{
							required:"必须选择委托单 "
							
						},
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
					formSubmit('invoiceAction_insert','_self');
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

<!-- <a href="#" onclick="formSubmit('invoiceAction_insert','_self');this.blur();">保存</a>
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
   新增发票
   <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发票号:</td>
	            <td class="tableContent"><input type="text" name="scNo" value=""/></td>
	        
	            <td class="columnTitle">提单号：</td>
	            <td class="tableContent"><input type="text" name="blNo" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">贸易条款:</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value=""/></td>
	        
	            <td class="columnTitle"></td>
	            <td class="tableContent"></td>
	        </tr>	
	        
		</table>
	</div>
 
<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
	<td class="tableHeader"><input type="radio" name="" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">运输方式</td>
		<td class="tableHeader">货主</td>
		<td class="tableHeader">提单抬头</td>
		
		<td class="tableHeader">正本通知人</td>
		<td class="tableHeader">信用证</td>
		<td class="tableHeader">装运港</td>
		
		<td class="tableHeader">转船港</td>
		<td class="tableHeader">卸货港</td>
		<td class="tableHeader">装期</td>
		
		<td class="tableHeader">效期</td>
		<td class="tableHeader">是否分批</td>
		<td class="tableHeader">是否转船</td>
		
		<td class="tableHeader">份数</td>
		<td class="tableHeader">扼要说明</td>
		<td class="tableHeader">运输要求</td>
		
		<td class="tableHeader">运费说明</td>
		<td class="tableHeader">复核人</td>
		<td class="tableHeader">状态</td>
		
	</tr>
	</thead>
	<tbody class="tableBody" >

	
	<c:forEach items="${shippingOrderList}" var="so" varStatus="status">
		 
		<tr bgcolor="#c3f3c3" height="30" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td><input type="radio" name="id" value="${so.id}"/></td>
			<td>${status.index+1}</td>
			<td>${so.orderType}</td>
			<td>${so.shipper}</td>
			<td>${so.consignee}</td>
			
			<td>${so.notifyParty}</td>
			<td>${so.lcNo}</td>
			<td>${so.portOfLoading}</td>
			
			<td>${so.portOfTrans}</td>
			<td>${so.portOfDischarge}</td>
			<td>${so.loadingDate}</td>
			<td>${so.limitDate}</td>
			
			<td> 
				<c:if test="${so.isBatch==1}">是</c:if> 
				<c:if test="${so.isBatch==0}">否</c:if> 
			</td>
			<td> 
				<c:if test="${so.isTrans==1}">是</c:if> 
				<c:if test="${so.isTrans==0}">否</c:if> 
			</td>
			<td>${so.copyNum}</td>
			
			<td>${so.remark}</td>
			<td>${so.specialCondition}</td>
			<td>${so.freight}</td>
			<td>${so.checkBy}</td>
			<td>
				<c:if test="${so.state==1}">已委托，未出发票</c:if>
		
			</td>
		</tr>
	</c:forEach>
	
	</tbody>
</table>
</div> 
 
</form>
</body>
</html>

