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
    <script type="text/javascript" src="/js/system/orderBill.js"></script>
    <title>PSS-订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>

<s:form id="searchForm" action="orderBill" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                </div>
            </div>
        </div>

        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <th>商品名称</th>
                    <th>商品编号</th>
                    <th>品牌</th>
                    <th>商品个数</th>
                    <th>采购成本价</th>
                    <th>小计</th>
                    <th>销售价</th>
                    <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="orderBill.items">
                        <tr>
                            <td><s:property value="product.name"/></td>
                            <td><s:property value="product.sn"/></td>
                            <td><s:property value="product.brand.name"/></td>
                            <td><s:property value="number"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="amount"/></td>
                            <td><s:property value="product.salePrice"/></td>

                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="ui_tb">
                <span>制单人：</span><s:property value="orderBill.inputUser.name"/>
                <br/>
                <span>审核人：</span><s:property value="orderBill.auditor.name"/>
                <br/>
                <br/>
                <input type="button" value="返回上级" class="left2right" onclick="window.history.back()"/>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>

