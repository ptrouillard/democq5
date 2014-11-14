<%@ page language="java" import="java.util.*" errorPage="" %>
<%@include file="/libs/foundation/global.jsp"%>
<cq:defineObjects/>

Title : <%=currentNode.getProperty("jcr:title").getValue().getString()%>
Description : <%=currentNode.getProperty("jcr:description").getValue().getString()%>
