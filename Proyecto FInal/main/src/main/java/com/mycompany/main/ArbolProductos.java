package com.mycompany.main;
import java.util.ArrayList;
import java.util.List;

public class ArbolProductos {
    private NodoArbol raiz;

    public void insertar(Producto producto) {
        raiz = insertarRec(raiz, producto);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Producto producto) {
        if (nodo == null) {
            return new NodoArbol(producto);
        }

        if (producto.getPrecio() < nodo.producto.getPrecio()) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, producto);
        } else if (producto.getPrecio() > nodo.producto.getPrecio()) {
            nodo.derecho = insertarRec(nodo.derecho, producto);
        }

        return nodo;
    }

    public List<Producto> obtenerProductosOrdenados() {
        List<Producto> productos = new ArrayList<>();
        inordenRec(raiz, productos);
        return productos;
    }

    private void inordenRec(NodoArbol nodo, List<Producto> productos) {
        if (nodo != null) {
            inordenRec(nodo.izquierdo, productos);
            productos.add(nodo.producto);
            inordenRec(nodo.derecho, productos);
        }
    }
}
