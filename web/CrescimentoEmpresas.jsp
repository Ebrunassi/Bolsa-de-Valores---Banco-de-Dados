<%-- 
    Document   : CrescimentoEmpresas
    Created on : 30/10/2018, 20:45:59
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <script src="JS/code/highcharts.js"></script>
        <script src="JS/code/modules/exporting.js"></script>
        <script src="JS/code/modules/export-data.js"></script>
        <div class="jumbotron text-center">
            <h3>Variação das ações das empresas</h3>
        </div>
        <%
        
        ArrayList<Cotacao> lista,lista_2;
        int num_emp;
        DAOFactory daoFactory = new DAOFactory();           // DAO será usado para fazer o acesso
        CotacaoDAO dao = daoFactory.getCotacaoDAO(); 
        
        num_emp = dao.get_num_emp();
        System.out.println("O NUMERO DE EMPRESAS É DE : " + num_emp);
        %>
        
        
        
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>



        <script type="text/javascript">

            Highcharts.chart('container', {
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Crescimento das empresas'
                },
                subtitle: {
                    text: 'IBOVESPA'
                },
                xAxis: {
                    <%
                    lista = dao.all_empresa(1);
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
                        text: 'Valor médio da cotação no dia'
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
                    <%
                    for(int i = num_emp ; i >= 1 ; i--){
                        lista = dao.all_empresa(i);
                        out.print("name: '" + lista.get(i).getEmpresa() + "',");
                        lista_2 = dao.all_empresa(i);
                        out.print("data : [");
                        for(int j = lista_2.size()-1 ; j > 0 ; j--){
                            if(j - 1 == 0){
                                out.print(lista_2.get(j).getMinima() + "]");
                            }else{
                                out.print(lista_2.get(j).getMinima() + ",");       // A média calculada está aqui
                            }
                        }
                        if(i - 1 == 0){
                            out.print("}]");
                        }else{
                            out.print("}, {");
                        }
                        
                    }
                        
                    %>

            });
	</script>
        
        <br /><br />
            <div class="panel panel-default">
                <div class="panel-body"><h3> O que faz uma ação crescer? </h3></div>
            </div>
                O mercado de ações funciona como uma espécie de leilão, onde a
                propriedade das empresas (chamadas de ações) estão sendo negociadas.<br />
                O preço de uma ação é determinado pela oferta e demanda.<br /><br />
                Podemos explicar o motivo de uma ação crescer com este simples exemplo: <br /><br />
                Se há mais pessoas que querem comprar uma ação do que vendê-la, 
                o preço irá subir, até porque essas ações são mais raras e as pessoas vão pagar um preço mais alto por elas. <br />
                Por outro lado, se há uma série de ações para venda e ninguém está interessado em comprá-las, o preço cairá rapidamente.<br /><br />
                
                Devido a isso, o mercado pode parecer flutuar bastante. Mesmo que não tenha nada de errado com uma empresa,
                um grande acionista que está tentando vender milhões de ações em um momento pode levar o preço das ações para baixo,
                simplesmente porque não há número suficiente de pessoas interessadas em comprar as ações que ele está tentando<br />
                vender. Uma vez que não há demanda real para as ações da empresa que está sendo vendida, o acionista é forçado
                a aceitar um preço mais baixo pelos papéis.
            <br /><br />
            
            
        
    </body>
</html>
