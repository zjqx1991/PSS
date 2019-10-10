<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/employee/employee.js"></script>
    <title>PSS-账户管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<s:form id="searchForm" action="employee" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        所属部门
                        <s:select name="qo.departId" list="#depts" listKey="id" listValue="name" headerKey="-1" headerValue="所有部门"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 next_btn" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="employee_input"/>
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
                        <th>用户名</th>
                        <th>EMAIL</th>
                        <th>年龄</th>
                        <th>所属部门</th>
                        <th>角色</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.resultList">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="email"/></td>
                            <td><s:property value="age"/></td>
                            <td><s:property value="department.name"/></td>
                            <td>角色1，角色2</td>
                            <td>
                                <s:url var = "editURL" namespace="/" action="employee_input">
                                    <s:param name="employee.id" value="id"/>
                                </s:url>

                                <a href="<s:property value="editURL"/>">编辑</a>
                                <s:a namespace="/" action="employee_delete">
                                    <s:param name="employee.id" value="id"/>删除
                                </s:a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="ui_tb_h30">
                <div class="ui_flt" style="height: 30px; line-height: 30px;">
                    共有

                    <span class="ui_txt_bold04"><s:property value="#pageResult.totalCount" /></span>
                    条记录，当前第
                    <span class="ui_txt_bold04"><s:property value="#pageResult.currentPage" />/<s:property value="#pageResult.totalPage" /></span>
                    页
                </div>
                <div class="ui_frt">
                    <input type="button" value="首页" class="ui_input_btn01 next_btn" data-page="<s:property value="1"/>"/>
                    <input type="button" value="上一页" class="ui_input_btn01 next_btn" data-page="<s:property value="#pageResult.prevPage"/>"/>
                    <input type="button" value="下一页" class="ui_input_btn01 next_btn" data-page="<s:property value="#pageResult.nextPage"/>"/>
                    <input type="button" value="尾页" class="ui_input_btn01 next_btn totalPage"  data-page="<s:property value="#pageResult.totalPage"/>"/>

                    <s:select list="{5,10,20,50}" name="qo.pageSize" cssClass="ui_select02"/>

                    转到第<s:textfield id="input_num_btn" name="qo.currentPage" cssClass="ui_input_txt01" />页
                    <input type="button" class="ui_input_btn01 next_btn" value="跳转"/>
                </div>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>

