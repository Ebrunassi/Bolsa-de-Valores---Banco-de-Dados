/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contoller;

import dao.DAO;
import dao.DAOFactory;
import dao.CotacaoDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Cotacao;


/**
 *
 * @author dskaster
 */
@MultipartConfig()
@WebServlet(name = "UsuarioController", urlPatterns = {"/usuario",
    "/usuario/create",
    "/usuario/update",
    "/usuario/delete",
    "/usuario/read"
})
public class CotacaoController extends HttpServlet {

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vÃ£o ser
     * salvos na pasta de build do servidor. Ao limpar pode-se perder estes
     * arquivos, faÃ§am backup antes de limpar.
     */
    private static final String SAVE_DIR = "uploads";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Cotacao> dao;
        Cotacao usuario;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getCotacaoDAO();

                    List<Cotacao> usuarioList = dao.all();
                    request.setAttribute("usuarioList", usuarioList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                break;

            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);

                break;

            case "/usuario/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getCotacaoDAO();

                    usuario = dao.read(request.getParameter("data"),request.getParameter("empresa"));
                    request.setAttribute("usuario", usuario);

                    dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;

            case "/usuario/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getCotacaoDAO();
                    dao.delete(request.getParameter("data"),request.getParameter("empresa"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;

            case "/usuario/read":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getCotacaoDAO();

                    usuario = dao.read(request.getParameter("data"),request.getParameter("empresa"));
                    
                    

                    
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CotacaoDAO dao;
        Cotacao usuario = new Cotacao();
        HttpSession session = request.getSession();
        String nascimento;

        switch (request.getServletPath()) {
            case "/usuario/create": {
                usuario.setMinima(Integer.parseInt(request.getParameter("minima")));
                usuario.setCotacaoValor(Integer.parseInt(request.getParameter("cotacao")));
                usuario.setData(request.getParameter("data"));
                usuario.setVolume(Integer.parseInt(request.getParameter("volume")));
                usuario.setEmpresa(request.getParameter("empresa"));
                usuario.setVariacao(Integer.parseInt(request.getParameter("variacao")));
                usuario.setMaxima(Integer.parseInt(request.getParameter("maxima")));
               

                try (DAOFactory daoFactory = new DAOFactory()) {
//                    System.err.println(nascimento);
//                    java.util.Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
//                    usuario.setNascimento(new Date(dataNascimento.getTime()));

                    dao = daoFactory.getCotacaoDAO();

                    dao.create(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario");
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario/create");
//                } catch (ParseException ex) {s
//                    session.setAttribute("error", "O formato de data aceito Ã© dd/mm/aaaa. Por favor, tente novamente.");
//                    response.sendRedirect(request.getContextPath() + "/usuario/create");
                }

                break;
            }

            case "/usuario/update": {
                usuario.setMinima(Integer.parseInt(request.getParameter("minima")));
                usuario.setCotacaoValor(Integer.parseInt(request.getParameter("cotacao")));
                usuario.setData(request.getParameter("data"));
                usuario.setVolume(Integer.parseInt(request.getParameter("volume")));
                usuario.setEmpresa(request.getParameter("empresa"));
                usuario.setVariacao(Integer.parseInt(request.getParameter("variacao")));
                usuario.setMaxima(Integer.parseInt(request.getParameter("maxima")));

                break;
            }
            case "/usuario/delete": {
                String[] usuarios = request.getParameterValues("delete");

                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getCotacaoDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String usuarioId : usuarios) {
                            dao.delete(request.getParameter("data"),request.getParameter("empresa"));
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            }

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
