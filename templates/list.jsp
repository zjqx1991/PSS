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
    <script type="text/javascript" src="/js/system/${lowerClassName}.js"></script>
    <title>PSS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>

<s:form id="searchForm" action="${lowerClassName}" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
<%--                    <div id="box_center">--%>
<%--                        姓名/邮箱--%>
<%--                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>--%>
<%--                        所属部门--%>
<%--                        <s:select name="qo.departId" list="#depts" listKey="id" listValue="name" headerKey="-1" headerValue="所有部门"/>--%>
<%--                    </div>--%>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 next_btn" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="${lowerClassName}_input"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batch" data-url="${lowerClassName}_deleteBatch" />
                    </div>
                </div>
            </div>
        </div>

        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <#list properts as pName>
                            <th>${pName}</th>
                        </#list>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td><input autocomplete="off" type="checkbox" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <#list properts as pName>
                                <td><s:property value="${pName}"/></td>
                            </#list>
                            <td>
                                <s:url var = "editURL" namespace="/" action="${lowerClassName}_input">
                                    <s:param name="${lowerClassName}.id" value="id"/>
                                </s:url>

                                <a href="<s:property value="editURL"/>">编辑</a>

                                <s:url var ="deleteURL" namespace="/" action="${lowerClassName}_delete">
                                    <s:param name="${lowerClassName}.id" value="id"/>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="deleteURL"/>">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
                <%-- 分页 --%>
            <%@include file="/WEB-INF/views/common/common_page.jsp"%>
        </div>
    </div>
</s:form>
</body>
</html>

