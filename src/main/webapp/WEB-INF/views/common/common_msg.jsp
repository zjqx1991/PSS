<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>

<script type="text/javascript">

    <%--错误信息--%>
    <s:if test="hasActionErrors()">
    <%--    <s:actionerror/>--%>
    var msg = '<s:property value="actionErrors[0]"/>';
    $.dialog({
        title: '温馨提示',
        content: msg,
        ok: true
    });
    </s:if>

    <%--其他信息--%>
    <s:if test="hasActionMessages()">

    var msg = '<s:property value="actionMessages[0]"/>';
    $.dialog({
        content: msg,
        ok:true
    });
    </s:if>

</script>