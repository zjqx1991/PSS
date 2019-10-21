$(function () {
    //选择货品
    $(".searchproduct").click(function () {
        var height = 300;
        var width = 500;

        var winOption = "height=1000,width=1500,top=50,left=50,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=100";
        window.open("product_productSelectList", window, winOption);
    });

    $("#tag_costPrice, input[tag='number']").change(function () {
        var costPrice = parseFloat($("#tag_costPrice").val());

        var number = parseFloat($("input[tag='number']").val());

        if (costPrice >= 0 && number >= 0) {
            $("[tag=amount]").text((costPrice * number).toFixed(2));
        }
    });

});

