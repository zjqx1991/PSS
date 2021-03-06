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
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
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
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginDate" format="yyyy-MM-dd" var="beginDate"/>
                        <s:textfield name="qo.beginDate" cssClass="ui_input_txt02" value="%{beginDate}"/>
                        ~
                        <s:date name="qo.endDate" format="yyyy-MM-dd" var="endDate"/>
                        <s:textfield name="qo.endDate" cssClass="ui_input_txt02" value="%{endDate}"/>
                        供应商
                        <s:select cssClass="ui_select03" name="qo.supplierId" list="#suppliers" listKey="id" listValue="name"
                                  headerKey="-1"
                                  headerValue="全部"/>
                        订单状态
                        <s:select cssClass="ui_select03" name="qo.status" list="#{-1:'全部', 0:'未审核', 1:'已审核'}"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 next_btn" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="orderBill_input"/>
                            <%--                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batch"--%>
                            <%--                               data-url="orderBill_deleteBatch"/>--%>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <th width="30"><input type="checkbox" id="all"/></th>
                    <th>订单编号</th>
                    <th>业务时间</th>
                    <th>供应商</th>
                    <th>采购数量</th>
                    <th>采购金额</th>
                    <th>制单人</th>
                    <th>审核人</th>
                    <th>审核状态</th>
                    <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td><input autocomplete="off" type="checkbox" name="IDCheck" class="acb"
                                       data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="sn"/></td>
                            <td>
                                <s:date name="vdate" format="yyy-MM-dd"/>
                            </td>
                            <td><s:property value="supplier.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>
                            <s:if test="status == 0">
                                <td><span style="color: #00B83F">未审核</span></td>
                                <td>
                                    <s:a namespace="/" action="orderBill_audit">
                                        <s:param name="orderBill.id" value="id"/>
                                        审核
                                    </s:a>
                                    <s:url var="editURL" namespace="/" action="orderBill_input">
                                        <s:param name="orderBill.id" value="id"/>
                                    </s:url>

                                    <a href="<s:property value="editURL"/>">编辑</a>

                                    <s:url var="deleteURL" namespace="/" action="orderBill_delete">
                                        <s:param name="orderBill.id" value="id"/>
                                    </s:url>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="<s:property value="deleteURL"/>">删除</a>
                                </td>
                            </s:if>
                            <s:elseif test="status == 1">
                                <td><span style="color: red">已审核</span></td>
                                <td>
                                    <s:a namespace="/" action="orderBill_show">
                                        <s:param name="orderBill.id" value="id"/>
                                        查看
                                    </s:a>
                                </td>
                            </s:elseif>


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

