package com.mycompany.main;
public class NodoArbol {
    Producto producto;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(Producto producto) {
        this.producto = producto;
        this.izquierdo = null;
        this.derecho = null;
    }
}
