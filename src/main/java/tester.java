import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.config.ContextResolverConfig;
import model.Person;
import model.TVSerie;
import repository.TVSerieDataRepository;

import javax.naming.Context;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class tester {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        Person Hirst = new Person(LocalDate.of(2022,1,1), "Michael", "Hirst");

        TVSerie Vikings = new TVSerie("Vikings", "Vikings transports us to the brutal and mysterious world of Ragnar Lothbrok, a Viking warrior and farmer who yearns to explore--and raid--the distant shores across the ocean.",
                LocalDate.of(2013,3,2), "https://www.emp-shop.no/dw/image/v2/BBQV_PRD/on/demandware.static/-/Sites-master-emp/default/dwecdb9636/images/3/1/8/0/318007.jpg?sfrm=png");

        TVSerieDataRepository TVSerieRepository = new TVSerieDataRepository();

        try {

            String JsonText = objectMapper.writeValueAsString(Vikings);
            System.out.println(JsonText);

            TVSerie Kopi = objectMapper.readValue(JsonText, TVSerie.class);
            System.out.println();

            ArrayList<TVSerie> TVSerier = new ArrayList<>();



            //DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            TVSerier.add(Vikings);
            TVSerier.addAll(TVSerieRepository.getAlleTVSerier());

            skrivTilJSON(TVSerier, "TVSerier.json");


            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("VikingsJSON"), Vikings);

            ArrayList<TVSerie> TVSerierFraFil = lesFraJSON("TVSerier.json");

            System.out.println(TVSerierFraFil);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    //skrivTilJSON("TVSerieJSONListe.json", getAlleTVSerier());
    //ArrayList<TVSerie> TVSerieArrayList = new ArrayList<>();
    //for (TVSerierJSON x : episoder)
    //lesFraJSON("tvshows_10.json");

    //File kilde = new File("TVSerieJSONListe.json");

    //skrivTilCSV("TVSerierJSON", kilde);

    //ArrayList<TVSerie> TVSerierFraJSON = lesFraJSON(TVSerierJSON);


    private static void skrivTilJSON(ArrayList<TVSerie> TVSerier, String fil) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fil), TVSerier);

    }

    private static ArrayList<TVSerie> lesFraJSON(String fil) {
        ArrayList<TVSerie> TVSerieListe = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            TVSerie[] TVSerieArray = objectMapper.readValue(new File(fil), TVSerie[].class);

            TVSerieListe.addAll(Arrays.asList(TVSerieArray));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return TVSerieListe;
    }
}

    //skrivTilJSON("TVSerieJSONListe.json", getAlleTVSerier());
    //ArrayList<TVSerie> TVSerieArrayList = new ArrayList<>();
    //for (TVSerierJSON x : episoder)
    //lesFraJSON("tvshows_10.json");

    //File kilde = new File("TVSerieJSONListe.json");

    //skrivTilCSV("TVSerierJSON", kilde);

    //ArrayList<TVSerie> TVSerierFraJSON = lesFraJSON(TVSerierJSON);








