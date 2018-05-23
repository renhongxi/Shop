<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>添加地址</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/userAddress.js" type="text/javascript"></script>
</head>

<body>

<%@include file="/common/header-nav.jsp" %>
<div class="page-header">
    <h2 align="center" valign="middle">添加新地址</h2>
</div>
<div class="container">

    <div class="row">



<div class="col-md-6 col-md-offset-3">
    <form:form id="inputForm" role="form" action="${ctx}/user/userAddress/add"
               method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">收货人</label>
            <div class="col-sm-10">
                <input minlength="2" required class="form-control" type="text" id="consignee" name="consignee"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">电话</label>

            <div class="col-sm-10">
                <input type="number" required class="form-control" type="text" id="phone" name="phone"/>
            </div>
        </div>
        <%--<div class="form-group">--%>
            <%--<label class="col-sm-2 control-label">邮编</label>--%>

            <%--<div class="col-sm-10">--%>
                <%--<input class="form-control" type="text" id="zipcode" name="zipcode"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <label class="col-sm-2 control-label">地址</label>

            <div class="col-sm-10">
                <textarea class="form-control" name="address" id="address"></textarea>
            </div>
        </div>
        <div class="text-center">
            <button type="submit"  class="btn btn-primary">保存</button>
        </div>
    </form:form>
</div>

    </div>
</div>



</body>
</html>
