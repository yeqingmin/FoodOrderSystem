<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/21
  Time: 15:32
  To change this template use File | Settings | File Templates.
  当前页面使用户活跃度分析的主界面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>活跃度分析</span>
    </div>
    <div class="providerAddBtn">
        <input type="button" class="week" value="查看每周点餐频率">
        <input type="button" class="month"  value="查看每月点餐频率">
        <input type="button" class="activity" value="查看不同时段的活跃程度">
    </div>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/activityHomePage.js"></script>
