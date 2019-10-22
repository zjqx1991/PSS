$(function () {

    //为edit_table_body添加[选择商品]、[删除明细]、[计算明细金额]
    $("#edit_table_body").on("click", ".searchproduct", function () {
        $(this).attr("isclick", true)
        var height = 300;
        var width = 500;
        var winOption = "height=1000,width=1500,top=50,left=50,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=100";
        window.open("product_productSelectList", window, winOption);
    }).on("change", "input[tag='costPrice'], input[tag='number']", function () {
        var tr = $(this).closest("tr");

        var costPrice = parseFloat(tr.find("input[tag='costPrice']").val());
        var number = parseFloat(tr.find("input[tag='number']").val());
        if (costPrice >= 0 && number >= 0) {
            tr.find("[tag=amount]").text((costPrice * number).toFixed(2));
        }
    }).on("click", ".removeItem", function () {
        var tbody = $("#edit_table_body tr");
        var tr = $(this).closest("tr");
        if (tbody.length == 1) {
            clearTrData(tr);
        } else {
            tr.remove();
        }
    })

    /**
     * 情况tr中的数据
     * @param cloneTR
     */
    function clearTrData(cloneTR) {
        cloneTR.find(".searchproduct").attr("isclick", false);
        cloneTR.find("[tag=name]").val("");
        cloneTR.find("[tag=pid]").val("");
        cloneTR.find("[tag=brand]").html("");
        cloneTR.find("[tag=costPrice]").val("");
        cloneTR.find("[tag=number]").val("");
        cloneTR.find("[tag=amount]").html("");
        cloneTR.find("[tag=remark]").val("");
    }

//添加明细
    $(".appendRow").click(function () {
        var tbody = $("#edit_table_body");
        var cloneTR = $("#edit_table_body tr:first").clone();
        //清空数据
        clearTrData(cloneTR);
        tbody.append(cloneTR);
    })


    //表单提交
    $("#editForm").submit(function () {
        var tbody = $("#edit_table_body tr");
        $.each(tbody, function (index, item) {
            $(item).find("[tag=pid]").prop("name", "stockOutBill.items[" + index + "].product.id");
            $(item).find("[tag=costPrice]").prop("name", "stockOutBill.items[" + index + "].salePrice");
            $(item).find("[tag=number]").prop("name", "stockOutBill.items[" + index + "].number");
            $(item).find("[tag=remark]").prop("name", "stockOutBill.items[" + index + "].remark");
        })
    });

});

/**
 * 时间选择器
 */
$(function () {
    $("input[name='qo.beginDate']").addClass("Wdate").click(function () {
        WdatePicker({
            maxDate:$("input[name='qo.endDate']").val() || new Date()
        });
    })

    $("input[name='qo.endDate']").addClass("Wdate").click(function () {
        WdatePicker({
            minDate:$("input[name='qo.beginDate']").val(),
            maxDate:new Date()
        });
    })

    $("input[name='stockOutBill.vdate']").addClass("Wdate").click(function () {
        WdatePicker({
            maxDate:new Date()
        });
    })
})