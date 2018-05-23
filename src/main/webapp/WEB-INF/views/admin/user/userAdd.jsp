<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>



<head>
    <%@ include file="/common/global.jsp" %>
    <title>添加用户</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/user.js" type="text/javascript"></script>
</head>



<body>
<%@include file="/common/admin-header-nav.jsp" %>

    <h2 align="center" valign="middle">添加新用户</h2>

<div class="container">
    <hr/>
    <div class="col-md-6 col-md-offset-3">

    <%--@elvariable id="user" type="text"--%>
        <form:form role="form" action="${ctx}/admin/user/add"
                   method="post" id="regForm" class="form-signin">
        <div class="form-group">
            <input type="text" class="form-control" name="username" placeholder="账号" required>
        </div>
        <div class="form-group">
            <input type="password"  class="form-control" minlength="6" name="password" placeholder="密码" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control"  name="address" placeholder="默认地址" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" minlength="11"  name="phone" placeholder="默认号码" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-primary btn-block">确认</button>
        </div>
    </form:form>
</div>
</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>
