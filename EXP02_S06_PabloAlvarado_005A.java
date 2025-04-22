package exp02_s06_pabloalvarado_005a;

/**Experiencia de Aprendizaje 02, Semana 06
 * Fundamentos de Programacion - 005A
 * @author Pablo Alvarado 
 */
import java.util.Scanner;

//01.Importación y definición de Clases y Variables

public class EXP02_S06_PabloAlvarado_005A {

    //DEFINICION DE VARIABLES
    //01.1 - Definición de Variables para el Algoritmo
    
    static String teatroNombre = "Teatro Moro";
    static int capacidad = 5;
    static double precioEntrada = 10000;
    static double totalIngresos = 0;
    static int totalEntradasVendidas = 0;

    //01.2 - Estado de Asientos para la interacción de Reservas
    String asiento1Estado = "Libre";
    String asiento2Estado = "Libre";
    String asiento3Estado = "Libre";
    String asiento4Estado = "Libre";
    String asiento5Estado = "Libre";
    
    
    //01.3 - Definicion de Valores para el Algoritmo
    double asiento1PrecioFinal = 0;
    double asiento2PrecioFinal = 0;
    double asiento3PrecioFinal = 0;
    double asiento4PrecioFinal = 0;
    double asiento5PrecioFinal = 0;

    public static void main(String[] args) {
        EXP02_S06_PabloAlvarado_005A sistema = new EXP02_S06_PabloAlvarado_005A();
        sistema.menu();
    }

    
    //INICIO DEL PROGRAMA
    //02. Inicio del Algoritmo - Teatro Moro
    
    //02.1.1 - Menú de Interacción para el Usuario.
    
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- " + teatroNombre + " ---");
            System.out.println("1. Reservar entrada");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Modificar venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            
            
            //02.1.2  - Interacciones del Menú
            
            switch (opcion) {
                case 1:
                    reservarEntrada();
                    break;
                case 2:
                    comprarEntrada();
                    break;
                case 3:
                    modificarVenta();
                    break;
                case 4:
                    imprimirBoleta();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
    }

    
    //02.2 - Interacción para Asientos Disponibles
    
    public void mostrarAsientos() {
        System.out.println("\nEstado de asientos:");
        System.out.println("1: " + asiento1Estado);
        System.out.println("2: " + asiento2Estado);
        System.out.println("3: " + asiento3Estado);
        System.out.println("4: " + asiento4Estado);
        System.out.println("5: " + asiento5Estado);
    }

    
    
    //02.3 - Interacción para Reservar entrada/s
    
    public void reservarEntrada() {
        Scanner sc = new Scanner(System.in);
        mostrarAsientos();
        System.out.print("Seleccione un asiento para reservar (1-5): ");
        int asiento = sc.nextInt();
        if (cambiarEstadoAsiento(asiento, "Libre", "Reservado")) {
            System.out.println("Asiento " + asiento + " reservado exitosamente.");
        } else {
            System.out.println("No se pudo reservar. El asiento no esta disponible.");
        }
    }

    
    
    //02.4 - Interacción para Comprar Entrada/s
    public void comprarEntrada() {
        Scanner sc = new Scanner(System.in);
        mostrarAsientos();
        System.out.print("Seleccione un asiento para comprar (1-5): ");
        int asiento = sc.nextInt();
        if (cambiarEstadoAsiento(asiento, "Libre", "Vendido") || cambiarEstadoAsiento(asiento, "Reservado", "Vendido")) {
            System.out.print("¿Aplica descuento? (s/n): ");
            char aplicaDescuento = sc.next().charAt(0);
            double descuento = (aplicaDescuento == 's' || aplicaDescuento == 'S') ? 0.1 : 0;
            double precioFinal = precioEntrada - (precioEntrada * descuento);
            actualizarPrecioAsiento(asiento, precioFinal);
            totalIngresos += precioFinal;
            totalEntradasVendidas++;
            System.out.println("Asiento " + asiento + " comprado exitosamente.");
        } else {
            System.out.println("No se pudo comprar. El asiento no está disponible.");
        }
    }

    
    //02.5 - Interacción para Modificar la Cantidad de Entradas
    public void modificarVenta() {
        Scanner sc = new Scanner(System.in);
        mostrarAsientos();
        System.out.print("Seleccione un asiento para liberar (1-5): ");
        int asiento = sc.nextInt();
        if (cambiarEstadoAsiento(asiento, "Vendido", "Libre")) {
            actualizarPrecioAsiento(asiento, 0);
            System.out.println("Venta anulada exitosamente para el asiento " + asiento);
        } else {
            System.out.println("No se pudo anular. El asiento no estaba vendido.");
        }
    }

    
    
