<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>个人信息</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/user.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">
    <%@include file="/common/user-admin-nav.jsp" %>

    <div class="row">

            <label style="font-size:18px;" for="address" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-6">
                <input id="address" class="form-control" type="text" name="address" value="${user.username}" />
            </div>


    </div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>