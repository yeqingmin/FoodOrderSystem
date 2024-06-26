<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>食堂点单系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>食堂点单系统</h1>
        <div class="publicHeaderR">
            <p>欢迎你！</p>
            <a href="${pageContext.request.contextPath }/jsp/logout">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
         <nav>
             <ul class="list">
                 <li><a href="${pageContext.request.contextPath }/jsp/merchant?method=view">商户中心</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/dish?method=merchantManage">菜单管理</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/merchant?method=analysis">菜品数据分析</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/merchant?method=loyalUser">忠实用户列表</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/merchant?method=customerAnalysis">用户画像</a></li>
             </ul>
         </nav>
     </div>
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>