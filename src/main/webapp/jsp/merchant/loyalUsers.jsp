<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/6/15
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/merchant/merchantCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>忠实用户列表</span>
    </div>
<%--    <div class="search">--%>
<%--        <form method="get" action="${pageContext.request.contextPath }/jsp/user">--%>
<%--            <input name="method" value="adminManage" class="input-text" type="hidden">--%>
<%--            <input type="hidden" name="pageIndex" value="1"/>--%>
<%--            <input	value="查看忠实用户" type="submit" id="searchbutton">--%>
<%--        </form>--%>
<%--    </div>--%>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">用户id</th>
            <th width="20%">用户名称</th>
            <th width="60%">操作</th>
        </tr>
        <c:forEach var="user" items="${userList}" varStatus="status">
            <tr>
                <td>
                    <span>${user.userId}</span>
                </td>
                <td>
                    <span>${user.userName}</span>
                </td>
                <td>
                    <span><a class="view" href="javascript:;" userId=${user.userId}><div class="providerAddBtn"><input type="button" value="查看消费分布"></div></a></span>
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
<div class="remove" id="removeUser">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/merchant/merchantCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/loyalUser.js"></script>
