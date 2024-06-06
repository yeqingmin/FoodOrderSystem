<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/29
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/merchant/merchantCommon/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜单管理 >> 添加菜品</span>
    </div>
    <div class="providerAdd">
        <form id="dishForm" name="dishForm" method="post" action="${pageContext.request.contextPath }/jsp/dish">
            <input type="hidden" name="method" value="addDish">
            <div>
                <label for="dishName">菜品名称：</label>
                <input type="text" name="dishName" id="dishName" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishPrice">菜品价格：</label>
                <input type="text" name="dishPrice" id="dishPrice" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishCategory">菜品分类：</label>
                <input type="text" name="dishCategory" id="dishCategory" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishDescription">菜品描述：</label>
                <input type="text" name="dishDescription" id="dishDescription" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishAllergens">菜品过敏源：</label>
                <input type="text" name="dishAllergens" id="dishAllergens" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishIngredients">菜品成分：</label>
                <input type="text" name="dishIngredients" id="dishIngredients" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="dishNutrition">菜品营养信息：</label>
                <input type="text" name="dishNutrition" id="dishNutrition" value="">
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" id="back" name="back" value="返回" >
                <input type="submit" id="add" name="add" value="提交" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishadd.js"></script>