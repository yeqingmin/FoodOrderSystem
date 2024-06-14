<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/13
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜品数据分析</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/merchant">
            <input name="method" value="analysis" class="input-text" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
        </form>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <%--            <th width="10%">商户编码</th>--%>
            <th width="10%">菜品评分</th>
            <th width="10%">菜品线上销量</th>
            <th width="10%">菜品线下销量</th>
            <th width="20%">购买该菜品次数最多的用户</th>

        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>
                <td>
                    <span>${dish.onlineSales}</span>
                </td>
                <td>
                    <span>${dish.offlineSales}</span>
                </td>
                <td>
                    <span>${dish.mostUser}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
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

