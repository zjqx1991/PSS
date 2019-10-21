<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox-1.3.4.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/plugins/fancybox/jquery.fancybox-1.3.4.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="/js/plugins/fancybox/style.css"/>
    <script type="text/javascript" src="/js/system/product.js"></script>
    <script type="text/javascript"  src="/js/system/orderBill.js"></script>
    <title>PSS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $(".left2right").click(function () {
                //回传数据
                $(window.opener.document.getElementById("tag_name")).val($(this).data("json").name);
                $(window.opener.document.getElementById("tag_brand")).text($(this).data("json").brandName);
                $(window.opener.document.getElementById("tag_costPrice")).val($(this).data("json").costPrice);
                $(window.opener.document.getElementById("tag_pid")).val($(this).data("json").id);
                //关闭页面
                window.close();
            })
        })
    </script>
</head>
<body>
<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>

<s:form id="searchForm" action="product_productSelectList" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        商品名称/编号
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        商品品牌
                        <s:select name="qo.brandId" cssClass="ui_select01" list="#brands" listKey="id" listValue="name" headerKey="-1"
                                  headerValue="所有品牌"/>
                                            </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 next_btn" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>商品编号</th>
                    <th>商品品牌</th>
                    <th>商品成本价</th>
                    <th>商品销售价</th>
                    <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td>
                                <a class="productImg" href="<s:property value='imagePath'/>"><img src="<s:property value='smallImagePath'/>" width="100px" height="100px"/></a>
                            </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>

                            <td>
                                <input type="button" value="选择商品" class="left2right" data-json="<s:property value="productJSON"/>"/>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
                <%-- 分页 --%>
            <%@include file="/WEB-INF/views/common/common_page.jsp" %>
        </div>
    </div>
</s:form>
</body>
</html>

