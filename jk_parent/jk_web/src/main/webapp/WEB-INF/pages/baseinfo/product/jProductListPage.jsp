<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
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
		
			return count==0;
		}
		
		function manyChecked(){
			var count = $("input[name='id']:checked").size();
		
			return count>1;
		}
		
		
		/* 打印校验，保证每次都只能打印一个购销合同 */
		function toupdate(){
			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('productAction_toupdate','_self')
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		
		function todelete(){
			if(isOnlyChecked()){
				alert("你确认删除这个商品")
				formSubmit('productAction_delete','_self');	
			}
			else {
				alert("请选择一项商品进行删除");
			} 
		}
	
		/*查看校验  */
		function toview(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('productAction_toview','_self')
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
<li id="view"><a href="#"  onclick="javascript:toview()">查看</a></li>
<li id="new"><a href="#"   onclick="formSubmit('productAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toupdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:todelete()">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
  <!--  formSubmit('productAction_toview','_self');this.blur(); -->
  <!-- formSubmit('productAction_delete','_self');this.blur(); -->
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
                 产品列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">ID</td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">图片</td>
		<td class="tableHeader">描述</td>
		<!-- <td class="tableHeader">公司号</td> -->
		<td class="tableHeader">公司简称</td>
		<td class="tableHeader">市场价</td>
		<!-- <td class="tableHeader">长</td>
		<td class="tableHeader">宽</td>
		<td class="tableHeader">高</td>
		<td class="tableHeader">颜色</td>
		<td class="tableHeader">包装</td>
		<td class="tableHeader">单位</td> -->
		<!-- <td class="tableHeader">20</td>
		<td class="tableHeader">40</td>
		<td class="tableHeader">40hc</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">体积</td>
		<td class="tableHeader">长</td>
		<td class="tableHeader">宽</td>
		<td class="tableHeader">高</td>
		<td class="tableHeader">备注</td>
		<td class="tableHeader">录入时间</td>
		<td class="tableHeader">创建人</td>
		<td class="tableHeader">创建部门</td>
		<td class="tableHeader">创建日期</td> -->
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
		<%-- <td><a href="productAction_toview?id=${o.id}">${o.deptName}</a></td> --%>
		<%-- <td>${o.productId}</td> --%>
		<td>${o.productNo}</td>
		<td>${o.productImage}</td>
		<td>${o.description}</td>
	<%-- 	<td>${o.factoryId}</td> --%>
		<td>${o.factoryName}</td>
		<td>${o.price}</td>
		<%-- <td>${o.sizeLenght}</td>
		<td>${o.sizeWidth}</td>
		<td>${o.sizeHeight}</td>
		<td>${o.color}</td>
		<td>${o.packing}</td>
		<td>${o.packingUnit}</td> --%>
		<%-- <td>${o.type20}</td>
		<td>${o.type40}</td>
		<td>${o.type40hc}</td>
		<td>${o.qty}</td>
		<td>${o.cbm}</td>
		<td>${o.mpsizeLenght}</td>
		<td>${o.mpsizeWidth}</td>
		<td>${o.mpsizeHeight}</td>
		<td>${o.remark}</td>
		<td>${o.inputTime}</td>
		<td>${o.createBy}</td>
		<td>${o.createDept}</td>
		<td>${o.createTime}</td> --%>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

