package model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Produksjon {
    private String tittel, beskrivelse;
    private LocalDate utgivelsesdato;
    private String bildeUrl;
    private int spilletid;
    private Person regissor;
    private ArrayList<Rolle> rolleListe;

    public Produksjon() {}

    public Produksjon(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato) {
        this.tittel = tittel;
        this.spilletid = spilletid;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        rolleListe = new ArrayList<>();
    }

    // String tittel, int episodeNr, int sesongNr, int spilletid,
    // String beskrivelse, String utgivelsesdato, Person regissor
    public Produksjon(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, Person regissor, String bildeUrl) {
        this.tittel = tittel;
        this.spilletid = spilletid;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.regissor = regissor;
        this.bildeUrl = bildeUrl;
        rolleListe = new ArrayList<>();
    }

    public Produksjon(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato, String bildeUrl) {
        this.tittel = tittel;
        this.spilletid = spilletid;
        this.beskrivelse = beskrivelse;
        this.utgivelsesdato = utgivelsesdato;
        this.bildeUrl = bildeUrl;
        rolleListe = new ArrayList<>();
    }

    public void leggTilEnRolle(Rolle enRolle){
        rolleListe.add(enRolle);

    }

    public void leggTilMangeRoller(ArrayList<Rolle> flereRoller){
        this.rolleListe.addAll(flereRoller);

    }
    @JsonIgnore
    @Override
    public String toString() {
        return "Denne Produksjonen har Tittel:" + tittel + " Spilletid:" + spilletid + " Utgivelsesdato" + utgivelsesdato
                + " Beskrivelse:" + beskrivelse + " Roller:" + rolleListe;
    }

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

    public int getSpilletid() {
        return spilletid;
    }

    public void setSpilletid(int spilletid) {
        this.spilletid = spilletid;
    }

    public Person getRegissor() {
        return regissor;
    }

    public void setRegissor(Person regissor) {
        this.regissor = regissor;
    }

    public ArrayList<Rolle> getRoller() {
        // Vi lager en kopi av listen vi har, slik at den interne listen ikke kan manipuleres direkte utenfor klassen (innkapsling)
        return new ArrayList<>(rolleListe);
    }

    public void setRoller(ArrayList<Rolle> roller) {
        this.rolleListe = roller;
    }

    /*
    public void setRoller(ArrayList<Rolle> roller) {
        this.roller = roller;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public int getSpilletid() {
        return spilletid;
    }

    public void setSpilletid(int spilletid) {
        this.spilletid = spilletid;
    }

    public String getUtgivelsesdato() {
        return utgivelsesdato;
    }

    public void setUtgivelsesdato(String utgivelsesdato) {
        this.utgivelsesdato = this.utgivelsesdato;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Person getRegissor() {
        return regissor;
    }

    public void setRegissor(Person regissor) {
        this.regissor = regissor;
    }

    public ArrayList<Rolle> getRoller() {
        return roller;
    }

    public ArrayList<Rolle> getRolleListe() {
        return new ArrayList<>(roller);
    }

    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    // episode og/eller film
  */

}
