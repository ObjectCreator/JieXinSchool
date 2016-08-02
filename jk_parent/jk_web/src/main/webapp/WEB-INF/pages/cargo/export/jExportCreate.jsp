<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		$(function(){
		
			$("#formId").validate({
				rules:{
					lcno:{
						required:true,
						maxlength:10
					},
					consignee:{
						required:true
					},
					marks:{
						required:true
					},
					shipmentPort:{
						required:true,
				
					},
					destinationPort:{
						required:true
					},
					transportMode:{
						required:true
					},
					priceCondition:{
						required:true,
					
					},
					remark:{
						required:true,
						number:true
					},
					boxNums:{
						required:true,
						number:true
					},
					grossWeights:{
						required:true,
						number:true
					},
					measurements:{
						required:true,
						number:true
					}
				},
				messages:{
					lcno:{
						required:"信用证号必须填写 ",
						maxlength:"信用证号最大长度为10"
					},
					consignee:{
						required:"收货人及地址必须填写 "
					},
					marks:{
						required:"唛头必须填写 "
					},
					shipmentPort:{
						required:"装运港必须填写 "
						
					},
					destinationPort:{
						required:"目的港必须填写 "
					},
					transportMode:{
						required:"运输方式必须填写 "
					},
					priceCondition:{
						required:"价格条件必须填写 "
						
					},
					remark:{
						required:"备注必须填写 "
					
					},
					boxNums:{
						required:"箱数必须填写 ",
						number:"箱数必须为数字 "
					},
					grossWeights:{
						required:"总毛重必须填写 ",
						number:"总毛重必须为数字 "
					},
					measurements:{
						required:"体积必须填写 ",
						number:"体积必须为数字 "
					}
				},
				errorLabelContainer: "#errorId"
			});
		});
	
		function tosave(){
		
			if($("#formId").valid()){
				formSubmit('exportAction_insert','_self');
			}else{
				return false;
			}
		}
	</script>
</head>

<body>
<form name="icform" method="post" id="formId">
   <!-- 保存的是购销合同的id,用逗号空格进行分隔 -->
   <input type="hidden" name="contractIds" value="${id }" />
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save">
<!-- <a href="#" onclick="formSubmit('exportAction_insert','_self');this.blur();">保存</a> -->
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
   新增出口报运
      <div class="msgtip" style="color:red" id="errorId"></div>
  </div> 
  
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">信用证号</td>
	            <td class="tableContent"><input type="text" name="lcno" /></td>
	       
	            <td class="columnTitle">收货人及地址</td>
	            <td class="tableContent"><input type="text" name="consignee"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">唛头</td>
	            <td class="tableContent"><input type="text" name="marks"/></td>
	       
	            <td class="columnTitle">装运港</td>
	            <td class="tableContent"><input type="text" name="shipmentPort"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">目的港</td>
	            <td class="tableContent"><input type="text" name="destinationPort"/></td>
	       
	            <td class="columnTitle">运输方式</td>
	            <td class="tableContent"><input type="text" name="transportMode" /></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">价格条件</td>
	            <td class="tableContent"><input type="text" name="priceCondition"/></td>
	      
	            <td class="columnTitle">备注</td>
	            <td class="tableContent"><input type="text" name="remark" /></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">总箱数</td>
	            <td class="tableContent"><input type="text" name="boxNums"/></td>
	       
	            <td class="columnTitle">总毛重</td>
	            <td class="tableContent"><input type="text" name="grossWeights"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">体积</td>
	            <td class="tableContent"><input type="text" name="measurements"/></td>
	        </tr>	
	       
		</table>
	</div>
 
 
</form>
</body>
</html>

