<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/26
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作 >> 商户详细信息 >> 商户菜单查看</span>
    </div>
    <!--账单表格 样式和供应商公用-->
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
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantMenuReviewView.js"></script>
