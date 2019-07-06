package com.example.medicon;

public class Hospitales {

    String nombre, dirrecion, distacia, tiempo, lon , lat;

    public Hospitales(String nombre, String dirrecion, String distacia, String tiempo, String lon, String lat) {
        this.nombre = nombre;
        this.dirrecion = dirrecion;
        this.distacia = distacia;
        this.tiempo = tiempo;
        this.lon = lon;
        this.lat = lat;
    }

    public Hospitales(String nombre, String dirrecion,String lon, String lat) {
        this.nombre = nombre;
        this.dirrecion = dirrecion;
        this.lon = lon;
        this.lat = lat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }

    public String getDistacia() {
        return distacia;
    }

    public void setDistacia(String distacia) {
        this.distacia = distacia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
