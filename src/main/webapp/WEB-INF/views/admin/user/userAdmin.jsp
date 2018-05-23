<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8" %>--%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>用户管理</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/user.js" type="text/javascript"></script>

</head>

<body>
<%@include file="/common/admin-header-nav.jsp" %>
<div class="container">
    <%@include file="/common/admin-admin-nav.jsp" %>
    <div class="row">
        <a class="btn btn-into" href="${ctx}/admin/user/add">添加用户</a>
        <table class="table table-striped">

            <thead>
            <tr>
                <td align="center" valign="middle">ID</td>
                <td align="center" valign="middle">用户名</td>
                <td align="center" valign="middle">用户密码</td>
                <td align="center" valign="middle">默认收货地址</td>
                <td align="center" valign="middle">默认手机</td>
                <td align="center" valign="middle">操作</td>
            </tr>
            </thead>


            <tbody>
            <c:forEach items="${page.result}" var="user">
                <tr userId="${user.id}">
                    <td align="center" valign="middle">${user.id}</td>
                    <td align="center" valign="middle">${user.username}</td>
                    <td align="center" valign="middle">${user.password}</td>
                    <td align="center" valign="middle">${user.address}</td>
                    <td align="center" valign="middle">${user.phone}</td>
                    <td align="center" valign="middle">
                        <a class="btn btn-info btn-xs" href="${ctx}/admin/user/update/${user.id}">修改</a>
                        <a class="btn btn-info btn-xs del-user-btn" userId="${user.id}">删除</a>
                        <%--<a class="btn btn-info btn-xs" href="${ctx}/admin/user/userAdmin/delete/${user.id}">删除</a>--%>
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
