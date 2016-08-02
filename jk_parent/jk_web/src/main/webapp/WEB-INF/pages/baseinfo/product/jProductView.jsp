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
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
产品详情
  </div>
  
<div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">商品ID：</td>
	            <td class="tableContent">${id}</td>
	            <td class="columnTitle">编号：</td>
	            <td class="tableContent">${productNo}</td>
	              <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" checked class="input"/>启用
	            	<input type="radio" name="state" value="0" class="input"/>停用
	            </td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">照片：</td>
	            <td class="tableContent">${productImage}</td>
	           
	            <td class="columnTitle">市场价</td>
	            <td class="tableContent">${price}</td>
	            
	          
	        </tr>	
	        <tr>
	            <td class="columnTitle">工厂ID：</td>
	            <td class="tableContent">${factoryId}</td>
	            <td class="columnTitle">工厂名称：</td>
	            <td class="tableContent">${factoryName}</td>
	        </tr>		
	        <tr>
	        	<td class="columnTitle">长：</td>
	            <td class="tableContent">${sizeLenght}</td>
	            <td class="columnTitle">宽：</td>
	            <td class="tableContent">${sizeWidth}</td>
	            <td class="columnTitle">高：</td>
	            <td class="tableContent">${sizeHeight}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent">${color}</td>
	            <td class="columnTitle">包装：</td>
	            <td class="tableContent">${packing}</td>
	        </tr>	
	      	
	        <tr>
	        	<td class="columnTitle">Type20：</td>
	            <td class="tableContent">${type20}</td>
	            <td class="columnTitle">Type40：</td>
	            <td class="tableContent">${type40}</td>
	            <td class="columnTitle">Type40hc：</td>
	            <td class="tableContent">${type40hc}</td>
	        </tr>	
	        <tr>
	        	<td class="columnTitle">单位：</td>
	            <td class="tableContent">${packingUnit}</td>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContent">${qty}</td>
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent">${cbm}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">长：</td>
	            <td class="tableContent">${mpsizeLenght}</td>
	            <td class="columnTitle">宽：</td>
	            <td class="tableContent">${mpsizeWidth}</td>
	            <td class="columnTitle">高：</td>
	            <td class="tableContent">${mpsizeHeight}</td>
	                    
	        </tr>	
	        <tr> 
	        	<td class="columnTitle">描述：</td>
	            <td class="tableContent">${description}</td>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent">${remark}</td>  
	        </tr>
				<tr>
					<td class="columnTitle">创建人：</td>
					<td class="tableContent">${createBy}</td>
					<td class="columnTitle">创建部门：</td>
					<td class="tableContent">${createDept}</td>
					<%--  <td class="columnTitle">所在部门：</td>
	            <td class="tableContent">
	            	<s:select name="dept.id" list="#request.deptList"
	            		listKey="id" listValue="deptName"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>
	            </td> --%>
					<%-- <td class="columnTitle">创建时间：</td>
					<td class="tableContent">${createTime}</td> --%>
				</tr>
			</table>
	</div>
 
</form>
</body>
</html>

