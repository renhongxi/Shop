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
            <a class="navbar-brand" href="#">购物商城</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${ctx}/">首页</a></li>
                <li><a href="${ctx}/product/">商品</a></li>
                <li><a>
                </a></li>
            </ul>


       <div>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty login_user}">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    ${login_user.username}
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${ctx}/user/profile">个人设置</a></li>
                                <li><a href="${ctx}/user/logout">登出</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${ctx}/user/login">登录</a></li>
                        <li><a href="${ctx}/user/reg">注册</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
       </div>

            <div class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="输入商品关键字" id="searchKeyWord" >
                    <%--<input type="text" class="form-control" placeholder="输入商品关键字" id="searchKeyWord" onkeydown="javascript:if(event.keyCode==13) search('searchKeyWord');">--%>
                </div>
                <button class="btn btn-default" onclick="searchProduct()">查找商品</button>
            </div>

            <form class="navbar-form navbar-right" role="cart">
                <a href="${ctx}/cart/" class="btn btn-info">购物车</a>
            </form>

        </div>
        <!--/.nav-collapse -->
    </div>
</div>
<script type="text/javascript">
    function searchProduct() {
        var search = {};
        search.searchKeyWord = document.getElementById("searchKeyWord").value;
        <%--window.location.href = "${ctx}/search?searchKeyWord=" + search.searchKeyWord;--%>

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
