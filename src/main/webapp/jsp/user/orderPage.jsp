<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/26
  Time: 16:52
  To change this template use File | Settings | File Templates.
  当前为点餐页面，显示的就是当前商户的菜单，每条菜品的操作有加入菜单，移出菜单两个操作
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作 >> 商户详细信息 >> 线上点餐页面</span>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <%--            <th width="10%">商户编码</th>--%>
            <th width="10%">菜品名称</th>
            <th width="10%">菜品价格</th>
            <th width="10%">菜品分类</th>
            <th width="20%">菜品描述</th>
            <th width="10%">菜品数量</th>
            <%--            <th width="10%">创建时间</th>--%>
            <th width="40%">操作</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>

                <td>
                    <span>${dish.dishPrice}</span>
                </td>
                <td>
                    <span>${dish.dishCategory}</span>
                </td>

                <td>
                    <span>${dish.dishDescription}</span>
                </td>
                <td>
                    <span class="quantityForDish" dishId="${dish.dishId}">0</span>
                </td>
                <td>
                    <span>
                        <a class="addDishToOrder" href="javascript:;" dishId=${dish.dishId} orderId=${orderId} dishName=${dish.dishName}>
                            <div class="providerAddBtn">
                                <input type="button" value="加入餐单">
                            </div>
                        </a>
                    </span>
                    <span>
                        <a class="deleteDishFromOrder" href="javascript:;" dishId=${dish.dishId} orderId=${orderId} dishName=${dish.dishName}>
                            <div class="providerAddBtn">
                                <input type="button" value="移出餐单">
                            </div>
                        </a>
                    </span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeDish">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain" >
            <p>你确定要删除该供应商吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<div class="providerAddBtn">
    <input type="button" id="back" name="back" value="返回">
</div>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderPage.js"></script>
