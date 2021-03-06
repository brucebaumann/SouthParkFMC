/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.episode;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.servlets.EpisodeController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class RemoveTag implements Action{
    DaoManager daoManager;
    int episodeId;
    int tagId;

    public RemoveTag(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        gatherParameters(request);
        EpisodeDao dao = daoManager.getEpisodeDao();
        boolean result = dao.removeTag(episodeId, tagId);

        response.setContentType("application/json");
        String json;
        if(result){
            //Pas d'erreur
            json = "{\"result\":true,\"tagId\":" + tagId + "}";
        }else{
            json = "{\"result\":false,\"tagId\":" + tagId + "}";
        }
        try {
            PrintWriter out = response.getWriter();
            out.print(json);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "#";
    }

    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
        this.tagId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_TAG_ID));
    }
}
