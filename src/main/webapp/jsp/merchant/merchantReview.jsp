<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/3
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reviewPage.css" type="text/css">
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>历史订单>> 订单详情页 >> 评价商户</span>
    </div>
    <div class="review-container">
        <%--    <p class="merchantId" hidden="hidden">${merchant.merchantId}</p>--%>
        <h1 class="merchant-name">${merchant.merchantName}</h1>
        <textarea class="review-text" placeholder="请输入您的评论..."></textarea>
        <div class="rating">
            <label for="rating">评分:</label>
            <select id="rating" name="rating">
                <option value="">请选择</option>
                <!-- 生成1到10的选项 -->
                <% for (int i = 1; i <= 10; i++) { %>
                <option value="<%= i %>"><%= i %>
                </option>
                <% } %>
            </select>
        </div>
        <button class="submit-review" merchantId=${merchant.merchantId}>提交评价</button>
    </div>
</div>
</section>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantReview.js"></script>