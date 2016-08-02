<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
查看反馈意见
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent">${inputBy}</td>
	       
	            <td class="columnTitle">提出时间：</td>   
	            <td class="tableContent">
	            ${inputTime}
	          <%--   <fmt:formatDate value="${inputTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	             --%>
	            </td>
	        </tr>	
	        <tr>
	           
	            <td class="columnTitle">标题：</td>
	            <td class="tableContent">${title}</td>
	             <td class="columnTitle">分类：</td>
	            <td class="tableContent">${classType}</td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">联系电话：</td>
	            <td class="tableContent">${tel}</td>
	            <td class="columnTitle">解决人：</td>
	            <td class="tableContent">${answerName}</td>
	       </tr>	
	        <tr>
	            <td class="columnTitle">解决时间：</td>
	             <td class="tableContent">
	            <fmt:formatDate value="${answerTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
	           
	            </td>
	     
	            <td class="columnTitle">解决方式：</td>
	            <td class="tableContent">${resolution}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">解决难度：</td>
	            <td class="tableContent">
	          
	            <c:if test="${difficulty==0}">
	            	简单
	            </c:if>
	             <c:if test="${difficulty==1}">
	            	中等
	            </c:if>
	             <c:if test="${difficulty==2}">
	            	困难
	            </c:if>
	            </td>
	      
	             <td class="columnTitle">状态：</td>
	            <td class="tableContent">
	             <c:if test="${state==0}">
	            	未解决
	            </c:if>
	            <c:if test="${state==1}">
	            	已解决
	            </c:if>
	            </td>
	        </tr>	
	        
	        <tr>
	        	   <td class="columnTitle">反馈内容：</td>
	            <td class="tableContent">
	              <textarea  name="content" style="height: 100px"
	              readonly="readonly">${content}</textarea>
	            </td>
	            <td class="columnTitle">解决方案：</td>
	            <td class="tableContent">
	              <textarea  name="content" style="height: 100px"
	              readonly="readonly">${solveMethod}</textarea>
	            </td>
	       
	     
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

