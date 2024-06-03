<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/29
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/merchant/merchantCommon/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜单管理 >> 修改菜品</span>
    </div>
    <div class="providerAdd">
        <form id="dishForm" name="dishForm" method="post" action="${pageContext.request.contextPath }/jsp/dish">
            <input type="hidden" name="method" value="modifyExecute">
            <input type="hidden" name="dishId" value="${dish.dishId}">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div>
                <label for="dishName">菜品名称：</label>
                <input type="text" name="dishName" id="dishName" value="${dish.dishName}">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishPrice">菜品价格：</label>
                <input type="text" name="dishPrice" id="dishPrice" value="${dish.dishPrice }">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishCategory">菜品分类：</label>
                <input type="text" name="dishCategory" id="dishCategory" value="${dish.dishCategory}">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishDescription">菜品描述：</label>
                <input type="text" name="dishDescription" id="dishDescription" value="${dish.dishDescription}">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishAllergens">菜品过敏源：</label>
                <input type="text" name="dishAllergens" id="dishAllergens" value="${dish.dishAllergens}">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishIngredients">菜品成分：</label>
                <input type="text" name="dishIngredients" id="dishIngredients" value="${dish.dishIngredients}">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishNutrition">菜品营养信息：</label>
                <input type="text" name="dishNutrition" id="dishNutrition" value="${dish.dishNutrition}">
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存">
                <input type="button" id="back" name="back" value="返回">
            </div>
        </form>
    </div>

</div>
</section>

<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishmodify.js"></script>