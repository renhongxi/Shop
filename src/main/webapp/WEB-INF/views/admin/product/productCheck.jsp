<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp"%>
    <title>商品信息</title>
    <%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/include-base-styles.jsp"%>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
</head>

<body>
<%@include file="/common/admin-header-nav.jsp" %>
<div>
    <h2 align="center" valign="middle">${product.title}</h2>
</div>
<div class="container">
    <hr/>
    <div class="row">
        <div class="col-md-5 text-center">
            <img class="img-responsive img-rounded" src="${ctx}${product.masterPic.url}">
        </div>
        <div class="col-md-7">
            <div class="form-group">
                <label>商品名称：</label>${product.title}
            </div>
            <div class="form-group">
                <label>商品编码：</label>${product.code}
            </div>
            <div class="form-group">
                <label>商品型号：</label>${product.model}
            </div>
            <div class="form-group">
                <label>商品价格：</label>${product.point}
            </div>
            <div class="form-group">
                <label>商品简介：</label>${product.note}
            </div>

        </div>
    </div>
</div>


</div> <!-- /container -->

<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp"%>
<script src="${ctx }/js/product.js" type="text/javascript"></script>
</body>
</html>