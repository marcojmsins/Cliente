<%--
  Created by IntelliJ IDEA.
  User: mmartinezmol
  Date: 2019-04-26
  Time: 13:25
--%>

<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Agencias Encontradas</title>
</head>

<body>
<table border="1">
    <g:each var="i" in="${agencies}">
        <tr>
        <td bgcolor="#daa520">Agency Code</td><td>${i.agency_code}</td>
        <td bgcolor="#daa520">Description</td></rd><td>${i.description}</td>
        <td bgcolor="#daa520">Payment Method</td></tf><td>${i.payment_method_id}</td>
        <td bgcolor="#daa520">City</td><td>${i.address.city}</td>
        <td bgcolor="#daa520">State</td><td>${i.address.state}</td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="button" onclick="addAgency(${i as JSON})">Like</button>
            </td>
        </tr>
        <tr><td colspan="10"><br></td></tr>
    </g:each>
</table>
</body>
</html>

<g:javascript>
function addAgency(agency) {
var URL="${createLink(controller: 'Client', action: 'addAgency')}";
$.ajax({
url:URL,
type: 'POST',
body: {agency: agency},
success: function (data){
alert("Guardado");
}
});
}
</g:javascript>