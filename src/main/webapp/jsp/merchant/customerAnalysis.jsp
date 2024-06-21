<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/21
  Time: 17:09
  To change this template use File | Settings | File Templates.
  //直接展示三张饼图，当前商户的gender分布图，role分布图和age分布图
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/merchant/merchantCommon/head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reviewPage.css" type="text/css">
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>用户画像</span>
  </div>
  <div id="main" style="width: 900px;height:600px;">
  </div>

</div>
</section>

<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.5.0/dist/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/customerAnalysis.js"></script>
