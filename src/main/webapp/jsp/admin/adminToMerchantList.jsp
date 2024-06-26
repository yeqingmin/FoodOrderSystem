<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/admin/adminCommon/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户管理</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/merchant">
            <input name="method" value="adminManage" class="input-text" type="hidden">
            <span>商家名称：</span>
            <input name="merchantName" type="text" value="${merchantName}">
            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath}/jsp/admin/merchantadd.jsp" >添加商户</a>
        </form>
    </div>
    <!--商户表格样式-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">商户编码</th>
            <th width="20%">商品名称</th>
            <th width="20%">商品地址</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="merchant" items="${merchantList}" varStatus="status">
            <tr>
                <td>
                    <span>${merchant.merchantId}</span>
                </td>
                <td>
                    <span>${merchant.merchantName}</span>
                </td>
                <td>
                    <span>${merchant.merchantAddr}</span>
                </td>
                <td>
                    <span><a class="deleteMerchant" href="javascript:;" merchantId=${merchant.merchantId} merchantName=${merchant.merchantName}><div class="providerAddBtn"><input type="button" value="删除"></div></a></span>
                    <span><a class="modifyMerchant" href="javascript:;" merchantId=${merchant.merchantId} merchantName=${merchant.merchantName}><div class="providerAddBtn"><input type="button" value="修改"></div></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeMerchant">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该商户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/admin/adminCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/adminMerchantList.js"></script>
