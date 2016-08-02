<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx }/js/datepicker/WdatePicker.js""></script>
<script type="text/javascript">
	 function checkDate(){
		var checkDate= $("#checkDate").val();
		 if(checkDate==""){
			 $("#inputDateSpan").html("<font color='red'>请选择日期</font>");
			 $("#checkInputBy").focus();
			 return false;
		 }else{
			 $("#inputDateSpan").html("");
			return true;
		 }
	 }
	 function checkBy(){
		 var checkInput=$("#checkInputBy").val();
		 if(checkInput==""){
			 $("#inputBySpan").html("<font color='red'>请输入制单人</font>");
			 $("#checkInputBy").focus();
			 return false;
		 }else{
			 $("#inputBySpan").html("");
			 return true;
		 }
	 }
	function update() {
		var count = $("input[name='id']:checked").size();
		if(!checkDate()){
			checkDate();
		}else if(!checkBy()){
			checkBy();
		}else
		{
		formSubmit('financeAction_update', '_self');
		}
}
	
</script>
</head>

<body>
	<form name="icform" method="post">
		<input type="hidden" name="id" value="${id}" />

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="update();this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
			修改财务报运单
		</div>



		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">制单日期：</td>
					<td class="tableContent"><input type="text" id="checkDate" style="width:160px" name="inputDate"
						value="${inputDate}"
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
						<span id="inputDateSpan"> </span>
						</td>
					<td class="columnTitle">制单人：</td>
					<td class="tableContent"><input type="text" style="width:160px" id="checkInputBy" name="inputBy"
						value="${inputBy}" />
						<span id="inputBySpan"></span>
					</td>
				</tr>
				<tr>
					<td class="columnTitle">状态：</td>
					<td class="columnTitle">启用:<input type="radio" name="state"
						value="1" ${state==1?"checked='checked'":""} />停用:<input
						type="radio" name="state" value="0"
						${state==0?"checked='checked'":""} /></td>
				</tr>
			</table>
		</div>


	</form>
</body>
</html>

