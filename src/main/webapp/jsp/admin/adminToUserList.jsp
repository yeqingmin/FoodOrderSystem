<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/admin/adminCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/user">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>用户名：</span>
            <input name="queryname" class="input-text"	type="text" value="${queryUserName}">
            <input type="hidden" name="pageIndex" value="1"/>
            <input	value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath}/jsp/useradd.jsp" >添加用户</a>
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">用户编码</th>
            <th width="20%">用户名称</th>
            <th width="10%">性别</th>
            <th width="60%">操作</th>
        </tr>
        <c:forEach var="user" items="${userList }" varStatus="status">
            <tr>
                <td>
                    <span>${user.userId}</span>
                </td>
                <td>
                    <span>${user.userName }</span>
                </td>
                <td>
					<span>${user.userGender}</span>
                </td>
                <td>
                    <span><a class="viewDish" href="javascript:;" userId=${user.userId}><div class="providerAddBtn"><input type="button" value="删除用户"></div></a></span>
                    <span><a class="viewDish" href="javascript:;" userId=${user.userId}><div class="providerAddBtn"><input type="button" value="修改用户"></div></a></span>
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
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/admin/adminCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userlist.js"></script>
