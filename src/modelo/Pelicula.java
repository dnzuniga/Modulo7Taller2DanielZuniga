/**
 * Resuelve el Taller número 4 del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".vv
 *
 * @autor Daniel Zúñiga Correa, 2017-12-13 (yyyy-mm-dd)
 */
package modelo;

/**
 * Clase para los futuros objetos Pelicula, que contendrán las tuplas de la
 * tabla pelicula de la base de datos, contiene las reglas de negocio
 * correspondientes
 *
 */
public class Pelicula {

//    atributos de clase
    private int codigo;
    private int precio;
    private Categoria categoria;
    private char formato4k;
    private String nombre;

//    constructor sin parámetros
    public Pelicula() {
    }

//    constructor con parámetros
    public Pelicula(int codigo, int precio, Categoria categoria, char formato4k, String nombre) {
        setCodigo(codigo);
        setPrecio(precio);
        setCategoria(categoria);
        this.formato4k = formato4k;
        this.nombre = nombre;
    }

    /*accesadores y mutadores que contienen las reglas de negocio respectivas*/
    public int getCodigo() {
        return codigo;
    }

    public boolean setCodigo(int codigo) throws IllegalArgumentException {
        if (codigo >= 10000 && codigo <= 99999) {
            this.codigo = codigo;
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getPrecio() {
        return precio;
    }

    public boolean setPrecio(int precio) throws IllegalArgumentException {
        if (precio > 1000) {
            this.precio = precio;
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return true;
    }

    public char getFormato4k() {
        return formato4k;
    }

    public boolean setFormato4k(char formato4k) {
        if (formato4k == 'S' || formato4k == 'N') {
            this.formato4k = formato4k;
            return true;
        } else {
            return false;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) throws IllegalArgumentException {
        if (nombre.length() > 2) {
            this.nombre = nombre;
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
