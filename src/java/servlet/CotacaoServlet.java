/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CotacaoDAO;
import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cotacao;
import com.google.gson.*;
import java.math.BigDecimal;
/**
 *
 * @author ebrun
 */
@WebServlet(name = "CotacaoServlet", urlPatterns = {"/CotacaoServlet", "/Criar","/Deletar","/ListaTodos","/Alterar","/AlterarDados"})
public class CotacaoServlet extends HttpServlet {
    String nomeEmpresaGlobal;
    String dataGlobal;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            DAOFactory daoFactory = new DAOFactory();
            
            CotacaoDAO dao = daoFactory.getCotacaoDAO();
            
            Cotacao cotacao = new Cotacao();
            String button = request.getParameter("button");

         if ("criar".equals(button)) {
            cotacao.setEmpresa(request.getParameter("empresa"));
            cotacao.setCotacaoValor(Float.parseFloat(request.getParameter("cotacao")));
            cotacao.setMinima(Float.parseFloat(request.getParameter("minima")));
            cotacao.setMaxima(Float.parseFloat(request.getParameter("maxima")));
            cotacao.setData(request.getParameter("data"));
            cotacao.setVolume(Float.parseFloat(request.getParameter("volume")));
            cotacao.setVariacao(Float.parseFloat(request.getParameter("variacao")));
            dao.create(cotacao);
            request.getRequestDispatcher("index.html").forward(request,response);
        } else if ("deletar".equals(button)) {
            // Recebe os dados vindos do forms da página 'Deletar.jsp' e apaga no banco 
//            cotacao = dao.read(request.getParameter("empresa"),request.getParameter("data"));
//            if(cotacao.getEmpresa().equals("") == true && cotacao.getData().equals("1") == true){
//                dao.delete(request.getParameter("empresa"),request.getParameter("data"));
//            }
            dao.delete(request.getParameter("empresa"),request.getParameter("data"));
            request.getRequestDispatcher("index.html").forward(request,response);
        } else if("mostrar".equals(button)){
            ArrayList<Cotacao> lista = dao.all();
            request.getRequestDispatcher("ListaTodos").forward(request,response);
        } else if("alterar".equals(button)){
            
            String empresaRecebida = "", dataRecebida = "";
            
            empresaRecebida = request.getParameter("empresa");
            
            dataRecebida = request.getParameter("data");
            cotacao = dao.read(empresaRecebida,dataRecebida);            
           
            if(cotacao.getData().equals("1") == true){
                request.getRequestDispatcher("index.html").forward(request,response);
            }else{
                nomeEmpresaGlobal = empresaRecebida;
                dataGlobal = dataRecebida;
                System.out.println("O NOME DA EMPRESA GLOBAL E : " + nomeEmpresaGlobal);
              request.getRequestDispatcher("AlterarDados.jsp").forward(request,response);
            }
        } else if("alterardados".equals(button)){
            cotacao = dao.read(nomeEmpresaGlobal,dataGlobal);   
            cotacao.setCotacaoValor(Float.parseFloat(request.getParameter("cotacao")));
            cotacao.setMinima(Float.parseFloat(request.getParameter("minima")));
            cotacao.setMaxima(Float.parseFloat(request.getParameter("maxima")));
            cotacao.setVolume(Float.parseFloat(request.getParameter("volume")));
            cotacao.setVariacao(Float.parseFloat(request.getParameter("variacao")));
            dao.update(cotacao);
            request.getRequestDispatcher("index.html").forward(request,response);
        } else if("visualizar".equals(button)){
            ArrayList<Cotacao> cotacaoLista;
            cotacaoLista = dao.all();
            request.setAttribute("cotacaoLista",cotacaoLista);
        }else if("deletarVisualizar".equals(button)){
            
            System.out.println("Empresa nome : "+request.getParameter("empresaNome"));
            dao.delete(request.getParameter("empresaNome"),request.getParameter("dataNome"));
            request.getRequestDispatcher("index.html").forward(request,response);
        } 
        else if("adicionaJson".equals(button)){
            
            String json = request.getParameter("json");
            System.out.println(json);
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();
            
            for(JsonElement element : array) {
                Cotacao novo = new Cotacao();
                JsonObject object = element.getAsJsonObject();
                float minima = object.get("minima").getAsFloat();
                float cotacaoValor = object.get("cotacao").getAsFloat();
                String dataNome = object.get("data").getAsString();
                float volume = object.get("volume").getAsFloat();
		String empresaNome = object.get("titulo").getAsString();
		float variacao = object.get("variacao").getAsFloat();
                float maxima = object.get("maxima").getAsFloat();
                novo.setMinima(minima);
                novo.setCotacaoValor(cotacaoValor);
                novo.setData(dataNome);
                novo.setVolume(volume);
                novo.setEmpresa(empresaNome);
                novo.setVariacao(variacao);
                novo.setMaxima(maxima);
		dao.create(novo);
            }

            request.getRequestDispatcher("index.html").forward(request,response);
        }else if("estatisticas".equals(button)){
            ArrayList<Cotacao> cotacaoLista;
            cotacaoLista = dao.all();
            request.setAttribute("cotacaoLista",cotacaoLista);           
            request.getRequestDispatcher("EstatisticasServlet.java").forward(request,response);
            
        }
//        } else if (request.getParameter("button3") != null) {
//            myClass.method3();
//        } else {
//            
//        }
            
        } catch (ClassNotFoundException ex) {
                            System.out.println("OI2");
            Logger.getLogger(CotacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
                            System.out.println("OI3");
            Logger.getLogger(CotacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
//           float a = Float.parseFloat(request.getParameter("cotacao"));
           //float valor = Float.parseFloat(request.getParameter("valor"));
           
//           float b = a + 1;
//           request.setAttribute("b",b);
           //request.setAttribute("valor",valor);
           request.getRequestDispatcher("ListaTodos.jsp").forward(request,response);
    }
    
    
    
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            RequestDispatcher dispatcher;
            DAOFactory daoFactory = new DAOFactory();
            
            CotacaoDAO dao = daoFactory.getCotacaoDAO();
            
            
            switch (request.getServletPath()) {
                
                case "/Criar":
                    dispatcher = request.getRequestDispatcher("/Criar.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "/Deletar":
                    dao.delete(request.getParameter("empresaNome"),request.getParameter("dataNome"));
                    dispatcher = request.getRequestDispatcher("/index.html");
                    dispatcher.forward(request, response);
                    
                    break;
                case "/Alterar":
                    dao.read(request.getParameter("empresaNome"),request.getParameter("dataNome"));
                    nomeEmpresaGlobal = request.getParameter("empresaNome");
                    dataGlobal = request.getParameter("dataNome");
                    request.getRequestDispatcher("AlterarDados.jsp").forward(request,response);
                    break;
                    
//            case "/player/update":
//                try (DAOFactory daoFactory = new DAOFactory()) {
//                    dao = daoFactory.getPlayerDAO();
//
//                    Player player = dao.read(Integer.parseInt(request.getParameter("id")));
//
//                    request.setAttribute("player", player);
//
//                } catch (ClassNotFoundException | IOException | SQLException ex) {
//                    request.getSession().setAttribute("error", ex.getMessage());
//                }
//
//                dispatcher = request.getRequestDispatcher("/playerUpdate.jsp");
//                dispatcher.forward(request, response);
//                break;
//
//            case "/player/delete":
//                try (DAOFactory daoFactory = new DAOFactory()) {
//                    dao = daoFactory.getPlayerDAO();
//
//                    dao.delete(Integer.parseInt(request.getParameter("id")));
//
//                } catch (ClassNotFoundException | IOException | SQLException ex) {
//                    request.getSession().setAttribute("error", ex.getMessage());
//                }
//                response.sendRedirect(request.getContextPath() + "/player");
//                break;
//
//
//            case "/player":
//                try (DAOFactory daoFactory = new DAOFactory()) {
//                    dao = daoFactory.getPlayerDAO();
//
//                    List<Player> list = dao.all();
//
//                    if (list != null) {
//                        request.setAttribute("playerList", list);
//                    }
//
//                    dispatcher = request.getRequestDispatcher("/playerList.jsp");
//                    dispatcher.forward(request, response);
//
//                } catch (ClassNotFoundException | IOException | SQLException ex) {
//                    PrintWriter out = response.getWriter();
//                    out.println(ex.getMessage());
//                    request.getSession().setAttribute("error", ex.getMessage());
//                }
//
//
//                break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CotacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CotacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
}

 
}
