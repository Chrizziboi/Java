package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode extends Produksjon implements Comparable<Episode>  {
    private int episodeNummer, sesongNummer;

    public Episode() {};

    public Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer) {
        super(tittel, spilletid, beskrivelse, utgivelsesdato, regissor, bildeurl);
        this.sesongNummer = sesongNummer;
        this.episodeNummer = episodeNummer;

    }
    public Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeurl, int sesongNummer, int episodeNummer) {
        super(tittel, spilletid, beskrivelse, utgivelsesdato, bildeurl);
        this.sesongNummer = sesongNummer;
        this.episodeNummer = episodeNummer;

    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Episoden har tittel: " + getTittel() + " er episode:  " + episodeNummer + " tilhører sesong: "
                + sesongNummer + " og har en spilletid på:  " + getSpilletid() + " Minutter.";
    }

    @Override
    public int compareTo(@NotNull Episode annenEpisode) {
        return this.episodeNummer - annenEpisode.getEpisodeNummer();
    }

    public int getEpisodeNummer() {
        return episodeNummer;
    }

    public void setEpisodeNummer(int episodeNummer) {
        this.episodeNummer = episodeNummer;
    }

    public int getSesongNummer() {
        return sesongNummer;
    }

    public void setSesongNummer(int sesongNummer) {
        this.sesongNummer = sesongNummer;
    }
}