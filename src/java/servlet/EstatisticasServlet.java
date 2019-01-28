/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import dao.CotacaoDAO;
import dao.DAOFactory;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import static javax.swing.Spring.height;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import model.CalculoEstatistica;
import model.Cotacao;
//import model.LineChartExample;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.ChartRenderingInfo;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.entity.StandardEntityCollection;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.category.CategoryItemRenderer;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
//import org.jfree.ui.RefineryUtilities;
/**
 *
 * @author ebrun
 */
@WebServlet(urlPatterns = {"/EstatisticasServlet"})

public class EstatisticasServlet extends HttpServlet {

    public EstatisticasServlet(){
        super();
    }
    
/*
        Aqui é onde estava a implementação da função que calculava
        as estatísticas e retornava um gráfico
*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Cotacao> cotacaoLista = (ArrayList<Cotacao>) request.getAttribute("cotacaoLista");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EstatisticasServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EstatisticasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RequestDispatcher dispatcher;
        
        switch (request.getServletPath()) {
                
                case "/Estatisticas":
                    dispatcher = request.getRequestDispatcher("/Estatisticas.jsp");
                    dispatcher.forward(request, response);
                    break;
        }
        doPost(request,response);
        processRequest(request, response);
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            DAOFactory daoFactory = new DAOFactory();           // DAO será usado para fazer o acesso
            CotacaoDAO dao = daoFactory.getCotacaoDAO();        // ao banco de dados
            Cotacao cotacao = new Cotacao();
            
            String button = request.getParameter("button");
            String opcao = request.getParameter("opcao");
            
            if("estatisticas".equals(button)){      // Estatísticas (%)s
                ArrayList<Cotacao> cotacaoLista;
                cotacaoLista = dao.cotacao_media();
                request.setAttribute("lista",cotacaoLista);
                //request.setAtrribute('data empresa');
                request.getRequestDispatcher("Estatisticas.jsp").forward(request,response);                  
            }
            
            else if("crescimento".equals(button)){
//                ArrayList<Cotacao> empresaLista;
//                empresaLista = dao.cotacao_media();
//                request.setAttribute("lista",empresaLista);
                request.getRequestDispatcher("CrescimentoEmpresas.jsp").forward(request,response); 
            }
            
            else if("top3_crescimento".equals(button)){
                request.getRequestDispatcher("Top3_Crescimento.jsp").forward(request,response); 
            }
            else if("top3_prejuizo".equals(button)){
                request.getRequestDispatcher("Top3_Prejuizo.jsp").forward(request,response); 
            }
            else if("investimento".equals(button)){
                request.getRequestDispatcher("Investimento.jsp").forward(request,response); 
            }
            else if("valor_investimento".equals(button)){
                
                request.getRequestDispatcher("Simulacao.jsp").forward(request,response); 
            }
            
            
             if(!"nulo".equals(opcao)){
                ArrayList<Cotacao> empresaLista = null;
                if("ambev".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(1);
                }if("bahema".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(2);
                }if("banrisul".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(3);
                }if("cielo".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(4);
                }if("itau".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(5);
                }if("magazine".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(6);
                }if("petrobras".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(7);
                }if("santander".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(8);
                }if("vale".equals(opcao)){
                    empresaLista = dao.all_empresa_tudo(9);
                }
                request.setAttribute("lista",empresaLista);
                request.getRequestDispatcher("AnaliseEmpresa.jsp").forward(request,response); 
                
             }
            
            
            
            
            
            if(!"nulo".equals(opcao)){
                ArrayList<Cotacao> empresaLista;
//                JFreeChart chart = null;
                if("ambev".equals(opcao)){
                    empresaLista = dao.all_empresa(1);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Ambev","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("bahema".equals(opcao)){
                    empresaLista = dao.all_empresa(2);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Bahema","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("banrisul".equals(opcao)){
                    empresaLista = dao.all_empresa(3);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Banrisul","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("cielo".equals(opcao)){
                    empresaLista = dao.all_empresa(4);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Cielo","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("itau".equals(opcao)){
                    empresaLista = dao.all_empresa(5);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Itau","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("magazine".equals(opcao)){
                    empresaLista = dao.all_empresa(6);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Magazine Luiza","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("petrobras".equals(opcao)){
                    empresaLista = dao.all_empresa(7);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Petrobrás","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("santander".equals(opcao)){
                    empresaLista = dao.all_empresa(8);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Santander","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }if("vale".equals(opcao)){
                    empresaLista = dao.all_empresa(9);
                    CalculoEstatistica calculo = new CalculoEstatistica();
//                    chart = ChartFactory.createLineChart("Vale","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                }
                
                
                
//                if (chart != null) {
//                    int width = 500;
//                    int height = 350;
//                    
//                    final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
//                    response.setContentType("image/png");
//                    //request.getRequestDispatcher("Estatisticas.jsp").forward(request,response);
//                    OutputStream out = response.getOutputStream();          //-- Descomentar para mostrar o gráfico
//                    ChartUtilities.writeChartAsPNG(out, chart, width, height, info);
////                    ChartUtilities.saveChartAsPNG(new File("acompanhamento.png"),chart,width,height,info);
////                    request.setAttribute("acompanhamento","acompanhamento.png");
//                    request.getRequestDispatcher("Estatisticas.jsp").forward(request,response);
//                }
                
                
                
            }
            if("ambev".equals(opcao)){         // Acompanha o crescimento da média da MAXIM e MINIMA da empresa por dia
                    ArrayList<Cotacao> empresaLista;
                    empresaLista = dao.all_empresa(1);           // Pega o MIN e MAX da empresa específica
                    CalculoEstatistica calculo = new CalculoEstatistica();
                   
//                    JFreeChart chart = ChartFactory.createLineChart("Ambev","Categorias","Valores", calculo.carregaLinha(empresaLista),PlotOrientation.VERTICAL, true, true, true );
                    
//                if (chart != null) {
//                    int width = 500;
//                    int height = 350;
//                    
//                    final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
//                    response.setContentType("image/png");
//                    //request.getRequestDispatcher("Estatisticas.jsp").forward(request,response);
//                    OutputStream out = response.getOutputStream();          //-- Descomentar para mostrar o gráfico
//                    ChartUtilities.writeChartAsPNG(out, chart, width, height, info);
//
//                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
