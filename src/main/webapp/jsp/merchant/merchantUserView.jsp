<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/26
  Time: 14:57
  To change this template use File | Settings | File Templates.
  当前是用户看到的商户详情页，包含点餐按钮和预订餐厅按钮
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户详情页</span>
    </div>
    <div class="providerView">
        <%--            <p><strong>用户编号：</strong><span>${user.userCode }</span></p>--%>
        <p><strong>商户名称：</strong><span>${merchantDetail.merchant.merchantName }</span></p>
        <p><strong>商户地址：</strong><span>${merchantDetail.merchant.merchantAddr }</span></p>
        <p>
            <strong>商户菜单：</strong>
        </p>
        <%--        <p><strong>用户地址：</strong><span>${user.address }</span></p>--%>
        <%--        <p><strong>用户角色：</strong><span>${user.userRoleName}</span></p>--%>
        <div class="providerAddBtn">
            <input type="button" id="favor" name="favor" value="收藏" >
        </div>
        <div class="providerAddBtn">
            <input type="button" id="book" name="book" value="预订点餐" >
        </div>
        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回" >
        </div>
    </div>
</div>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userMerchantView.js"></script>
