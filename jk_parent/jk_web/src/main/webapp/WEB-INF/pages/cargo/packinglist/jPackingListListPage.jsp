<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<!-- checkbox选择校验，校验用户是否选择合适的选项 -->
	<script type="text/javascript">
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
	/* 报运校验，保证至少选择一个 */
	function tocreate(){
		
			formSubmit("${ctx}/cargo/packingListAction_tocreate","_self");
		
	}
	/*查看校验  */
	function toview(){

		if(isOnlyChecked()){
			//如果符合，进跳转到程序中
			formSubmit('${ctx}/cargo/contractAction_toview','_self')
		}else{
			alert("请选择一项，并且只能选择一项");
		} 
	}
	/*查看校验  */
	function toupdate(){

		if(isOnlyChecked()){
			//如果符合，进跳转到程序中
			formSubmit('${ctx}/cargo/packingListAction_toupdate','_self')
		}else{
			alert("请选择一项，并且只能选择一项");
		} 
	}
	/*查看校验  */
	function todelete(){

		if(isNullChecked()){
			//如果符合，进跳转到程序中
			formSubmit('${ctx}/cargo/packingListAction_delete','_self')
		}else{
			alert("请至少选择一项进行操作");
		} 
	}
	/*查看校验  */
	function tosubmit(){

		if(isNullChecked()){
			//如果符合，进跳转到程序中
			formSubmit('${ctx}/cargo/packingListAction_submit','_self')
		}else{
			alert("请至少选择一项进行操作");
		} 
	}
	/*查看校验  */
	function tocancel(){

		if(isNullChecked()){
			//如果符合，进跳转到程序中
			formSubmit('${ctx}/cargo/packingListAction_cancel','_self')
		}else{
			alert("请至少选择一项进行操作");
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
<!-- <li id="view"><a href="#" onclick="formSubmit('packingListAction_toview','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('packingListAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('packingListAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('packingListAction_delete','_self');this.blur();">删除</a></li>
 -->
 <li id="view"><a href="#" onclick="javascript:toview()">查看</a></li>
<li id="new"><a href="#" onclick="javascript:tocreate()">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toupdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:todelete()">删除</a></li>
 <li id="update"><a href="#" onclick="javascript:tosubmit()">提交</a></li>
 <li id="update"><a href="#" onclick="javascript:tocancel()">取消</a></li>
 </ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    装箱单列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">卖方</td>
		<td class="tableHeader">买方</td>
		<td class="tableHeader">发票号</td>
		<td class="tableHeader">发票日期</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'"  align="left">
		<td>
		<c:if test="${o.state<2}">
		<input type="checkbox" name="id" value="${o.id}"/>
		</c:if>
		<c:if test="${o.state>=2}">
		<input type="hidden" />
		</c:if>
		
		
		
		<td>
		<a href="packingListAction_toview?id=${o.id}">
		${status.index+1}</td>
		<td>${o.seller}</td>
		<td>${o.buyer}</td>
		<td>${o.invoiceNo}</td>
		<td>${o.invoiceDate}</td>
	
		<td>
		
		<c:if test="${o.state==0}">草稿</c:if>
		<c:if test="${o.state==1}"><b><font color="green">已上报</font></b></c:if>
		<c:if test="${o.state==2}">已委托</c:if>
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

