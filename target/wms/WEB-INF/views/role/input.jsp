<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<body>
<%--引入信息界面--%>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>

<s:form name="editForm" action="role_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="role.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">用户编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">角色名</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">角色SN</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">权限</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <s:select id="unSelectRole" list="#permissions" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01"/>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <s:select id="selectedRole" name="role.permissions.id" list="role.permissions" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">系统菜单</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <s:select id="unSelectRole_menu" list="#systemMenus" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01"/>
                                </td>
                                <td align="center">
                                    <input type="button" id="select_menu" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll_menu" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect_menu" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll_menu" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <s:select id="selectedRole_menu" name="role.systemMenuList.id" list="role.systemMenuList" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>