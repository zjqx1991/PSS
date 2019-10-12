/**
 *  加载权限
 */
$(function () {
    $(".btn_add_reload").click(function () {
        var reloadURL = $(this).data("url");
        $.dialog({
            title: "温馨提示",
            icon: "face-smile",
            content: "加载权限可能需要一些时间, 确定要加载权限 ？",
            cancel: true,
            ok:function () {
                $.get(reloadURL, function (data, status) {
                    if (status == "success") {
                        //重新加载
                        window.location.reload();
                    }
                })
            }
        });
    });
})