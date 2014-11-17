<%@ page language="java" import="java.util.*,decafeine.demo.cq5.democq5.*,decafeine.demo.cq5.democq5.impl.*" errorPage="" %>
<%@include file="/libs/foundation/global.jsp"%>
<cq:defineObjects/>

<cq:includeClientLib categories="bootstrap.style" />

<cq:include path="header" resourceType="democq5/pages/header"/>

<%
    PeopleService service = sling.getService(PeopleService.class);
    List<PeopleDTO> peoples = null;

    // search OR list ?
    String lastName = request.getParameter("lastName");
    if (lastName != null && lastName.isEmpty()) {
        peoples = service.search(lastName.toString());
    }
    else {
        lastName = "";
        peoples = service.list();
    }
%>

<h1>Search by lastname</h1>
<form action="contact.list" method="GET">
    Last name : <input type="text" name="lastName" value="<%=lastName%>"/>
</form>

<h1>Results</h1>
<table class="table table-striped">
    <thead>
        <tr class="row">
            <th>Email</th>
            <th>First name</th>
            <th>Last name</th>
        </tr>
    </thead>
    <tbody>
    <% for (PeopleDTO people : peoples) { %>
        <tr class="row">
            <td class="col-md-4"><cq:text value="<%=people.getEmail() %>"/></td>
            <td class="col-md-4"><cq:text value="<%=people.getFirstName() %>"/></td>
            <td class="col-md-4"><cq:text value="<%=people.getLastName() %>"/></td>
        </tr>
    <% } %>
    </tbody>
</table>

<h1>Create a new contact</h1>
<cq:include script="edit.jsp" />

<cq:include path="footer" resourceType="democq5/pages/footer"/>

