$(function () {
    //新增按钮
    $(".btn_input").click(function () {
        //获取data-url中的参数
        window.location.href = $(this).data("url");
    });
})

//翻页
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

//全部选中
$(function () {

    $("#all").click(function () {
        //当前选中状态
        var currentStatus = $(this).prop("checked");
        //设置选中
        $(".acb").prop("checked", currentStatus);
    });

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
            alert("请选中被删除的数据！");
            return;
        }

        //发送Ajax请求
        console.debug(ids);
        // $.get($(this).data("url"),
        //     {'ids':ids},
        //     function (data, status) {
        //     alert("数据" + data + "\n状态" + status);
        // });

        $.ajax({
            type: "GET",
            url: $(this).data("url"),
            data: {'ids':ids},
            success: function (data, status) {
                window.location.reload();
                console.debug("数据" + data + "\n状态" + status);
            }
        });

    })
})
