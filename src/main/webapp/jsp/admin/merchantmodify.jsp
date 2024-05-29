<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/29
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/admin/adminCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户管理页面 >> 商户修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="merchantForm" name="merchantForm" method="post" action="${pageContext.request.contextPath }/jsp/merchant">
            <input type="hidden" name="method" value="modifyExecute">
            <input type="hidden" name="merchantId" value="${merchant.merchantId}"/>
            <div>
                <label for="merchantName">商户名称：</label>
                <input type="text" name="merchantName" id="merchantName" value="${merchant.merchantName}">
                <font color="red"></font>
            </div>
            <div>
                <label >商户地址：</label>
                <input type="text" name="merchantAddr" id="merchantAddr" value="${merchant.merchantAddr}">
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存" />
                <input type="button" id="back" name="back" value="返回"/>
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/admin/adminCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantmodify.js"></script>


