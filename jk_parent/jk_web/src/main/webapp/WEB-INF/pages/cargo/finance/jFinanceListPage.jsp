<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<!--使用jsp校验，解决用户勾选的问题  -->
<script type="text/javascript" >
	$(function(){
		$("#barrage1").html(" <marquee  loop=1 scrollamount='20' ><font size='15px' color='red'> 66666</font></marquee>");
		$("#searchId").keyup(query);
	});
	setTimeout("span1()",2000);
	function span1(){
		$("#barrage2").html(" <marquee  loop=1 scrollamount='10' ><font size='18px' color='blue'>这项目做的不错  给满分</font></marquee>");
	}
	function changeZt(){
		var zt = $("#zhuangTai option:selected").val();
		var inputBy=$("#searchId").val();
		var order="${order1==1?'date1':'date0'}";
		var url="${ctx}/cargo/financeAction_query?inputBy="+inputBy+"&order="+order+"&state="+zt;
		window.location=url;
	}
	
	function orderByDate(){
		var zt = $("#zhuangTai option:selected").val();
		var inputBy=$("#searchId").val();
		var flag=${order1==1?0:1};
		if(flag==0){
			var url="${ctx}/cargo/financeAction_query?inputBy="+inputBy+"&order=date0&state="+zt;
			window.location=url;
		}else if(flag==1){
			var url="${ctx}/cargo/financeAction_query?inputBy="+inputBy+"&order=date1&state="+zt;
			window.location=url;
		}
	}
	var i=0;
	function barrage1(){
		var content=$("#barrage").val();
		if(content==""){
			return;
		}
		if(i==0){
			i++;
			$("#barrage1").html("<marquee  loop=1 scrollamount='15' ><font size='15px' color='red'>"+content+"</font></marquee>");
		}else
		if(i==1){
			$("#barrage2").html(" <marquee  loop=1 scrollamount='10' ><font size='18px' color='blue'>"+content+"</font></marquee>");
			i=0;		
		} 
	}
		//判断是否选择一个
		function  isOnlyChecked(){
			var count = $("input[name='id']:checked").size();
			
			return count==1;
		}
		//判断是否选中
		function isNullChecked(){
			var count = $("input[name='id']:checked").size();
		
			return count>0;
		}
		function todelete(){
			if(isNullChecked()){
				//如果符合，进跳转到程序中
				formSubmit('financeAction_delete','_self');
			}else{
				alert("请至少选择一项进行操作");
			} 
		}
		function toupdate(){
			if(isOnlyChecked()){
				formSubmit("financeAction_toupdate","_self");
			}else{
				alert("请选择一项，并且只能选择一项");
			}
		}
		/*查看校验  */
		function toview(){

			if(isOnlyChecked()){
				//如果符合，进跳转到程序中
				formSubmit('financeAction_toview','_self');
			}else{
				alert("请选择一项，并且只能选择一项");
			} 
		}
		
		
		/*
			内容搜索
		*/
		function query(){
			//获得键盘输入内容
			var inputVal = this.value;
			// 优化：如果没有数据不查询
			if(inputVal == ""){
				// div隐藏
				$("#completeShow").hide();
				return;
			}
			// 发送ajax进行查询所有
			$.get("${ctx}/cargo/financeAction_findByInput",{"inputBy":inputVal},function(data){
				// 遍历所有数据
				$("#completeShow ul").html("");	//清空
				var zt = $("#zhuangTai option:selected").val();
				var order="${order1==1?'date1':'date0'}";
				for(var i = 0 ; i < data.length ; i ++){
					var p = data[i];
					var t = p.inputBy;  
					// 处理高亮 -- 遍历关键字，将每一个字符使用<font>括起来
					for(var m = 0 ; m < inputVal.length ; m ++){
						var str = inputVal.substring(m , m + 1);
						t = t.replace(str,"<font color='#f00'>"+str+"</font>");
					}
					$("#completeShow ul").append("<li =><a href='${ctx}/cargo/financeAction_query?inputBy="+inputVal+"&order="+order+"&state="+zt+"'><font size=2px>"+t+"</font></a></li>");
				}
				// 将div显示
				$("#completeShow").show();
			},"json");
		}
	</script>
	<style type="text/css">
		.list li{list-style-type:none;
				margin-right: 200px;
		}
		#completeShow{
			border: 1px solid #999;
			height: 200px;
			position: absolute;
			width: 196px;
			z-index: 1000;
			background-color: #fff;
			border-radius: 5px;
			display: none;
			}
	</style>
