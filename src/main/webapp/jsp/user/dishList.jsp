<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜品操作</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/dish">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>商户名称：</span>
            <input name="merchantName" class="merchantName" type="text" value="${merchantName}">

            <span>商户地址：</span>
            <select name="merchantAddr" class="merchantAddr"></select>

            <span>菜品：</span>
            <input name="dishName" type="text" value="${dishName}">

            <input value="查 询" type="submit" id="searchbutton">
        </form>
    </div>
    <!--商户表格样式-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <%--            <th width="10%">商户编码</th>--%>
            <th width="10%">菜品名称</th>
            <th width="10%">菜品商户</th>
            <th width="10%">菜品价格</th>
            <th width="10%">菜品分类</th>
            <th width="20%">菜品描述</th>
            <%--            <th width="10%">创建时间</th>--%>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>
                <td>
                    <span>${dish.merchantId}</span>
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
                    <span>
                        <a class="viewDish" href="javascript:;" dishid=${dish.dishId}>
                            <div class="providerAddBtn">
                                <input type="button" value="查看详情">
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
<div class="remove" id="removeMerchant">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该菜品吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishList.js"></script>
