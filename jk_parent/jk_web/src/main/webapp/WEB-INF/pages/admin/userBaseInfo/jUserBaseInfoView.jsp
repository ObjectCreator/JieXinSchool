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
   浏览个人全部信息<!-- 分为两部分一部分是用户信息，User表中数据，一种是个人基本信息 -->
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	           
	        
	            <td class="columnTitle">昵称：</td>
	            <td class="tableContent">${nickName}</td>
	       
	            <td class="columnTitle">民族：</td>
	            <td class="tableContent">${nation}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">家庭住址：</td>
	            <td class="tableContent">${homeAddress}</td>
	     
	            <td class="columnTitle">政治面貌：</td>
	            <td class="tableContent">${politicsStatus}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">个人特长：</td>
	            <td class="tableContent">${speciality}</td>
	      
	            <td class="columnTitle">姓名：</td>
	            <td class="tableContent">${user.userInfo.name }</td>
	           
	        </tr>	
	        
	          <tr>
	            <td class="columnTitle">所在部门：</td>
          
           <td class="tableContent">
           	${user.dept.deptName }
           </td>
            <td class="columnTitle">直属领导：</td>
	            <td class="tableContent">
	            	  ${user.userInfo.manager.userInfo.name }
	            </td>
           
	        </tr>
        	<tr>
	            <td class="columnTitle">登录名：</td>
	            <td class="tableContent">${user.userName }</td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${user.state==1}">
	            	启用
	            	</c:if>
	            	<c:if test="${user.state==0}">
	            	停用
	            	</c:if>
	           
	            </td>
	        </tr>
        		
	        <tr>
	            <td class="columnTitle">入职时间：</td>
	            <td class="tableContent">
					
	            	  <fmt:formatDate value='${user.userInfo.joinDate }' pattern='yyyy-MM-dd' />
	             	
				</td>
				<td class="columnTitle">薪水：</td>
	            <td class="tableContent">${user.userInfo.salary }</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">等级：</td>
	            <td class="tableContentAuto">
	            	
	           		<c:if test="${user.userInfo.degree==0}">
	            	超级管理员
	            	</c:if>
	          		 <c:if test="${user.userInfo.degree==1}">
	            	跨部门跨人员
	            	</c:if>
	            	<c:if test="${user.userInfo.degree==2}">
	            	管理所有下属部门和人员
	            	</c:if>
	            	<c:if test="${user.userInfo.degree==3}">
	            	管理本部门
	            	</c:if>
	            	<c:if test="${user.userInfo.degree==4}">
	            	普通员工
	            	</c:if>
	            	
	           
	            </td>
				<td class="columnTitle">性别：</td>
	            <td class="tableContentAuto">
	            <c:if test="${user.userInfo.gender==1}">
	            	男
	            	</c:if>
	            	<c:if test="${user.userInfo.gender==0}">
	            	女
	            	</c:if>
	            </td>
	        </tr>	
        	<tr>
	            <td class="columnTitle">岗位：</td>
	            <td class="tableContent">${user.userInfo.station }</td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent">${user.userInfo.telephone }</td>
	        </tr>	
        	<tr>
        	    <td class="columnTitle">邮箱：</td>
	            <td class="tableContent">${user.userInfo.email }</td>
	            <td class="columnTitle">出生年月：</td>
	            <td class="tableContent">
					
	            	<fmt:formatDate value='${user.userInfo.birthday }' pattern='yyyy-MM-dd' />
	             	
	           
				</td>
	        </tr>	
        	<tr>
        	    <td class="columnTitle">排序号：</td>
	            <td class="tableContent">${user.userInfo.orderNo }</td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="userInfo.remark" style="height:120px;" readonly="readonly">${user.userInfo.remark }</textarea>
	            </td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

