<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/26
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
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
        <p><strong>菜品收藏量：</strong><span>${dish.dishFavourNumber }</span></p>
        <p><strong>菜品线上销量：</strong><span>${onlineOrderNum}</span></p>
        <p><strong>菜品线下销量：</strong><span>${offlineOrderNum}</span></p>

        <%--        <p><strong>菜品描述：</strong><span>${user.address }</span></p>--%>

        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回">
            <input type="button" class="favor" dishId=${dish.dishId} dishName=${dish.dishName} value="收藏菜品">
        </div>
        <div class="providerAddBtn">
            <input type="button" class="queryReview" dishId=${dish.dishId}  value="查看菜品评价评分">
        </div>
    </div>
</div>
</section>
<!--点击收藏按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="favorDish">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要收藏该菜品吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishview.js"></script>