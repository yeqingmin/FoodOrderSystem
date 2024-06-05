<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>系统消息</span>
    </div>
    <div class="providerAddBtn">
        <input type="button" class="order" userId=${userId} value="查看订单消息">
        <input type="button" class="book" userId=${userId} value="查看预订消息">
    </div>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/message.js"></script>