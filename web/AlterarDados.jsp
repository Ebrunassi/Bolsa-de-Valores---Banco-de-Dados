<%-- 
    Document   : Alterar
    Created on : 01/09/2018, 18:11:33
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
        <h3>Empresa encontrada! Digite os novos dados: </h3>
        <form action="CotacaoServlet" method="post">
<!--            Empresa : <br>
            <input type="text" size="10" name="empresa"><br>
            Data : <br>
            <input type="text" size="10" name="data"><br>-->
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
            <input type="submit" name="button" value="alterardados">
        </form>
    </body>
</html>
