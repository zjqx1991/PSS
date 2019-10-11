/**
 *  表单验证
 */
$(function () {
    if ($("#editForm").size() > 0) {
        $("#editForm").validate({
            rules: {
                "department.name": {  // 部门名
                    required: true,
                    rangelength: [2, 8]
                },

                "department.sn": {  // 部门SN
                    required: true,
                }
            },
            messages: {

                "department.name": {  // 部门名
                    required: "请输入部门名",
                    rangelength: "部门名长度{0}~{1}字符",
                },

                "department.sn": {  //  部门SN
                    required: "请输入部门SN",
                }
            }
        })
    }
})

