<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/merchant/merchantCommon/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>忠实用户列表 >> 消费分布</span>
    </div>
<%--    <div class="search">--%>
<%--        <form method="get" action="${pageContext.request.contextPath }/jsp/dish">--%>
<%--            <input name="method" value="merchantManage" class="input-text" type="hidden">--%>
<%--            <span>菜品名称：</span>--%>
<%--            <input name="dishName" type="text" value="${dishName}">--%>
<%--            <input type="hidden" name="pageIndex" value="1"/>--%>
<%--            <a href="${pageContext.request.contextPath}/jsp/merchant/dishadd.jsp">添加菜品</a>--%>
<%--        </form>--%>
<%--    </div>--%>
    <!--商户表格样式-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="20%">菜品名称</th>
            <th width="20%">菜品分类</th>
            <th width="20%">购买次数</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>
                <td>
                    <span>${dish.dishCategory}</span>
                </td>
                <td>
                    <span>${dish.totalCount}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
<%--    <c:import url="rollpage.jsp">--%>
<%--        <c:param name="totalCount" value="${totalCount}"/>--%>
<%--        <c:param name="currentPageNo" value="${currentPageNo}"/>--%>
<%--        <c:param name="totalPageCount" value="${totalPageCount}"/>--%>
<%--    </c:import>--%>
</div>

</section>

<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishManageList.js"></script>


