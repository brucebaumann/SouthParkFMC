/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.servlets;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.actions.ActionFactory;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.DataSourceSupplier;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
@WebServlet(name = "EpisodeController", urlPatterns = {"/episode/*"})
public class EpisodeController extends HttpServlet {
    public static final String PARAM_EPISODEID = "episodeId";
    public static final String PARAM_PRODUCTION_CODE = "productionCode";
    public static final String PARAM_SEASON_ID = "seasonId";
    public static final String PARAM_NAME_VO = "nameVO";
    public static final String PARAM_NAME_VF = "nameVF";
    public static final String PARAM_PLOT = "plot";
    public static final String PARAM_INDEX_IN_SEASON= "indexInSeason";
    public static final String PARAM_ERROR= "error";
    public static final String PARAM_SELECTED_EPISODE= "episode";
    public static final String PARAM_CHARACTERID= "characterId";
    public static final String PARAM_QUOTE_TEXT= "quoteText";
    public static final String PARAM_QUOTE_NOTE= "quoteNote";
    public static final String PARAM_QUOTE_ID= "quoteId";
    public static final String PARAM_TAG_ID= "tagId";
    public static final String PARAM_ROLE_ID= "roleId";
    public static final String PARAM_NOTE= "note";

    private static Logger LOGGER = LoggerFactory.getLogger(EpisodeController.class);


    ActionFactory actionFactory = new ActionFactory(new DaoManager(DataSourceSupplier.getDataSource()));

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Requete reçue : " + request.getMethod()+request.getServletPath()+request.getPathInfo());
        Action action = actionFactory.getAction(request);
        
        String view = action.execute(request, response);

        if(view.endsWith(".jsp")){
            request.getRequestDispatcher("/" + view).forward(request, response);
        }else if(!view.equals("#")){
            response.sendRedirect(view);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
