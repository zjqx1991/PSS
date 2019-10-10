$(function () {
    //新增按钮
    $(".btn_input").click(function () {
        //获取data-url中的参数
        window.location.href = $(this).data("url");
    });
})


//翻页
$(function () {
    $(".next_btn").click(function () {
        //跳转页
        var page = $(this).data("page");
        //
        $("input[name='qo.currentPage']").val(page);
        //submit
        $("#searchForm").submit();
    });

    //设置页数
    $("select[name='qo.pageSize']").change(function () {
        //submit
        $("#searchForm").submit();
    });

    //手动跳转
    $(".btn_go").click(function () {
        $("#searchForm").submit();
    });

})

