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
})

