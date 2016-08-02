<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//动态设置生产厂家的名称 
		function setFactoryName(val){
			
			document.getElementById("factoryName").value = $("#factory").find("option:selected").text();;
		}
	</script>
	<script type="text/javascript">
		$(function(){
		
			$("#formId").validate({
				rules:{
					"factory.id":{
						required:true
					},
					productNo:{
						required:true
					},
					productImage:{
						required:true
					},
					cnumber:{
						required:true,
						number:true
					},
					packingUnit:{
						required:true
					},
					loadingRate:{
						required:true
					},
					boxNum:{
						required:true,
						number:true
					},
					price:{
						required:true,
						number:true
					},
					orderNo:{
						required:true,
						number:true
					},
					productDesc:{
						required:true,
					
					},
					productRequest:{
						required:true
					}
				},
				messages:{
					"factory.id":{
						required:"生产厂家必须选择 "
					},
					productNo:{
						required:"货号必须填写 "
					},
					productImage:{
						required:"图片必须填写 "
					},
					cnumber:{
						required:"数量必须填写 ",
						number:"数量必须为数字"
					},
					packingUnit:{
						required:"包装单位必须选择 "
					},
					loadingRate:{
						required:"装率必须选择 "
					},
					boxNum:{
						required:"箱数必须填写 ",
						number:"箱数必须为数字 "
					},
					price:{
						required:"价格必须填写 ",
						number:"价格必须为数字 "
					},
					orderNo:{
						required:"排序号必须填写 ",
						number:"排序号必须为数字 "
					},
					productDesc:{
						required:"货物说明必须填写 ",
					
					},
					productRequest:{
						required:"货物要求必须填写 "
					}
				},
				errorLabelContainer: "#errorId"
			});
		});
	
		function tosave(){
		
			if($("#formId").valid()){
				formSubmit('contractProductAction_insert','_self'); 
			
			}else{
				return false;
			}
		}
	</script>
</head>

<body>
<form name="icform" method="post" id="formId"  enctype="multipart/form-data">
	<input type="hidden" name="contract.id" value="${contract.id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">
<!-- <a href="#" onclick="formSubmit('contractProductAction_insert','_self');this.blur();">保存</a> -->

<a href="#"  onclick="javascript:tosave()">保存</a>
</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增货物
   <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">生产厂家：</td>
	            <td class="tableContent">
	            	 <s:select name="factory.id" list="factoryList" id="factory"
	            				onchange="setFactoryName(this.options[this.selectedIndex].text);"
	            				listKey="id" listValue="factoryName" 
	            				headerKey="" headerValue="--请选择--"/>
	            				
	            	<input type="hidden" id="factoryName" name="factoryName" value=""/>
	            </td>
	            <td class="columnTitle">货号：</td>
	            <td class="tableContentAuto"><input type="text" name="productNo" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">货物照片：</td>
	            <td class="tableContent"><input type="file" name="productImg" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContent"><input type="text" name="cnumber" value=""/></td>
	            <td class="columnTitle">包装单位：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="packingUnit" value="PCS" class="input">只
	            	<input type="radio" name="packingUnit" value="SETS" class="input">套
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">装率：</td>
	            <td class="tableContent"><input type="text" name="loadingRate" value=""/></td>
	            <td class="columnTitle">箱数：</td>
	            <td class="tableContent"><input type="text" name="boxNum" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">单价：</td>
	            <td class="tableContent"><input type="text" name="price" value=""/></td>
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">货物描述：</td>
	            <td class="tableContent"><textarea name="productDesc" style="height:150px;"></textarea>
	            <td class="columnTitle">要求：</td>
	            <td class="tableContent"><textarea name="productRequest" style="height:150px;"></textarea>
	        </tr>		
		</table>
	</div>


  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    货物列表
  </div> 


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">厂家</td>
		<td class="tableHeader">货号</td>
		<td class="tableHeader">装率</td>
		<td class="tableHeader">箱数</td>
		<td class="tableHeader">包装单位</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">单价</td>
		<td class="tableHeader">总金额</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links }
 	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.factoryName}</td>
		<td>${o.productNo}</td>
		<td>${o.loadingRate}</td>
		<td>${o.boxNum}</td>
		<td>${o.packingUnit}</td>
		<td>${o.cnumber}</td>
		<td>${o.price}</td>
		<td>${o.amount}</td>
		<td>
			<a href="contractProductAction_toupdate.action?id=${o.id}">[修改]</a>
			<a href="contractProductAction_delete.action?id=${o.id}&contract.id=${o.contract.id}" onclick="javascript:confirm('确认要删除该货物么')">[删除]</a>
			<a href="extCproductAction_tocreate.action?contractProduct.contract.id=${o.contract.id}&contractProduct.id=${o.id}">[附件]</a>
		</td>
	</tr>
	
	<c:forEach items="${o.extCproducts}" var="ext" varStatus="status">
	<tr height="40" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
	    <td>&nbsp;</td>
		<td align="right"><font color="blue">附件：${status.index+1}&nbsp;</font></td>
		<td>${ext.factoryName}</td>
		<td>${ext.productNo}</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>${ext.packingUnit}</td>
		<td>${ext.cnumber}</td>
		<td>${ext.price}</td>
		<td>${ext.amount}</td>
	</tr>
	</c:forEach>
	
	</c:forEach> 
	
	</tbody>
</table>
</div> 
 
</form>
</body>
</html>

