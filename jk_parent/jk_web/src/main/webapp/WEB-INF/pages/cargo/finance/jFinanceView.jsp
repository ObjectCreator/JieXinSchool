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
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   浏览部门
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">制单日期：</td>
	            <td class="tableContent">${inputDate}</td>
	            <td class="columnTitle">制单人：</td>
	            <td class="tableContent">${inputBy}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">${state==0?"停用":"启用"}</td>
	            <td class="columnTitle">创建人：</td>
	            <td class="tableContent">${createBy}</td>
	        </tr>	
		</table>
	</div>
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    发票列表
  </div> 


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">SC_NO</td>
		<td class="tableHeader">BL_NO</td>
		<td class="tableHeader">贸易条款</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">创建人</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<tr bgcolor="#c3f3c3" height="30" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td>1</td>
		<td>${invoice.scNo}</td>
		<td>${invoice.blNo}</td>
		<td>${invoice.tradeTerms}</td>
		<td>${invoice.state==0?"停用":"启用"}</td>
		<td>${invoice.createBy}</td>
	</tr>
	
	</tbody>
</table>
</div>
 
</form>
</body>
</html>

