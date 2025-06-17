package src.models;
public class Producto {
    Integer id, cantidad;
    String nombre;
    Float precio;
    public Producto(Integer id, String nombre, Float precio, Integer cantidad){
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
    }
    public void actualizarCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }
    public float calcularValorInventario(){
        return this.precio * this.cantidad;
    }
    public Integer getId(){
        return this.id;
    }
    public Integer getCantidad(){
        return this.cantidad;
    }
    public String getNombre(){
        return this.nombre;
    }
    public Float getPrecio(){
        return this.precio;
    }
}
