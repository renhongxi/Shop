<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>订单管理</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/order.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/admin-header-nav.jsp" %>
<div class="container">
    <%@include file="/common/admin-admin-nav.jsp" %>

    <div class="row">


        <table class="table table-responsive table-striped">
                <thead>
                <tr>
                    <td align="center" valign="middle">ID</td>
                    <td align="center" valign="middle">订单日期</td>
                    <td align="center" valign="middle">订单编号</td>
                    <td align="center" valign="middle">订单金额</td>
                    <td align="center" valign="middle">订单状态</td>
                    <td align="center" valign="middle">收件人</td>
                    <td align="center" valign="middle">操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.result}" var="order">
                    <tr pid="${order.id}">
                        <td align="center" valign="middle">${order.id}</td>
                        <td align="center" valign="middle">${order.createTime}</td>
                        <td align="center" valign="middle">${order.orderNumber}</td>
                        <td align="center" valign="middle">${order.finalPrice}</td>
                        <td align="center" valign="middle">
                            <c:choose>
                                <c:when test="${order.status==0}">等待付款</c:when>
                                <c:when test="${order.status==1}">等待发货</c:when>
                                <c:when test="${order.status==2}">已发货</c:when>
                                <c:when test="${order.status==3}">已取消</c:when>
                                <c:when test="${order.status==4}">已完成</c:when>
                            </c:choose>
                        </td>
                        <td align="center" valign="middle">${order.consignee}</td>
                        <td align="center" valign="middle">
                            <c:choose>
                                <c:when test="${order.status==0}"><a class="btn btn-info btn-xs" href="${ctx}/admin/order/${order.id}">查看</a> <a class="btn btn-info btn-xs del-order-btn" orderId="${order.id}">删除</a></c:when>
                                <c:when test="${order.status==1}"><a class="btn btn-info btn-xs" href="${ctx}/admin/order/${order.id}">查看</a> <a class="btn btn-info btn-xs ship-order-btn">发货</a> <a class="btn btn-info btn-xs del-order-btn" orderId="${order.id}">删除</a></c:when>
                                <c:when test="${order.status==2}"><a class="btn btn-info btn-xs" href="${ctx}/admin/order/${order.id}">查看</a></c:when>
                                <c:when test="${order.status==3}"><a class="btn btn-info btn-xs" href="${ctx}/admin/order/${order.id}">查看</a></c:when>
                                <c:when test="${order.status==4}"><a class="btn btn-info btn-xs" href="${ctx}/admin/order/${order.id}">查看</a></c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
        </table>
    </div>
    <div class="text-center">
        <tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
    </div>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>