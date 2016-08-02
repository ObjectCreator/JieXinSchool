<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
	<script type="text/javascript">
	<!-- 使用js校验来检查确保用户输入的数据合格 -->
	
		$(function(){
		 
			$("#formId").validate({
				rules:{
					seller:{
						required:true
					},
					buyer:{
						required:true
					},
					invoiceNo:{
						required:true
					},
					invoiceDate:{
						required:true
					}, 
					marks:{
						required:true
					}
				},
				messages:{
					seller:{
						required:"卖方必须填写 "
					},
					buyer:{
						required:"买方必须填写 "
					} ,
					invoiceNo:{
						required:"发票号必须填写 "
					},
					invoiceDate:{
						required:"发票日期必须填写  "
					}, 
					marks:{
						required:"唛头必须填写 "
					}
				},
				errorLabelContainer: "#errorId"
			});
		});
		
		function tosave(){
		
			if($("#formId").valid()){

				formSubmit('packingListAction_update','_self');this.blur();
			}else{
				return false;
			}
		}
	</script>
	</script>
</head>

<body>
<form name="icform" method="post" id="formId">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">
 <a href="#" onclick="javascript:tosave()">保存</a>
<!-- <a href="#" onclick="formSubmit('packingListAction_update','_self');this.blur();">保存</a></li>
 --><li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改装箱单
   <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        
	        <tr>
	            <td class="columnTitle">卖方：</td>
	            <td class="tableContent"><input type="text" name="seller" value="${seller}"/></td>
	      
	            <td class="columnTitle">买方：</td>
	            <td class="tableContent"><input type="text" name="buyer" value="${buyer}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="invoiceNo" value="${invoiceNo}"/></td>
	        
	            <td class="columnTitle">发票日期：</td>
	            <td class="tableContent">
	             <input type="text" style="width:90px;" name="invoiceDate"
	            	 value="${invoiceDate}"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	 
	        <tr>
	            <td class="columnTitle">唛头：</td>
	            <td class="tableContent"><input type="text" name="marks" value="${marks}"/></td>
	      
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="descriptions" value="${descriptions}"/></td>
	        </tr>	
	       
		</table>
	</div>
 
 
</form>
</body>
</html>

