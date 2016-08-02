<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
<head>
	<title></title>
<script type="text/javascript">
 function checkDate(){
	var checkDate= $("#checkDate").val();
	 if(checkDate==""){
		 $("#inputDateSpan").html("<font color='red'>请选择日期</font>");
		 $("#checkDate").focus();
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
function insert() {
	var count = $("input[name='id']:checked").size();
	if(!checkDate()){
		checkDate();
	}else if (!checkBy()){
		checkBy();
	}
	else if(count==0){
		alert("请选择发票");
	}else
	{
	formSubmit('financeAction_insert', '_self');
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
<li id="save"><a href="#" onclick="insert();this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增财务报运单
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">制单日期：</td>
	             <td class="tableContent"><input type="text" id="checkDate" style="width:160px" name="inputDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	        		<span id="inputDateSpan"> </span>
	        	</td>
	            <td class="columnTitle">制单人：</td>
	          <td class="tableContent">  <input type="text" id="checkInputBy" name="inputBy" style="width:160px" value=""/>
	          	<span id="inputBySpan"></span>
	          </td>
	        </tr>	
		</table>
	</div>
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    发票列表
  </div> 
 <div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="radio" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">SC_NO</td>
		<td class="tableHeader">BL_NO</td>
		<td class="tableHeader">贸易条款</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
<%-- ${links} --%>
	
	<c:forEach items="${list}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="radio" name="id"  value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.scNo}</td>
		<td>${o.blNo}</td>
		<td>${o.tradeTerms}</td>
		 <c:if test="${o.state==1}">
				<td>已出发票，未出财务</td>	
		</c:if>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</form>
</body>
</html>

