<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/admin/adminCommon/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户管理页面 >> 商户添加页面</span>
    </div>
    <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/jsp/merchant">
            <input type="hidden" name="method" value="add">
            <div>
                <label for="merchantName">商户名称：</label>
                <input type="text" name="merchantName" id="merchantName" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="merchantPassword">商户密码：</label>
                <input type="password" name="merchantPassword" id="merchantPassword" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="merchantAddr">商户地址：</label>
                <input name="merchantAddr" id="merchantAddr"  value="">
            </div>
            <div class="providerAddBtn">
                <input type="button" name="add" id="add" value="保存" >
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/admin/adminCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantadd.js"></script>