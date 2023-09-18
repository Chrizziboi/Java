package repository;

import model.Episode;
import model.TVSerie;

import java.time.LocalDate;
import java.util.ArrayList;

public interface TVSerieRepository {

    ArrayList<TVSerie> getAlleTVSerier();

    TVSerie getTVSerie(String TVSerieId);

    ArrayList<Episode> getAlleEpisoder(String TVSerieTittel, int sesongNummer);

    Episode getEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer);

    public void createEpisode(String TVSerieTittel, String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer);

    public void updateEpisode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer);

    public void deleteEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer);

    /*

    //TVSerieJSONRepository
/*
    public void lagEpisode(String);
    public void oppdaterEpisode(String);
    public void SlettEpisode(String);

*/

}
