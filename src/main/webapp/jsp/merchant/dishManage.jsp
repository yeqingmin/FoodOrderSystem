<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/merchant/merchantCommon/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜单管理</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/dish">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>菜品名称：</span>
            <input name="dishName" type="text" value="${dishName}">

<%--            <span>商户：</span>--%>
<%--            <select name="queryMerchantId">--%>
<%--                <c:if test="${dishList != null }">--%>
<%--                    <option value="0">--请选择--</option>--%>
<%--                    <c:forEach var="merchant" items="${dishList}">--%>
<%--                        <option <c:if test="${dish.dishId == queryDishId }">selected="selected"</c:if>--%>
<%--                                value="${dish.dishId}">${dish.dishName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </c:if>--%>
<%--            </select>--%>

            <%--            <span>是否付款：</span>--%>
            <%--            <select name="queryIsPayment">--%>
            <%--                <option value="0">--请选择--</option>--%>
            <%--                <option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>--%>
            <%--                <option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>--%>
            <%--            </select>--%>

            <input value="查 询" type="submit" id="searchbutton">
            <%--            <a href="${pageContext.request.contextPath }/jsp/merchantadd.jsp">添加商户</a>这个是在管理员页面里面看见的东西--%>
        </form>
    </div>
    <!--商户表格样式-->
    <table class="merchantTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <%--            <th width="10%">商户编码</th>--%>
            <th width="20%">菜品名称</th>
            <th width="10%">菜品价格</th>
            <th width="30%">菜品图片</th>
            <%--            <th width="10%">创建时间</th>--%>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName }</span>
                </td>
                <td>
                    <span>${dish.merchantID}</span>
                </td>
                <td>
                    <span>${dish.dishPrice}</span>
                </td>
                <td>
                    <span>${dish.dishImage}</span>
                </td>

                <td>
<%--                    <span><a class="viewDish" href="javascript:;" dishid=${dish.dishId} dish=${dish.dishName}><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>--%>
                    <span><a class="modifyDish" href="javascript:;" dishid=${dish.dishId} dish=${dish.dishName}><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteDish" href="javascript:;" dishid=${dish.dishId} dish=${dish.dishName}><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeMerchant">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该菜品吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantlist.js"></script>

