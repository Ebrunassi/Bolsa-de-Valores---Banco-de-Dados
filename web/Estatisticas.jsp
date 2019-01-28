<%@page import="java.util.ArrayList"%>
<%@page import="model.Cotacao"%>
<%@page import="model.Cotacao"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Highcharts Example</title>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style type="text/css">

		</style>
	</head>
	<body>
<script src="JS/code/highcharts.js"></script>
<script src="JS/code/modules/exporting.js"></script>
<script src="JS/code/modules/export-data.js"></script>
<div class="jumbotron text-center">
            <h3>Empresas com maiores cotações</h3>
        </div>

<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>


            <%ArrayList<Cotacao> lista = (ArrayList<Cotacao>) request.getAttribute("lista");%>
            
            <script type="text/javascript">

                Highcharts.chart('container', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: 'Empresas com maiores cotações'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [
                            {
                        name: 'Brands',
                        colorByPoint: true,
                        data: [
                        <%
                            String nome,nome_atual;
                            float cotacao,cotacao2;
                            float maior_cot = 0,maior_cot_2 = 0,menor_cot = 9999,menor_cot_2 = 9999;
                            String nome_maior = null;
                            String nome_maior_2 = null;
                            String nome_menor = null;
                            String nome_menor_2 = null;
                            for(int i = 0; i < lista.size() ; i++){
                                nome = lista.get(i).getEmpresa();
                                cotacao    = lista.get(i).getCotacaoValor();
                                if(cotacao > maior_cot){
                                    maior_cot = cotacao;
                                    nome_maior = nome;
                                }
                                if(cotacao > maior_cot_2 && cotacao < maior_cot){
                                    maior_cot_2 = cotacao;
                                    nome_maior_2 = nome;
                                }
                                if(cotacao < menor_cot){
                                    menor_cot = cotacao;
                                    nome_menor = nome;
                                }
                                if(cotacao < menor_cot_2 && cotacao > menor_cot){
                                    menor_cot_2 = cotacao;
                                    nome_menor_2 = nome;
                                }
                                out.print("{");
                                out.print("name: '" + nome + "',");
                                out.print("y:" + cotacao);   
                                if(i + 1>= lista.size()){  // If para verificar a última linha
                                    out.print("}");
                                }else{
                                out.print("},");                                    
                                }
                            }                        
                        %>
                         ]
                    }]
                });
            </script>
            <br /><br />
            <div class="panel panel-default">
                <div class="panel-body"><h3> Quais as mais caras? </h3></div>
            </div>
                Em comparação com as empresas cadastradas, a <b> <% out.print("'"+nome_maior+"'"); %> </b>foi a que apresentou as maiores cotações <br />
                Apresentando um valor médio de <b><% out.print("R$" + maior_cot); %></b> por ação no período apresentado               
            <br /><br />
                Logo depois, a segunda empresa mais cara foi a <b> <% out.print("'"+nome_maior_2+"'"); %> </b> que <br />
                apresentou cotações de valores médios de <b><% out.print("R$" + maior_cot_2); %></b> por ação no período apresentado               
            <br /><br />
            <div class="panel panel-default">
                <div class="panel-body"><h3> Quais as mais baratas? </h3></div>
            </div>
                No período analizado, a empresa com as cotações mais baratas foi a <b> <% out.print("'"+nome_menor+"'"); %> </b><br />
                Apresentando um valor médio de <b><% out.print("R$" + menor_cot); %></b> por ação no período               
            <br /><br />
                
	</body>
</html>
