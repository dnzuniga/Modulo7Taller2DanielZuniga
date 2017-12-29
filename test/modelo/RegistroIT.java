/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Daniel
 */
public class RegistroIT {

    public RegistroIT() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Iniciando las pruebas...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Todas las pruebas han finalizado ...");
    }

    @Before
    public void setUp() {
        System.out.println("Iniciando el caso de prueba ...");
    }

    @After
    public void tearDown() {
        System.out.println("Caso de prueba finalizado ...");
    }

    /**
     * Test of eliminaTablas method, of class Registro.
     */
    @Test
    @Ignore
    public void testEliminaTablas() {
        System.out.println("eliminaTablas");
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.eliminaTablas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creaTablas method, of class Registro.
     */
    @Test
    @Ignore
    public void testCreaTablas() {
        System.out.println("creaTablas");
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.creaTablas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cantidadCategorias method, of class Registro.
     */
    @Test
    @Ignore
    public void testCantidadCategorias() {
        System.out.println("cantidadCategorias");
        Registro instance = new Registro();
        int expResult = 0;
        int result = instance.cantidadCategorias();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerCategorias method, of class Registro.
     */
    @Test
    @Ignore
    public void testObtenerCategorias() {
        System.out.println("obtenerCategorias");
        Registro instance = new Registro();
        Categoria[] expResult = null;
        Categoria[] result = instance.obtenerCategorias();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cantidadPeliculas method, of class Registro.
     */
    @Test
    @Ignore
    public void testCantidadPeliculas() {
        System.out.println("cantidadPeliculas");
        Registro instance = new Registro();
        int expResult = 0;
        int result = instance.cantidadPeliculas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPeliculas method, of class Registro.
     */
    @Test
    @Ignore
    public void testObtenerPeliculas() {
        System.out.println("obtenerPeliculas");
        Registro instance = new Registro();
        Pelicula[] expResult = null;
        Pelicula[] result = instance.obtenerPeliculas();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarPeliculas method, of class Registro.
     */
    @Test
    @Ignore
    public void testMostrarPeliculas() {
        System.out.println("mostrarPeliculas");
        Registro instance = new Registro();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.mostrarPeliculas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarCategorias method, of class Registro.
     */
    @Test
    @Ignore
    public void testMostrarCategorias() {
        System.out.println("mostrarCategorias");
        Registro instance = new Registro();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.mostrarCategorias();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarCategoria method, of class Registro.
     */
    @Test
    @Ignore
    public void testAgregarCategoria() {
        System.out.println("agregarCategoria");
        String descripcion = "Romance";
        Registro instance = new Registro();
        instance.fijaTipoConexionDB("MySqlLocal");
        boolean expResult = true;
        boolean result = instance.agregarCategoria(descripcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarPelicula method, of class Registro.
     */
    @Test
    @Ignore
    public void testAgregarPelicula() {
        System.out.println("agregarPelicula");
        int codigo = 15000;
        int precio = 4000;
        String descripcion = "Acción";
        char formato4k = 'S';
        String nombre = "Película de prueba para Taller 2";
        Registro instance = new Registro();
        instance.fijaTipoConexionDB("MySqlLocal");
        boolean expResult = true;
        boolean result = instance.agregarPelicula(codigo, precio, descripcion,
                formato4k, nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarCategoria method, of class Registro.
     */
    @Test
    @Ignore
    public void testModificarCategoria() {
        System.out.println("modificarCategoria");
        int id = 0;
        String descripcion = "";
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.modificarCategoria(id, descripcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPelicula method, of class Registro.
     */
    @Test
//    @Ignore
    public void testModificarPelicula() {
        String descripcion = "Drama";
        System.out.println("modificarPelicula con descripcion: " + descripcion);
        int codigo = 15000;
        int precio = 4000;
        char formato4k = 'S';
        String nombre = "Película de prueba para Taller 2";
        Registro instance = new Registro();
        instance.fijaTipoConexionDB("MySqlLocal");
        boolean expResult = true;
        boolean result = instance.modificarPelicula(codigo, precio, descripcion,
                 formato4k, nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of peliculaExiste method, of class Registro.
     */
    @Test
    @Ignore
    public void testPeliculaExiste_int() {
        System.out.println("peliculaExiste");
        int codigo = 0;
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.peliculaExiste(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of peliculaExiste method, of class Registro.
     */
    @Test
    @Ignore
    public void testPeliculaExiste_String() {
        System.out.println("peliculaExiste");
        String texto = "";
        Registro instance = new Registro();
        int expResult = 0;
        int result = instance.peliculaExiste(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoriaEnPelicula method, of class Registro.
     */
    @Test
    @Ignore
    public void testCategoriaEnPelicula() {
        System.out.println("categoriaEnPelicula");
        int id = 0;
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.categoriaEnPelicula(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoriaExiste method, of class Registro.
     */
    @Test
    @Ignore
    public void testCategoriaExiste_int() {
        System.out.println("categoriaExiste");
        int id = 0;
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.categoriaExiste(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoriaExiste method, of class Registro.
     */
    @Test
    @Ignore
    public void testCategoriaExiste_String() {
        System.out.println("categoriaExiste");
        String descripcion = "";
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.categoriaExiste(descripcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPelicula method, of class Registro.
     */
    @Test
    @Ignore
    public void testBuscarPelicula() {
        System.out.println("buscarPelicula");
        int codigo = 0;
        Registro instance = new Registro();
        Pelicula expResult = null;
        Pelicula result = instance.buscarPelicula(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPeliculasTexto method, of class Registro.
     */
    @Test
    @Ignore
    public void testBuscarPeliculasTexto() {
        String texto = "película", texto2 = "";
        System.out.println("buscarPeliculasTexto con texto: " + texto);
        Registro instance = new Registro();
        instance.fijaTipoConexionDB("MySqlLocal");
        Pelicula[] expResult = null;
        Pelicula[] result = instance.buscarPeliculasTexto(texto);
        for (int cont = 0; cont < result.length; cont++) {
            texto2 += "Código: " + result[cont].getCodigo()
                    + "\n Nombre: " + result[cont].getNombre()
                    + "\n Categoría: " + result[cont].getCategoria()
                            .getDescripcion()
                    + " Id Categoría: " + result[cont].getCategoria().getId()
                    + "\n Precio: " + result[cont].getPrecio()
                    + " Formato: " + result[cont].getFormato4k()
                    + "\n";
        }
        System.out.println(texto2);
//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPeliculasCategoria method, of class Registro.
     */
    @Test
    @Ignore
    public void testBuscarPeliculasCategoria() {
        String descripcion = "comedia", texto = "";
        System.out.println("buscarPeliculasCategoria con descripcion: "
                + descripcion);
        Registro instance = new Registro();
        instance.fijaTipoConexionDB("MySqlLocal");
//        Pelicula[] expResult = null;
        Pelicula[] result = instance.buscarPeliculasCategoria(descripcion);
        for (int cont = 0; cont < result.length; cont++) {
            texto += "Código: " + result[cont].getCodigo()
                    + "\n Nombre: " + result[cont].getNombre()
                    + "\n Categoría: " + result[cont].getCategoria()
                            .getDescripcion()
                    + " Id Categoría: " + result[cont].getCategoria().getId()
                    + "\n Precio: " + result[cont].getPrecio()
                    + " Formato: " + result[cont].getFormato4k()
                    + "\n";
        }
        System.out.println(texto);

//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarCategoria method, of class Registro.
     */
    @Test
    @Ignore
    public void testBuscarCategoria_int() {
        System.out.println("buscarCategoria");
        int id = 0;
        Registro instance = new Registro();
        Categoria expResult = null;
        Categoria result = instance.buscarCategoria(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarCategoria method, of class Registro.
     */
    @Test
    @Ignore
    public void testBuscarCategoria_String() {
        System.out.println("buscarCategoria");
        String descripcion = "";
        Registro instance = new Registro();
        Categoria expResult = null;
        Categoria result = instance.buscarCategoria(descripcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarPelicula method, of class Registro.
     */
    @Test
    @Ignore
    public void testEliminarPelicula() {
        System.out.println("eliminarPelicula");
        int codigo = 0;
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.eliminarPelicula(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCategoria method, of class Registro.
     */
    @Test
    @Ignore
    public void testEliminarCategoria() {
        System.out.println("eliminarCategoria");
        int id = 0;
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.eliminarCategoria(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reordenaCategorias method, of class Registro.
     */
    @Test
    @Ignore
    public void testReordenaCategorias() {
        System.out.println("reordenaCategorias");
        int id = 0;
        Registro instance = new Registro();
        boolean expResult = false;
        boolean result = instance.reordenaCategorias(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fijaTipoConexionDB method, of class Registro.
     */
    @Test
    @Ignore
    public void testFijaTipoConexionDB() {
        System.out.println("fijaTipoConexionDB");
        String tipoConexionActiva = "";
        Registro instance = new Registro();
        instance.fijaTipoConexionDB(tipoConexionActiva);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excepcionadorCodigo method, of class Registro.
     */
    @Test
    @Ignore
    public void testExcepcionadorCodigo() {
        System.out.println("excepcionadorCodigo");
        int codigo = 0;
        Registro instance = new Registro();
        String expResult = "";
        String result = instance.excepcionadorCodigo(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excepcionadorPrecio method, of class Registro.
     */
    @Test
    @Ignore
    public void testExcepcionadorPrecio() {
        System.out.println("excepcionadorPrecio");
        int precio = 0;
        Registro instance = new Registro();
        String expResult = "";
        String result = instance.excepcionadorPrecio(precio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excepcionadorNombre method, of class Registro.
     */
    @Test
    @Ignore
    public void testExcepcionadorNombre() {
        System.out.println("excepcionadorNombre");
        String nombre = "";
        Registro instance = new Registro();
        String expResult = "";
        String result = instance.excepcionadorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
