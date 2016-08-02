<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	<script >
	//判断是否报运
	
	//判断状态
	function judgeState(data){
		
		//获得选中复选框  后面的状态
		/*var a=true;
		var state = $("input:checked").parent().parent().children(":last-child");
		 $.each(state,function(i,n){
			if(n.innerHTML.indexOf('已财务报运') >=0){
    			 //如果状态为 已经财务报运，提示,并返回false
   				alert(data);
   				a=false;
   				return ;
			}
		});
		return a; */
		
		var state = $("input:checked").parent().parent().children(":last-child");
     	for(var i=0;i<state.length;i++){
     		if(state[i].innerHTML.indexOf('已财务报运') >=0){
     			 //如果状态为 已经财务报运，提示,并返回false
    			alert(data);
    			return false;
    		}
     	}
     	//如果没有，就返回true
     	return true;
	} 									
	

	
	//只选中一个
	 function isOnlyChecked(){
    	 var checkBoxArray = document.getElementsByName('id');
			var count=0;
			for(var index=0; index<checkBoxArray.length; index++) {
				if (checkBoxArray[index].checked) {
					count++;
				}	
			}
		if(count==1)
			return true;
		else
			return false;
     }
	 
	//至少选中一个
	function isNullChecked(){
		var checkBoxArray=document.getElementsByName('id');
		var count=0;
		for(var index=0;index<checkBoxArray.length;index++){
			if (checkBoxArray[index].checked) {
				count++;
			}	
		}
		if(count==0){
			return false;
		}else{
			return true;
		}
	}
	
	 //查看方法
     function toView(){
    	if(isOnlyChecked()){
    		formSubmit('invoiceAction_toview','_self');
  		}else{
  			alert("请先选中一项并且只能选中一项，再进行操作！");
  		}
  		
     }
	
	//实现新增
	function toCreate(){
		formSubmit('${pageContext.request.contextPath }/cargo/invoiceAction_tocreate','_self');
	}
	 
    //实现更新
 	function toUpdate(){
 		if(isOnlyChecked()){
 			if(judgeState("已经财务报运，不能修改！")){
 	 			formSubmit('${pageContext.request.contextPath }/cargo/invoiceAction_toupdate','_self');
 			};
 		}else{
 			alert("请先选中一项并且只能选中一项，再进行操作！");
 		}
 	}
 	
 	//实现删除
 	function toDelete(){
     	if(isNullChecked()){
     		if(confirm("确定要删除该发票么")){
     			//判断状态
         		if(judgeState("已经财务报运，不能删除！")){
        		 	formSubmit('${pageContext.request.contextPath }/cargo/invoiceAction_delete','_self'); 
    			};
     		}
     		
     	}else{
     		 alert("请先选择至少一项，再进行操作！");
     	 }
      }
 	
 	//实现提交
 	function tosubmit(){
 		if(isOnlyChecked()){
 			//判断状态
     		if(judgeState("已经财务报运，不能提交！")){
     			formSubmit('${pageContext.request.contextPath }/cargo/invoiceAction_tosubmit','_self');
			};
         	
 		}else{
 			alert("请先选中一项并且只能选中一项，再进行操作！");
 		}
 	}
 	
 	//实现取消
 	function tocancel(){
 		if(isOnlyChecked()){
 			
 			//判断状态
     		if(judgeState("已经财务报运，不能取消！")){
     			formSubmit('${pageContext.request.contextPath }/cargo/invoiceAction_tocancel','_self');
            };
 			
 		}else{
 			alert("请先选中一项并且只能选中一项，再进行操作！");
 		}
 	}
	 
	</script>
	
</head>

<body>
<s:actionmessage cssStyle="list-style-type:none;" escape="false"/>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toView()">查看</a></li>
<li id="new"><a href="#" onclick="javascript:toCreate()">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:toDelete()">删除</a></li>
<li id="submit"><a href="#" onclick="javascript:tosubmit()">提交</a></li>
<li id="new"><a href="#" onclick="javascript:tocancel()">取消</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx}/skin/default/images/icon/currency_yen.png"/>
    发票列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">发票号</td>
		<td class="tableHeader">提单号</td>
		<td class="tableHeader">贸易条款</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td>
		<c:if test="${o.state<=1}">
			<input type="checkbox" name="id" value="${o.id}" />
		</c:if>
	<c:if test="${o.state>1}">
			<input type="hidden"   />
		</c:if>
		
		</td>
		<td>${status.index+1}</td>
		<td>
		<a href="invoiceAction_toview?id=${o.id}">
		${o.scNo}
		
		</td>
		<td>${o.blNo}</td>
		<td>${o.tradeTerms}</td> 
		<td class="state">
			<c:if test="${o.state==0}">草稿</c:if>
			<c:if test="${o.state==1}">已上报</c:if>
			<c:if test="${o.state==2}">已财务报运</c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