    //02.6 - Para imprimir la Boleta
    
    public void imprimirBoleta() {
        mostrarAsientos();
        System.out.println("\n--- Boleta de Venta ---");
        for (int i = 1; i <= 5; i++) {
            if (getEstadoAsiento(i).equals("Vendido")) {
                System.out.println("Asiento " + i + ": $" + getPrecioAsiento(i));
            }
        }
        System.out.println("Total ingresos: $" + totalIngresos);
        System.out.println("Entradas vendidas: " + totalEntradasVendidas);
    }

    
    
    //03.1- Propiedad IF para modificar los Asientos
    
    public boolean cambiarEstadoAsiento(int asiento, String estadoActual, String nuevoEstado) {
        boolean cambiado = false;
        switch (asiento) {
            case 1:
                if (asiento1Estado.equals(estadoActual)) {
                    asiento1Estado = nuevoEstado;
                    cambiado = true;
                }
                break;
            case 2:
                if (asiento2Estado.equals(estadoActual)) {
                    asiento2Estado = nuevoEstado;
                    cambiado = true;
                }
                break;
            case 3:
                if (asiento3Estado.equals(estadoActual)) {
                    asiento3Estado = nuevoEstado;
                    cambiado = true;
                }
                break;
            case 4:
                if (asiento4Estado.equals(estadoActual)) {
                    asiento4Estado = nuevoEstado;
                    cambiado = true;
                }
                break;
            case 5:
                if (asiento5Estado.equals(estadoActual)) {
                    asiento5Estado = nuevoEstado;
                    cambiado = true;
                }
                break;
            default:
                System.out.println("Numero de asiento inválido.");
        }
        return cambiado;
    }

    
    
    //03.2 - Propiedad IF para Actualizar el valor de las entradas
    
    public void actualizarPrecioAsiento(int asiento, double precio) {
        switch (asiento) {
            case 1:
                asiento1PrecioFinal = precio;
                break;
            case 2:
                asiento2PrecioFinal = precio;
                break;
            case 3:
                asiento3PrecioFinal = precio;
                break;
            case 4:
                asiento4PrecioFinal = precio;
                break;
            case 5:
                asiento5PrecioFinal = precio;
                break;
        }
    }

    
    //03.3 - Propiedad para que el usuario pueda ver su entrada
    
    public String getEstadoAsiento(int asiento) {
        switch (asiento) {
            case 1:
                return asiento1Estado;
            case 2:
                return asiento2Estado;
            case 3:
                return asiento3Estado;
            case 4:
                return asiento4Estado;
            case 5:
                return asiento5Estado;
            default:
                return "";
        }
    }

    
    //03.4 Propiedad para el valor de la entrada
    
    public double getPrecioAsiento(int asiento) {
        switch (asiento) {
            case 1:
                return asiento1PrecioFinal;
            case 2:
                return asiento2PrecioFinal;
            case 3:
                return asiento3PrecioFinal;
            case 4:
                return asiento4PrecioFinal;
            case 5:
                return asiento5PrecioFinal;
            default:
                return 0;
        }
    }
    
    //FIN DEL PROGRAMA
}
