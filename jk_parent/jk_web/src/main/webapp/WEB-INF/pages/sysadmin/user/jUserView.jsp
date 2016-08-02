<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
      <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('deptAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   查看用户
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	       	
	        
	        <tr>
	            <td class="columnTitle">所在部门：</td>
          
           <td class="tableContent">
           	${dept.deptName }
           </td>
            <td class="columnTitle">直属领导：</td>
	            <td class="tableContent">
	            	  ${userInfo.manager.userInfo.name }
	            </td>
           
	        </tr>
        	<tr>
	            <td class="columnTitle">登录名：</td>
	            <td class="tableContent">${userName }</td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${state==1}">
	            	启用
	            	</c:if>
	            	<c:if test="${state==0}">
	            	停用
	            	</c:if>
	           
	            </td>
	        </tr>
        	<tr>
	            <td class="columnTitle">姓名：</td>
	            <td class="tableContent">${userInfo.name }"</td>
	            
	        </tr>		
	        <tr>
	            <td class="columnTitle">入职时间：</td>
	            <td class="tableContent">
					
	            	  <fmt:formatDate value='${userInfo.joinDate }' pattern='yyyy-MM-dd' />
	             	
				</td>
				<td class="columnTitle">薪水：</td>
	            <td class="tableContent">${userInfo.salary }</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">等级：</td>
	            <td class="tableContentAuto">
	            	
	           		<c:if test="${userInfo.degree==0}">
	            	超级管理员
	            	</c:if>
	          		 <c:if test="${userInfo.degree==1}">
	            	跨部门跨人员
	            	</c:if>
	            	<c:if test="${userInfo.degree==2}">
	            	管理所有下属部门和人员
	            	</c:if>
	            	<c:if test="${userInfo.degree==3}">
	            	管理本部门
	            	</c:if>
	            	<c:if test="${userInfo.degree==4}">
	            	普通员工
	            	</c:if>
	            	
	           
	            </td>
				<td class="columnTitle">性别：</td>
	            <td class="tableContentAuto">
	            <c:if test="${userInfo.gender==1}">
	            	男
	            	</c:if>
	            	<c:if test="${userInfo.gender==1}">
	            	女
	            	</c:if>
	            </td>
	        </tr>	
        	<tr>
	            <td class="columnTitle">岗位：</td>
	            <td class="tableContent">${userInfo.station }</td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent">${userInfo.telephone }</td>
	        </tr>	
        	<tr>
        	    <td class="columnTitle">邮箱：</td>
	            <td class="tableContent">${userInfo.email }</td>
	            <td class="columnTitle">出生年月：</td>
	            <td class="tableContent">
					
	            	<fmt:formatDate value='${userInfo.birthday }' pattern='yyyy-MM-dd' />
	             	
	           
				</td>
	        </tr>	
        	<tr>
        	    <td class="columnTitle">排序号：</td>
	            <td class="tableContent">${userInfo.orderNo }</td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
	            	<textarea name="userInfo.remark" style="height:120px;" readonly="readonly">${userInfo.remark }</textarea>
	            </td>
	        </tr>	
		</table>
	</div>
 </form>
</body>
</html>