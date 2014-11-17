<%@ page language="java" import="java.util.*" errorPage="" %>
<%@include file="/libs/foundation/global.jsp"%>
<cq:defineObjects/>

<cq:includeClientLib categories="bootstrap.style" />

<cq:include path="header" resourceType="democq5/pages/header"/>

<ul>
    <li><a href="contact.edit">Add a new contact</a></li>
    <li><a href="contact.list">Search/List contacts</a></li>
</ul>

<cq:include path="footer" resourceType="democq5/pages/footer"/>

