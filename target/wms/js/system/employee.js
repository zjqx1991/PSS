/**
 *  表单验证
 */
$(function () {
    if ($("#editForm").size() > 0) {
        $("#editForm").validate({
            rules: {
                "employee.name": {  // 用户名
                    required: true,
                    rangelength: [2, 8]
                },

                "employee.password": {  //密码
                    required: true,
                    minlength: 6,
                },

                "repassword": { //验证密码
                    required: true,
                    equalTo: "#password"
                },

                "employee.email": { //Email
                    required: true,
                    email: true
                },

                "employee.age": {   //年龄
                    required: true,
                    range: [10, 99],
                }


            },
            messages: {

                "employee.name": {  // 用户名
                    required: "请输入用户名",
                    rangelength: "用户名长度{0}~{1}字符",
                },

                "employee.password": {  //密码
                    required: "请输入密码",
                    range: "密码长度{0}位~{1}位",
                },

                "repassword": { //验证密码
                    required: "请输入验证密码",
                    equalTo: "两次密码输入不一样"
                },

                "employee.email": { //Email
                    required: "请输入一个正确的邮箱",
                    email: "请输入一个正确的邮箱"
                },

                "employee.age": {   //年龄
                    required: "请输入年龄",
                    range: "年龄{0}~{1}",
                }
            }
        })
    }
});

/**
 * 角色
 */
$(function () {
    //单个选中
    $("#select").click(function () {
        $("#allRole option:selected").appendTo($("#selectRole"));
    });
    //全选
    $("#selectAll").click(function () {
        $("#allRole option").appendTo($("#selectRole"));
    });
    //单个非选中
    $("#deselect").click(function () {
        $("#selectRole option:selected").appendTo($("#allRole"));
    });
    //全不选
    $("#deselectAll").click(function () {
        $("#selectRole option").appendTo($("#allRole"));
    });

    //表单提交
    $("#editForm").submit(function () {
        //设置右侧所有option为选中状态
        $("#selectRole option").prop("selected", true);
    });
})

/**
 *  移除已有的角色
 */
$(function () {
    //1 已经有些角色id
    var roles = $("#selectRole option").map(function (index, item) {

        return $(item).val();
    });


    //2
    $("#allRole option").map(function (index, item) {

        index = $.inArray($(item).val(), roles);
        if (index > -1) {
            $(item).remove();
        }
        console.debug(index);

    });
})