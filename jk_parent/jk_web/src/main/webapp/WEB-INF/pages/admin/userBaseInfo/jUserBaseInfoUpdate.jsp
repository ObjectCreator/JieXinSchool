<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
<li id="save"><a href="#" onclick="formSubmit('userBaseInfoAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
个人基本信息修改
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	           
	            <td class="columnTitle">昵称：</td>
	            <td class="tableContent"><input type="text" name="nickName" value="${nickName}"/></td>
	        	  <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="tel" value="${tel}"/></td>
	       
	        
	        </tr>	
	        <tr>
	          
	            <td class="columnTitle">民族：</td>
	            <td class="tableContent"><input type="text" name="nation" value="${nation}"/></td>
	          <td class="columnTitle">家庭住址：</td>
	            <td class="tableContent"><input type="text" name="homeAddress" value="${homeAddress}"/></td>
	       
	        </tr>	
	        <tr>
	         
	            <td class="columnTitle">政治面貌：</td>
	            <td class="tableContent"><input type="text" name="politicsStatus" value="${politicsStatus}"/></td>
	         <td class="columnTitle">个人特长：</td>
	            <td class="tableContent"><input type="text" name="speciality" value="${speciality}"/></td>
	        
	        </tr>	
	        
	        
		</table>
	</div>
 
 
</form>
</body>
</html>

