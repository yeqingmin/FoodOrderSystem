<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/3
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/merchant/merchantCommon/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜品操作 >> 菜品详细信息 >> 查看菜品评价</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/dish">
            <input name="method" value="queryReview" class="input-text" type="hidden">
            <input name="dishId" value="${dishId}" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
        </form>
    </div>
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">评价用户</th>
            <th width="80%">评价内容</th>
            <th width="10%">评分</th>
        </tr>
        <c:forEach var="review" items="${udReviewList}" varStatus="status">
            <tr>
                <td>
                    <span>${review.userId}</span>
                </td>
                <td>
                    <span>${review.dishComment}</span>
                </td>
                <td>
                    <span>${review.dishRating}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>


<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishReviewView.js"></script>
