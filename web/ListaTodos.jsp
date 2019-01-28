<%-- 
    Document   : ListaTodos
    Created on : 01/09/2018, 23:46:20
    Author     : ebrun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Cotacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banco</title>
    </head>
    <body>
        <form action="CotacaoServlet" method="post">
        <h1>Cotações armazenadas no banco</h1>
        <%ArrayList<Cotacao> cotacaoLista = (ArrayList<Cotacao>) request.getAttribute("cotacaoLista");
       
       
            out.print("<fieldset>");
            out.print("<legend><strong>Cotações</strong></legend>");
            %>
            <table border="1px" width="80%">
            <%
            out.print("<tr>");
            out.print("<th>Empresa</th><th>Data</th><th>Cotação</th><th>Minima</th><th>Maxima</th><th>Variação(%)</th><th>Variação</th>");
            for(int i = 0 ; i < cotacaoLista.size(); i++){
                
                out.print("<tr>");
                out.print("<td>  "+cotacaoLista.get(i).getEmpresa()+"  </td>   ");
                out.print("<td>  "+cotacaoLista.get(i).getData()+"  </td>    ");
                out.print("<td>  "+cotacaoLista.get(i).getCotacaoValor()+" </td> ");
                out.print("<td>  "+cotacaoLista.get(i).getMinima()+"  </td>    ");
                out.print("<td>  "+cotacaoLista.get(i).getMaxima()+"  </td>");
                out.print("<td>  "+cotacaoLista.get(i).getVolume()+"  </td>");
                out.print("<td>  "+cotacaoLista.get(i).getVariacao()+"  </td>");
                out.print("<td>");                
                %>
                <a href="/BolsaValores/Alterar?empresaNome=<%=cotacaoLista.get(i).getEmpresa()%>&dataNome=<%=cotacaoLista.get(i).getData()%>">ALTERAR</a>
                <%
                out.print("</td>");
                out.print("<td>");
                %>
                <a href="/BolsaValores/Deletar?empresaNome=<%=cotacaoLista.get(i).getEmpresa()%>&dataNome=<%=cotacaoLista.get(i).getData()%>">DELETAR</a>
                <!--<input type="submit" name="button" value="deletarVisualizar">-->
                <%
                out.print("</td>");
                out.print("</tr>");
            }
            out.print("</tr>");
            out.print("</table>");
        %>
        </form>
    </body>
</html>
