<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		
			$(function(){
				
				$("#formId").validate({
					rules:{
						ctype:{
							required:true
							
						},
						fullName:{
							required:true
						},
						contacts:{
							required:true
						},
						address:{
							required:true
						},
						phone:{
							required:true,
							number:true
						},
						mobile:{
							required:true,
							number:true
						},
						fax:{
							required:true,
							number:true
						},
						inspector:{
							required:true
						},
						orderNo:{
							required:true
						}
						
						
						
					},
					messages:{
						ctype:{
							required:"工厂类型必须填写 "
							
						},
						fullName:{
							required:"工厂全称必须填写 "
						},
						contacts:{
							required:"联系人必须填写 "
						},
						address:{
							required:"地址必须填写"
						},
						phone:{
							required:"电话必须填写 ",
							number:"必须为数字 "
						},
						mobile:{
							required:"手机必须填写 ",
							number:"必须为数字 "
						},
						fax:{
							required:"传真必须填写 ",
							number:"必须为数字 "
						},
						inspector:{
							required:"验货员必须填写  "
						},
						orderNo:{
							required:"排序号必须填写"
						}
					},
					errorLabelContainer: "#errorId"
				});
			});
		
			function tosave(){
			
				if($("#formId").valid()){
					formSubmit('factoryAction_insert','_self');
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

<!-- <a href="#" onclick="formSubmit('factoryAction_insert','_self');this.blur();">保存</a> -->
 <a href="#" onclick="javascript:tosave()">保存</a>
</li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增工厂
      <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
    <div>
		<table class="commonTable" cellspacing="1">       	
	         <tr>
	            <td class="columnTitle">类型：</td>
	            
	            <td class="tableContentAuto">
	            <input type="radio" name="ctype" value="货物" checked class="input" ${ctype=='货物'?'checked':'' }/>货物
	            	<input type="radio" name="ctype" value="附件" class="input" ${ctype=='附件'?'checked':'' }/>附件
	            </td>

	        
	            <td class="columnTitle">厂家全称：</td>
	            <td class="tableContent"><input type="text" name="fullName" /></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">名称缩写：</td>
	            <td class="tableContent"><input type="text" name="factoryName" /></td>
	             <td class="columnTitle">联系人：</td>
	            <td class="tableContent"><input type="text" name="contacts" /></td>
	        </tr>
	       	
	        <tr>
	           
	       		 <td class="columnTitle">地址：</td>
	            <td class="tableContent"><input type="text" name="address" /></td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="phone" /></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">手机：</td>
	            <td class="tableContent"><input type="text" name="mobile" /></td>
	        
	            <td class="columnTitle">传真：</td>
	            <td class="tableContent"><input type="text" name="fax" /></td>
	        </tr>	
	        <tr>
	           
	        
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent"><input type="text" name="inspector" /></td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="remark" /></td>
	        </tr>	
	        <tr>
	          
  				
	            <td class="columnTitle">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" /></td>
	        </tr>		       		       		       	       		       	        
		</table>
	</div>
 
 
</form>
</body>
</html>

