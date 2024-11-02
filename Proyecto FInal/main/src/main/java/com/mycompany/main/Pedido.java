package com.mycompany.main;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Pedido {
    private int numeroPedido;
    private List<Producto> productos;
    private String estado;
    private LocalDateTime fechaHora;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.productos = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.fechaHora = LocalDateTime.now();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(int indice) {
        if (indice >= 0 && indice < productos.size()) {
            productos.remove(indice);
        }
    }

    public double calcularTotal() {
        return calcularTotalRec(productos, 0);
    }

    private double calcularTotalRec(List<Producto> productos, int indice) {
        if (indice >= productos.size()) {
            return 0;
        }
        return productos.get(indice).getPrecio() + calcularTotalRec(productos, indice + 1);
    }

    public int getNumeroPedido() { return numeroPedido; }
    public List<Producto> getProductos() { return productos; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    public void mostrarDetalle() {
        System.out.println("\n=== Detalle del Pedido #" + numeroPedido + " ===");
        System.out.println("Estado: " + estado);
        System.out.println("Fecha y hora: " + fechaHora);
        System.out.println("\nProductos:");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, p.getNombre(), p.getPrecio());
        }
        System.out.printf("\nTotal: $%.2f%n", calcularTotal());
    }
}
