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
    <table class="providerTable">
        <tr class="firstTrr">
            <th class="messageTh">商家名称</th>
            <th class="messageTh">评价内容</th>
            <th class="messageTh">评分</th>
            <th class="messageTh">评价时间</th>
        </tr>
        <c:forEach var="order" items="${orderMessages}">
            <tr class="firstTr">
                <td class="messageTd">${order.merchantName}</td>
                <td class="messageTd">${order.reviewContent == null ? "未评价" : order.reviewContent}</td>
                <td class="messageTd">${order.rating == null ? "未评分" : order.rating}</td>
                <td class="messageTd">${order.rating == null ? "未评分" : order.rating}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
