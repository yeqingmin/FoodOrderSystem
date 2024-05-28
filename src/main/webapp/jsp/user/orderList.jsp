<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>历史订单页面</span>
    </div>
<%--    <div class="search">--%>
<%--        <form method="get" action="${pageContext.request.contextPath }/jsp/order.do">--%>
<%--            <input name="method" value="query" class="input-text" type="hidden">--%>
<%--            <span>商品名称：</span>--%>
<%--            <input name="queryProductName" type="text" value="${queryProductName }">--%>

<%--            <span>供应商：</span>--%>
<%--            <select name="queryProviderId">--%>
<%--                <c:if test="${providerList != null }">--%>
<%--                    <option value="0">--请选择--</option>--%>
<%--                    <c:forEach var="provider" items="${providerList}">--%>
<%--                        <option <c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>--%>
<%--                                value="${provider.id}">${provider.proName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </c:if>--%>
<%--            </select>--%>

<%--            <span>是否付款：</span>--%>
<%--            <select name="queryIsPayment">--%>
<%--                <option value="0">--请选择--</option>--%>
<%--                <option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>--%>
<%--                <option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>--%>
<%--            </select>--%>

<%--            <input	value="查 询" type="submit" id="searchbutton">--%>
<%--            <a href="${pageContext.request.contextPath }/jsp/orderadd.jsp">添加订单</a>--%>
<%--        </form>--%>
<%--    </div>--%>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">订单编码</th>
            <th width="20%">商品名称</th>
            <th width="10%">供应商</th>
            <th width="10%">订单金额</th>
            <th width="10%">是否付款</th>
            <th width="10%">创建时间</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="order" items="${orderList }" varStatus="status">
            <tr>
                <td>
                    <span>${order.orderCode }</span>
                </td>
                <td>
                    <span>${order.productName }</span>
                </td>
                <td>
                    <span>${order.providerName}</span>
                </td>
                <td>
                    <span>${order.totalPrice}</span>
                </td>
                <td>
					<span>
						<c:if test="${order.isPayment == 1}">未付款</c:if>
						<c:if test="${order.isPayment == 2}">已付款</c:if>
					</span>
                </td>
                <td>
					<span>
					<fmt:formatDate value="${order.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
<%--                <td>--%>
<%--                    <span><a class="vieworder" href="javascript:;" orderid=${order.id } ordercc=${order.orderCode }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>--%>
<%--                    <span><a class="modifyorder" href="javascript:;" orderid=${order.id } ordercc=${order.orderCode }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>--%>
<%--                    <span><a class="deleteorder" href="javascript:;" orderid=${order.id } ordercc=${order.orderCode }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>--%>
<%--                </td>--%>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderlist.js"></script>