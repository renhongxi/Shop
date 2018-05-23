
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