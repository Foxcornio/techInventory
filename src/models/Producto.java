package src.models;

// Producto :: Esta clase nos solicita en su construcctor los atributos que tendra el objeto, ID, NOMMBRE, PRECIO y CANTIDAD. Ademas tiene varias funciones que podran ser utilizadas como metodos:
// actualizarCantidad :: Esta funcion nos permite modificar el atributo cantidad del elemento. Solicita un Integer para asignarlo como nuevo valor a el atributo cantidad.
// calcularValorInventario :: Esta funcion retorna un dato de tipo Float para que pueda trabajarse, el Float lo consigue de multiplicar el atributo precio por el atributo cantidad, su funcion es proporcionar el valor total en de nuestro Producto en Inventario.
// Metodos get: getId, getCantidad, getNombre y getPrecio retornan el valor de los atributos id,cantidad,nombre y precio con el fin de que estos datos puedan ser trabajados.
public class Producto {
  // Declaramos de forma global los atributos
    Integer id, cantidad;
    String nombre;
    Float precio;
    
    public Producto(Integer id, String nombre, Float precio, Integer cantidad){ // Solicitamos en el constructor los atributos del objeto.
      // Almacenamos los atributos recibidos en los atributos globales, para poder usarlos en los metodos.
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public void actualizarCantidad(Integer cantidad){ // Solicitamos un dato de tipo Integer para poder usar este metodo.
        this.cantidad = cantidad; // Asignamos el valor de nuestro dato Integer recibido a nuestro atributo cantidad del objeto Producto, haciendo referencia a el con la palabra reservada this
    }
    
    public float calcularValorInventario(){ // Esta funcion retorna un dato de tipo Float.
        return this.precio * this.cantidad; // llamamos a los atributos precio y cantidad con la palabra reservada this , realizamos una multiplicacion entre ambos datos y retornamos el resultado de la operacion.
    }
    
    public Integer getId(){ // retorna el atributo id haciendo referencia hacia el con la palabra reservada this.
        return this.id;
    }
    
    public Integer getCantidad(){// retorna el atributo cantidad haciendo referencia hacia el con la palabra reservada this.
        return this.cantidad;
    }
    public String getNombre(){// retorna el atributo nombre haciendo referencia hacia el con la palabra reservada this.
        return this.nombre;
    }
    public Float getPrecio(){// retorna el atributo precio haciendo referencia hacia el con la palabra reservada this.
        return this.precio;
    }
}
