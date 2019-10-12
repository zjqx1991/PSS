<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/commonAll.js"></script>
    <script type="text/javascript" src="/js/system/permission.js"></script>
    <title>PSS-权限管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>

<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>

<s:form id="searchForm" action="permission" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <input type="button" value="加载权限" class="ui_input_btn01 btn_add_reload" data-url="permission_reload"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batch" data-url="permission_deleteBatch"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>权限表达式</th>
                        <th>权限名称</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td><input autocomplete="off" type="checkbox" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="expression"/></td>
                            <td><s:property value="name"/></td>
                            <td>

                                <s:url var="deleteURL" namespace="/" action="permission_delete">
                                    <s:param name="permission.id" value="id"/>
                                </s:url>

                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="deleteURL"/>">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="ui_tb_h30">
                <div class="ui_flt" style="height: 30px; line-height: 30px;">
                    共有
                    <span class="ui_txt_bold04"><s:property value="#pageResult.totalCount"/></span>
                    条记录，当前第
                    <span class="ui_txt_bold04"><s:property value="#pageResult.currentPage"/>/<s:property value="#pageResult.totalPage"/></span>
                    页
                </div>
                <%-- 分页列表 --%>
                <%@include file="/WEB-INF/views/common/common_page.jsp"%>

            </div>
        </div>
    </div>
</s:form>
</body>
</html>

