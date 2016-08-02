<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">

<a href="#" onclick="formSubmit('${ctx}/baseinfo/productAction_insert','_self');this.blur();">保存</a>


</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    产品新增
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	           <td class="columnTitle">商品ID：</td>
	            <td class="tableContent"></td>  
	            <td class="columnTitle">商品编号：</td>
	            <td class="tableContent"><input type="text" name="productNo" value="" /></td>
	              <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" checked class="input"/>启用
	            	<input type="radio" name="state" value="0" class="input"/>停用
	            </td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">照片：</td>
	            <td class="tableContent"><input type="text" name="productImage" value=""/></td>
	           
	            <td class="columnTitle">市场价：</td>
	            <td class="tableContent"><input type="text" name="price" value=""/></td>
	            
	          
	        </tr>	
	        <tr>
	            <td class="columnTitle">工厂ID：</td>
	            <td class="tableContent"><input type="text" name="factoryId" value=""/></td>
	            <td class="columnTitle">工厂名称：</td>
	            <td class="tableContent"><input type="text" name="factoryName" value=""/></td>
	        </tr>		
	        <tr>
	        	<td class="columnTitle">长：</td>
	            <td class="tableContent"><input type="text" name="sizeLenght" value=""/></td>
	            <td class="columnTitle">宽：</td>
	            <td class="tableContent"><input type="text" name="sizeWidth" value=""/></td>
	            <td class="columnTitle">高：</td>
	            <td class="tableContent"><input type="text" name="sizeHeight" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent"><input type="text" name="color" value=""/></td>
	            <td class="columnTitle">包装：</td>
	            <td class="tableContent"><input type="text" name="packing" value=""/></td>
	        </tr>	
	      	
	        <tr>
	        	<td class="columnTitle">Type20：</td>
	            <td class="tableContent"><input type="text" name="type20" value=""/></td>
	            <td class="columnTitle">Type40：</td>
	            <td class="tableContent"><input type="text" name="type40" value=""/></td>
	            <td class="columnTitle">Type40hc：</td>
	            <td class="tableContent"><input type="text" name="type40hc" value=""/></td>
	        </tr>	
	        <tr>
	        	<td class="columnTitle">单位：</td>
	            <td class="tableContent"><input type="text" name="packingUnit" value=""/></td>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContent"><input type="text" name="qty" value=""/></td>
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent"><input type="text" name="cbm" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">长：</td>
	            <td class="tableContent"><input type="text" name="mpsizeLenght" value=""/></td>
	            <td class="columnTitle">宽：</td>
	            <td class="tableContent"><input type="text" name="mpsizeWidth" value=""/></td>
	            <td class="columnTitle">高：</td>
	            <td class="tableContent"><input type="text" name="mpsizeHeight" value=""/></td>
	                    
	        </tr>	
	        <tr> 
	        	<td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="description" value=""/></td>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><input type="text" name="remark" value=""/></td>  
	        </tr>
				<tr>
					<td class="columnTitle">创建人：</td>
					<td class="tableContent"><input type="text" name="createBy"
						value="${_CURRENT_USER.userName}" readonly="readonly" /></td>
					<td class="columnTitle">创建部门</td>
					<td class="tableContent"><input type="text" name="createDept"
						value="${_CURRENT_USER.dept.deptName}" readonly="readonly" /></td>
					<%--  <td class="columnTitle">所在部门：</td>
	            <td class="tableContent">
	            	<s:select name="dept.id" list="#request.deptList"
	            		listKey="id" listValue="deptName"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>
	            </td> --%>
					<!-- <td class="columnTitle">创建时间：</td>
					<td class="tableContent"><input type="text"
						style="width: 90px;" name="createTime" value=""
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
					</td> -->
				</tr>
			</table>
	</div>
 
 
</form>
</body>
</html>

