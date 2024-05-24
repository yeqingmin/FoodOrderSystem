<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/merchant/merchantCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户中心</span>
    </div>
    <div class="providerView">
        <%--            <p><strong>用户编号：</strong><span>${user.userCode }</span></p>--%>
        <p><strong>商户名称：</strong><span>${merchant.merchantName }</span></p>
        <p><strong>商户地址：</strong><span>${merchant.merchantAddr }</span></p>
        <p>
            <strong>商户菜单：</strong>
        </p>
<%--        <p><strong>用户地址：</strong><span>${user.address }</span></p>--%>
<%--        <p><strong>用户角色：</strong><span>${user.userRoleName}</span></p>--%>
        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回" >
        </div>
    </div>
</div>
</section>
<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userview.js"></script>
