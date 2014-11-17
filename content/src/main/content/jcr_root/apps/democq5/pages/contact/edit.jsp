<%@ page language="java" import="java.util.*" errorPage="" %>
<%@include file="/libs/foundation/global.jsp"%>
<cq:defineObjects/>

<cq:includeClientLib categories="bootstrap.style" />

<cq:include path="header" resourceType="democq5/pages/header"/>

<form action="/content/contacts/*" method="POST">
    <table>
        <tbody>
            <tr>
                <td>First name</td>
                <td><input type="text" name="firstName"/></td>
            </tr>

            <tr>
                <td>Last name</td>
                <td><input type="text" name="lastName"/></td>
            </tr>

            <tr>
                <td>Email</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>Save contact</td>
                <td><input type="submit"/></td>
            </tr>
        </tbody>
    </table>
</form>

<cq:include path="footer" resourceType="democq5/pages/footer"/>
