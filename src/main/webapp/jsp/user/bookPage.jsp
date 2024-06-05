<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/5
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<link rel="stylesheet" href="../css/bookStyle.css">
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作 >> 商户详细信息 >> 预订页面</span>
    </div>
    <div class="container">
        <h1>预订餐厅</h1>
        <form method="get" action="${pageContext.request.contextPath }/jsp/order">
            <input name="method" value="book" type="hidden">
            <input name="merchantId" value="${merchantId}" type="hidden">
            <label for="startTime">开始时间:</label>
            <input type="datetime-local" id="startTime" name="startTime" required>

            <label for="endTime">结束时间:</label>
            <input type="datetime-local" id="endTime" name="endTime" required>

            <button type="submit">提交预订</button>
        </form>
    </div>
</div>
</section>

<div class="providerAddBtn">
    <input type="button" id="back" name="back" value="返回">
</div>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/bookPage.js"></script>--%>