package com.example.camposdegolf;

public class Encapsulador {

    private int imagen;
    private String identificador;
    private String nombre;

    public Encapsulador(int idImagen, String textoidentificador, String textoNombre) {
        imagen = idImagen;
        identificador = textoidentificador;
        nombre = textoNombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String textoTitulo) {
        this.identificador = textoTitulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String textoContenido) {
        this.nombre = textoContenido;
    }
}
