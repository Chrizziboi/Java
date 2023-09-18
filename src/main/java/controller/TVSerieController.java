package controller;
import io.javalin.http.Context;
import model.TVSerie;
import repository.*;

import java.util.ArrayList;

public class TVSerieController {
    private TVSerieRepository TVSerieRepository;

    public TVSerieController(TVSerieRepository TVSerieRepository) {
        this.TVSerieRepository = TVSerieRepository;
    }

    public void getAlleTVSerier(Context ctx) {
       // ArrayList<TVSerie> AlleTVSerier = TVSerieRepository.getAlleTVSerier();
        ctx.json(TVSerieRepository.getAlleTVSerier());
    }

    public void getTVSerie(Context context) {
        String TVSerieId = context.pathParam("tvserie-id");
        //TVSerie TVSerien = TVSerieRepository.getTVSerie(TVSerieNavn);
        context.json(TVSerieRepository.getTVSerie(TVSerieId));
    }


}
