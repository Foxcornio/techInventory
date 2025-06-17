package src.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionInventario {
    List<Producto> listaProductos;
    public GestionInventario(){
        this.listaProductos = new ArrayList<>();
    }
    public Boolean agregar(Producto producto){
        return this.listaProductos.add(producto);
    }
    public Producto buscarId(Integer id){
        Producto productoEncontrado=null;
        Optional<Producto> encontrado = listaProductos.stream().filter(producto -> producto.getId().equals(id)).findFirst();
        productoEncontrado = encontrado.get();
        imprimirDetalles(productoEncontrado);
        return productoEncontrado;
    }
    public Float valorTotalInventario(){
        Float valorTotal = 0.0f;
        valorTotal = this.listaProductos.stream().map(producto -> producto.calcularValorInventario()).reduce(0.0f, Float::sum);
        return valorTotal;
    }
    private void imprimirDetalles(Producto productoEncontrado){
        String detallesProducto = "";
        detallesProducto = "Nombre del producto : "+productoEncontrado.getNombre() + "\nCantidad actual en el inventario : " + productoEncontrado.getCantidad()+ "\nPrecio unitario : " + productoEncontrado.getPrecio();
        System.out.println(detallesProducto);
    }
    public Integer getCantidadElementos(){
        Integer cantidad = this.listaProductos.size();   
        return cantidad;
    }
}