</head>

<body>
<embed width=1 height=1 loop=true  hidden=true src="${ctx}/1.mp3" ></embed>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="toview();this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('financeAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="toupdate();this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="todelete();this.blur();">删除</a></li>
<li style="width:160px"><input type="text" id="barrage" style="width:160px" value=""/></li>
<li ><input type="button"  id="btn" onclick="barrage1()" style="margin-left: 20px;width: 80px" value="发送弹幕"/></li>
</ul>
  </div>
</div>
</div>
</div>
<div id="middleMenubar" style="margin-right: 600px">

	<font size="3px" >制单人搜索:</font><input type="text" id="searchId" value="${input }"/ >&nbsp;&nbsp;&nbsp;&nbsp;
	<font size="3px" >状态:</font>
	<select id="zhuangTai" onchange="changeZt()" name="zt">
		<option value="" >全部状态</option>
		<option value="1" ${zhuangtai==1?"selected='selected'":""}>启用</option>
		<option value="0" ${zhuangtai==0?"selected='selected'":""}>停用</option>
	</select>
	<div id="completeShow" style="margin-left:110px">
      	<ul class="list" >
		</ul>
    </div>&nbsp;&nbsp;&nbsp;
    <input type="button" onclick="changeZt()" style="width: 80px" value="查询"/>
</div> 
  <div class="textbox-title">
  <img src="${ctx}/skin/default/images/icon/currency_yen.png"/>
    财务列表
  </div> 
  
  <div id="barrage1"  style="height:60px"></div>
<div>
<div class="eXtremeTable" >

<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader"><a href="#" onclick="orderByDate()">制单日期排序${order0==0?"▼":"▲"}</a></td>
		<td class="tableHeader">制单人</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.inputDate}</td>
		<td>${o.inputBy}</td>
		<td>${o.state==0?"停用":"启用"}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
</div>
 
</form>
	<font size="3px">	<c:choose>
			<c:when test="${totalPage<=10 }">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="${totalPage }"/>
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="${pageNo-5 }"/>
				<c:set var="end" value="${pageNo+5 }"/>
				<c:if test="${begin<1 }">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="10"/>
				</c:if>
				<c:if test="${end>totalPage }">
					<c:set var="begin" value="${totalPage-9}"/>
					<c:set var="end" value="${totalPage}"/>
				</c:if>
			</c:otherwise>
		</c:choose>
		第${pageNo}页/共${totalPage}页
		<a href="${url }page.pageNo=${1}"><font size="3px">首页</font></a>
		<c:if test="${pageNo>1 }">
			<a href="${url }page.pageNo=${pageNo-1}"><font size="3px">上一页</font></a>
		</c:if>
		<c:forEach var="i" begin="${begin }" end="${end }">
			<c:choose >
				<c:when test="${pageNo==i }">
					[${i}]
				</c:when>
				<c:otherwise>
					<a href="${url }page.pageNo=${i }"><font size="3px">[${i}]</font>	</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageNo!=totalPage }">
			<a href="${url }page.pageNo=${pageNo+1}"><font size="3px">下一页</font></a>
		</c:if>
		<a href="${url }page.pageNo=${totalPage}"><font size="3px">尾页</font></a>
	</font>
</body>
  <div id="barrage2"  style="height:60px"></div>
</html>

