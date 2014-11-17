<%@ page language="java" import="java.util.*" errorPage="" %>
<%@include file="/libs/foundation/global.jsp"%>
<cq:defineObjects/>

<cq:includeClientLib categories="bootstrap.style" />

<%
 String message = request.getParameter("message").toString();
%>

<p class="bg-info">
<%=message%>
</p>