<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:debug/>
<%--错误信息--%>
<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>

<%--其他信息--%>
<s:if test="hasActionMessages()">
    <s:actionmessage/>
</s:if>