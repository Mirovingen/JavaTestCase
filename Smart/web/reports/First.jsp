<%@ page import="java.util.ArrayList" %>
<%@ page import="app.model.Model" %>
<%@ page import="app.model.Output" %><%--
  Created by IntelliJ IDEA.
  User: Memes
  Date: 4/11/2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей за последний час</title>
</head>
<body>
<div>
    <h1>Вывести список пользователей и используемых ими форм за последний час</h1>
</div>

<table border="1">
    <tr>
        <th>ssoid</th>
        <th>ts</th>
        <th>grp</th>
        <th>type</th>
        <th>subtype</th>
        <th>url</th>
        <th>orgid</th>
        <th>formid</th>
        <th>code</th>
        <th>ltpa</th>
        <th>sudirresponse</th>
        <th>ymdh</th>
    </tr>
    <%

        ArrayList<Output> str = ((Model) request.getAttribute("output")).getList();

        for (Output s: str
             ) {
            out.println("<tr><td>" + s.getSsoid() + "</td>");
            out.println("<td>" + s.getTs() + "</td>");
            out.println("<td>" + s.getGrp() + "</td>");
            out.println("<td>" + s.getType() + "</td>");
            out.println("<td>" + s.getSubtype() + "</td>");
            out.println("<td>" + s.getUrl() + "</td>");
            out.println("<td>" + s.getOrgid() + "</td>");
            out.println("<td>" + s.getFormid() + "</td>");
            out.println("<td>" + s.getCode() + "</td>");
            out.println("<td>" + s.getLtpa() + "</td>");
            out.println("<td>" + s.getSudirresponse() + "</td>");
            out.println("<td>" + s.getYmdh() + "</td></tr>");
        }

    %>
</table>


</body>
</html>
