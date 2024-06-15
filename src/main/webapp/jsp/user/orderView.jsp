<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/5
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>历史订单 >> 订单详细内容查看</span>
    </div>
    <h3>下单菜品列表</h3>
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="20%">菜品名称</th>
            <th width="20%">菜品数量</th>
            <th width="20%">菜品总价</th>
            <th width="20%">菜品分类</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <c:set var="dishCount" value="${dish.totalCount}"/>
            <c:set var="totalPrice" value="${dish.dishPrice * dishCount}"/>
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>
                <td>
                    <span>${dishCount}</span>
                </td>
                <td>
                    <span>${totalPrice}</span>
                </td>
                <td>
                    <span>${dish.dishCategory}</span>
                </td>
                <td>
                    <span class="providerAddBtn">
                        <input type="button" class="dishReview" dishId=${dish.dishId} dishName=${dish.dishName} value="评价菜品">
                    </span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>菜品总价：${dishSumPrice}</h2>
    <div class="providerAddBtn">
        <input type="button" class="merchantReview" merchantId=${merchantId}  value="评价商家">
    </div>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderDetailView.js"></script>

<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderList.js"></script>--%>


