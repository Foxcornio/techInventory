package src.models;
// Se importan las librerias que nos permiten utilizar listas y acceder a ciertas funciones para trabajarlas con la clase Optional
import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

// GestionInventario :: Esta clase nos ayuda a crear una lista donde se almacenaran los productos que el cliente vaya creando desde el main ademas de esto tendra las siguientes funciones: 

// buscarId :: Funcion que nos ayudara a buscar un producto por su id y retornarlo para trabajarlo.
// valorTotalInventario :: Que sumara el resultado que arroje el metodo calcularValorInventario de los objetos Producto.
// agregar :: Nos ayuda a agregar un producto nuevo a la lista, devuelve un boolean para trabajar la respuesta en el main.
// imprimirDetalles :: Nos ayuda a mostrar en pantalla los campos como nombre, precio y cantidad.
// getCantidadElementos :: Nos ayuda a contar la cantidad de elementos que tiene la lista y lo retorna en un entero.

public class GestionInventario { 
    
    List<Producto> listaProductos; // Creamos una referencia a la clase List para que esta pueda ser usada de forma global con this
    
    public GestionInventario(){ // constructor por defecto creara un objeto Array de tipo List al inicializar.
        this.listaProductos = new ArrayList<>(); // Inicializamos y creamos un array de tipo lista para comenzar a utilizarlo.
    }

    public Boolean agregar(Producto producto){ // La función agregar solicita un Producto para ser utilizada
        return this.listaProductos.add(producto); // .add es un metodo de la interfaz List el cual nos permite agregar un elemento al final y devuelve un boolean (true si fue posible/false si no fue posible)
    }

    public Producto buscarId(Integer id){ // La funcion buscarId solicita un Integer para ser utilizado en la busqueda
        Producto productoEncontrado=null; // Creamos e inicializamos un objeto de tipo Producto donde se almacenara a futuro el objeto encontrado
        
        // Optional y Strams API .
        //Optional es objeto que almacena otro objeto, de esta forma evitamos recibir una respuesta null y con ello problemas en ejecución del programa, hacemos el codigo más limpio y legible.
        //Strams API nos ayuda a a procesar colecciones de datos, similar a lo que se hace en una base de datos. Si el proyecto crece puede utilizarse de forma paralela lo cual nos ahorra tiempo de busqueda.
        Optional<Producto> encontrado
        ;
        
        encontrado = listaProductos.stream().filter(producto -> producto.getId().equals(id)).findFirst(); // stream() :: Saca todos los productos de la pila para trabajarlos. No es una nueva lista solo permite procesarlos.

        //filter() :: Es nuestro filtro el cual tiene una instruccion toma producto obtiene el id con la funcion del objeto getId lo compara con el id ingresado, devolvera true si coincide o false si no coincide, si coincide sigue con el siguiente metodo, si no coincide se vuelve a ejecutar hasta ese punto pero con el siguiente producto.

        //findFirst :: De los elementos que tengan el id buscado se devuelve el primero. Sin embargo devuelve el producto en este caso dentro de un objeto Optional. Esto funciona porque si no encuentra el objeto producto o no existe, aun asi devuelve un Optional.empty() con el cual podemos trabajar sin tener errores al momento de ejecucion.

        if(encontrado.isPresent()){ // Si el objeto Optional encontrado tiene algo dentro ejecutara el siguiente bloque
            productoEncontrado = encontrado.get(); // tomara el contenido del objeto Optional con .get() y lo amacenamos en referencia productoEncontrado 
        }else{ // Caso contrario el contenido del objeto Opcional esta vacio ejecutamos el siguiente bloque.
            productoEncontrado = new Producto(id,"No se encontro ningun elemento con el ID ingresado",0.0f,0); // Creamos un nuevo objeto Producto notificando que no se encontro el id y con todos los campos en 0
        }
        imprimirDetalles(productoEncontrado); // llamamos a nuestra funcion imprimirDetalles y le pasamos el objeto del cual deseamos los detalles.
        return productoEncontrado; // Retornamos el objetoEncontrado para que trabajen con el mismo de ser necesario.
    }
    
    public Float valorTotalInventario(){
        Float valorTotal = 0.0f; // Declaramos una variable de tipo Float para almacenar el valorTotal de la suma.
        valorTotal = this.listaProductos.stream().map(producto -> producto.calcularValorInventario()).reduce(0.0f, Float::sum); // stream() :: Saca todos los productos de la pila uno por uno para trabajarlos.
        // map :: genera un nuevo Stream y lo retorna al concluir la operacion interna. Esto es escencial ya que en ningun momento se toma el valor directamente del objeto Producto, se toma una copia del resultado de ejecutar el metodo calcularValorInventario() y se transforma en un Stream.
        // reduce :: El metodo reduce() sumara los elementos del Stream que reciba del metodo map() y almacenara el resultado de la suma en sum, la variable sum se inicializa en 0.0f, se retorna el resultado de la suma y se almacena en valorTotal.
        return valorTotal; // retornamos valorTotal
    }
    
    private void imprimirDetalles(Producto productoEncontrado){ // solicita un objeto de tipo Producto
        String detallesProducto = ""; // declaramos una variable de tipo String vacia, que almacenara los detalles del producto.
        detallesProducto = "Nombre del producto : "+productoEncontrado.getNombre() + "\nCantidad actual en el inventario : " + productoEncontrado.getCantidad()+ "\nPrecio unitario : " + productoEncontrado.getPrecio(); // Llamamos a los netodos get de Producto para recolectar los atributos nombre, pregio y cantidad, concatenamos el resultado de cada metodo get a la cadena de texto detallesProducto.
        System.out.println(detallesProducto); // Imprimimos en pantalla la cadena de texto detallesProducto.
    }
    
    public Integer getCantidadElementos(){ 
        Integer cantidad = this.listaProductos.size(); // creamos una variable de tipo Integer y la inicializamos con el valor retornado por el metodo size() de nuestra listaProductos.
        return cantidad; // retornamos la cantidad de elementos actuales.
    }
}
