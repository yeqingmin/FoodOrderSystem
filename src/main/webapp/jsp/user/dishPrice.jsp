<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/6
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reviewPage.css" type="text/css">
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜品操作 >> 菜品详细信息 >> 菜品价格历史</span>
    </div>
    <div id="main" style="width: 900px;height:600px;">
    </div>
    <div class="providerAddBtn">
        <input type="button" id="update" dishId=${dishId}  value="查看图表">
    </div>
</div>
</section>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.5.0/dist/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishPrice.js"></script>