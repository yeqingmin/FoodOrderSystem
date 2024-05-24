<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 12:13
  To change this template use File | Settings | File Templates.
  当前界面是用户所能看到的界面
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>
<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt=""/>
    <div class="wFont">
        <%--        <h2>${userSession.userName }</h2>--%>
        <p>欢迎来到食堂点单系统!</p>
    </div>
</div>
</section>
<%@include file="/jsp/user/userCommon/foot.jsp" %>
