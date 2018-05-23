<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>修改地址</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
    <%@ include file="/common/include-base-js.jsp" %>

</head>
<body>
<%@include file="/common/header-nav.jsp" %>

<div class="page-header">
    <h2 align="center" valign="middle">修改地址</h2>
</div>

<div class="container">

        <div class="row">

    <div class="col-md-6 col-md-offset-3">
        <form:form  role="form" action="${ctx}/user/userAddress/edit"
                   method="post" class="form-horizontal" enctype="multipart/form-data">
            <input type="hidden" id="id" name="id" value="${userAddress.id}"/>
            <div class="form-group">
                <label class="col-sm-2 control-label">收货人</label>
                <div class="col-sm-10">
                    <input minlength="1" required class="form-control" value="${userAddress.consignee}" type="text" id="consignee" name="consignee"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">电话</label>

                <div class="col-sm-10">
                    <input type="number" required class="form-control" value="${userAddress.phone}" type="text" id="phone" name="phone"/>
                </div>
            </div>
            <%--<div class="form-group">--%>
                <%--<label class="col-sm-2 control-label">邮编</label>--%>

                <%--<div class="col-sm-10">--%>
                    <%--<input class="form-control" value="${userAddress.zipcode}" type="text" id="zipcode" name="zipcode"/>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <label class="col-sm-2 control-label">地址</label>

                <div class="col-sm-10">
                    <input class="form-control" value="${userAddress.address}" type="text" name="address" id="address"></input>
                </div>
            </div>
            <div class="text-center">
                <button type="submit"  class="btn btn-primary">保存</button>
            </div>
        </form:form>
    </div>

        </div>
</div>


<!-- /container -->
<%@include file="/common/footer.jsp" %>
</body>
</html>
</html>