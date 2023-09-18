package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class TVSerie implements Comparable<TVSerie> {
    private String tittel, beskrivelse, bildeUrl;
    private ArrayList<Episode> episoder;
    private LocalDate utgivelsesdato;

    private float gjennomsnittligSpilletid;

    private int antallSesonger;

    public TVSerie() {};

    public TVSerie(String tittel, String beskrivelse, LocalDate utgivelsesdato){
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        episoder = new ArrayList<>();
        gjennomsnittligSpilletid = 0;
        antallSesonger = 0;
    }
    public TVSerie(String tittel, String beskrivelse, LocalDate utgivelsesdato, String bildeurl) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.bildeUrl = bildeurl;
        episoder = new ArrayList<>();
        gjennomsnittligSpilletid = 0;
        antallSesonger = 0;
    }

    //0lic void episoder = new ArrayList<>;

    public void leggTilEpisode(Episode episoden) {
        /*
        ArrayList<Episode> serieListe = new ArrayList<Episode>();
        episoder.add(episoden);
        oppdaterGjennomsnittligSpilletid();
        */
        if (episoden.getSesongNummer() <= antallSesonger +1) {
            episoder.add(episoden);
            if (episoden.getSesongNummer() == antallSesonger +1) {
                antallSesonger = episoden.getSesongNummer();
            }
        }
        else {
            System.out.println("Feil! Registrerte sesonger er: " + antallSesonger +
                    " så kan ikke legge til nye episoder i sesong: " + episoden.getSesongNummer());
        }
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "TV Serien har tittel: " + tittel + " Har bekrivelsen:  " + beskrivelse + "\n"
                + "TV Serien ble lansert i år: " + utgivelsesdato;
    }

    public String toStringAll() {
        return "Hele Serien printet ut: " + tittel + " " + episoder;
    }

    private void oppdaterGjennomsnittligSpilletid() {
        int totspilletid = 0;

        for (Episode i : episoder) {
            totspilletid += i.getSpilletid();
        }
        gjennomsnittligSpilletid = (float) totspilletid / episoder.size();
    }
    public Episode getEpisode(int sesongNummer, int episodeNummer) {
        for (Episode episode : episoder) {
            if (episode.getSesongNummer() == sesongNummer && episode.getEpisodeNummer() == episodeNummer)
                return episode;
        }

        return null;

    }

    public ArrayList<Episode> hentEpisoderISesong(int sesongNummer) {
        ArrayList<Episode> printSerieListe = new ArrayList<>();
        for (Episode i : episoder) {
            if (i.getSesongNummer() == sesongNummer) {
                printSerieListe.add(i);
            }
        }
        return printSerieListe;
    }

    public ArrayList<Rolle> hentRollebesetning() {
        // Lager en ny rolle som vi skal fylle opp med alle roller som spilles i episodene
        ArrayList<Rolle> rollebesetning = new ArrayList<>();

        // Går gjennom hver enkelt episode i episodelisten
        for (Episode enEpisode : episoder) {
            // Går gjennom hver enkelt rolle som er listet opp i en episode
            for (Rolle enRolle : enEpisode.getRoller()) {
                // Undersøker om vi har lagt til akkurat denne rollen i listen før
                if (!rollebesetning.contains(enRolle))
                    // Hvis vi ikke har det, legger vi den til i lista
                    rollebesetning.add(enRolle);
            }
        }

        return rollebesetning;
    }


    @Override
    public int compareTo(TVSerie t) {
        return getTittel().toUpperCase().compareTo(t.getTittel().toUpperCase());
    }
    //@JsonProperty("TVSerie_Tittel")
    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public LocalDate getUtgivelsesdato() {
        return utgivelsesdato;
    }

    public void setUtgivelsesdato(LocalDate utgivelsesdato) {
        this.utgivelsesdato = utgivelsesdato;
    }

    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    public ArrayList<Episode> getEpisoder() {
        return episoder;
    }

    public void setEpisoder(ArrayList<Episode> episoder) {
        this.episoder = episoder;
    }

    @JsonIgnore
    public float getGjennomsnittligSpilletid() {
        return gjennomsnittligSpilletid;
    }


    public void setGjennomsnittligSpilletid(float gjennomsnittligSpilletid) {
        this.gjennomsnittligSpilletid = gjennomsnittligSpilletid;
    }

    public int getAntallSesonger() {
        return antallSesonger;
    }

    public void setAntallSesonger(int antallSesonger) {
        this.antallSesonger = antallSesonger;
    }

    public void updateEpisode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {
        Episode e = new Episode(tittel, spilletid, beskrivelse, utgivelsesdato, bildeUrl, sesongNummer, episodeNummer);

        int i;
        for (i = 0; i<episoder.size();  i++) {
            e = episoder.get(i);
            if(e.getSesongNummer() == sesongNummer && e.getEpisodeNummer() == episodeNummer) {
                episoder.set(i, e);
            }
        }
    }

    public void deleteEpisode(int sesongNummer, int episodeNummer) {
        int i;

        for (i = 0; i < episoder.size(); i++) {

            Episode x = episoder.get(i);

            if(x.getSesongNummer() == sesongNummer && x.getEpisodeNummer() == episodeNummer) {
                episoder.remove(i);
            }
        }
    }

    public void createEpisode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeUrl, int sesongNummer, int episodeNummer) {
        Episode x = new Episode(tittel, spilletid, beskrivelse, utgivelsesdato, regissor, bildeUrl, sesongNummer, episodeNummer);
        episoder.add(x);
    }

    //TVSerie(String tittel, String beskrivelse, String utgivelsesdato, String bildeurl)
    //Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer

    //public void createEpisode(String tittel, )
    /*
    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getUtgivelsesdato() {
        return utgivelsesdato;
    }

    public void setUtgivelsesdato(String utgivelsesdato) {
        this.utgivelsesdato = utgivelsesdato;
    }

    public ArrayList<Episode> getEpisoder() {
        return episoder;
    }

    public void setEpisoder(ArrayList<Episode> episoder) {
        this.episoder = episoder;
    }

    public float getGjennomsnittligSpilletid() {
        return gjennomsnittligSpilletid;
    }

    public int getAntallSesonger() {
        return antallSesonger;
    }


    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    public void setAntallSesonger(int antallSesonger) {
        this.antallSesonger = antallSesonger;
    }
*/
}