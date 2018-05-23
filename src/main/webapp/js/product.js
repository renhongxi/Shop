$(function() {
    $("#sub-nav-product").attr("class","active");
    //添加购物车
    $(".addCart").click(function () {
        $.ajax({
            url:ctx+"/cart/add/"+$(this).attr("productid")+"/1",
            success:function(result){
                if(result.status=="SUCCESS"){
                    toastr.info("添加购物车成功.");
                }else{
                    toastr.warn(result.message);
                }
            },
            error:function(){
                toastr.warn("发生错误,稍后重试.");
            }
        })
    })

    //删除商品
    $(".del-product-btn").click(function () {
        var productId = $(this).attr("productId");
        $.ajax({
            url:ctx + "/admin/product/productAdmin/delete/" + productId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    $("tr[productId="+productId+"]").remove();
                    toastr.info("删除商品成功.");

                }else {
                    toastr.error(result.message);
                }

            },
            error:function () {
                toastr.error("发生错误");

            }
        })
    })







})