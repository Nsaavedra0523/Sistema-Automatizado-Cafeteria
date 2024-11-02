package com.mycompany.main;
import java.util.*;

public class SistemaCafeteria {
    private ArbolProductos arbolProductos;
    private Queue<Pedido> colaPedidos;
    private Stack<Pedido> pedidosCancelados;
    private Map<String, Double> catalogoProductos;
    private Map<String, Producto> productosDisponibles;
    private int contadorPedidos;

    public SistemaCafeteria() {
        this.arbolProductos = new ArbolProductos();
        this.colaPedidos = new LinkedList<>();
        this.pedidosCancelados = new Stack<>();
        this.catalogoProductos = new HashMap<>();
        this.productosDisponibles = new HashMap<>();
        this.contadorPedidos = 0;
        inicializarProductos();
    }

    private void inicializarProductos() {
        agregarProducto("Cafe Americano", 2.50, "BEBIDA");
        agregarProducto("Cappuccino", 3.50, "BEBIDA");
        agregarProducto("Latte", 3.75, "BEBIDA");
        agregarProducto("Te Verde", 2.00, "BEBIDA");
        agregarProducto("Croissant", 2.25, "COMIDA");
        agregarProducto("Sandwich", 4.50, "COMIDA");
        agregarProducto("Muffin", 1.75, "COMIDA");
        agregarProducto("Bagel", 3.00, "COMIDA");
    }

    public void agregarProducto(String nombre, double precio, String categoria) {
        Producto producto = new Producto(nombre, precio, categoria);
        arbolProductos.insertar(producto);
        catalogoProductos.put(nombre, precio);
        productosDisponibles.put(nombre, producto);
    }

    public Producto buscarProducto(String nombre) {
        return productosDisponibles.get(nombre);
    }

    public List<Producto> obtenerMenuOrdenado() {
        return arbolProductos.obtenerProductosOrdenados();
    }

    public Pedido crearPedido() {
        contadorPedidos++;
        Pedido pedido = new Pedido(contadorPedidos);
        colaPedidos.add(pedido);
        return pedido;
    }

    public void cancelarPedido(Pedido pedido) {
        if (colaPedidos.remove(pedido)) {
            pedido.setEstado("CANCELADO");
            pedidosCancelados.push(pedido);
        }
    }

    public Pedido siguientePedido() {
        return colaPedidos.poll();
    }

    public void mostrarMenu() {
        List<Producto> productos = obtenerMenuOrdenado();
        System.out.println("\n=== MENÚ DE LA CAFETERÍA ===");
        System.out.println("\nBEBIDAS:");
        for (Producto p : productos) {
            if (p.getCategoria().equals("BEBIDA")) {
                System.out.printf("%s - $%.2f%n", p.getNombre(), p.getPrecio());
            }
        }
        System.out.println("\nCOMIDAS:");
        for (Producto p : productos) {
            if (p.getCategoria().equals("COMIDA")) {
                System.out.printf("%s - $%.2f%n", p.getNombre(), p.getPrecio());
            }
        }
    }

    public Queue<Pedido> getColaPedidos() {
        return colaPedidos;
    }

    public Stack<Pedido> getPedidosCancelados() {
        return pedidosCancelados;
    }

    public int getPedidosPendientes() {
        return colaPedidos.size();
    }

    public void mostrarEstadisticas() {
        System.out.println("\n=== Estadísticas del Sistema ===");
        System.out.println("Pedidos pendientes: " + colaPedidos.size());
        System.out.println("Pedidos cancelados: " + pedidosCancelados.size());
        System.out.println("Total de pedidos creados: " + contadorPedidos);
    }
}