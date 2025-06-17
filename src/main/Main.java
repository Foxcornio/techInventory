package src.main;
//Importamos las bibliotecas a utilizar, Scanner para entradas desde el teclado y models para utilizar nuestras clases.
import java.util.Scanner;
import src.models.*;

// Main :: Esta clase es la que se ejecutara al momento de compilar y ejecutar el codigo. Muestra un menu con las opciones disponibles para el usuario, estas funciones son las siguientes:
// agregarNuevoProducto :: Esta funcion es privada ya que unicamente es llamada por la clase, no puede ser ejecutada directamente desde terminal. Para ser utilizada solicita los atributos del objeto Producto que se desea ingresar y la lista donde este sera agregado.
// actualizarInventario :: Esta funcion tambien es privada y solo sera utilizads por la clase, para poder usarla solicitara el identifixador del producto, la cantidad a actualizar y el objeto lista donde se encuentra el producto, al finalizar su ejecucion devuelve un booleano que nos servira para trabajar la respuesta.
// consultaValorTotalInventario :: Esta funcion no retorna ningun valor es vacia y para poder usarla solicita el objeto de tipo lista donde realizara las consultas.
//METODOS ADICIONALES:
// getIntegerInput y getFloatInput :: Solicitan un objeto Scanner para ser utilizados y rerornan el tipo de dato correspondiente, su funcion es evitar que en momento de ejecucion, no se rompa el programa. Evitando de esta forma que si el usuario introduce un dato que no es valido se le notificara el tipo de dato y como debe introducirlo.
// instrucciones :: Retorna un dato tipo string que contiene las instrucciones de Inicio, donde se notificaval usuario que tareas puede realizar.
public class Main {
    public static void main(String [] args){
        GestionInventario inventario = new GestionInventario(); // Creamos y inicializamos un objeto de tipo GestionInventario.
        // Creamos las variables de los atributos por si el usuario desea crear un nuevo producto.
        Integer id, cantidad;
        Float precio;
        String nombre;
        // Creamos un objeto de tipo Scanner para detectar entradas
        Scanner entrada = new Scanner(System.in);
        // Creamos un dato de tipo opcion que almacenara la aopcion a elegir por el usuario, la inicialzamos en 0.
        Integer opcion = 0;
        do{ // con un ciclo do-while comenzamos el ciclo del programa
            System.out.println(instrucciones()); // Mandamos a imprimir en pantalla las instrucciones las cuales las obtendremos con la funcion instrucciones().
            opcion = getIntegerInput(entrada, "Seleccionar una opcion."); // A opcion le asignamos el valor que retorne getIntegerInput, en caso de que el usuario introduzca una valor invalido mostrara el mensaje que pasamos como parametro.
            switch(opcion){ // Dependiendo el valor de opcion elegiremos un caso en nuestro switch.
                case 1: // Caso 1 agregarNuevoProducto
                System.out.println("Ingresa el ID del producto:"); // Imprimimos en pantalla el dato a esperar.
                id = getIntegerInput(entrada, "ID"); // Llamamos a nuestra funcion getIntegerInput para recibir el dato y en caso de no recibir lo esperado mostrar el mensaje que pasamos como segundo parametro.
                entrada.nextLine(); // Borramos el buffer del Scanner.

                System.out.println("Ingresa el NOMBRE del producto:"); // Imprimimos en pantalla el dato a esperar
                nombre = entrada.nextLine();// Llamamos al metodo nextLine() de nuestro objeto Scanner para recibir el valor de entrada que sera de tipo String.

                System.out.println("Ingresa el PRECIO del producto:"); //  Imprimimos en pantalla el dato a esperar.
                precio = getFloatInput(entrada);// Llamamos a nuestra funcion getFloatInput para recibir el dato.

                System.out.println("Ingresa la CANTIDAD a ingresar en el sistema:"); // Imprimimos en pantalla el dato a esperar
                cantidad = getIntegerInput(entrada, "Cantidad."); // Llamamos a nuestra funcion getIntegerInput para recibir el dato y en caso de no recibir lo esperado mostrar el mensaje que pasamos como segundo parametro.
                
                if(agregarNuevoProducto(id,nombre,precio,cantidad,inventario)){ // Este if nos permitura notificar si el metodo agregarNuevoProducto se completo de forma exitosa o fallo en el intento, evaluando el booleano que retorna la funcion.
                    System.out.println("Producto agregado con éxito!!\n");
                }else{
                    System.out.println("Ocurrio un error intentelo de nuevo más tarde!!\n");
                }
                break;
                case 2: // actualizarInventario
                    System.out.println("Ingresa el ID del producto que deseas actualizar:"); // Imprimimos en pantalla el dato a esperar.
                    id = getIntegerInput(entrada, "ID"); // Llamamos a nuestra funcion getIntegerInput para recibir el dato y en caso de no recibir lo esperado mostrar el mensaje que pasamos como segundo parametro.
                    entrada.nextLine(); // Limpiamos el buffer de Scanner
                    System.out.println("Ingresa la CANTIDAD nueva:"); // Imprimimos en pantalla el dato a esperar.
                    cantidad = getIntegerInput(entrada, "CANTIDAD"); // Llamamos a nuestra funcion getIntegerInput para recibir el dato y en caso de no recibir lo esperado mostrar el mensaje que pasamos como segundo parametro.
                    entrada.nextLine(); // Limpiamos el buffer de Scanner
                    if(actualizarInventario(id,cantidad,inventario)){ // Este if nos ayudara para evaluar la respusta que retorna la funcion actualizarInventario, de esta forma notificar tarea exitosa si es true ó ocurrio un error si es false.
                        System.out.println("Producto actualizado con éxito!!\n");
                    }else{
                        System.out.println("Ocurrio un error intentelo de nuevo más tarde!!\n");
                    }
                break;
                case 3:
                    consultaValorTotalInventario(inventario); // Ejecutamos la funcion consultaValorTotalInventario()
                break;
            }
        }while(opcion < 4 && opcion > 0);
        entrada.close();
    }
    
