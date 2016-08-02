<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商务综合管理平台</title>
	<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/login.css" media="all" />
	<script src="${ctx}/components/pngfix/DD_belatedPNG.js"></script>
	<script> DD_belatedPNG.fix('*'); </script>
	<script type="text/javascript">
		if(top.location != self.location){
			top.location = self.location;
		}
	</script>
	
	<!-- 利用校验，提示请输入用户名和密码 -->
	<style type="text/css">
	/* label标签下 class =error的标签 */	
	 label.error
		{
		color:Red;
		font-size:13px;
		/* margin-left:1px;
		padding-left:16px;
		 */
		}
	</style>
	<script type="text/javascript">
		$(function(){
			
				$("#login_main").validate({
					rules:{
						username:{
							required:true
						},
						password:{
							required:true
						}
					},
					messages:{
						username:{
							required:"用户名不能为空"
						},
						password:{
							required:"密码不能为空"
						}
					},
					/* 将错误信息统一到一个容器中进行显示 */
					 errorLabelContainer: "#errorId"
			});
		});
		
		function tologin(){
			if($("#login_main").valid()){
				formSubmit('${ctx}/login.action','_self');
			}else{
				return false;
			}
		}
	</script>
	
	
</head>

<body>
<form id="login_main" method="post"> 
<div id="png">
	<div class="box">
			<div class="inputstyle">
				<!-- 用于提示信息 -->
			<%-- 	<span style="color: red">${msg }</span> --%>
				<div class="inputlable">用户名：
					<input type="text" value="${userName}"  name="username"  id="userName" onFocus="this.select();" title="请您输入用户名"/>
					<div id="ts" style="z-index:1;">
					</div>
				</div>

			    <div class="inputlable">密　码：
					<input type="password" value="${password}" name="password" id="password" onfocus="$('#ts').css('display','none');this.select();"
						onKeyDown="javascript:if(event.keyCode==13){ submitFind(); }" title="请您输入密码"/>
				</div>
			</div>
			<div class="btnstyle">
				<%-- <input  class="loginImgOut" value="" type="button" onclick="formSubmit('${ctx}/login.action','_self');"
				  onmouseover="this.className='loginImgOver'" 
				  onmouseout="this.className='loginImgOut'"
				/> --%>
				<input  class="loginImgOut" value="" type="button" onclick="javascript:tologin();"
				  onmouseover="this.className='loginImgOver'" 
				  onmouseout="this.className='loginImgOut'"
				/>
				<input class="resetImgOut" value="" type="button"   
				  onmouseover="this.className='resetImgOver'" 
				  onmouseout="this.className='resetImgOut'"
				/>
			</div>
		  	<div class="msgtip" style="color: red" id="errorId">
				<c:if test="${!empty errorInfo}">
					${errorInfo}
				</c:if>
			</div>
	</div>
</div>
</form>

<script type="text/JavaScript">
	document.getElementById('login_main').userName.focus();
</script>

</body>
</html>


