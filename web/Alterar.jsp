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
        <title>Alterar dados</title>
    </head>
    <body>
        <h1>Alterando dados</h1>
        <h3> Digite o nome da empresa e data da cotação </h3>
        <form action="CotacaoServlet" method="post">
            Empresa : <br>
            <input type="text" size="10" name="empresa"><br>
            Data : <br>
            <input type="text" size="10" name="data"><br>
            <a href="/BolsaValores/AlterarDados">Alterar dados desta empresa</a>
            <!--<input type="submit" name="button" value="alterar">-->
        </form>
    </body>
</html>
