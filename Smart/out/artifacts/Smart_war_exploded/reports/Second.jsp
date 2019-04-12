<%@ page import="app.model.Model" %>
<%@ page import="app.model.Output" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Memes
  Date: 4/11/2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Топ 5 форм</title>
</head>
<body>
<div>
    <h1>Составить ТОП – 5 самых используемых форм.</h1>
</div>

<table border="1">
    <tr>

        <th>formid</th>

    </tr>
    <%

        ArrayList<Output> str = ((Model) request.getAttribute("top5")).getList();

        for (Output s: str
        ) {
            out.println("<tr><td>" + s.getFormid() + "</td></tr>");
        }

    %>
</table>
</body>
</html>
