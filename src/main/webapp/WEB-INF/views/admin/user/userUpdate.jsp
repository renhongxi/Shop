<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>修改用户</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <%@ include file="/common/include-base-js.jsp" %>

</head>


<body>
<%@include file="/common/admin-header-nav.jsp" %>

    <h2 align="center" valign="middle">修改用户信息</h2>

<div class="container">
    <hr/>

    <div class="col-md-6 col-md-offset-3">
    <%--@elvariable id="userP" type=""--%>
    <form:form action="/admin/user/update" method="post" class="form-horizontal" enctype="multipart/form-data" role="form">
        <input type="hidden" id="id" name="id" value="${user.id}"/>
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.username}"/>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="text" class="form-control" id="password" name="password" value="${user.password}"/>
        </div>
        <div class="form-group">
            <label for="address">默认地址</label>
            <input type="text" class="form-control" id="address" name="address" value="${user.address}"/>
        </div>
        <div class="form-group">
            <label for="phone">默认手机</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}"/>
        </div>



        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-primary btn-block">提交</button>
        </div>
    </form:form>
</div>
</div>

<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>
