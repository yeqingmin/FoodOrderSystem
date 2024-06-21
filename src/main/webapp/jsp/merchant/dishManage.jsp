<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/merchant/merchantCommon/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>菜单管理</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/dish">
            <input name="method" value="merchantManage" class="input-text" type="hidden">
            <span>菜品名称：</span>
            <input name="dishName" type="text" value="${dishName}">
            <input type="hidden" name="pageIndex" value="1"/>
            <a href="${pageContext.request.contextPath}/jsp/merchant/dishadd.jsp">添加菜品</a>
        </form>
    </div>
    <!--商户表格样式-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">菜品名称</th>
            <th width="10%">菜品商户</th>
            <th width="10%">菜品价格</th>
            <th width="10%">菜品分类</th>
            <th width="20%">菜品描述</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="dish" items="${dishList}" varStatus="status">
            <tr>
                <td>
                    <span>${dish.dishName}</span>
                </td>
                <td>
                    <span>${dish.merchantId}</span>
                </td>
                <td>
                    <span>${dish.dishPrice}</span>
                </td>
                <td>
                    <span>${dish.dishCategory}</span>
                </td>
                <td>
                    <span>${dish.dishDescription}</span>
                </td>
                <td>
                    <span><a class="modifyDish" href="javascript:;" dishId=${dish.dishId} dishName=${dish.dishName}><img
                            src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改"
                            title="修改"/></a></span>
                    <span><a class="deleteDish" href="javascript:;" dishId=${dish.dishId} dishName=${dish.dishName}><img
                            src="${pageContext.request.contextPath }/images/schu.png" alt="删除"
                            title="删除"/></a></span>
                    <span><a class="userImage" href="javascript:;" dishId=${dish.dishId} dishName=${dish.dishName}>菜品用户画像</a></span>
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
<div class="remove" id="removeDish">
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dishManageList.js"></script>

