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
    <script type="text/javascript" src="/js/system/productStock.js"></script>
    <title>PSS-库存管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>

<s:form id="searchForm" action="productStock" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        商品名称/仓库名称
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 next_btn" data-page="1"/>
                            <%--                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="productStock_input"/>--%>
                            <%--                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batch" data-url="productStock_deleteBatch" />--%>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <th width="30"><input type="checkbox" id="all"/></th>
                    <th>编号</th>
                    <th>商品名称</th>
                    <th>仓库名称</th>
                    <th>销售价</th>
                    <th>库存</th>
                    <th>商品总价</th>
                    <th>入库时间</th>
                    <th>出库时间</th>
                    <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td><input autocomplete="off" type="checkbox" name="IDCheck" class="acb"
                                       data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="product.name"/></td>
                            <td><s:property value="depot.name"/></td>
                            <td><s:property value="price"/></td>
                            <td><s:property value="storeNumber"/></td>
                            <td><s:property value="amount"/></td>
                            <td><s:property value="incomeDate"/></td>
                            <td><s:property value="outcomeDate"/></td>
                                <%--                            <td>--%>
                                <%--                                <s:url var="editURL" namespace="/" action="productStock_input">--%>
                                <%--                                    <s:param name="productStock.id" value="id"/>--%>
                                <%--                                </s:url>--%>

                                <%--                                <a href="<s:property value="editURL"/>">编辑</a>--%>

                                <%--                                <s:url var="deleteURL" namespace="/" action="productStock_delete">--%>
                                <%--                                    <s:param name="productStock.id" value="id"/>--%>
                                <%--                                </s:url>--%>
                                <%--                                <a href="javascript:;" class="btn_delete"--%>
                                <%--                                   data-url="<s:property value="deleteURL"/>">删除</a>--%>
                                <%--                            </td>--%>
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

