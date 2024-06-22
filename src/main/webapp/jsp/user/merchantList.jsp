<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/5/24
  Time: 15:01
  To change this template use File | Settings | File Templates.
  当前页面罗列所有的商户
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/user/userCommon/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商户操作</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/merchant">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>商家名称：</span>
            <input name="merchantName" type="text">
            <input value="查 询" type="submit" id="searchbutton">
        </form>
    </div>
    <!--商户表格样式-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <h3>最高销量：地址为${merchantBest.merchantAddr}的${merchantBest.merchantName}</h3>
        <tr class="firstTr">
            <th width="20%">商店名称</th>
            <th width="20%">商店地址</th>
            <th width="20%">主打菜品</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="merchant" items="${merchantList}" varStatus="status">
            <tr>
                <td>
                    <span>${merchant.merchantName }</span>
                </td>
                <td>
                    <span>${merchant.merchantAddr}</span>
                </td>
                <td>
                    <span>${merchant.featureDish}</span>
                </td>
                <td>
                    <span>
                        <a class="viewMerchant" href="javascript:;" merchantid=${merchant.merchantId}>
                            <div class="providerAddBtn">
                                <input type="button" value="查看详情">
                            </div>
                        </a>
                    </span>
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