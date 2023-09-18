package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TVSerieDataRepository implements TVSerieRepository {
    private ArrayList<TVSerie> TVSerier = new ArrayList<>();
    //ObjectMapper mapper = new ObjectMapper();

    public TVSerieDataRepository() {

        TVSerie TheWalkingDead = new TVSerie("The Walking Dead",
                "En post-apokalyptisk overlevelses-fortelling", LocalDate.of(2010, 10, 31)
                ,"https://yt3.googleusercontent.com/GY7LAmxlbRvYD7Ol-LXSMrFIUkc-cBkUbR8WCjo-5-86xbHIlP7iLsdMW5e8rHJHrMN94FTFOA=s900-c-k-c0x00ffffff-no-rj");

        Person FrankDarabont = new Person(LocalDate.of(2022,1,1), "Frank", "Darabont");

        Episode DaysGoneBye = new Episode("Days Gone Bye", 67, "Deputy Sheriff Rick Grimes awakens from a coma, and searches for his family in a world ravaged by the undead.", LocalDate.of(2010, 10, 31) , FrankDarabont,"https://m.media-amazon.com/images/M/MV5BMTYwMzIwODM3NF5BMl5BanBnXkFtZTcwNjE3MDQwNA@@._V1_.jpg", 1, 1);
        Episode Guts = new Episode("Guts", 45, "In Atlanta, Rick is rescued by a group of survivors, but they soon find themselves trapped inside a department store surrounded by walkers.", LocalDate.of(2010, 11, 7), FrankDarabont, "https://m.media-amazon.com/images/M/MV5BMTI4OTk4MzQzMV5BMl5BanBnXkFtZTcwOTk2MDQwNA@@._V1_.jpg", 1, 2);
        Episode Tell = new Episode("Tell", 44, "Rick is reunited with Lori and Carl but soon decides - along with some of the other survivors - to return to the rooftop and rescue Merle. Meanwhile, tensions run high between the other survivors at the camp.", LocalDate.of(2010,11,14), FrankDarabont, "https://m.media-amazon.com/images/M/MV5BMjE3NDIxMjgzMF5BMl5BanBnXkFtZTcwNzE1NzkwNA@@._V1_FMjpg_UX1000_.jpg", 1, 3);
        Episode Vatos = new Episode("Vatos", 45, "Rick, Glenn, Daryl and T-Dog come across a group of seemingly hostile survivors whilst searching for Merle. Back at camp, Jim begins behaving erratically.",LocalDate.of(2010, 11, 21), FrankDarabont, "https://m.media-amazon.com/images/M/MV5BMTUxMTQ5ODk4OF5BMl5BanBnXkFtZTcwOTE5MjIxNA@@._V1_.jpg", 1, 4);

        TheWalkingDead.leggTilEpisode(DaysGoneBye);
        TheWalkingDead.leggTilEpisode(Guts);
        TheWalkingDead.leggTilEpisode(Tell);
        TheWalkingDead.leggTilEpisode(Vatos);

        TVSerie LOST = new TVSerie("LOST", "The survivors of a plane crash are forced to work together in " +
                "order to survive on a seemingly deserted tropical island.",LocalDate.of(2004, 2, 2), "https://ntvb.tmsimg.com/assets/p185013_b_h10_af.jpg?w=960&h=540");

        Person JJAbrams = new Person(LocalDate.of(2022,1,1), "Jeffrey", "Abrams");

        Episode Pilot1 = new Episode("Pilot: Part 1", 42, "Forty-eight survivors of an airline flight originating from Australia, bound for the U.S., which crash-lands onto an unknown island 1000 miles off course, struggle to figure out a way to survive while trying to find a way to be rescued.", LocalDate.of(2004, 2, 2), JJAbrams, "https://m.media-amazon.com/images/M/MV5BNDY5MDY4NDEtMTFhOS00NmM5LWE4MDAtMGEyMWFiYWMyNDY4XkEyXkFqcGdeQXVyNTY1MDAxMzc@._V1_.jpg", 1,1);
        Episode Pilot2 = new Episode("Pilot: Part 2", 40, "Having retrieved the plane tranceiver, a group of survivors try to transmit a signal, only to receive a strange transmission from another inhabitant.", LocalDate.of(2004, 10, 29), JJAbrams,"https://m.media-amazon.com/images/M/MV5BZmJhNjY2MTMtNDhkNy00MGZiLWFmOGEtMGIyYWVhOWUzOGUxXkEyXkFqcGdeQXVyNTY1MDAxMzc@._V1_.jpg",1, 2);
        Episode Tabula = new Episode("Tabula Rasa", 43, "Jack and Hurley discover an alarming secret about Kate, while the marshal's life hangs in the balance.", LocalDate.of(2004, 10, 6), JJAbrams, "https://m.media-amazon.com/images/M/MV5BMTY2NTU2MTMyN15BMl5BanBnXkFtZTgwMTgzNDY2MjE@._V1_.jpg", 1, 3);

        Person Hirst = new Person(LocalDate.of(2022,1,1), "Michael", "Hirst");

        TVSerie Vikings = new TVSerie("Vikings", "Vikings transports us to the brutal and mysterious world of Ragnar Lothbrok, a Viking warrior and farmer who yearns to explore--and raid--the distant shores across the ocean.",
                LocalDate.of(2013, 3, 3), "https://www.emp-shop.no/dw/image/v2/BBQV_PRD/on/demandware.static/-/Sites-master-emp/default/dwecdb9636/images/3/1/8/0/318007.jpg?sfrm=png");

        LOST.leggTilEpisode(Pilot1);
        LOST.leggTilEpisode(Pilot2);
        LOST.leggTilEpisode(Tabula);

       TVSerier.add(TheWalkingDead);
       TVSerier.add(LOST);

    }

    @Override
    public ArrayList<TVSerie> getAlleTVSerier() {
        return TVSerier;

    }

    @Override
    public TVSerie getTVSerie(String TVSerieTittel) {
        for (TVSerie x : TVSerier) {
            if (x.getTittel().equals(TVSerieTittel))
                return x;
        }
        return null;
    }

    @Override
    public ArrayList<Episode> getAlleEpisoder(String TVSerieTittel, int SesongNummer) {
        return getTVSerie(TVSerieTittel).hentEpisoderISesong(SesongNummer);
    }

    @Override
    public Episode getEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer) {
        return getTVSerie(TVSerieTittel).getEpisode(sesongNummer, episodeNummer);
    }

    @Override
    public void createEpisode(String TVSerieTittel, String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {};
    @Override
    public void updateEpisode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {};
    @Override
    public void deleteEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer) {};
    //TVSerie(String tittel, String beskrivelse, String utgivelsesdato, String bildeurl)
    //Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer


/*
    @Override
    public ArrayList<TVSerie> getAlleTVSerier() {
        ArrayList<TVSerie> TVSerieListe = new ArrayList<TVSerie>();
        for (TVSerie x : TVSerier) {
            TVSerieListe.add(x);
        }
        return TVSerieListe;
    }

    getTVSerie

    @Override
    public TVSerie getTVSerie(TVSerie TVSerieNavn) {
        ArrayList<TVSerie> enkelTVSerie = new ArrayList<TVSerie>();
        for (TVSerie x : TVSerier) {
            if (TVSerie.contains(TVSerieNavn))
                enkelTVSerie.add(TVSerieNavn);


        }
        return enkelTVSerie;
    }


    @Override
    public TVSerie getTVSerie(String TVSerieNavn) {
        return null;
    }

     */

}