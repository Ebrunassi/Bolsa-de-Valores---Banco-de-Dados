<%@page import="java.util.Collections"%>
<%@page import="dao.CotacaoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DAOFactory"%>
<%@page import="model.Cotacao"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
</head>
<body>
     <script src="JS/code/highcharts.js"></script>
        <script src="JS/code/modules/exporting.js"></script>
        <script src="JS/code/modules/export-data.js"></script>
        <div class="jumbotron text-center">
            <h3>Top 3 - Lucro</h3>
        </div>
   <%   ArrayList<Cotacao> lista;
        DAOFactory daoFactory = new DAOFactory();           // DAO será usado para fazer o acesso
        CotacaoDAO dao = daoFactory.getCotacaoDAO(); 
        lista = dao.get_cresc_positivo();
        float max = 0, max1,max2,max3;
        String top1=null,top2=null,top3=null;
        for(int i = 0 ; i < lista.size() ; i++){        // Top 1
            if(lista.get(i).getCotacaoValor() > max){
                max = lista.get(i).getCotacaoValor();
                top1 = lista.get(i).getEmpresa();
            }
        }
        max1 = max;
        max = 0;
        for(int i = 0 ; i < lista.size() ; i++){        // Top 2
            if(lista.get(i).getCotacaoValor() > max && lista.get(i).getCotacaoValor() < max1 ){
                max = lista.get(i).getCotacaoValor();
                top2 = lista.get(i).getEmpresa();
            }
        }
        max2 = max;
        max = 0;
        for(int i = 0 ; i < lista.size() ; i++){        // Top 3
            if(lista.get(i).getCotacaoValor() > max && lista.get(i).getCotacaoValor() < max2){
                max = lista.get(i).getCotacaoValor();
                top3 = lista.get(i).getEmpresa();
            }
        }
        max3 = max;
//        out.print("1. " + top1 + "  2." + top2 + "  3. "+ top3);
   %>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>


<script type="text/javascript">

Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Top 3 empresas que mais cresceram'
    },
    subtitle: {
        text: 'IBOVESPA'
    },
    xAxis: {
        categories: [
            'Crescimento no período de iserção'
        ],
        crosshair: true
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Taxa de crescimento (max - min)'
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    series: [{
        <%
            out.print("name: '" + top2 + "',");
            out.print("data: [" + max2 +"]");
            out.print("}, {");
            out.print("name: '" + top1 + "',");
            out.print("data: [" + max1 +"]");
            out.print("}, {");
            out.print("name: '" + top3 + "',");
            out.print("data: [" + max3 +"]");
            
        %>
        


    }]
});
</script>
<div class="panel panel-default">
                <div class="panel-body"><h3>Empresas que mais cresceram </h3></div>
            </div>
               Desde o seu primeiro dia, a empresa <b>'<%out.print(top1);%>' </b>apresentou uma taxa de crescimento de <%out.print(max1);%><br />
               Entende-se como taxa de crescimento o valor da soma diferença da cotação máxima e mínima de cada dia do período.<br /><br />
               Em segundo lugar, a empresa <b>'<%out.print(top2);%>'</b> mostra o seu crescimento com a taxa de <%out.print(max2);%><br />
               E por último, em terceiro, a empresa <b>'<%out.print(top3);%>'</b> apresenta uma taxa de crescimento de <%out.print(max3);%><br />
            <br /><br />
</body>
</html>



