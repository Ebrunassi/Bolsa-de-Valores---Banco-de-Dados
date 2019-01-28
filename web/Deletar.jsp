<%-- 
    Document   : Deletar
    Created on : 29/08/2018, 14:11:49
    Author     : ebrun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Deletar cotação</h1>
        <form action="CotacaoServlet" method="post">
            Empresa : <br>
            <input type="text" size="10" name="empresa"><br>
            Data : <br>
            <input type="text" size="10" name="data"><br>
            <input type="submit" name="button" value="deletar">
        </form>
    </body>
</html>
