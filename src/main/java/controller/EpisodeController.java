package controller;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import model.Episode;
import model.Person;
import model.Produksjon;
import model.TVSerie;
import repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.logging.Level.parse;

public class EpisodeController {
    private TVSerieRepository TVSerieRepository;

    public EpisodeController(TVSerieRepository TVSerieRepository) {
        this.TVSerieRepository = TVSerieRepository;
    }

    public void getAlleEpisoder(Context ctx) {
        String TVSerieTittel = ctx.pathParam("tvserie-id");
        String sesong = ctx.pathParam("sesong-nr");
        String sortering = ctx.queryParam("sortering");
        int sesongNummer = sesong.isEmpty() ? 1 : Integer.parseInt(sesong);

        //ctx.json(TVSerieRepository.getAlleEpisoder(TVSerieTittel, sesongNummer));

        ArrayList<Episode> AlleEpisoder = TVSerieRepository.getAlleEpisoder(TVSerieTittel, sesongNummer);

        if (sortering != null) {
            switch (sortering) {
                case "episodeNummer" -> Collections.sort(AlleEpisoder);
                case "tittel" -> AlleEpisoder.sort((e1, e2) -> e1.getTittel().compareTo(e2.getTittel()));
                case "spilletid" -> AlleEpisoder.sort(Comparator.comparingInt(Produksjon::getSpilletid));

            }
        }

        ctx.json(AlleEpisoder);

    }

    public void getEpisode(Context ctx) {
        String TVSerieTittel = ctx.pathParam("tvserie-id");
        String sesongNummer = ctx.pathParam("sesong-nr");
        String episodeNummer = ctx.pathParam("episode-nr");

        ctx.json(TVSerieRepository.getEpisode(TVSerieTittel, Integer.parseInt(sesongNummer), Integer.parseInt(episodeNummer)));
    }

    public  void updateEpisodeController(Context ctx) {
        String TVSerieTittel = ctx.pathParam("tvserie-id");
        String sesongNr = ctx.pathParam("sesong-nr");
        String episodeNr = ctx.pathParam("episode-nr");

        String tittel = ctx.formParam("tittel");
        int spilletid = Integer.parseInt(ctx.formParam("spilletid"));
        String beskrivelse = ctx.formParam("beskrivelse");
        LocalDate utgivelsesdato = LocalDate.parse(ctx.formParam("utgivelsesdato"));
        String bildeUrl = ctx.formParam("bildeUrl");
        int sesongNummer = Integer.parseInt(ctx.formParam("sesongNummer"));
        int episodeNummer = Integer.parseInt(ctx.formParam("episodeNummer"));

        //TVSerieRepository.updateEpisode(tittel, spilletid, beskrivelse, utgivelsesdato, regissor, bildeUrl, sesongNummer, episodeNummer);
        TVSerieRepository.updateEpisode(tittel, spilletid, beskrivelse, utgivelsesdato, bildeUrl, sesongNummer, episodeNummer);
        //TVSerieRepository.updateEpisode();
        ctx.redirect("/tvserie/" + tittel + "/sesong/" + sesongNr + "/episode/" + episodeNr);
    }

    public void createEpisodeController(Context ctx) {
        String TVSerieTittel = ctx.pathParam("tvserie-id");

        String tittel = ctx.formParam("tittel");
        int spilletid = Integer.parseInt(ctx.formParam("spilletid"));
        String beskrivelse = ctx.formParam("beskrivelse");
        LocalDate utgivelsesdato = LocalDate.parse(ctx.formParam("utgivelsesdato"));
        //Person regissor = Person.class(ctx.formParam("regissor"));
        String bildeUrl = ctx.formParam("bildeUrl");
        int sesongNummer = Integer.parseInt(ctx.formParam("sesongNummer"));
        int episodeNummer = Integer.parseInt(ctx.formParam("episodeNummer"));

        TVSerieRepository.createEpisode(TVSerieTittel, tittel, beskrivelse, spilletid,  utgivelsesdato, bildeUrl, sesongNummer, episodeNummer);

        ctx.redirect("/tvserie/" +"/sesong/" + sesongNummer );
    }

    public void deleteEpisodeController(Context ctx) {
        String TVSerieTittel = ctx.pathParam("tvserie-id");
        int sesongNummer = Integer.parseInt(ctx.formParam("sesongNummer"));
        int episodeNummer = Integer.parseInt(ctx.formParam("episodeNummer"));

        TVSerieRepository.deleteEpisode(TVSerieTittel, sesongNummer, episodeNummer);
        ctx.redirect("/tvserie" + TVSerieTittel + "/sesong/" + sesongNummer);

    }
    //TVSerie(String tittel, String beskrivelse, String utgivelsesdato, String bildeurl)
    //Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer
}
