package com.example.unidad2_6;

public class Encapsulador {
    private int imagen;
    private String titulo;
    private String texto;

    public Encapsulador(int idImagen, String textoTitulo, String textoContenido) {
        imagen = idImagen;
        titulo = textoTitulo;
        texto = textoContenido;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String textoTitulo) {
        this.titulo = textoTitulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String textoContenido) {
        this.texto = textoContenido;
    }
}
