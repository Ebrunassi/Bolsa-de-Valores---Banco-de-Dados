<%-- 
    Document   : Investimento
    Created on : 31/10/2018, 01:26:59
    Author     : ebrun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.CotacaoDAO"%>
<%@page import="dao.DAOFactory"%>
<%@page import="model.Cotacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="JS/code/highcharts.js"></script>
        <script src="JS/code/modules/exporting.js"></script>
        <script src="JS/code/modules/export-data.js"></script>
        <div class="jumbotron text-center">
            <h3>Em qual empresa investir?</h3>
            <p>As vezes temos dúvidas sobre qual seria a melhor escolha de investimento</p>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                <b><h4>Relação da estabilidade das empresas</h4></b> <br />
                
            
            
           
        
        <%
        ArrayList<Cotacao> listaEstavel,listaInstavel,lista;

        DAOFactory daoFactory = new DAOFactory();           // DAO será usado para fazer o acesso
        CotacaoDAO dao = daoFactory.getCotacaoDAO(); 
        
        lista = dao.all_empresa_dif(1);
        float soma=0,somaMax = 0;
        int posInstavel=0, posEstavel=0;
        for(int i = 1 ; i < 9 ; i++){
            lista = dao.all_empresa_dif(i);
            
            for(int j = 0 ; j < lista.size() ; j++){
                soma = soma + lista.get(j).getMinima();
            }
            if(soma > somaMax){
                somaMax = soma;
                posInstavel = i;
            }
            soma = 0;
        }
        lista = dao.all_empresa_dif(posInstavel);
        out.print("<b>Empresa mais instável: '"+ lista.get(1).getEmpresa()+"'</b><br />");
        %>
         <%

        lista = dao.all_empresa_dif(1);
        soma=0;
        somaMax = 100000;
        posEstavel=0;
        for(int i = 1 ; i < 9 ; i++){
            lista = dao.all_empresa_dif(i);
            
            for(int j = 0 ; j < lista.size() ; j++){
                soma = soma + lista.get(j).getMinima();
            }
            if(soma < somaMax){
                somaMax = soma;
                posEstavel = i;
            }
            soma = 0;
        }
        lista = dao.all_empresa_dif(posEstavel);
        out.print("<b>Empresa mais estável: '"+ lista.get(1).getEmpresa()+"'</b>");
        
        listaInstavel = dao.all_empresa_tudo(posInstavel);    
        listaEstavel = dao.all_empresa_tudo(posEstavel);
        %>
        </div>
        </div>
         
        
        
     <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        <script type="text/javascript">

    Highcharts.chart('container', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'Gráfico de variação do valor de cotação'
        },
        subtitle: {
            text: 'IBOVESPA'
        },
        xAxis: {
            
            <%
                    lista = dao.all_empresa_tudo(1);
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
                text: 'Valor cotação em reais (R$)'
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
            <%out.print("name: '"+listaEstavel.get(0).getEmpresa()+"',");%>
            <%
            out.print("data: [");
            for(int i = listaEstavel.size() -1 ; i > 0 ; i--){
                if(i - 1 != 0){
                    out.print(listaEstavel.get(i).getCotacaoValor() + ", ");
                }else{
                    out.print(listaEstavel.get(i).getCotacaoValor());
                }
                
            }
            out.print("]");
            %>
           
        }, {
            <%out.print("name: '"+listaInstavel.get(0).getEmpresa()+"',");%>
            <%
            out.print("data: [");
            for(int i = listaInstavel.size() -1 ; i > 0 ; i--){
                if(i - 1 != 0){
                    out.print(listaInstavel.get(i).getCotacaoValor() + ", ");
                }else{
                    out.print(listaInstavel.get(i).getCotacaoValor());
                }
                
            }
            out.print("]");
            %>
          }]
    });
    </script>
    
    <form action="EstatisticasServlet" method="post">
     <div class="form-group">
      <label for="usr">Quanto você pretende investir?</label>
      Ex: (2500.00)
      <input type="text" name="valor_investir" class="form-control" id="usr">
      <button type="submit" name="button" class="btn btn-default" value="valor_investimento">Investir</button>
      </div>
    </form>
    
    </body>
</html>
