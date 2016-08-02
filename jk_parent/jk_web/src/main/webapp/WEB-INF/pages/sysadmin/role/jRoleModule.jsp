<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<link rel="stylesheet" href="${ctx }/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" />

	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript">
	/* 	var zTreeObj;
		var setting = {
			check : {
				enable : true//设置节点上是否显示单选框和复选框，true表示显示
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		
		$(document).ready(function() {
			$.ajax( {
				url : "${ctx}/sysadmin/roleAction_roleModuleJsonStr2.action?id=${id}",
				type : "get",
				dataType : "text",
				success : initZtree
			});
		}); 
		
		//初始化ZTree树
		function initZtree(data) {
			var zNodes = eval("(" + data + ")");		//将返回的data转换为json串
			zTreeObj = $.fn.zTree.init($('#jkTree'), setting, zNodes);	//jkTree 树的id，支持多个树
			zTreeObj.expandAll(true);		//展开所有树节点
		} 
		 */
		/*  //获取所有选择的节点
		function submitCheckedNodes() {
			var nodes = new Array();
			nodes = zTreeObj.getCheckedNodes(true);		//获取所有选中的节点
			var str = "";
			for (i = 0; i < nodes.length; i++) {
				if (str != "") {
					str += ",";
				}
				str += nodes[i].id;//获取节点的id值，进行拼接
			}
			$('#moduleIds').val(str);//设置moduleIds的值
		}   */
		/* 尝试写自己的ztree */
	 	var ztreeObj; //定义ZTree树
		//设置ztree参数
		var setting={
			check: {
				enable: true//设置是否显示checkbox
			},

			data:{
				simpleData:{
					enable:true //采用简单数据模式
				}	
			}
		};
		$(function(){
			//实现ajax请求
			$.ajax({

				url:"${ctx}/sysadmin/roleAction_roleModuleJsonStr4.action?id=${id}",//请求路径
				type:"get",//请求方式
				dataType:"text",//返回数据格式
				success:function(data){//回调函数
					ztreeObj=$("#jkTree");//获取ztree对象
					var zNodes = eval("("+data+")");//将data转换为json串
					ztreeObj=$.fn.zTree.init(ztreeObj, setting, zNodes);//初始化ztree树
					ztreeObj.expandAll(true);	//展开所有的节点
				}
			});
		}); 
		function submitCheckedNodes(){//提交时获取，所有选择的节点的id值，并进行拼接
			//获取所有已选择的节点
			var nodes = new Array();
			nodes = ztreeObj.getCheckedNodes(true);
			var  str = "";
			for(var i = 0;i<nodes.length;i++){
				if(str != ""){
					str+=",";
				}
				str+=nodes[i].id;
			}
			$('#moduleIds').val(str);
		}
	</script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>
	<!-- 用于传递选中的权限的值 -->
	 
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<!-- <li id="save"><a href="#" onclick="formSubmit('roleAction_module','_self');this.blur();">保存</a></li> -->
 <li id="save"><a href="#" onclick="submitCheckedNodes();formSubmit('roleAction_module','_self');this.blur();">保存</a></li>
 
 <li id="back"><a href="#" onclick="formSubmit('roleAction_list','_self');this.blur();">返回</a></li> 
<input type="hidden" id="moduleIds" name="moduleIds" value="1" /> 

</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    配置 [${name}] 角色的模块  
  </div> 
  </div>
  </div>
  



 <%-- <div style="text-align:left">
	<c:forEach items="${requestScope.moduleList}" var="o">
		<div style="padding:3px;">
		<input type="checkbox" name="moduleIds" value="${o.id}" class="input"
			<c:if test="${fn:contains(modules,o)}">checked</c:if>
		>
		${o.name}
		
	</c:forEach>
</div> --%> 
 

<div>  
	<ul id="jkTree" class="ztree"></ul>   
</div>
 
 
</form>
</body>
</html>

