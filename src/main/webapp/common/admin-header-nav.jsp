<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">购物商城后台管理系统</a>
        </div>

        <div class="navbar-collapse collapse">
        <div>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty login_admin}">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    ${login_admin.username}
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${ctx}/admin/product">后台管理</a></li>
                                <li><a href="${ctx}/admin/logout">登出</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${ctx}/admin/login">登录</a></li>
                        <li><a href="${ctx}/admin/rege">注册</a> </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
        </div>
        </div>
        <!--/.nav-collapse -->
    </div>
<script type="text/javascript">

</script>