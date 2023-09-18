import controller.EpisodeController;
import controller.TVSerieController;

import repository.*;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.vue.VueComponent;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Javalin appV = Javalin.create(config -> {
            config.staticFiles.enableWebjars();
            config.vue.vueAppName = "app";
        }).start(1325);

        appV.before("/", ctx -> ctx.redirect("/tvserie"));

        appV.get("/", new VueComponent("hello-world"));

        //funker, sjekket
        appV.get("/tvserie", new VueComponent("tvserie-overview"));
        appV.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/", new VueComponent("tvserie-detail"));
        appV.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new VueComponent("episode-detail"));
        appV.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/updateepisode",new VueComponent("episode-update"));
        appV.get("/tvserie/{tvserie-id}/createepisode", new VueComponent("episode-create"));



        TVSerieDataRepository TVSerieRepository = new TVSerieDataRepository();
        TVSerieController TVSerieController = new TVSerieController(TVSerieRepository);
        EpisodeController EpisodeController = new EpisodeController(TVSerieRepository);


        TVSerieJSONRepository TVSerieJSONRepository = new TVSerieJSONRepository("tvshows_10.json");
        //TVSerieCSVRepository TVSerieCSVRepository = new TVSerieCSVRepository("tvshows_10.csv", ";"); //"tvshows_10.csv", ";"



        //sjekket stemmer
        appV.get("/api/tvserie", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {
                TVSerieController.getAlleTVSerier(ctx);
            }
        });


        appV.get("/api/tvserie/{tvserie-id}", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {
                TVSerieController.getTVSerie(ctx);
            }
        });


        appV.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {
                EpisodeController.getAlleEpisoder(ctx);
            }
        });

        appV.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {
                EpisodeController.getEpisode(ctx);
            }
        });

        appV.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/deleteepisode", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {

                EpisodeController.deleteEpisodeController(ctx);
            }
        });

        appV.post("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/updateepisode", new Handler() {
            @Override
            public void handle (Context ctx) throws  Exception{
                EpisodeController.updateEpisodeController(ctx);
            }
        });

        appV.post("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/createepisode", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {
                EpisodeController.createEpisodeController(ctx);
            }
        });








        //TVSerieJSONRepository TVSerieJSONRepository = new TVSerieJSONRepository("TVSerier.json");
        //TVSerieJSONRepository.lesFraJSON();


        /*
        String lestTekst = "";
        System.out.println("Skriv tekst ('avslutt' for å avslutte)");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileWriter filskriver = new FileWriter("testskriving.json", true);

            while (!lestTekst.equals("avslutt")) {
                System.out.println(": ");

                lestTekst = bufferedReader.readLine();

                filskriver.write(lestTekst);
            }
            filskriver.close();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
*/

        //TVSerieJSONRepository TVSerieNEWRepository = new TVSerieJSONRepository();
        //TVSerieNEWRepository.getAlleTVSerier();




        /*appV.get("api/tvserie/{tvserie-id}", context -> TVSerieController.getTVSerie(context));

        appV.get("api/tvserie/{tvserie-id}/sesong/{sesong-nr}", EpisodeController::getAlleEpisoder);

        appV.get("api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", EpisodeController::getEpisode);*/




        // System.out.println(TVSerieRepository);
//

    }
}
