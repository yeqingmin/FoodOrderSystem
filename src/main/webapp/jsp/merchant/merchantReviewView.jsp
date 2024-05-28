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
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">评价用户的id</th>
            <th width="10%">评分</th>
            <th width="80%">评价内容</th>
        </tr>
        <c:forEach var="review" items="${reviewList}" varStatus="status">
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
</div>
</section>


<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantReviewView.js"></script>