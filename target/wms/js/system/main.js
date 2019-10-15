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

    $("#left_menu ul li").click(function () {
        //1、遍历所有的li未选中
        $.each($("#left_menu ul li"), function (index, item) {
            $(item).removeClass();
			$(item).children("img").prop("src", "images/common/"+ (index + 1) +".jpg");
        });
        //2、设置为选中状态
        $(this).prop("class", "selected");
        //3、设置选中图片
		$(this).children("img").prop("src",
			"images/common/" + ($(this).index() + 1) + "_hover.jpg");
    });
}

$(function () {
    //1、加载当前时间
    loadDate();

    //2、左侧菜单点击
    clickLeftMenu();
});