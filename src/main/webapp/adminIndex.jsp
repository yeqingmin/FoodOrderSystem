<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 12:13
  To change this template use File | Settings | File Templates.
  当前页面为管理员可以看到的页面
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/admin/adminCommon/head.jsp"%>
<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt=""/>
    <div class="wFont">
        <%--        <h2>${userSession.userName }</h2>--%>
        <p>欢迎来到食堂点单系统!</p>
    </div>
</div>
</section>
<%@include file="/jsp/admin/adminCommon/foot.jsp" %>
