<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/21
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/merchant/merchantCommon/head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reviewPage.css" type="text/css">
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜单管理 >> 菜品用户画像</span>
    </div>
    <input type="hidden" id="dishId" value="${dish.dishId}">
    <div id="main" style="width: 900px;height:600px;">
    </div>

</div>
</section>

<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.5.0/dist/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishCustomerAnalysis.js"></script>