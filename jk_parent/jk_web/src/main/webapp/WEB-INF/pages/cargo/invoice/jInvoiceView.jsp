<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx}/skin/default/images/icon/currency_yen.png"/>
   浏览发票
  </div>
  

    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent">${scNo}</td>
	        
	            <td class="columnTitle">提单号：</td>
	            <td class="tableContent">${blNo}</td>
	        </tr>	
	        <tr>
	        
	        	<td class="columnTitle">贸易条款：</td>
	            <td class="tableContent">${tradeTerms}</td>
	            
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">
	           		<c:if test="${state==0}">草稿</c:if>
					<c:if test="${state==1}">已上报</c:if>
					<c:if test="${state==2}">已财务报运</c:if>
	            </td>
	            
	        </tr>	
	        
		</table>
	</div>
 
</form>
		
	<div class="textbox-title">
		<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    	委托单列表
  	</div> 
	
	<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
	
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
	
		<tr bgcolor="#c3f3c3" height="30" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td>${1}</td>
			<td>${shippingOrder.orderType}</td>
			<td>${shippingOrder.shipper}</td>
			<td>${shippingOrder.consignee}</td>
			
			<td>${shippingOrder.notifyParty}</td>
			<td>${shippingOrder.lcNo}</td>
			<td>${shippingOrder.portOfLoading}</td>
			
			<td>${shippingOrder.portOfTrans}</td>
			<td>${shippingOrder.portOfDischarge}</td>
			<td>${shippingOrder.loadingDate}</td>
			<td>${shippingOrder.limitDate}</td>
			
			<td> 
				<c:if test="${shippingOrder.isBatch==1}">是</c:if> 
				<c:if test="${shippingOrder.isBatch==0}">否</c:if> 
			</td>
			<td> 
				<c:if test="${shippingOrder.isTrans==1}">是</c:if> 
				<c:if test="${shippingOrder.isTrans==0}">否</c:if> 
			</td>
			<td>${shippingOrder.copyNum}</td>
			
			<td>${shippingOrder.remark}</td>
			<td>${shippingOrder.specialCondition}</td>
			<td>${shippingOrder.freight}</td>
			
			<td>${shippingOrder.checkBy}</td>
			<td>${shippingOrder.state}</td>
		
		</tr>
	
	
	</tbody>
	
</table>

</body>
</html>

