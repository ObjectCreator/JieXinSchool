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
		/* 打印校验，保证每次都只能打印一个购销合同 */
		function toprint(){
			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/contractAction_print','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/* 报运校验，保证至少选择一个 */
		function tocreate(){
			if(isNullChecked()){
				formSubmit("${ctx}/cargo/exportAction_tocreate","_self");
			}else{
				alert("请至少选择一项进行操作");
			}
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
	</script>
</head>

<body>
<form name="icform" method="post">
    
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<%-- <li id="view"><a href="#" onclick="formSubmit('${ctx}/cargo/contractAction_toview','_self');this.blur();">查看</a></li> --%>
<%-- <li id="print"><a href="#" onclick="formSubmit('${ctx}/cargo/contractAction_print','_self');this.blur();">打印</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/cargo/exportAction_tocreate','_self');this.blur();">报运</a></li>
 --%>

<li id="view"><a href="#" onclick="javascript:toview()">查看</a></li>
<li id="print"><a href="#" onclick="javascript:toprint()">打印</a></li>
<li id="new"><a href="#" onclick="javascript:tocreate()">报运</a></li>

</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
	购销合同列表
  </div> 


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">客户名称</td>
		<td class="tableHeader">合同号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">制单人</td>
		<td class="tableHeader">审单人</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">签单日期</td>
		<td class="tableHeader">交货期限</td>
		<td class="tableHeader">船期</td>
		<td class="tableHeader">贸易条款</td>
		<td class="tableHeader">总金额</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.customName}</td>
		<td>
			<a href="contractAction_toview?id=${o.id}">${o.contractNo}</a>
		</td>
		<td align="center">
			${o.productNum }
			/${o.extCproductNum }
			
		</td>
		<td>${o.inputBy}</td>
		<td>${o.checkBy}</td>
		<td>${o.inspector}</td>
		<td><fmt:formatDate value="${o.signingDate}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.shipTime}" pattern="yyyy-MM-dd"/></td>
		<td>${o.tradeTerms}</td>
		<td>${o.totalAmount}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>


</div>
 
 
</form>
</body>
</html>

