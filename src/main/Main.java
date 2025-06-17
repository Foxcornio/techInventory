package src.main;
import java.util.Scanner;

import src.models.*;
public class Main {
    public static void main(String [] args){
        GestionInventario inventario = new GestionInventario();
        Integer id, cantidad;
        Float precio;
        String nombre;
        Scanner entrada = new Scanner(System.in);
        Integer opcion = 0;
        do{
            System.out.println(instrucciones());
            opcion = getIntegerInput(entrada, "Seleccionar una opcion.");
            switch(opcion){
                case 1:
                System.out.println("Ingresa el ID del producto:");
                id = getIntegerInput(entrada, "ID");
                entrada.nextLine();

                System.out.println("Ingresa el NOMBRE del producto:");
                nombre = entrada.nextLine();

                System.out.println("Ingresa el PRECIO del producto:");
                precio = getFloatInput(entrada);

                System.out.println("Ingresa la CANTIDAD a ingresar en el sistema:");
                cantidad = getIntegerInput(entrada, "Cantidad.");
                
                if(agregarNuevoProducto(id,nombre,precio,cantidad,inventario)){
                    System.out.println("Producto agregado con éxito!!\n");
                }else{
                    System.out.println("Ocurrio un error intentelo de nuevo más tarde!!\n");
                }
                break;
                case 2:
                    System.out.println("Ingresa el ID del producto que deseas actualizar:");
                    id = entrada.nextInt();
                    System.out.println("Ingresa la CANTIDAD nueva:");
                    cantidad = entrada.nextInt();
                    if(actualizarInventario(id,cantidad,inventario)){
                        System.out.println("Producto actualizado con éxito!!\n");
                    }else{
                        System.out.println("Ocurrio un error intentelo de nuevo más tarde!!\n");
                    }
                break;
                case 3:
                    consultaValorTotalInventario(inventario);
                break;
            }
        }while(opcion < 4 && opcion > 0);
        entrada.close();
    }
    private static Integer getIntegerInput(Scanner scanner ,String fieldName){
        while (!scanner.hasNextInt()) {
            System.out.println("¡Error! Por favor, ingresa un NÚMERO ENTERO para el " + fieldName + ".");
            scanner.next(); // Consume the invalid input
        }
        Integer res = scanner.nextInt();
        return res;
    }
    private static Float getFloatInput(Scanner scanner){
        Boolean precioValido=false;
        Float res = 0.0f;
        do{
            String precioStr = scanner.nextLine();
            try{
                if(precioStr.contains(",")){
                    precioStr = precioStr.replace(",", ".");
                }
                res = Float.parseFloat(precioStr);
                precioValido = true;
            }catch(NumberFormatException e){
                System.out.println("Precio invalido!!\n El precio debe ser agregado con decimales y usando una ,");
            }
        }while(!precioValido);
        return res;
    }
    private static String instrucciones(){
        String text = "Bienvenido a el inventario de Tech Inventrory.\nEstas son las acciones que puedes realizar:\n1.-Agrega un nuevo producto a tu inventario.\n2.-Actualiza tu inventario de algun producto existente.\n3.-Consulta el valor total de tu inventario.";
        return text;
    }
    // Agregar producto nuevo.
    private static Boolean agregarNuevoProducto(Integer id, String nombre, Float precio, Integer cantidad, GestionInventario inventario){
        Producto producto = new Producto(id,nombre,precio,cantidad);
        return agregarInventario(producto, inventario);
    }
    private static Boolean agregarInventario(Producto producto, GestionInventario inventario){
        return inventario.agregar(producto);
    }
    // Actualizar inventario actual.
    private static Boolean actualizarInventario(Integer id, Integer cantidad, GestionInventario inventario){
        if(inventario.getCantidadElementos() > 0){    
            System.out.println("\nInventario desactualizado:");
            Producto producto = inventario.buscarId(id);
            producto.actualizarCantidad(cantidad);
            System.out.println("\nNuevo inventario.");
            producto = inventario.buscarId(id);
            return cantidad == producto.getCantidad() ? true:false;
        }
        return false;
    }
    // Consultar valor total del inventario completo.
    private static void consultaValorTotalInventario(GestionInventario inventario){
        if(inventario.getCantidadElementos() > 0){
            System.out.println("Tu inventario total tiene un valor de $"+inventario.valorTotalInventario()+"\n");
        }else{
            System.out.println("Aun no tienes productos en tu inventario.");
        }
    }
}
