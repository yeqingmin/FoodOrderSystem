<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/26
  Time: 14:57
  To change this template use File | Settings | File Templates.
  当前是用户看到的商户详情页，包含点餐按钮和预订餐厅按钮
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作 >> 商户详情页</span>
    </div>
    <div class="providerView">
        <%--            <p><strong>用户编号：</strong><span>${user.userCode }</span></p>--%>
        <p><strong>商户名称：</strong><span>${merchant.merchantName }</span></p>
        <p><strong>商户地址：</strong><span>${merchant.merchantAddr }</span></p>
        <p>
            <strong>商户菜单：</strong>
        <form id="menuForm" method="get" action="${pageContext.request.contextPath }/jsp/merchant">
            <input name="method" value="queryMenu" class="input-text" type="hidden">
            <input type="hidden" name="merchantId" id="merchantId" value="${merchant.merchantId}"/>
        </form>
        <div class="providerAddBtn">
            <input type="button" id="menu" name="menu" value="查看菜单">
        </div>
        </p>
        <%--        <p><strong>用户地址：</strong><span>${user.address }</span></p>--%>
        <%--        <p><strong>用户角色：</strong><span>${user.userRoleName}</span></p>--%>
        <div class="providerAddBtn">
            <input type="button" class="favor" merchantId=${merchant.merchantId} merchantName=${merchant.merchantName} value="收藏商家">
            <input type="button" class="review" merchantId=${merchant.merchantId} merchantName=${merchant.merchantName} value="评价商家">
            <input type="button" class="queryReview" merchantId=${merchant.merchantId} merchantName=${merchant.merchantName} value="查看评价和评分">
        </div>
        <div class="providerAddBtn">
            <%--            先提交隐藏表单交给后端merchantServlet进行一个方法：createOrder先插入一条Order数据（获取merchantId通过表单提交,userId通过获取session），然后重定向到orderPage.jsp页面--%>
            <form id="orderForm" method="get" action="${pageContext.request.contextPath }/jsp/merchant">
                <input name="method" value="createOrderAndListMenu" class="input-text" type="hidden">
                <input type="hidden" name="merchantId" id="orderMerchantId" value="${merchant.merchantId}"/>
            </form>
            <input type="button" id="orderOnline" name="book" value="线上点餐">
        </div>
        <div class="providerAddBtn">
            <input type="button" id="book" value="预订" merchantId="${merchant.merchantId}">
        </div>
        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回">
        </div>
    </div>
</div>
<div class="zhezhao"></div>
<div class="remove" id="favorMerchant">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要收藏该商家吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userMerchantView.js"></script>
