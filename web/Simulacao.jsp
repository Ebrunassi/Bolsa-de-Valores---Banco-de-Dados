<%-- 
    Document   : Simulacao
    Created on : 31/10/2018, 11:16:16
    Author     : ebrun
--%>

<%@page import="dao.CotacaoDAO"%>
<%@page import="java.util.ArrayList"%>
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
        
        <%
            ArrayList<Cotacao> lista,lista_2;
            int num_emp,num = 0;
            String valor_investir = request.getParameter("valor_investir");
            float valor = Float.parseFloat(valor_investir);
            float num_acoes;
            int vet_pos[];
            vet_pos = new int[20];
            
            DAOFactory daoFactory = new DAOFactory();           // DAO será usado para fazer o acesso
            CotacaoDAO dao = daoFactory.getCotacaoDAO(); 
            
            num_emp = dao.get_num_emp();
        
            for(int i = 1 ; i <= num_emp-1  ; i++){
                lista = dao.all_empresa_pode_investir(i,valor);
                if(lista.size() != 0){
                    vet_pos[num] = i;
                    num++;
                }
            }
            vet_pos[num] = 999;                     // Vetor contendo a posição na lista das empresas que se pode investir (a ultima posicao do vetor tem '999')
            %>
            <div class="jumbotron text-center">
            <h3>SIMULAÇÃO DE INVESTIMENTO</h3>
            <p></p>
        </div>
        <div class="panel panel-default">
            <div class="panel-body"> <b>
                <%
            out.print("Total investido: R$ " + valor); %> <br /> <%
            out.print("\nVocê poderia investir em " + num + " empresas!"); %> <br /> <%
            out.print(" Sendo elas : ");
            for(int i = 0; i <= vet_pos.length; i++){
                if(vet_pos[i] == 999)
                    break;
                lista = dao.all_empresa_pode_investir(vet_pos[i],valor);
                out.print("'"+ lista.get(0).getEmpresa() + "'");
                if(vet_pos[i+1] != 999)
                    out.print(", ");
            }
        %>
            
            
                </b></div>
        </div>
                
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    <script type="text/javascript">

        Highcharts.chart('container', {
            chart: {
                type: 'line'
            },
            title: {
                text: 'Simulação de investimento'
            },
            subtitle: {
                text: 'IBOVESPA'
            },
            xAxis: {
                
                <%
                    lista = dao.all_empresa_pode_investir(1,3000);
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
                    text: 'Valor em reais (R$)'
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
                for(int i = 0; i < vet_pos.length ; i++){
                    if(vet_pos[i] == 999){
                        break;
                    }
                    lista = dao.all_empresa_pode_investir(vet_pos[i],valor);
                    num_acoes = (int) (valor / lista.get(lista.size() - 1).getCotacaoValor());
                    
                    out.print("name: '" + lista.get(1).getEmpresa() + "',");
                    out.print("data: [");
                    for(int j = lista.size() - 1 ; j > 0 ; j--){
                        if(j - 1 == 0){
                            out.print((lista.get(j).getCotacaoValor() * num_acoes));
                        }else{
                            out.print((lista.get(j).getCotacaoValor() * num_acoes)+",");
                        }
                    }
                    out.print("]");
                    if(i + 1 == vet_pos.length){
                        break;
                    }else{
                        out.print("}, {");    
                    }
                }
                %>
                
            }]
        });
    </script>
    
        
    </body>
</html>
