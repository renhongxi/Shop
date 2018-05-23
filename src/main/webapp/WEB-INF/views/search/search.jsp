<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>搜索结果</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/product.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
</head>
<body>
<%@include file="/common/header-nav.jsp" %>

<!-- 中间内容 -->

<%--<div class="container">--%>
    <%--<div class="row margin-t">--%>
        <%--<div class="form-horizontal">--%>
            <%--<div class="col-md-2 col-sm-2"></div>--%>
            <%--<div class="form-group form-group-lg col-sm-6 col-md-6">--%>
                <%--<input type="text" class="form-control" id="newSearchKeyWord" placeholder="请输入关键字" />--%>
            <%--</div>--%>
            <%--<button class="btn btn-primary" onclick="searchPre()">查找商品</button>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>



<div class="container">
    <div class="row">
        <div id = "searchResultArea">
        </div>
    </div>

</div>


<!-- 尾部 -->
<%@include file="/common/footer.jsp" %>

<script type="text/javascript">

    searchInit();

    function searchInit() {
        var searchKeyWord = "${searchKeyWord}";
        if(searchKeyWord != "" && searchKeyWord != undefined && searchKeyWord != null){
            updateList(searchKeyWord);
        }
    }

    function searchPre() {
        var searchKeyWord = document.getElementById("newSearchKeyWord").value;
        updateList(searchKeyWord);
    }

    function search(searchKeyWord) {
        var search = {};
        search.searchKeyWord = searchKeyWord;
        var searchResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${ctx}/searchProduct',
            data : search,
            dataType : 'json',
            success : function(result) {
                searchResult = result.result;
            },
            error : function() {
                layer.alert('查询错误');
            }
        });
        searchResult = eval("("+searchResult+")");
        return searchResult;
    }

    function updateList(searchKeyWord) {
        var searchResultArea = document.getElementById("searchResultArea");
        var searchResult = search(searchKeyWord);
        var html = "";
        searchResultArea.innerHTML = "";
        for(var i=0;i<searchResult.length;i++){
            var imgURL = "${ctx}/"+searchResult[i].id+"";
            html+= '<div class="col-sm-3 col-md-3 search-padding">'+
                '<div class="boxes pointer" onclick="productDetail('+searchResult[i].id+')">'+
                '<div class="big bigimg">'+
                '<img src="'+imgURL+'">'+
                '</div>'+
                '<p class="product-title">'+searchResult[i].title+'</p>'+
                '<p class="price">¥'+searchResult[i].point+'</p>'+
                '</div>'+
                '</div>'+
                '<div class="col-sm-1 col-md-1"></div>';
        }
        searchResultArea.innerHTML += html;
    }

    function productDetail(id) {
        var product = {};
        var jumpResult = '';
        product.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${ctx}/productDetail',
            data : product,
            dataType : 'json',
            success : function(result) {
                jumpResult = result.result;
            },
            error : function() {
                layer.alert('查询错误');
            }
        });

        if(jumpResult == "success"){
            window.location.href = "${ctx}/product_detail";
        }
    }
</script>



</body>
</html>
