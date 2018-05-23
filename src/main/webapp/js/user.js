$(function () {
    $("#sub-nav-user").attr("class", "active");
    //删除用户
    $(".del-user-btn").click(function () {
        var userId = $(this).attr("userId");
        $.ajax({
            url:ctx + "/admin/user/userAdmin/delete/" + userId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    $("tr[userId="+userId+"]").remove();
                    toastr.info("用户删除成功.");

                }else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("发生错误...");
            }
        })
    });
})