    private static Integer getIntegerInput(Scanner scanner ,String fieldName){ // Esta funcion devuelve un entero y solicita un objeto Scanner para leer entradas y un dato de tipo texto que sera el mensaje a mostrar en caso de fallar.
        while (!scanner.hasNextInt()) { // Con un ciclo while  detectamos que scanner haya recibido un dato de tipo Integer, si no es asi el ciclo seguira ejecutandose
            System.out.println("¡Error! Por favor, ingresa un NÚMERO ENTERO para el " + fieldName + "."); // Notificamos que el dato no es el esperado  mostramos el dsto esperado y el mensaje que se recibio como parametro.
            scanner.next(); // Consumimos el dato ingresado por el usuario.
        }
        Integer res = scanner.nextInt(); // Solicitamos con nextInt() la entrada y la almacenaremos en una variable res de tipo Integer, si scanner no recibe el dsto correcto el ciclo de arriba seguira ejecutandose hasta que el dsto correcto sea Ingresado.
        return res; // Retornamos el Integer ingresado por el usuario.
    }
    
    private static Float getFloatInput(Scanner scanner){ // Esta funcion devuelve un dato de tipo Flotante y solicita un objeto de tipo Scanner para poder utilizarlo.
        Boolean precioValido=false; // Nos ayudara a saber si el precio es valido o se introdujo un dsto incorrecto.
        Float res = 0.0f; // Almacenara la respuesta, se inicializa en 0
        do{ // Con un ciclo do-while solicitaremos la entrada de datos en caso de no ser la esperada se repetira el ciclo nuevamente.
            String precioStr = scanner.nextLine(); // Solicitamos una entrada String para analizarla
            try{ // Intentamos analizar la cadena y trabajarla.
                if(precioStr.contains(",")){ // si precioStr contiene una coma esta sera remplazsds por un . la finalidad es transformar el dato en un Float
                    precioStr = precioStr.replace(",", ".");
                }
                res = Float.parseFloat(precioStr); // Transformamos el dato precioStr en un Float y lo almacenamos en res
                precioValido = true; // Una ves que el dato se transformo de forma exitosa notificamos que precioValido es true.
            }catch(NumberFormatException e){ // En caso de errar se notificara al usuario el dato esperado.
                System.out.println("Precio invalido!!\n El precio debe ser agregado con decimales y usando una ,");
            }
        }while(!precioValido); // El ciclo se ejecutara hasta que precioValido sea verdadero.
        return res; // retornamos el Float res.
    }
    
    private static String instrucciones(){ // Esta funcion retorna una cadena de texto. Se hizo en una funcion por si en un futuro es necesario cambiar la funcion en tiempo de ejecucion.
        String text = "Bienvenido a el inventario de Tech Inventrory.\nEstas son las acciones que puedes realizar:\n1.-Agrega un nuevo producto a tu inventario.\n2.-Actualiza tu inventario de algun producto existente.\n3.-Consulta el valor total de tu inventario."; // Creamos una variable de tipo String donde almacenaremos las intrucciones de uso.
        return text; // retornamos text
    }
    
