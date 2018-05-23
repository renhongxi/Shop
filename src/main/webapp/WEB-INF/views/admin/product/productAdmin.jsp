<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>商品管理</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
    <%@ include file="/common/include-base-js.jsp" %>
    <script src="${ctx }/js/product.js" type="text/javascript"></script>
</head>

<body>
<%@include file="/common/admin-header-nav.jsp" %>
<div class="container">
    <%@include file="/common/admin-admin-nav.jsp" %>
    <div class="row">
        <a class="btn btn-into" href="${ctx}/admin/product/new">添加商品</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <td align="center" valign="middle">ID</td>
                    <td align="center" valign="middle">名称</td>
                    <td align="center" valign="middle">型号</td>
                    <td align="center" valign="middle">编码</td>
                    <td align="center" valign="middle">价格</td>
                    <td align="center" valign="middle">库存</td>
                    <td align="center" valign="middle">操作</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.result}" var="product">
                    <tr productId="${product.id}">
                        <td align="center" valign="middle">${product.id}</td>
                        <td align="center" valign="middle">${product.title}</td>
                        <td align="center" valign="middle">${product.model}</td>
                        <td align="center" valign="middle">${product.code}</td>
                        <td align="center" valign="middle">${product.point}</td>
                        <td align="center" valign="middle">${product.stock}</td>
                        <td align="center" valign="middle">
                            <a class="btn btn-info btn-xs" href="${ctx}/admin/product/${product.id}">查看</a>
                            <a class="btn btn-info btn-xs" href="${ctx}/admin/product/edit/${product.id}">修改</a>
                            <a class="btn btn-info btn-xs del-product-btn" productId="${product.id}">删除</a>
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