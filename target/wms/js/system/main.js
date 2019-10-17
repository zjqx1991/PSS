/**
 * zTree
 */
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClick
    },
    async: {
        enable: true,
        url: "systemMenu_loadSystemMenuByParentSn",
        autoParam:["sn=qo.parentSn"]
    }

};


//标准JSON数据
var zNodes = {
    "businessMenu": [
        {id: 2, pId: 0, name: "业务模块", isParent: true, sn:"business"}
        ],
    "systemMenu": [
        {id: 1, pId: 0, name: "系统模块", isParent: true, sn:"system"}
        ],
    "chartMenu": [
        {id: 3, pId: 0, name: "报表模块", isParent: true, sn:"chart"}
        ],
};


function onClick(treeId, treeNode, clickFlag) {
    if (clickFlag.action) {
        $("#here_area").html("当前位置：系统&nbsp;>&nbsp;" + clickFlag.name);
        $("#rightMain").prop("src", clickFlag.action);
    }
}


/**
 * 加载菜单
 * @param sn
 */
function loadMenu(sn) {
    //3、加载ztree
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}

/**
 * 当前日期
 */
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
    }
}


/**
 * 左侧菜单
 */
function clickLeftMenu() {

    // 1、默认页面
    loadMenu("businessMenu");

    // 2、点击左侧菜单
    $("#left_menu ul li").click(function () {
        //1、遍历所有的li未选中
        $.each($("#left_menu ul li"), function (index, item) {
            $(item).removeClass();
            $(item).children("img").prop("src", "images/common/" + (index + 1) + ".jpg");
        });
        //2、设置为选中状态
        $(this).prop("class", "selected");
        //3、设置选中图片
        $(this).children("img").prop("src",
            "images/common/" + ($(this).index() + 1) + "_hover.jpg");
        //4、设置标题图片
        $("#nav_module").children("img").prop("src",
            "images/common/module_" + ($(this).index() + 1) + ".png");
        //5、获取当前点击内容
        var rootMenu = $(this).data("rootmenu");
        loadMenu(rootMenu);
    });
}

$(function () {
    //1、加载当前时间
    loadDate();

    //2、左侧菜单点击
    clickLeftMenu();


});