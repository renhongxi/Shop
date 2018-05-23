<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>地址管理</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/userAddress.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <%@include file="/common/user-admin-nav.jsp" %>
    <div class="row">
        <a class="btn btn-into" href="${ctx}/user/userAddress/add">添加新地址</a>
        <table class="table table-responsive table-striped">
            <thead>
            <tr>
                <td align="center" valign="middle">ID</td>
                <td >收货人</td>
                <td >电话</td>
                <td >地址</td>
                <td align="center" valign="middle">操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.result}" var="userAddress">
                <tr addressId="${userAddress.id}">
                    <td align="center" valign="middle">${userAddress.id}</td>
                    <td >${userAddress.consignee}</td>
                    <td >${userAddress.phone}</td>
                    <td >${userAddress.address}</td>
                    <td align="center" valign="middle">
                        <a class="btn btn-info btn-xs deleteAddresslBtn" addressId="${userAddress.id}">删除</a>
                        <a class="btn btn-info btn-xs" href="${ctx}/user/userAddress/edit/${userAddress.id}">修改</a>
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
<%--<script>--%>
    <%--$(function(){--%>
        <%--$('#confirm_trigger').scojs_confirm();--%>
    <%--})--%>
<%--</script>--%>
</body>
</html>