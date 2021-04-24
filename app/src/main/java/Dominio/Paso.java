package Dominio;

import java.io.Serializable;

public class Paso implements Serializable {

    private int idUser;
    private int pasos;
    private String fecha;

    public Paso(int idUser, int pasos, String fecha) {
        this.idUser = idUser;
        this.pasos = pasos;
        this.fecha = fecha;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }



}
