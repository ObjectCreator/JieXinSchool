<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" >
		//判断是否选择一个
		function  isOnlyChecked(){
			var count = $("input[name='id']:checked").size();
			
			return count==1;
		}
		//判断是否选中
		function isNullChecked(){
			var count = $("input[name='id']:checked").size();
		
			return count>0;
		}
		
		
		/* 打印校验，保证每次都只能打印一个购销合同 */
		function toprint(){
			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/contractAction_print','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
			
		function tocreate(){
		
			formSubmit("${ctx}/cargo/contractAction_tocreate","_self");
		
		}
		/*查看校验  */
		function toview(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('feedbackAction_toview','_self');
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/*修改校验  */
		function toreply(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('feedbackAction_toreply','_self');
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/*删除校验  */
		function todelete(){

			if(isNullChecked()){
				if(confirm("是否要删除选中的反馈意见")){
					formSubmit('feedbackAction_delete','_self');
				}
				//如果符合，进跳转到程序中
				
			}else{
				alert("请至少选择一项");
			} 
		}
		
		
	</script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toview();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('feedbackAction_tocreate','_self');this.blur();">新增</a></li>

<li id="delete"><a href="#" onclick="javascript:todelete();">删除</a></li>
<li id="update"><a href="#" onclick="formSubmit('feedbackAction_list','_self');this.blur();">所有</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
  反馈意见列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">提出人</td>
		<td class="tableHeader">提出时间</td>
		<td class="tableHeader">标题</td>
		<td class="tableHeader">分类</td>
		<td class="tableHeader">解决人</td>
		<td class="tableHeader">解决时间</td>
		<td class="tableHeader">状态</td>
		
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.inputBy}</td>
		<td>
		<fmt:formatDate value="${o.inputTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>${o.title}</td>	
		<td>${o.classType}</td>	
		<td>${o.answerName}</td>
		<td>
			<fmt:formatDate value="${o.answerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	
		<td>
		<c:if test="${o.state==0}">
			未解决
		</c:if>
		<c:if test="${o.state==1}">
			已解决
		</c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

