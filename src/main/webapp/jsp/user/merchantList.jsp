<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 15:01
  To change this template use File | Settings | File Templates.
  当前页面罗列所有的商户
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/user/userCommon/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/merchant">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>商家名称：</span>
            <input name="merchantName" type="text" value="${merchantName }">

<%--            <span>商户：</span>--%>
<%--            <select name="queryMerchantId">--%>
<%--                <c:if test="${merchantList != null }">--%>
<%--                    <option value="0">--请选择--</option>--%>
<%--                    <c:forEach var="merchant" items="${merchantList}">--%>
<%--                        <option <c:if test="${merchant.id == queryMerchantId }">selected="selected"</c:if>--%>
<%--                                value="${merchant.id}">${merchant.name}</option>--%>
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
            <th width="10%">商户编码</th>
            <th width="20%">商品名称</th>
            <th width="10%">商户名称</th>
            <th width="10%">订单金额</th>
            <th width="10%">是否付款</th>
            <th width="10%">创建时间</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="merchant" items="${merchantList }" varStatus="status">
            <tr>
                <td>
                    <span>${merchant.code }</span>
                </td>
                <td>
                    <span>${merchant.productName }</span>
                </td>
                <td>
                    <span>${merchant.name}</span>
                </td>
                <td>
                    <span>${merchant.totalPrice}</span>
                </td>
                <td>
					<span>
						<c:if test="${merchant.isPayment == 1}">未付款</c:if>
						<c:if test="${merchant.isPayment == 2}">已付款</c:if>
					</span>
                </td>
                <td>
					<span>
					<fmt:formatDate value="${merchant.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
                <td>
                    <span><a class="viewMerchant" href="javascript:;" merchantid=${merchant.id } merchantcc=${merchant.code }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyMerchant" href="javascript:;" merchantid=${merchant.id } merchantcc=${merchant.code }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteMerchant" href="javascript:;" merchantid=${merchant.id } merchantcc=${merchant.code }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
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
            <p>你确定要删除该商户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/user/userCommon/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/merchantlist.js"></script>