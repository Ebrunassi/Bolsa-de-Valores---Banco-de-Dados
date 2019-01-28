<%-- 
    Document   : AnaliseEmpresa
    Created on : 31/10/2018, 00:41:04
    Author     : ebrun
--%>

<%@page import="dao.CotacaoDAO"%>
<%@page import="dao.DAOFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cotacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analise Empresa</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <script src="JS/code/highcharts.js"></script>
        <script src="JS/code/modules/exporting.js"></script>
        <script src="JS/code/modules/export-data.js"></script>
        <div class="jumbotron text-center">
            <h3>Análise de empresa</h3>
        </div>
        <%ArrayList<Cotacao> lista = (ArrayList<Cotacao>) request.getAttribute("lista");%>
        <div class="container">
  <h2>Histórico de cotações</h2>
  <p>A tabela mostra o histórico de cotações da empresa selecioada:</p>            
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Empresa</th>
        <th>Data</th>
        <th>Cotacao</th>
        <th>Minima</th>
        <th>Maxima</th>
        <th>Variação</th>
        <th>Variação(%)</th>
      </tr>
    </thead>
    <tbody>
      
      <%
      for(int i = 0 ; i < lista.size() ; i++){
          out.print("<tr>");
          out.print("<td>" + lista.get(i).getEmpresa() + "</td>");
          out.print("<td>" + lista.get(i).getData() + "</td>");
          out.print("<td>" + lista.get(i).getCotacaoValor() + "</td>");
          out.print("<td>" + lista.get(i).getMinima() + "</td>");
          out.print("<td>" + lista.get(i).getMaxima() + "</td>");
          out.print("<td>" + lista.get(i).getVariacao() + "</td>");
          out.print("<td>" + lista.get(i).getVolume() + "</td>");
          out.print("</tr>");
      }
      %>
    </tbody>
  </table>
        <h3>Crescimento da empresa no período
            
            
            
            
        <%
        int num_emp;
        DAOFactory daoFactory = new DAOFactory();           // DAO será usado para fazer o acesso
        CotacaoDAO dao = daoFactory.getCotacaoDAO(); 
        
        num_emp = dao.get_num_emp();
        %>
        
       
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>



    <script type="text/javascript">

    Highcharts.chart('container', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'Crescimento da empresa'
        },
        subtitle: {
            text: 'IBOVESPA'
        },
        xAxis: {
            
             <%
                    out.print("categories: [");
                    for(int i = lista.size() -1 ; i > 0 ; i--){
                        out.print("'"+ lista.get(i).getData() +"'");
                        if(i - 1 == 0){
                            out.print("]");
                        }else{
                            out.print(",");
                        }
                    }
            %>
        },
        yAxis: {
            title: {
                text: 'Valor da ação'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            <%out.print("name: '"+lista.get(0).getEmpresa()+"',");%>
            <%
            out.print("data: [");
            for(int i = lista.size() -1 ; i > 0 ; i--){
                if(i - 1 != 0){
                    out.print(lista.get(i).getCotacaoValor() + ", ");
                }else{
                    out.print(lista.get(i).getCotacaoValor());
                }
                
            }
            out.print("]");
            %>
           
        }]
    });
    </script>
            
            
            
            
            
            
            
            
            
            
            
            
</div>
    </body>
</html>
