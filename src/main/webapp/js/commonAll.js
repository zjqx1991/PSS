jQuery.ajaxSettings.traditional = true;

/**
 * table鼠标悬停换色
 */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});

/**
 * 按钮操作
 */
$(function () {
    //新增按钮
    $(".btn_input").click(function () {
        //获取data-url中的参数
        window.location.href = $(this).data("url");
    });

    //表单单列删除
    $(function () {
        $(".btn_delete").click(function () {
            var url = $(this).data("url");
            $.dialog({
                title: '删除提示',
                content: "亲，确定要删除 ？",
                ok: function () {
                    $.get(url, function (data, status) {
                        if (status == "success") {
                            $.artDialog({
                                title: '删除提示',
                                icon: 'face-smile',
                                content: "删除成功",
                                ok: function () {
                                    window.location.reload();
                                }
                            })
                        }
                    });
                }
            })
        })
    })

    //表单单列删除
    $(function () {
        $(".btn_delete_msg").click(function () {
            var url = $(this).data("url");
            $.dialog({
                title: '删除提示',
                content: "亲，确定要删除 ？",
                ok: function () {
                    $.get(url, function (data, status) {
                        if (status == "success") {
                            $.artDialog({
                                title: '删除提示',
                                icon: 'face-smile',
                                content: data,
                                ok: function () {
                                    window.location.reload();
                                }
                            })
                        }
                    });
                }
            })
        })
    })

	//批量删除
    $(function () {

        $(".btn_batch").click(function () {
            //批量删除id
            /*
            var ids = [];
            //获取所有选中的td
            $.each($(".acb:checked"), function (index, ele) {
                ids[index] = $(ele).data("id");
            })
            */

            var ids = $.map($(".acb:checked"), function (item) {
                return $(item).data("eid");
            });
            if (ids.length == 0) {
                $.dialog({
                    title: '温馨提示',
                    content: "请选中被删除的数据！"
                });
                return;
            }

            //发送Ajax请求
            console.debug(ids);
            // $.get($(this).data("url"),
            //     {'ids':ids},
            //     function (data, status) {
            //     alert("数据" + data + "\n状态" + status);
            // });

            /* $.ajax({
                 type: "GET",
                 url: ,
                 data: {'ids': ids},
                 success: function (data, status) {
                     window.location.reload();
                     console.debug("数据" + data + "\n状态" + status);
                 }
             });*/
            var url = $(this).data("url");
            $.dialog({
                title: '批量删除提示',
                content: '确定要删除选中的数据 ？',
                cancel: true,
                ok: function () {
                    $.get(url, {'ids': ids}, function (data, status) {
                        if (status == "success") {
                            $.artDialog({
                                title: '删除提示',
                                content: "删除成功",
                                ok: function () {
                                    window.location.reload();
                                }
                            })
                        }
                    })
                }
            })

        })
    })

})

/**
 * 翻页
 */
$(function () {

    //当前页位置可以点击设置
    $("input[name='qo.currentPage']").prop("type", "number")
        .css("width", "50px")
        .prop("min", "1")
        .prop("max", $(".totalPage").data("page"));

    //翻页
    $(".next_btn").click(function () {
        //跳转页
        var page = $(this).data("page") || $("input[name='qo.currentPage']").val();
        // 设置页数
        $("input[name='qo.currentPage']").val(page);
        //submit
        $("#searchForm").submit();
    });

    //设置显示页数
    $("select[name='qo.pageSize']").change(function () {
        //submit
        $("#searchForm").submit();
    });

})

/**
 * 全部选中
 */
$(function () {

    $("#all").click(function () {
        //当前选中状态
        var currentStatus = $(this).prop("checked");
        //设置选中
        $(".acb").prop("checked", currentStatus);
    });

})

/**
 * 角色权限移动
 */
$(function () {
    /**
     *  移除已有的角色
     */
    //1 已经有些角色id
    var roles = $("#selectedRole option").map(function (index, item) {
            return $(item).val();
        });
    //2
    $("#unSelectRole option").map(function (index, item) {
        index = $.inArray($(item).val(), roles);
        if (index > -1) {
            $(item).remove();
        }
    });

    //单个选中
    $("#select").click(function () {
        $("#unSelectRole option:selected").appendTo($("#selectedRole"));
    });
    //全选
    $("#selectAll").click(function () {
        $("#unSelectRole option").appendTo($("#selectedRole"));
    });
    //单个非选中
    $("#deselect").click(function () {
        $("#selectedRole option:selected").appendTo($("#unSelectRole"));
    });
    //全不选
    $("#deselectAll").click(function () {
        $("#selectedRole option").appendTo($("#unSelectRole"));
    });

})


/**
 * 角色系统菜单移动
 */
$(function () {
    /**
     *  移除已有的菜单
     */
        //1 已经有些菜单id
    var roles = $("#selectedRole_menu option").map(function (index, item) {
            return $(item).val();
        });
    //2
    $("#unSelectRole_menu option").map(function (index, item) {
        index = $.inArray($(item).val(), roles);
        if (index > -1) {
            $(item).remove();
        }
    });




    //单个选中
    $("#select_menu").click(function () {
        $("#unSelectRole_menu option:selected").appendTo($("#selectedRole_menu"));
    });
    //全选
    $("#selectAll_menu").click(function () {
        $("#unSelectRole_menu option").appendTo($("#selectedRole_menu"));
    });
    //单个非选中
    $("#deselect_menu").click(function () {
        $("#selectedRole_menu option:selected").appendTo($("#unSelectRole_menu"));
    });
    //全不选
    $("#deselectAll_menu").click(function () {
        $("#selectedRole_menu option").appendTo($("#unSelectRole_menu"));
    });

})

/**
 * 表单提交
 */
$(function () {

    //表单提交
    $("#editForm").submit(function () {
        //设置权限右侧所有option为选中状态
        $("#selectedRole option").prop("selected", true);
        //设置系统菜单右侧所有option为选中状态
        $("#selectedRole_menu option").prop("selected", true);
    });
})