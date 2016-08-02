<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	<script>
	     function isOnlyChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				} 
			//jquery
			//var count = $("[input name='id']:checked").size();
			if(count==1)
				return true;
			else
				return false;
	     }
	     function isNullChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				} 
			//jquery
			//var count = $("[input name='id']:checked").size();
			if(count==0)
				return false;
			else
				return true;
	     }
	     function toView(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('${ctx}/baseinfo/factoryAction_toview','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('${ctx}/baseinfo/factoryAction_toupdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现删除
	     function toDelete(){
	    	 if(isNullChecked()){
	    		 if(confirm("确认要删除么")){
	    			 formSubmit('factoryAction_delete','_self');
	    		 }
	    		 
	    	 }else{
	    		 alert("请至少选择一项，再进行操作！");
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
<li id="view"><a href="#" onclick="javascript:toView()">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('factoryAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:toDelete();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    工厂列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">类型</td>
		<td class="tableHeader">厂家全称</td>
		<td class="tableHeader">名称缩写</td>
		<td class="tableHeader">联系人</td>
		<td class="tableHeader">电话</td>
		<td class="tableHeader">验货员</td>		
		<td class="tableHeader">状态</td>		
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>						
		<td>${o.ctype}</td>
		<td>${o.fullName}</td>
		<td>${o.factoryName}</td>
		<td>${o.contacts}</td>
		<td>${o.phone}</td>		
		<td>${o.inspector}</td>	
		<td><c:if test="${o.state==0}">未启用</c:if>
		<c:if test="${o.state==1}"><font color="green">已启用</font></c:if>
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

