<%-- 
    Document   : Criar
    Created on : 29/08/2018, 13:40:38
    Author     : ebrun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criação</title>
    </head>
    <body>
        <form action="CotacaoServlet" method="post">
            Empresa : <br>
            <input type="text" size="10" name="empresa"><br>
            Data : <br>
            <input type="text" size="10" name="data"><br>
            Cotacao : <br>
            <input type="text" size="10" name="cotacao"><br>
            Minima : <br>
            <input type="text" size="10" name="minima"><br>
            Maxima : <br>
            <input type="text" size="10" name="maxima"><br>
            Variacao : <br>
            <input type="text" size="10" name="variacao"><br>
            Volume : <br>
            <input type="text" size="10" name="volume"><br>
            <input type="submit" name="button" value="criar">
        </form>
    </body>
</html>
