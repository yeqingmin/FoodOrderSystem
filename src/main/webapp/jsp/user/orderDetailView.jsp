<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/4
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作 >> 商户详细信息 >> 线上点餐页面 >> 订单内容查看</span>
    </div>
    <h1>下单成功!</h1>
    <h3>下单菜品列表</h3>
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="25%">菜品名称</th>
            <th width="25%">菜品数量</th>
            <th width="25%">菜品总价</th>
            <th width="25%">菜品分类</th>
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
                    <!-- 假设每个菜品对象也有一个分类属性 -->
                    <span>${dish.dishCategory}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>菜品总价：${dishSumPrice}</h2>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>


<%--    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>--%>
<%--    <c:import url="rollpage.jsp">--%>
<%--        <c:param name="totalCount" value="${totalCount}"/>--%>
<%--        <c:param name="currentPageNo" value="${currentPageNo}"/>--%>
<%--        <c:param name="totalPageCount" value="${totalPageCount}"/>--%>
<%--    </c:import>--%>
<%--</div>--%>
<%--</section>--%>

<%--<%@include file="/jsp/user/userCommon/foot.jsp" %>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderList.js"></script>--%>

