<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/27
  Time: 12:00
  To change this template use File | Settings | File Templates.
  当前页面表示对一个商户所有评价信息的列表
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作 >> 当前商户的所有评价列表</span>
    </div>
    <form method="get" action="${pageContext.request.contextPath }/jsp/merchant">
        <input name="method" value="queryReview" class="input-text" type="hidden">
        <input name="merchantId" value="${merchantId}" type="hidden">
        <input type="hidden" name="pageIndex" value="1"/>
    </form>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">评价用户的id</th>
            <th width="10%">评分</th>
            <th width="80%">评价内容</th>
        </tr>
        <c:forEach var="review" items="${umReviewList}" varStatus="status">
            <tr>
                <td>
                    <span>${review.userId}</span>
                </td>
                <td>
                    <span>${review.merchantRating}</span>
                </td>
                <td>
                    <span>${review.merchantComment}</span>
                </td>
        </c:forEach>
    </table>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>


<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantReviewView.js"></script>