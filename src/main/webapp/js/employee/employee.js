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

