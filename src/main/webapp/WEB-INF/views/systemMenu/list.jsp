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
    <script type="text/javascript" src="/js/system/systemMenu.js"></script>
    <title>PSS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>

<s:form id="searchForm" action="systemMenu" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <s:url var="NewURL" namespace="/" action="systemMenu_input">
                            <s:param name="qo.parentId" value="qo.parentId"/>
                        </s:url>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url='<s:property value="NewURL"/>'/>
                            <%--                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url='systemMenu_input'/>--%>

                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batch"
                               data-url="systemMenu_deleteBatch"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui_content">
            当前：
            <s:a namespace="/" name="systemMenu">根菜单</s:a>
            <s:iterator value="#menus">
                ->
                <s:a namespace="/" action="systemMenu">
                    <s:param name="qo.parentId" value="id"/>
                    <s:property value="name"/>
                </s:a>
            </s:iterator>

            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <th width="30"><input type="checkbox" id="all"/></th>
                    <th>编号</th>
                    <th>菜单名称</th>
                    <th>菜单编码</th>
                    <th>上级菜单</th>
                    <th>URL</th>
                    <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td><input autocomplete="off" type="checkbox" name="IDCheck" class="acb"
                                       data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="parent.name"/></td>
                            <td><s:property value="url"/></td>
                            <td>
                                    <%--
                                    http://localhost:8080/systemMenu_input.action?systemMenu.id=44&amp;qo.parentId=20
                                    这样的方式拼接参数时会多&amp
                                    <s:url var="editURL" namespace="/" action="systemMenu_input">
                                        <s:param name="systemMenu.id" value="id"/>
                                        <s:param name="qo.parentId" value="qo.parentId"/>
                                    </s:url>
                                    <a href="<s:property value="editURL"/>">编辑</a>
                                    --%>
                                <s:a namespace="/" action="systemMenu_input">
                                    <s:param name="systemMenu.id" value="id"/>
                                    <s:param name="qo.parentId" value="qo.parentId"/>
                                    编辑
                                </s:a>


                                <s:url var="deleteURL" namespace="/" action="systemMenu_delete">
                                    <s:param name="systemMenu.id" value="id"/>
                                </s:url>
                                <a href="javascript:;" class="btn_delete_msg"
                                   data-url="<s:property value="deleteURL"/>">删除</a>
                                <s:a namespace="/" action="systemMenu">
                                    <s:param name="qo.parentId" value="id"/>
                                    查看子菜单
                                </s:a>
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

