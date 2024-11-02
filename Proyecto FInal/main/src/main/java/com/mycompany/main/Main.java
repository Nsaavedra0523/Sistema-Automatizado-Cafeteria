package com.mycompany.main;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaCafeteria sistema = new SistemaCafeteria();
    
    public static void main(String[] args) {
        boolean ejecutar = true;
        
        while (ejecutar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    sistema.mostrarMenu();
                    break;
                case 2:
                    crearNuevoPedido();
                    break;
                case 3:
                    atenderSiguientePedido();
                    break;
                case 4:
                    cancelarPedido();
                    break;
                case 5:
                    sistema.mostrarEstadisticas();
                    break;
                case 6:
                    ejecutar = false;
                    System.out.println("\n¡Gracias por usar el sistema! Hasta pronto.");
                    break;
                default:
                    System.out.println("\nOpción no válida. Por favor, intente nuevamente.");
            }
        }
        scanner.close();
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE CAFETERÍA ===");
        System.out.println("1. Ver menú");
        System.out.println("2. Crear nuevo pedido");
        System.out.println("3. Atender siguiente pedido");
        System.out.println("4. Cancelar pedido");
        System.out.println("5. Ver estadísticas");
        System.out.println("6. Salir");
        System.out.print("\nSeleccione una opción: ");
    }
    
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void crearNuevoPedido() {
        Pedido pedido = sistema.crearPedido();
        boolean agregandoProductos = true;
        
        while (agregandoProductos) {
            System.out.println("\n=== Crear Pedido #" + pedido.getNumeroPedido() + " ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Finalizar pedido");
            System.out.print("\nSeleccione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    agregarProductoAPedido(pedido);
                    break;
                case 2:
                    eliminarProductoDePedido(pedido);
                    break;
                case 3:
                    pedido.mostrarDetalle();
                    break;
                case 4:
                    if (pedido.getProductos().isEmpty()) {
                        System.out.println("\nNo se puede finalizar un pedido vacío.");
                    } else {
                        agregandoProductos = false;
                        System.out.println("\nPedido creado exitosamente.");
                        pedido.mostrarDetalle();
                    }
                    break;
                default:
                    System.out.println("\nOpción no válida.");
            }
        }
    }
    
    private static void agregarProductoAPedido(Pedido pedido) {
        sistema.mostrarMenu();
        System.out.print("\nIngrese el nombre del producto: ");
        String nombreProducto = scanner.nextLine();
        
        Producto producto = sistema.buscarProducto(nombreProducto);
        if (producto != null) {
            pedido.agregarProducto(producto);
            System.out.println("\nProducto agregado exitosamente.");
        } else {
            System.out.println("\nProducto no encontrado.");
        }
    }
    
    private static void eliminarProductoDePedido(Pedido pedido) {
        if (pedido.getProductos().isEmpty()) {
            System.out.println("\nEl pedido está vacío.");
            return;
        }
        
        pedido.mostrarDetalle();
        System.out.print("\nIngrese el número del producto a eliminar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            pedido.eliminarProducto(indice);
            System.out.println("\nProducto eliminado exitosamente.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\nNúmero de producto inválido.");
        }
    }
    
    private static void atenderSiguientePedido() {
        Pedido pedido = sistema.siguientePedido();
        if (pedido != null) {
            System.out.println("\n¡Atendiendo siguiente pedido!");
            pedido.setEstado("COMPLETADO");
            pedido.mostrarDetalle();
        } else {
            System.out.println("\nNo hay pedidos pendientes.");
        }
    }
    
    private static void cancelarPedido() {
        if (sistema.getPedidosPendientes() == 0) {
            System.out.println("\nNo hay pedidos pendientes para cancelar.");
            return;
        }
        
        System.out.println("\n=== Pedidos Pendientes ===");
        Queue<Pedido> pedidosPendientes = sistema.getColaPedidos();
        for (Pedido p : pedidosPendientes) {
            p.mostrarDetalle();
        }
        
        System.out.print("\nIngrese el número de pedido a cancelar: ");
        try {
            int numeroPedido = Integer.parseInt(scanner.nextLine());
            for (Pedido p : pedidosPendientes) {
                if (p.getNumeroPedido() == numeroPedido) {
                    sistema.cancelarPedido(p);
                    System.out.println("\nPedido #" + numeroPedido + " cancelado exitosamente.");
                    return;
                }
            }
            System.out.println("\nNúmero de pedido no encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("\nNúmero de pedido inválido.");
        }
    }
}
