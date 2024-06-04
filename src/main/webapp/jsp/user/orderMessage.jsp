<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/4
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>系统消息 >> 订单历史消息</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/order">
            <input name="method" value="query" class="input-text" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
        </form>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="80%">消息内容</th>
            <th width="20%">发送时间</th>
        </tr>
        <c:forEach var="message" items="${orderMessageList}" varStatus="status">
            <tr>
                <td>
                    <span>${message.orderStatusMessage}</span>
                </td>
                <td>
					<span>
					    <fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd"/>
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
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderList.js"></script>--%>

