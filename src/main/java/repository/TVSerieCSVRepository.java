package repository;

import model.Episode;
import model.TVSerie;
import model.Person;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TVSerieCSVRepository implements TVSerieRepository{
    private ArrayList<TVSerie> TVSerieListe = new ArrayList<>();
    LinkedHashMap<String, TVSerie> HashTVSerier = new LinkedHashMap<>();
    //private HashMap<String, TVSerie> HashTVSerier = new HashMap<>();

    public TVSerieCSVRepository(String fil, String d) throws IOException {
        try(BufferedReader lesFraCSV = new BufferedReader(new FileReader(fil))) {
        //File x = new File(fil);

            String line;

            while((line = lesFraCSV.readLine()) !=null){
            String[] v = line.split(d);

            String TVTittel = v[0];
            String TVBeskrivelse = v[1];
            String TVUtgivelsesdato = v[2];
            String TVBildeUrl = v[3];

            String episodeTittel = v[4];
            String episodeBeskrivelse = v[5];
            String episodeNummer = v[6];
            String episodeSesong = v[7];
            String episodeTid = v[8];
            String episodeUtgivelsesdato = v[9];
            String episodeBildeUrl = v[10];

            String regissorFornavn = v[11];
            String regissorEtternavn = v[12];
            String regissorFodselsDato = v[13];

            TVSerie tvserie = HashTVSerier.get(episodeTittel);

            LocalDate TVUtgivelsesdatoLocaldate = LocalDate.parse(TVUtgivelsesdato);

            if (tvserie == null) {
                tvserie = new TVSerie(TVTittel, TVBeskrivelse, TVUtgivelsesdatoLocaldate, TVBildeUrl);
                HashTVSerier.put(TVTittel, tvserie);
            }


            int episodeNummerNum = (episodeNummer.isEmpty() ? 1 : Integer.parseInt(episodeNummer));
            int episodeSesongNum = (episodeSesong.isEmpty() ? 1 : Integer.parseInt(episodeNummer));
            int episodeSpilletidNum = (episodeTid.isEmpty() ? 0 : Integer.parseInt(episodeTid));

            LocalDate regissorFodselsDatodate = LocalDate.parse(regissorFodselsDato);
            LocalDate episodeUtgivelsesdatoLocaldate = LocalDate.parse(episodeUtgivelsesdato);


            Person regissor = new Person(regissorFodselsDatodate, regissorFornavn, regissorEtternavn);
            Episode episode = new Episode(episodeTittel, episodeSpilletidNum, episodeBeskrivelse, episodeUtgivelsesdatoLocaldate, regissor, episodeBildeUrl, episodeSesongNum, episodeNummerNum);
            episode.setRegissor(regissor);
            tvserie.leggTilEpisode(episode);
        }

            TVSerieListe = new ArrayList<>(HashTVSerier.values());

            //dataFraCSV(fil);
            skrivTilCSV(TVSerieListe, "CSVListe.csv");

        } catch (IOException e) {
            e.printStackTrace();
    }
    }

    /*
    "tittel" : "The Walking Dead",
  "beskrivelse" : "En post-apokalyptisk overlevelses-fortelling",
  "utgivelsesdato" : "2010",
  "bildeUrl" : "https://yt3.googleusercontent.com/GY7LAmxlbRvYD7Ol-LXSMrFIUkc-cBkUbR8WCjo-5-86xbHIlP7iLsdMW5e8rHJHrMN94FTFOA=s900-c-k-c0x00ffffff-no-rj",
  "episoder"
    * */


    public void skrivTilCSV(ArrayList<TVSerie> CSVliste, String fil) {

        try (BufferedWriter skriver = new BufferedWriter(new FileWriter(fil))){

            //TVSerie(String tittel, String beskrivelse, String utgivelsesdato, String bildeurl)
            //Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer
            for (TVSerie t : CSVliste) {
                for (Episode e : t.getEpisoder()) {
                    skriver.write(t.getTittel() +";"+ t.getBeskrivelse() +";"+ t.getUtgivelsesdato() +";"+ t.getBildeUrl());
                    skriver.write(e.getTittel()+";"+e.getSpilletid()+";"+e.getBeskrivelse()+";"+e.getUtgivelsesdato()+";"+e.getRegissor()+";"+e.getBildeUrl()+";"+e.getSesongNummer()+";"+e.getEpisodeNummer());
                    skriver.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void lesFraCSV(File fil) {
        try {
            String r = new String(Files.readAllBytes(fil.toPath()));
            for (String l : r.split("/r/n")) {
                if (l.startsWith("#"))
                    continue;

                l.strip();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
        private static void skrivTilCSV(File fil) {
            //File fil = new File(HashTVSerier, fil);

            try { (BufferedWriter buffskriv = new BufferedWriter(new FileWriter(fil))) {
                for (Map.entry(Integer, TVSerie) x : HashTVSerier.entrySet()) {
                    TVSerie x = x.getTittel();
                    buffskriv.write(x.getTittel() + ";" + x.getBeskrivelse() + ";" + x.getUtgivelsesdato() + ";" + x.getBildeUrl() +";"+
                            x.getEpisoder());
                    buffskriv.newLine();

                }
            }

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Fil ikke funnet: " + fileNotFoundException.getMessage());
            }

            } catch (IOException ioException) {
                ioException.printStackTfcrace();

        }
    */
        @Override
        public ArrayList<TVSerie> getAlleTVSerier() {
            return new ArrayList<>(HashTVSerier.values());
        }

        @Override
        public TVSerie getTVSerie(String TVSerieId) {
            for (TVSerie x : TVSerieListe) {
                if (x.getTittel().equals(TVSerieId)) {
                    return x;
                }
            }
            return null;
        }
//TVSerie(String tittel, String beskrivelse, String utgivelsesdato, String bildeurl)
    //Episode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeurl, int sesongNummer, int episodeNummer

        public void updateEpisode(String TVSerieTittel, String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {
            TVSerie x = getTVSerie(TVSerieTittel);
            x.updateEpisode(tittel, spilletid, beskrivelse, utgivelsesdato, bildeUrl, sesongNummer, episodeNummer);

            skrivTilCSV(TVSerieListe, "tvshows_10.csv");
        }
        public void deleteEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer) {
            TVSerie x = getTVSerie(TVSerieTittel);

            x.deleteEpisode(sesongNummer, episodeNummer);

            skrivTilCSV(TVSerieListe, "tvshows_10.csv");
        }

        public void createEpisode(String TVSerieTittel, String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeUrl, int sesongNummer, int episodeNummer) {
            TVSerie x = getTVSerie(TVSerieTittel);
            x.createEpisode(tittel, spilletid, beskrivelse, utgivelsesdato, regissor, bildeUrl, sesongNummer, episodeNummer);
            skrivTilCSV(TVSerieListe, "tvshows_10.csv");
        }

    @Override
    public ArrayList<Episode> getAlleEpisoder(String TVSerieTittel, int sesongNummer) {
        return getTVSerie(TVSerieTittel).hentEpisoderISesong(sesongNummer);
    }

    @Override
    public Episode getEpisode(String TVSerieTittel, int sesongNummer, int episodeNummer) {
        return getTVSerie(TVSerieTittel).getEpisode(sesongNummer, episodeNummer);
    }

    @Override
    public void createEpisode(String TVSerieTittel, String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {

    }



    @Override
    public void updateEpisode(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl, int sesongNummer, int episodeNummer) {

    }


}
