package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Film extends Produksjon {

    public Film() {};
    public Film(String tittel, int spilletid, String beskrivelse, LocalDate utgivelsesdato) {
        super(tittel, spilletid, beskrivelse, utgivelsesdato);

    }
    @JsonIgnore
    @Override
    public String toString() {
        return "Denne Produksjonen har Tittel:" + getTittel() + " Spilletid:" + getSpilletid() + " Utgivelsesdato" + getUtgivelsesdato()
                + " Beskrivelse:" + getBeskrivelse() + " Regissor:" + getRegissor();
    }


}
