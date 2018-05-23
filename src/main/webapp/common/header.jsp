<%--
  Created by IntelliJ IDEA.
  User: ASUS_PC
  Date: 2018/4/28
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@ include file="/common/include-base-styles.jsp" %>
    <%@ include file="/common/include-base-js.jsp" %>

    <script src="${ctx }/js/layer.js" type="text/javascript"></script>
    <script src="${ctx }/js/ajaxfileupload.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
    <title>Title</title>
</head>
<body>

<!--导航栏部分-->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${cp}/main">购物+</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty currentUser}">
                    <li><a href="${cp}/register" methods="post">注册</a></li>
                    <li><a href="${cp}/login" methods="post">登录</a></li>
                </c:if>
                <c:if test="${not empty currentUser}">
                    <c:if test="${currentUser.role == 1}">
                        <li><a href="${cp}/control" methods="post">控制台</a></li>
                    </c:if>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                ${currentUser.nickName}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${cp}/shopping_car">购物车</a></li>
                            <li><a href="${cp}/shopping_record">订单状态</a></li>
                            <c:if test="${currentUser.role == 1}">
                                <li><a href="${cp}/shopping_handle">处理订单</a></li>
                            </c:if>
                            <li role="separator" class="divider"></li>
                            <li><a href="${cp}/amend_info">个人资料修改</a></li>
                            <li><a href="${cp}/doLogout">注销登录</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>

            <div class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="数据库" id="searchKeyWord"/>
                </div>
                <button class="btn btn-default" onclick="searchProduct();">查找商品</button>
            </div>
        </div>
    </div>
</nav>
<script type="text/javascript">
    function searchProduct() {
        var search = {};
        search.searchKeyWord = document.getElementById("searchKeyWord").value;
        var searchResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${ctx}/searchPre',
            data : search,
            dataType : 'json',
            success : function(result) {
                searchResult = result.result;
            },
            error : function(result) {
                layer.alert('查询错误');
            }
        });
        if(searchResult == "success")
            window.location.href = "${ctx}/search";
    }
</script>


</body>
</html>
