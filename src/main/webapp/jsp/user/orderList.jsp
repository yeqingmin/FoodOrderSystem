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
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/system">
            <input name="method" value="queryOrderMessage" class="input-text" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
        </form>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="15%">下单商家</th>
            <th width="15%">订单状态</th>
            <th width="30%">下单时间</th>
            <th width="40%">查看详情</th>
        </tr>
        <c:forEach var="order" items="${orderList}" varStatus="status">
            <tr>
                <td>
                    <span>${order.merchantId }</span>
                </td>
                <td>
                    <span>${order.orderStatus}</span>
                </td>
                <td>
					<span>
					    <fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
               <td>
                    <span>
                        <a class="orderDetailView" href="javascript:;" orderId=${order.orderId}>
                            <div class="providerAddBtn"><input type="button" value="查看详情"></div>
                        </a>
                    </span>
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


<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderList.js"></script>