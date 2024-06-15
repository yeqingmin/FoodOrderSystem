<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/15
  Time: 10:24
  To change this template use File | Settings | File Templates.
  菜品收藏列表
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>收藏列表</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/dish">
            <input name="method" value="favorView" class="input-text" type="hidden">
            <div class="time-range-selector" style="display: inline-block">
                <label for="timeRange">选择时间范围:</label>
                <select id="timeRange" name="timeRange">
                    <option value="lastWeek">近一周</option>
                    <option value="lastMonth">近一月</option>
                    <option value="lastYear">近一年</option>
                </select>
            </div>
            <div style="width: 40px;height:20px; display: inline-block">
                <input value="查 看" type="submit" id="searchbutton">
            </div>
        </form>
    </div>
    <!--商户表格样式-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <%--            <th width="10%">商户编码</th>--%>
            <th width="10%">菜品名称</th>
            <th width="45%">线上销量</th>
            <th width="45%">线下销量</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>
                <td>
                    <span>${dish.onlineSales}</span>
                </td>
                <td>
                    <span>${dish.offlineSales}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishfavorView.js"></script>
