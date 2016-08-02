<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
	<!-- 使用js校验来检查确保用户输入的数据合格 -->
	<script type="text/javascript">
		$(function(){
		
			$("#formId").validate({
				rules:{
					seller:{
						required:true
					},
					buyer:{
						required:true
					},
					/* invoiceNo:{
						required:true
					},
					invoiceDate:{
						required:true
					}, */
					marks:{
						required:true
					},
					exportIds:{
						required:true
					}
				},
				messages:{
					seller:{
						required:"卖方必须填写 "
					},
					buyer:{
						required:"买方必须填写 "
					},
					/* invoiceNo:{
						required:"发票号必须填写 "
					},
					invoiceDate:{
						required:"发票日期必须填写  "
					}, */
					marks:{
						required:"唛头必须填写 "
					},
					exportIds:{
						required:"报运单必须勾选 "
					}
				},
				errorLabelContainer: "#errorId"
			});
		});
		
		function tosave(){
		
			if($("#formId").valid()){

				formSubmit('packingListAction_insert','_self');
			}else{
				return false;
			}
		}
	</script>
</head>

<body>
<form name="icform" method="post" id="formId">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">

 <a href="#" onclick="javascript:tosave()">保存</a>
 <li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增装箱单
   <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">卖方：</td>
	            <td class="tableContent"><input type="text" name="seller" value=""/></td>
	        
	            <td class="columnTitle">买方：</td>
	            <td class="tableContent"><input type="text" name="buyer" value=""/></td>
	        </tr>	
	    <!--     <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="invoiceNo" value=""/></td>
	        
	            <td class="columnTitle">发票日期：</td>
	            <td class="tableContent">
	            <input type="text" style="width:90px;" name="invoiceDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	 -->
	        <tr>
	            <td class="columnTitle">唛头：</td>
	            <td class="tableContent"><input type="text" name="marks" value=""/></td>
	        
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="descriptions" value=""/></td>
	        </tr>	
	        
		</table>
	</div>
 <div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('exportIds',this)"></td>
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
<%-- ${links} --%>
	
	<c:forEach items="${requestScope.exportList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="exportIds" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
		<td align="center">
			${o.exProductNum}
			/${o.extEproductNum}
			
			<%-- <c:set var="extNumber" value="0"></c:set><!-- 设置一个变量，用来累加，初始值0 -->
			<c:forEach items="${o.exportProducts}" var="ep">
			   <c:if test="${ep.extEproducts.size()!=0 }">
					<c:set var="extNumber" value="${extNumber + ep.extEproducts.size()}"/>
				</c:if>
			</c:forEach> --%>
	<%-- 		${extNumber} --%>
		</td>		
		<td>${o.lcno}</td>
		<td>${o.consignee}</td>
		<td>${o.shipmentPort}</td>
		<td>${o.destinationPort}</td>
		<td>${o.transportMode}</td>
		<td>${o.priceCondition}</td>
		<td><fmt:formatDate value="${o.inputDate }" pattern="yyyy-MM-dd"/></td>
			${o.state}
		
		 <c:if test="${o.state==2}">
				<td>已报运，未装箱</td>	
		</c:if>
		 
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</form>
</body>
</html>

