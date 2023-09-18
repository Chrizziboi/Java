package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Episode;
import model.Person;
import model.TVSerie;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TVSerieJSONRepository  implements TVSerieRepository {
    private ArrayList<TVSerie> TVSerieJSONListe;
    public TVSerieJSONRepository(String fil) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        //File x = new File(fil);
        try {
            TVSerie[] TVSerierfraJSON = mapper.readValue(new File(fil), TVSerie[].class);

            TVSerieJSONListe = new ArrayList<>(Arrays.asList(TVSerierfraJSON));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

/*
        ArrayList<TVSerie> TVSerierFraFil = skrivTilJSON(fil);
        System.out.println(TVSerierFraFil);

        ArrayList<TVSerie> TVSerierFraFil = lesFraJSON(fil);
        System.out.println(TVSerierFraFil);
*/
    }

        //skrivTilJSON("TVSerieJSONListe.json", getAlleTVSerier());
        //ArrayList<TVSerie> TVSerieArrayList = new ArrayList<>();
        //for (TVSerierJSON x : episoder)
        //lesFraJSON("tvshows_10.json");

        //File kilde = new File("TVSerieJSONListe.json");

        //skrivTilCSV("TVSerierJSON", kilde);

        //ArrayList<TVSerie> TVSerierFraJSON = lesFraJSON(TVSerierJSON);

    public static void skrivTilJSON(ArrayList<TVSerie> TVSerier, String fil) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fil), TVSerier);
    }

    public static  List<TVSerie> lesFraJSON(String fil) {
        List<TVSerie> TVSerieListe = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            TVSerie[] TVSerieArray = mapper.readValue(new File(fil), TVSerie[].class);

            TVSerieListe = Arrays.asList(TVSerieArray);


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return TVSerieListe;
    }
/*
    public static void skrivTilJSON(String filnavn,  List<TVSerie> TVSerieArrayList) {
        try {
            File fil = new File(filnavn);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());


            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fil, filnavn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<TVSerie> lesFraJSON(String filnavn) {
        List<TVSerie> TVSerier = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            TVSerie[] TVSerieArray = objectMapper.readValue(new File(filnavn), TVSerie[].class);

            TVSerier = Arrays.asList(TVSerieArray);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return TVSerier;
    }
*/
    //public Static


   @Override
   public ArrayList<TVSerie> getAlleTVSerier() {
       return TVSerieJSONListe;
   }

    @Override
    public TVSerie getTVSerie(String TVSerieTittel) {
        for (TVSerie x : TVSerieJSONListe) {
            if (x.getTittel().equals(TVSerieTittel))
                return x;
        }
        return null;
    }

    @Override
    public ArrayList<Episode> getAlleEpisoder(String TVSerieTittel, int sesongNummer) {
        return getTVSerie(TVSerieTittel).hentEpisoderISesong(sesongNummer);
    }

    @Override
    public Episode getEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer) {
        return getTVSerie(TVSerieTittel).getEpisode(sesongNummer, episodeNummer);
    }

    //TVSerie(String tittel, String beskrivelse, String utgivelsesdato, String bildeurl)
    //Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer

    @Override
    public void createEpisode(String TVSerieTittel, String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {}
    @Override
    public void updateEpisode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {}
    @Override
    public void deleteEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer) {}


}
