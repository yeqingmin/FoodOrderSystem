<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/3
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reviewPage.css" type="text/css">
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜品操作 >> 菜品详细信息 >> 评价菜品</span>
    </div>
    <div class="review-container">
        <%--    <p class="dishId" hidden="hidden">${dish.dishId}</p>--%>
        <h1 class="dish-name">${dish.dishName}</h1>
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
        <button class="submit-review" dishId=${dish.dishId}>提交评价</button>
    </div>
</div>
</section>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishReview.js"></script>