package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Person {
    private LocalDate fodselsDato;
    private String fornavn;
    private String etternavn;

    public Person() {}
    public Person(LocalDate fodselsDato, String fornavn, String etternavn) {
        this.fodselsDato = fodselsDato;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public Person( String fornavn, String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    @JsonIgnore
    public String getFulltNavn() {
        return this.fornavn + " " + this.etternavn + ".";
    }
    @JsonIgnore
    public String fulltNavnOgAlder() {
        return fornavn + " " + etternavn + " og har alder " + fodselsDato + ".";
    }

    public LocalDate getFodselsDato() {
        return fodselsDato;
    }

    public void setFodselsDato(LocalDate fodselsDato) {
        this.fodselsDato = fodselsDato;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    @Override
    public String toString() {
        return fornavn + " " + etternavn + " " + fodselsDato + ".";
    }


}
