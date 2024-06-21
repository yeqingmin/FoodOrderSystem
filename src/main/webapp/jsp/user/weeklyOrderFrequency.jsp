<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reviewPage.css" type="text/css">
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>活跃度分析 >> 每星期的点餐频率变化趋势</span>
    </div>
    <div id="main" style="width: 900px;height:600px;">
    </div>
    <div class="providerAddBtn">
        <input type="button" id="update"  value="查看图表数据">
    </div>
</div>
</section>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.5.0/dist/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/weekly.js"></script>

