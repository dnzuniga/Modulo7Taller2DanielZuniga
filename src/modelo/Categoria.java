/**
 * Resuelve el Taller número 4 del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".vv
 *
 * @autor Daniel Zúñiga Correa, 2017-12-13 (yyyy-mm-dd)
 */
package modelo;

/**
 * Clase para los futuros objetos Categoria, que contendrán las tuplas de la
 * tabla categoria de la base de datos, contiene las reglas de negocio
 * correspondientes
 *
 */
public class Categoria {

//    atributos de clase
    private int id = 0;
    private String descripcion = "";

//    constructor sin parámetros
    public Categoria() {
    }

//    constructor con parámetros
    public Categoria(int id, String descripcion) {
        setId(id);
        setDescripcion(descripcion);
    }

//    accesadores y mutadores
    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        this.id = id;
        return true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return true;
    }
}
