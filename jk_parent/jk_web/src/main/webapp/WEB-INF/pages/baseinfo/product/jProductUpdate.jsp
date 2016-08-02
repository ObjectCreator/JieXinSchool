<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
	 <input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>

<li id="save"><a href="#" onclick="formSubmit('${ctx}/baseinfo/productAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   修改产品详情
  </div> 
  
      <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">商品ID：</td>
	            <td class="tableContent">${id}</td> 
	            <td class="columnTitle">编号：</td>
	            <td class="tableContent"><input type="text" name="productNo" value="${productNo}"/></td>
	              <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="state" value="1" checked class="input" ${state==1?'checked':'' }/>启用
	            	<input type="radio" name="state" value="0" class="input"  ${state==1?'checked':'' }//>停用
	            </td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">照片：</td>
	            <td class="tableContent"><input type="text" name="productImage" value="${productImage}"/></td>
	           
	            <td class="columnTitle">市场价</td>
	            <td class="tableContent"><input type="text" name="price" value="${price}"/></td>
	            
	          
	        </tr>	
	        <tr>
	            <td class="columnTitle">工厂ID：</td>
	            <td class="tableContent"><input type="text" name="factoryId" value="${factoryId}"/></td>
	            <td class="columnTitle">工厂名称：</td>
	            <td class="tableContent"><input type="text" name="factoryName" value="${factoryName}"/></td>
	        </tr>		
	        <tr>
	        	<td class="columnTitle">长：</td>
	            <td class="tableContent"><input type="text" name="sizeLenght" value="${sizeLenght}"/></td>
	            <td class="columnTitle">宽：</td>
	            <td class="tableContent"><input type="text" name="sizeWidth" value="${sizeWidth}"/></td>
	            <td class="columnTitle">高：</td>
	            <td class="tableContent"><input type="text" name="sizeHeight" value="${sizeHeight}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent"><input type="text" name="color" value="${color}"/></td>
	            <td class="columnTitle">包装：</td>
	            <td class="tableContent"><input type="text" name="packing" value="${packing}"/></td>
	        </tr>	
	      	
	        <tr>
	        	<td class="columnTitle">Type20：</td>
	            <td class="tableContent"><input type="text" name="type20" value="${type20}"/></td>
	            <td class="columnTitle">Type40：</td>
	            <td class="tableContent"><input type="text" name="type40" value="${type40}"/></td>
	            <td class="columnTitle">Type40hc：</td>
	            <td class="tableContent"><input type="text" name="type40hc" value="${type40hc}"/></td>
	        </tr>	
	        <tr>
	        	<td class="columnTitle">单位：</td>
	            <td class="tableContent"><input type="text" name="packingUnit" value="${packingUnit}"/></td>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContent"><input type="text" name="qty" value="${qty}"/></td>
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent"><input type="text" name="cbm" value="${cbm}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">长：</td>
	            <td class="tableContent"><input type="text" name="mpsizeLenght" value="${mpsizeLenght}"/></td>
	            <td class="columnTitle">宽：</td>
	            <td class="tableContent"><input type="text" name="mpsizeWidth" value="${mpsizeWidth}"/></td>
	            <td class="columnTitle">高：</td>
	            <td class="tableContent"><input type="text" name="mpsizeHeight" value="${mpsizeHeight}"/></td>
	                    
	        </tr>	
	        <tr> 
	        	<td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="description" value="${description}"/></td>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>  
	        </tr>
	        <tr>
	        <td class="columnTitle">创建人：</td>
					<td class="tableContent"><input type="text" name="createBy"
						value="${_CURRENT_USER.userName}" readonly="readonly" /></td>
					<td class="columnTitle">创建部门：</td>
					<td class="tableContent"><input type="text" name="createDept"
						value="${_CURRENT_USER.dept.deptName}" readonly="readonly" /></td>
			</tr>
			</table>
	</div>
</form>
</body>
</html>

