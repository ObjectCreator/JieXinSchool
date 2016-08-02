<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	
		<!-- 导入jquery -->
	<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
	<!--使用jsp校验，解决用户勾选的问题  -->

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
		
		//新增	
		function tocreate(){
		
			formSubmit("${ctx}/cargo/shippingOrderAction_tocreate","_self");
		
		}
		/*查看校验  */
		function toview(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/shippingOrderAction_toview','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/*修改校验  */
		function toupdate(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/shippingOrderAction_toupdate','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/*删除校验  */
		function todelete(){

			if(isNullChecked()){
				//如果符合，进跳转到程序中
				if(confirm("确认要删除委托单么")){
					formSubmit('${ctx}/cargo/shippingOrderAction_delete','_self')
				}
				
			}else{
				alert("请选择至少选择一项");
			} 
		}
		
		/*取消校验  */
		function tocancel(){

			if(isNullChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/shippingOrderAction_cancel','_self')
			}else{
				alert("请选择至少选择一项");
			} 
		}
		/*提交校验  */
		function tosubmit(){

			if(isNullChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/shippingOrderAction_submit','_self')
			}else{
				alert("请选择至少选择一项");
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

<li id="view"><a href="#" onclick="javascript:toview()">查看</a></li>
<li id="new"><a href="#" onclick="javascript:tocreate()">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toupdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:todelete()">删除</a></li>
<li id="new"><a href="#" onclick="javascript:tosubmit()">提交</a></li>
<li id="new"><a href="#" onclick="javascript:tocancel()">取消</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    托运单列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">&nbsp;&nbsp;&nbsp;托运单号</td>
		<td class="tableHeader">海运/空运</td>
		
		<td class="tableHeader">货主</td>
		
		
		<td class="tableHeader">装运港</td>
		<td class="tableHeader">转船港</td>
		
		<td class="tableHeader">卸货港</td>
		<td class="tableHeader">装期</td>
		<td class="tableHeader">效期</td>
		
		<td class="tableHeader">是否分批</td>
		<td class="tableHeader">是否转船</td>
		<td class="tableHeader">份数</td>
		
		
		
		<td class="tableHeader">复核人</td>
		<td class="tableHeader">状态</td>
		
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td>
			<c:if test="${o.state<=1}">
			<input type="checkbox" name="id" value="${o.id}"/>
		
			</c:if>
		<c:if test="${o.state>1}">
			<input type="hidden"  value=""/>
		
			</c:if>
		
		</td>
		<td>
			<a href="shippingOrderAction_toview?id=${o.id}">
		${status.index+1}</td>
		<td>${o.id}</td>
		<td>${o.orderType}</td>
		
		<td>${o.shipper}</td>
		
		
		<td>${o.portOfLoading}</td>
		<td>${o.portOfTrans}</td>
		
		<td>${o.portOfDischarge}</td>
		<td>${o.loadingDate}</td>
		<td>${o.limitDate}</td>
		
		<td>${o.isBatch}</td>
		<td>${o.isTrans}</td>
		<td>${o.copyNum}</td>
		<td>${o.checkBy}</td>
		<td>	
			<c:if test="${o.state==0}"><b><font color="red">草稿</font></b></c:if>
			<c:if test="${o.state==1}"><b><font color="green">已上报</font></b></c:if>	
			<c:if test="${o.state==2}"><b><font color="green">已出发票</font></b></c:if>	
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

