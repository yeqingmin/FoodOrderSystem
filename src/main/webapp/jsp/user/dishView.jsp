<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/26
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜品操作 >> 菜品详细信息</span>
    </div>
    <div class="providerView">
        <%--            <p><strong>用户编号：</strong><span>${user.userCode }</span></p>--%>
        <p><strong>菜品名称：</strong><span>${dish.dishName }</span></p>
        <p><strong>菜品价格：</strong><span>${dish.dishPrice }</span></p>
        <p><strong>菜品分类：</strong><span>${dish.dishCategory }</span></p>
        <p><strong>菜品描述：</strong><span>${dish.dishDescription }</span></p>
        <p><strong>菜品过敏源：</strong><span>${dish.dishAllergens}</span></p>
        <p><strong>菜品成分：</strong><span>${dish.dishIngredients }</span></p>
        <p><strong>菜品营养信息：</strong><span>${dish.dishNutrition }</span></p>
<%--        <p><strong>菜品描述：</strong><span>${user.address }</span></p>--%>

        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回" >
        </div>
    </div>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishview.js"></script>