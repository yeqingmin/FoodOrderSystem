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
            <input name="method" value="query" class="input-text" type="hidden">
            <span>商家名称：</span>
            <input name="merchantName" type="text" value="${merchantName }">

            <input value="查 询" type="submit" id="searchbutton">
        </form>
    </div>
    <!--商户表格样式-->
    <table class="merchantTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">商户编码</th>
            <th width="20%">商品名称</th>
            <th width="10%">商户名称</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="merchant" items="${merchantList }" varStatus="status">
            <tr>
                <td>
                    <span>${merchant.code }</span>
                </td>
                <td>
                    <span>${merchant.productName }</span>
                </td>
                <td>
                    <span>${merchant.name}</span>
                </td>
                <td>
                    <span>${merchant.totalPrice}</span>
                </td>
                <td>
					<span>
						<c:if test="${merchant.isPayment == 1}">未付款</c:if>
						<c:if test="${merchant.isPayment == 2}">已付款</c:if>
					</span>
                </td>
                <td>
					<span>
					<fmt:formatDate value="${merchant.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
                <td>
                    <span><a class="viewMerchant" href="javascript:;" merchantid=${merchant.id } merchantcc=${merchant.code }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyMerchant" href="javascript:;" merchantid=${merchant.id } merchantcc=${merchant.code }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteMerchant" href="javascript:;" merchantid=${merchant.id } merchantcc=${merchant.code }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantlist.js"></script>
