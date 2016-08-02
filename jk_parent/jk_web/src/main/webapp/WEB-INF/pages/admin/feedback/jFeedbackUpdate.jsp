<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--  日历控件-->
<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<title></title>
		<script type="text/javascript">
		//动态设置解决人姓名的名称 
		function setAnswerName(val){
			
			document.getElementById("answerName").value = $("#selectUser").find("option:selected").text();
		}
	</script>
	
	<script type="text/javascript">

	$(function(){
	
		$("#formId").validate({
			
			rules:{
				answerBy:{
					required:true
				},
				resolution:{
					required:true

				},
				answerTime:{
					required:true
				},
				solveMethod:{
					required:true
				}
			},
			messages:{
				answerBy:{
					required:"解决必须选择  "
				},
				resolution:{
					required:"解决方式必须填写  "

				},
				answerTime:{
					required:"解决时间必须选择 "
				},
				solveMethod:{
					required:"解决方案必须填写  "
				}
			}, 
			errorLabelContainer: "#errorId"
		});
	});
	
	function tosave(){
	
		if($("#formId").valid()){
			
			formSubmit('feedbackAction_update','_self');
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

<!-- <a href="#" onclick="formSubmit('feedbackAction_update','_self');this.blur();">保存</a> -->
<a href="#" onclick="javascript:tosave()">保存</a>
</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   回复反馈意见
   <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
			
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent">${inputBy}</td>
	       
	             <td class="columnTitle">提出时间：</td>
	            <td class="tableContent">
	            ${inputTime}
	            <%-- <fmt:formatDate value="${inputTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
 			
	            <td class="tableContent">
	            <s:select name="answerBy" list="userList" id="selectUser" 
	            	onchange="setAnswerName(this.options[this.selectedIndex].text);"
	            		listKey="id" listValue="userInfo.name"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>   
	            	  <input type="hidden" name="answerName" value="" id="answerName"/>
	         
	            </a></td>
	
	        </tr>	
	       	
	        <tr>
	            <td class="columnTitle">解决方式：</td>
	            <td class="tableContent"><input type="text" name="resolution" value="${resolution}"/></td>
	        
	            <td class="columnTitle">解决难度：</td>
	            <td class="tableContentAuto">
	             <input type="radio" name="difficulty" value="0" class="input" ${difficulty==0?'checked':'' }/>简单
	            <input type="radio" name="difficulty" value="1" class="input" ${difficulty==1?'checked':'' }/>中等
	            <input type="radio" name="difficulty" value="2" class="input" ${difficulty==2?'checked':'' }/>困难

	            
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">是否公开：</td>
	            <td class="tableContent">
	            	<c:if test="${isShare==0}">
	            		不公开
	            	</c:if>
	            	<c:if test="${isShare==1}">
	            		公开
	            	</c:if>
	   
	            </td>
	        
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            <input type="radio" name="state" value="0" class="input" ${state==0?'checked':'' }/>未解决
	            <input type="radio" name="state" value="1" class="input" ${state==1?'checked':'' }/>已解决
	            	
	            </td>
	        </tr>
	        <tr>
	        <td class="columnTitle">解决时间：</td>
	         <td class="tableContent"> 
	       
	         <input type="text" style="width:90px;" name="answerTime"
	            	 value="<fmt:formatDate value='${answerTime }' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	             	
	         </td>
	          
	        	
	         </tr>
	        <tr>
	          
	        	   <td class="columnTitle">反馈内容：</td>
	            <td class="tableContent">
	              <textarea  name="content" style="height: 100px"
	              readonly="readonly">${content}</textarea>
	            </td>
	       		 <td class="columnTitle">解决方案：</td>
	            <td class="" >
	            <textarea  name="solveMethod" style="height: 100px"value="">${solveMethod}</textarea></td>   
	         </tr>
	        	
		</table>
	</div>
 
 
</form>
</body>
</html>