    // Agregar producto nuevo.
    private static Boolean agregarNuevoProducto(Integer id, String nombre, Float precio, Integer cantidad, GestionInventario inventario){ // Esta funcion retorna un booleano y para su funcionamiento solicita los atributos del Producto nuevo y un objeto de tipo GestionInventario que es donde se agregara el nuevo Producto.
        Producto producto = new Producto(id,nombre,precio,cantidad); // Creamos un nuevo producto y le pasamos los atributos que recibimos como parametros. 
        return agregarInventario(producto, inventario); // retornamos el valor que devuelve la funcion agregarInventario le pasamos los parametros que solicita un producto y un objeto de tipo GestionInventario.
    }
    
    private static Boolean agregarInventario(Producto producto, GestionInventario inventario){ // Esta funcion devuelve un booleano para que puedan trabajar con la respuesta para su uso solicita un objeto de tipo Producto y un objeto GestionInventario.
        return inventario.agregar(producto); // Llamamos al metodo agregar del objeto GestionInventario que en este caso es inventario y le pasamos como parametro el objeto de tipo Producto que recibimos como parametro. Retornamos la respuesta que nos devuelva el metodo.
    }
    
    // Actualizar inventario actual.
    private static Boolean actualizarInventario(Integer id, Integer cantidad, GestionInventario inventario){ // Esta funcion devuelve un boolano y para ser utilizado solicita 2 datos de tipo entero y un objeto de tipo GestionInventario
        if(inventario.getCantidadElementos() > 0){ // Este if nos ayuda a comparar el valor retornado del metodo getCantidadElementos de nuestro objeto inventario con un 0. Si el valor retornado es mayor a 0 entonces se ejecutara el siguiente bloque
            System.out.println("\nInventario desactualizado:"); // Imprimimos en pantalla lo que ocurrira a continuacion.
            Producto producto = inventario.buscarId(id); // llamamos a el metodo buscarId de nuestro objeto inventario y le pasamos como parametro el id recibido, almacenaremos la respuesta en una instancia de tipo producto, este metodo buscarId() tambien llamara a imprimir el inventario actual para que el usuario visualice el cambio ejecutado mas adelante.
            producto.actualizarCantidad(cantidad); // Ahora llamamos al metodo actualizarCantidad del objeto producto que hacevreferencia al objeto retornado por inventario.buscarId(), le pasamos como parametro el atributo cantidad que recibimos para actualizar la cantidad.
            System.out.println("\nNuevo inventario."); // Imprimimos en pantalla la accion siguiente a realizar
            producto = inventario.buscarId(id); // volvemos a buscar el producto en nuestro objeto inventario con el metodo buscarId() Esto aparte de traernos devuelta el objeto, imprimira en pantalla los detalles sobre el mismo para que el usuario vea que el cambio en cantidad ya esta reflejado.
            return cantidad == producto.getCantidad() ? true:false; // Retornaremos true o false si el parametro cantidad que recibimos es igual a el atributo cantidad que se obtendra con el metodo getCantidad() de el objeto producto.
        }
        return false; // En caso de que la condicion no se cumpla se retornara false.
    }
    
    // Consultar valor total del inventario completo.
    private static void consultaValorTotalInventario(GestionInventario inventario){ // Esta funcion nos ayuda a consultar el monto total de todos los productos, no retorna nads es vacia y solicita simplemente un objeto de tipo GestionInventario.
        if(inventario.getCantidadElementos() > 0){ // Este if nos ayudara a evaluar si inventario contiene elementos, getCantidadElementos() retorna un numero entero que se compara con un 0 en caso de ser mayor se ejecuta el siguiente bloque de codigo.
            System.out.println("Tu inventario total tiene un valor de $"+inventario.valorTotalInventario()+"\n"); // Imprimimos en pantalla un mensaje jotificando el valor total del inventario y concatenamos la respuesta del metodo valorTotalInventario() de nuestro objeto inventario, el valor que recibiremos sera un Float el cual se concatenara con la cadena de texto.
        }else{
            // En caso de que getCantidadElementos() no sea mayor que 0 mostraremos el siguiente mensaje en pantalla
            System.out.println("Aun no tienes productos en tu inventario."); 
        }
    }
}
