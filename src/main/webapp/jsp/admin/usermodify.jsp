<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/29
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/admin/adminCommon/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面 >> 用户修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/jsp/user">
            <input type="hidden" name="method" value="modifyExecute">
            <input type="hidden" name="userId" value="${user.userId}"/>
            <div>
                <label for="userName">用户名称：</label>
                <input type="text" name="userName" id="userName" value="${user.userName}">
                <font color="red"></font>
            </div>
            <div>
                <label >用户性别：</label>
                <select name="userGender" id="userGender">
                    <c:choose>
                        <c:when test="${user.userGender == 'male' }">
                            <option value="1" selected="selected">male</option>
                            <option value="2">female</option>
                        </c:when>
                        <c:otherwise>
                            <option value="1">male</option>
                            <option value="2" selected="selected">female</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存" />
                <input type="button" id="back" name="back" value="返回"/>
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/admin/adminCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/usermodify.js"></script>

