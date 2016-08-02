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
		
	
		/*查看校验  */
		function toview(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/exportAction_toview','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/*修改校验  */
		function toupdate(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/exportAction_toupdate','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		/*删除校验  */
		function todelete(){

			if(isNullChecked()){
				//如果符合，进跳转到程序中
				if(confirm('确认要删除该报运单么')){
					formSubmit('${ctx}/cargo/exportAction_delete','_self')
				}
				
			}else{
				alert("请选择至少选择一项");
			} 
		}
		
		/*取消校验  */
		function tocancel(){

			if(isNullChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/exportAction_cancel','_self')
			}else{
				alert("请选择至少选择一项");
			} 
		}
		/*提交校验  */
		function tosubmit(){

			if(isNullChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/exportAction_submit','_self')
			}else{
				alert("请选择至少选择一项");
			} 
		}
		/*电子报运*/
		function toexport(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('${ctx}/cargo/exportAction_export','_self')
			}else{
				alert("请选择选择一项且只能选择一项");
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
<!-- <li id="view"><a href="#" onclick="formSubmit('exportAction_toview','_self');this.blur();">查看</a></li>
<li id="update"><a href="#" onclick="formSubmit('exportAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('exportAction_delete','_self');this.blur();">删除</a></li>
<li id="new"><a href="#" onclick="formSubmit('exportAction_submit','_self');this.blur();">提交</a></li>
<li id="new"><a href="#" onclick="formSubmit('exportAction_cancel','_self');this.blur();">取消</a></li> -->
<!-- <li id="work_assign"><a href="#" onclick="formSubmit('exportAction_export','_self');this.blur();">电子报运</a></li>
 -->
<li id="view"><a href="#" onclick="javascript:toview()">查看</a></li>
<li id="update"><a href="#" onclick="javascript:toupdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:todelete()">删除</a></li>
<li id="new"><a href="#" onclick="javascript:tosubmit()">提交</a></li>
<li id="new"><a href="#" onclick="javascript:tocancel()">取消</a></li>
<li id="work_assign"><a href="#" onclick="javascript:toexport()">电子报运</a></li>

</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    出口报运列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">报运号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">信用证号</td>
		<td class="tableHeader">收货人及地址</td>
		<td class="tableHeader">装运港</td>
		<td class="tableHeader">目的港</td>
		<td class="tableHeader">运输方式</td>
		<td class="tableHeader">价格条件</td>
		<td class="tableHeader">制单日期</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
	 	<c:if test="${o.state<2}">
			<td><input type="checkbox" name="id" value="${o.id}"/></td>
		</c:if> 
	 	<c:if test="${o.state>=2}">
			<td><input type="hidden" name="" value=""/></td> 
			
		</c:if> 
			<%-- <td><input type="checkbox" name="id" value="${o.id}"/></td> --%>
		<td>${status.index+1}</td>
		<td>
		<a href="exportAction_toview?id=${o.id}">
		${o.id}</td>
		<td align="center">
			${o.exProductNum }
			/${o.extEproductNum }
			
			
		</td>		
		<td>${o.lcno}</td>
		<td>${o.consignee}</td>
		<td>${o.shipmentPort}</td>
		<td>${o.destinationPort}</td>
		<td>${o.transportMode}</td>
		<td>${o.priceCondition}</td>
		<td><fmt:formatDate value="${o.inputDate }" pattern="yyyy-MM-dd"/></td>
		<c:if test="${o.state==0}">
			<td>草稿</td>
		</c:if>
		<c:if test="${o.state==1}">
			<td>已提交</td>
		</c:if>
		<c:if test="${o.state==2}">
			<td>已报运</td>
		</c:if>
		<c:if test="${o.state==3}">
			<td>已装箱</td>
		</c:if>
		<%-- <td>${o.state==0?"草稿":"已上报"}</td> --%>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

