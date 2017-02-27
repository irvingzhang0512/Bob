<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    Integer id = (Integer)session.getAttribute("id");
    String name = (String)session.getAttribute("name");
    String meter_number = (String)session.getAttribute("meter_number");
    String joinDate = (String)session.getAttribute("joinDate");
    String isAlive = (String)session.getAttribute("isAlive");
    if( isAlive == null ) isAlive = "未知";
    String energySource = (String)session.getAttribute("energySource");
    if( energySource == null ) energySource = "未知";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>个人信息</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="isAlive" action="/user/isAliveLocalSystem">

		<div class="row cl">
    		<label class="form-label col-xs-4 col-sm-3">用户名：</label>
    		<div class="formControls col-xs-8 col-sm-9" style="text-align:left"><%=name%></div>
    	</div>
    	<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">注册时间：</label>
            <div class="formControls col-xs-8 col-sm-9" style="text-align:left"><%=joinDate%></div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">电表号：</label>
            <div class="formControls col-xs-8 col-sm-9" style="text-align:left"><%=meter_number%></div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">本地管理系统连接状态：</label>
            <div class="formControls col-xs-8 col-sm-9" style="text-align:left"><%=isAlive%></div>
        </div>

        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;查询本地管理系统状态&nbsp;&nbsp;">
		</div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">本地管理系统能量源：</label>
            <div class="formControls col-xs-8 col-sm-9" style="text-align:left"><%=energySource%></div>
        </div>
	</form>


	<form class="form form-horizontal" id="isAlive" action="/user/supply">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;使用市电&nbsp;&nbsp;">
		</div>
	</form>

	<div class="row cl"> <label class="form-label col-xs-4 col-sm-3"> </label></div>

	<form class="form form-horizontal" id="isAlive" action="/user/battery">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
    			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;使用电池&nbsp;&nbsp;">
    		</div>
    	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>