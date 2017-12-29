/**
 * Resuelve el Taller número 4 del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".vv
 *
 * @autor Daniel Zúñiga Correa, 2017-12-13 (yyyy-mm-dd)
 */
package modelo;

/*importación de las librerías correspondientes*/
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import sql.ConexionDB;

/**
 * Clase que contiene los métodos propios necesarios para leer y poblar la base
 * de datos
 *
 * @author daniel Zúñiga Correa, 2017-12-07 (yyyy-mm-dd)
 */
public class Registro extends ConexionDB {

    /*Constantes publicas referidas a los parámetros necesarios para ejecutar 
    las conexiones a las bases de datos MySql y Oracle, locales y en la nuve*/
    private String url = "localhost";
    private String puerto = "1521";
    private String schema = "modulo6taller4danielzuniga";
    private String usuario = "root";
    private String contrasena = "root";
    public String tipoConexionActiva = "MySqlLocal";
    public static Statement stmt;
    public static Connection con;

    /**
     * Método para eliminar las tablas
     */
    public boolean eliminaTablas() {
        boolean exito = false;
        String[] queryMySql = new String[3];
        queryMySql[0] = "USE `modulo6taller4danielzuniga`";
        queryMySql[1] = "DROP TABLE IF EXISTS `pelicula`\n";
        queryMySql[2] = "DROP TABLE IF EXISTS `categoria`\n";
        String[] queryOracle = new String[2];
        queryOracle[0] = "DROP TABLE PELICULA\n";
        queryOracle[1] = "DROP TABLE CATEGORIA\n";
        try {

            if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                    || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                con = getConexionMySql(this.url, this.usuario,
                        this.contrasena, this.schema);
                stmt = con.createStatement();
                stmt.executeUpdate(queryMySql[0]);
                stmt.executeUpdate(queryMySql[1]);
                stmt.executeUpdate(queryMySql[2]);
            } else {
                con = this.getConexionOracle(this.url, this.puerto, this.usuario,
                        this.contrasena);
                stmt = con.createStatement();
                stmt.executeUpdate(queryOracle[0]);
                stmt.executeUpdate(queryOracle[1]);
            }
            stmt.close();
            con.close();
            exito = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            exito = false;
        }
        return exito;
    }

    /**
     * Método para construir las tablas
     *
     */
    public boolean creaTablas() {
        boolean exito = false;
        String[] queryMySql = new String[3];
        queryMySql[0] = "USE `modulo6taller4danielzuniga`";
        queryMySql[1] = "CREATE TABLE `categoria` (\n"
                + "  `id` int(11) NOT NULL,\n"
                + "  `descripcion` varchar(50) NOT NULL,\n"
                + "  PRIMARY KEY (`id`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
        queryMySql[2] = "CREATE TABLE `pelicula` (\n"
                + "  `codigo` int(11) NOT NULL,\n"
                + "  `precio` int(11) NOT NULL,\n"
                + "  `id_categoria` int(11) NOT NULL,\n"
                + "  `formato4k` varchar(1) NOT NULL,\n"
                + "  `nombre` varchar(50) NOT NULL,\n"
                + "  PRIMARY KEY (`codigo`),\n"
                + "  KEY `fk_categoria_idx` (`id_categoria`),\n"
                + "  CONSTRAINT `fk_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
        String[] queryOracle = new String[6];
        queryOracle[0] = "CREATE TABLE categoria ( id INTEGER NOT NULL, descripcion VARCHAR2(50) NOT NULL )\n";
        queryOracle[1] = " ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY ( id )\n";
        queryOracle[2] = "CREATE TABLE pelicula ( codigo INTEGER NOT NULL,"
                + " precio INTEGER NOT NULL, formato4k VARCHAR2(1) NOT NULL,"
                + " nombre VARCHAR2(50 CHAR) NOT NULL, id_categoria INTEGER NOT NULL )\n";
        queryOracle[3] = " ALTER TABLE pelicula ADD CONSTRAINT "
                + "pelicula_pk PRIMARY KEY ( codigo )\n";
        queryOracle[4] = " ALTER TABLE pelicula ADD CONSTRAINT "
                + "categoria_fk FOREIGN KEY ( id_categoria ) REFERENCES "
                + "categoria ( id )\n";
        queryOracle[5] = " COMMIT\n";
        try {
            if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                    || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                con = getConexionMySql(this.url, this.usuario,
                        this.contrasena, this.schema);
                stmt = con.createStatement();
                stmt.executeUpdate(queryMySql[0]);
                stmt.executeUpdate(queryMySql[1]);
                stmt.executeUpdate(queryMySql[2]);
            } else {
                con = this.getConexionOracle(this.url, this.puerto, this.usuario,
                        this.contrasena);
                stmt = con.createStatement();
                stmt.executeUpdate(queryOracle[0]);
                stmt.executeUpdate(queryOracle[1]);
                stmt.executeUpdate(queryOracle[2]);
                stmt.executeUpdate(queryOracle[3]);
                stmt.executeUpdate(queryOracle[4]);
                stmt.executeUpdate(queryOracle[5]);
            }
            stmt.close();
            con.close();
            exito = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            exito = false;
        }
        return exito;
    }

    /**
     * Método para obtener la cantidad de registros que contiene la tabla
     * categoria, y que fué necesario programar por separado del método
     * obtenerCategorias para evitar un error por existir demasiadas conexiones
     * a la base de datos
     *
     * @return retorna el total de registros existentes en la tabla pelicula
     */
    public int cantidadCategorias() {
        int cantidadRegistros = 0;
        String queryMySql = "SELECT count(*) as total FROM categoria";
        String queryOracle = "SELECT count(*) as total FROM categoria";
        try {
            PreparedStatement pstm;
            if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                    || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                pstm = this.getConexionMySql(this.url, this.usuario,
                        this.contrasena, this.schema)
                        .prepareStatement(queryMySql);
            } else {
                pstm = this.getConexionOracle(this.url, this.puerto,
                        this.usuario, this.contrasena)
                        .prepareStatement(queryOracle);
            }
            ResultSet res = pstm.executeQuery();
            res.next();
            cantidadRegistros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            if (cantidadRegistros > 0) {
                System.err.println(e.getMessage());
            }
        }
        return cantidadRegistros;
    }

    /**
     * Método para almacenar el contenido de la tabla categoria en un array de
     * objetos Categoria, para su posterior uso
     *
     * @return retorna un array de objetos Categoria
     */
    public Categoria[] obtenerCategorias() {
        Categoria[] todasCategorias;
        int cantidadRegistros = cantidadCategorias();
        todasCategorias = new Categoria[cantidadRegistros];
        String queryMySql = "SELECT * FROM categoria";
        String queryOracle = "SELECT * FROM categoria";
        try {
            PreparedStatement pstm;
            if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                    || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                pstm = this.getConexionMySql(this.url, this.usuario,
                        this.contrasena, this.schema)
                        .prepareStatement(queryMySql);
            } else {
                pstm = this.getConexionOracle(this.url, this.puerto,
                        this.usuario, this.contrasena)
                        .prepareStatement(queryOracle);
            }
            ResultSet res = pstm.executeQuery();
            int cont = 0;
            while (res.next()) {
                todasCategorias[cont] = new Categoria();
                todasCategorias[cont].setId(Integer.parseInt(res.getString("id")));
                todasCategorias[cont].setDescripcion(res.getString("descripcion"));
                cont++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return todasCategorias;
    }

    /**
     * Método para obtener la cantidad de registros que contiene la tabla
     * películas, y que fué necesario programar por separado del método
     * obtenerPeliculas para evitar un error por existir demasiadas conexiones a
     * la base de datos
     *
     * @return retorna el total de registros existentes en la tabla pelicula
     */
    public int cantidadPeliculas() {
        int cantidadRegistros = 0;
        String queryMySql = "SELECT count(*) as total FROM pelicula";
        String queryOracle = "SELECT count(*) as total FROM pelicula";
        try {
            PreparedStatement pstm;
            if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                    || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                pstm = this.getConexionMySql(url, usuario, contrasena,
                        schema)
                        .prepareStatement(queryMySql);
            } else {
                pstm = this.getConexionOracle(this.url, this.puerto,
                        this.usuario, this.contrasena)
                        .prepareStatement(queryOracle);
            }
            ResultSet res = pstm.executeQuery();
            res.next();
            cantidadRegistros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            if (cantidadRegistros > 0) {
                System.err.println(e.getMessage());
            }
        }
        return cantidadRegistros;
    }

    /**
     * Método para almacenar el contenido de la tabla pelicula en un array de
     * objetos Pelicula, para su posterior uso
     *
     * @return retorna un array de objetos Pelicula
     */
    public Pelicula[] obtenerPeliculas() {
        Pelicula[] todasPeliculas;
        int cantidadRegistros = cantidadPeliculas();
        todasPeliculas = new Pelicula[cantidadRegistros];
        String queryMySql = "SELECT * FROM pelicula";
        String queryOracle = "SELECT * FROM pelicula";
        try {
            PreparedStatement pstm;
            if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                    || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                pstm = this.getConexionMySql(url, usuario, contrasena,
                        schema).prepareStatement(queryMySql);
            } else {
                pstm = this.getConexionOracle(this.url, this.puerto,
                        this.usuario, this.contrasena).prepareStatement(queryOracle);
            }
            ResultSet res = pstm.executeQuery();
            int cont = 0;
            int id_categoria = 0;
            while (res.next()) {
                todasPeliculas[cont] = new Pelicula();
                todasPeliculas[cont].setCodigo(Integer.parseInt(res.getString("codigo")));
                todasPeliculas[cont].setPrecio(Integer.parseInt(res.getString("precio")));
                id_categoria = Integer.parseInt(res.getString("id_categoria"));
                todasPeliculas[cont].setCategoria(buscarCategoria(id_categoria));
                todasPeliculas[cont].setFormato4k((char) res.getString("formato4k").charAt(0));
                todasPeliculas[cont].setNombre(res.getString("nombre"));
                cont++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return todasPeliculas;
    }

    /**
     * Método para crear la matriz de películas a ingresar en el JTable de la
     * vista listar
     *
     * @return retorna un objeto DefaultTableModel que contiene una matriz de
     * películas
     */
    public DefaultTableModel mostrarPeliculas() {
        String texto = "";
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] nombreColumnas = {"Codigo", "Nombre", "Categoria", "Id Categoria",
            "Precio", "Formato"};
        Pelicula[] peliculasTemp = obtenerPeliculas();
        Object[][] dato = new String[peliculasTemp.length][6];
        int cont = 0;
        while (cont < peliculasTemp.length) {
            dato[cont][0] = String.valueOf(peliculasTemp[cont].getCodigo());
            dato[cont][1] = peliculasTemp[cont].getNombre();
            dato[cont][2] = peliculasTemp[cont].getCategoria().getDescripcion();
            dato[cont][3] = String.valueOf(peliculasTemp[cont].getCategoria().getId());
            dato[cont][4] = String.valueOf(peliculasTemp[cont].getPrecio());
            if (peliculasTemp[cont].getFormato4k() == 'S') {
                texto = "4K";
            } else {
                texto = "Normal";
            }
            dato[cont][5] = texto;

            cont++;
        }
        tablemodel.setDataVector(dato, nombreColumnas);
        return tablemodel;
    }

    /**
     * Método para crear la matriz de categoías a ingresar en el JTable de la
     * vista manejar categorías
     *
     * @return retorna un objeto DefaultTableModel que contiene una matriz de
     * categorías
     */
    public DefaultTableModel mostrarCategorias() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] nombreColumnas = {"Id Categoría", "Descripción Categoría"};
        Categoria[] categoriasTemp = obtenerCategorias();
        Object[][] dato = new String[categoriasTemp.length][2];
        int cont = 0;
        while (cont < categoriasTemp.length) {
            dato[cont][0] = String.valueOf(categoriasTemp[cont].getId());
            dato[cont][1] = categoriasTemp[cont].getDescripcion();
            cont++;
        }
        tablemodel.setDataVector(dato, nombreColumnas);
        return tablemodel;
    }

    /**
     * Método para insertar un objeto Categoria a la tabla categoria
     *
     * @param id corresponde al campo respectivo de la tabla
     * @param descripcion corresponde al campo respectivo de la tabla
     * @return retorna true si la inserción fué existosa
     */
    public boolean agregarCategoria(String descripcion) {
        int id = -1, total = cantidadCategorias();
        boolean acierto = false;
        if (!categoriaExiste(descripcion)) {
            if (total > 0) {
                Categoria[] categoriasTmp = obtenerCategorias();
                while (id <= total && !acierto) {
                    id++;
                    if (id == total) {
                        acierto = true;
                    } else {
                        if (categoriasTmp[id].getId() > id) {
                            acierto = true;
                        }
                    }
                }
            } else {
                id = 0;
            }
            String queryMySql = " INSERT INTO categoria(id"
                    + ",descripcion) "
                    + "VALUES ( '" + id + "','" + descripcion + "' )";
            String queryOracle = " INSERT INTO categoria(id"
                    + ",descripcion) "
                    + "VALUES ( '" + id + "','" + descripcion + "' )";
            try {
                PreparedStatement pstm;
                if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                        || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                    pstm = this.getConexionMySql(url, usuario, contrasena,
                            schema).prepareStatement(queryMySql);
                } else {
                    pstm = this.getConexionOracle(this.url, this.puerto,
                            this.usuario, this.contrasena).prepareStatement(queryOracle);
                }
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para insertar un objeto Pelicula a la tabla pelicula
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @param precio corresponde al campo respectivo de la tabla
     * @param id_categoria corresponde al campo respectivo de la tabla
     * @param desc_categoria corresponde al campo respectivo de la tabla
     * @param formato4k corresponde al campo respectivo de la tabla
     * @param nombre corresponde al campo respectivo de la tabla
     * @return retorna true si la inserción fué existosa
     */
    public boolean agregarPelicula(int codigo, int precio, String descripcion, char formato4k, String nombre) {
        Pelicula peliculaTemp = new Pelicula();
        Categoria categoriaTemp = new Categoria();

//      nos aseguramos que la categoria exista, y si no, la agregamos en la base
//      de datos
        agregarCategoria(descripcion);
        int id = buscarCategoria(descripcion).getId();
        categoriaTemp.setDescripcion(descripcion);
        categoriaTemp.setId(id);
//        verifica la inexistencia de una tupla en la tabla pelicula que 
//            contenga el mismo código, pues ese campo corresponde a la llave 
//            primaria de dicha tabla
        if (!peliculaExiste(codigo)) {
            if (peliculaTemp.setCodigo(codigo)
                    && peliculaTemp.setPrecio(precio)
                    && peliculaTemp.setCategoria(categoriaTemp)
                    && peliculaTemp.setFormato4k(formato4k)
                    && peliculaTemp.setNombre(nombre)) {
                String queryMySql = " INSERT INTO pelicula(codigo,precio,"
                        + "id_categoria, formato4k, nombre) "
                        + "VALUES ( '" + codigo + "','" + precio + "', '" + id
                        + "','" + formato4k + "', '" + nombre
                        + "' )";
                String queryOracle = " INSERT INTO pelicula(codigo,precio,"
                        + "id_categoria, formato4k, nombre) "
                        + "VALUES ( '" + codigo + "','" + precio + "', '" + id
                        + "','" + formato4k + "', '" + nombre
                        + "' )";
                try {
                    PreparedStatement pstm;
                    if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                            || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                        pstm = this.getConexionMySql(url, usuario, contrasena,
                                schema).prepareStatement(queryMySql);
                    } else {
                        pstm = this.getConexionOracle(this.url, this.puerto,
                                this.usuario, this.contrasena).prepareStatement(queryOracle);
                    }
                    pstm.execute();
                    pstm.close();
                    return true;
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para modificar una tupla contenida en la tabla categoria, método
     * que no se utiliza en la ejecución del taller, pero se agrega para un uso
     * eventual
     *
     * @param id corresponde al campo respectivo de la tabla
     * @param descripcion corresponde al campo respectivo de la tabla
     * @return retorna true si la modificación fué exitosa
     */
    public boolean modificarCategoria(int id, String descripcion) {
        Categoria categoriaTemp = new Categoria();
        /*verifica que el id de la tupla exista y que la variable descripcion 
        cumpla con las reglas de negocio establecidas para dicho atributo*/
        if (categoriaExiste(id) && categoriaTemp.setDescripcion(descripcion)) {
            String queryMySql = "UPDATE categoria SET"
                    + " descripcion='" + descripcion + "'"
                    + " WHERE id='" + id + "'";
            String queryOracle = "UPDATE categoria SET"
                    + " descripcion='" + descripcion + "'"
                    + " WHERE id='" + id + "'";
            try {
                PreparedStatement pstm;
                if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                        || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                    pstm = this.getConexionMySql(url, usuario, contrasena,
                            schema).prepareStatement(queryMySql);
                } else {
                    pstm = this.getConexionOracle(this.url, this.puerto,
                            this.usuario, this.contrasena).prepareStatement(queryOracle);
                }
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para modificar una tupla contenida en la tabla pelicula
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @param precio corresponde al campo respectivo de la tabla
     * @param id_categoria corresponde al campo respectivo de la tabla
     * @param desc_categoria corresponde al campo respectivo de la tabla
     * @param formato4k corresponde al campo respectivo de la tabla
     * @param nombre corresponde al campo respectivo de la tabla
     * @return retorna true si la modificación fué exitosa
     */
    public boolean modificarPelicula(int codigo, int precio,
            String descripcion, char formato4k, String nombre) {
        Pelicula peliculaTemp = new Pelicula();
        Categoria categoriaTemp = new Categoria();
//      nos aseguramos que la categoria exista, y si no, la agregamos en la base
//      de datos
        agregarCategoria(descripcion);
        int id = buscarCategoria(descripcion).getId();
        categoriaTemp.setDescripcion(descripcion);
        categoriaTemp.setId(id);
//        en cumplimiento de lo señalado en el punto número 6 del documento del 
//        taller, NO SE VERIFICA A ESTE NIVEL la existencia de una tupla en la 
//        tabla pelicula, pues ello se constanta al ejecutar el método 
//        buscarPelicula() contemplado por este alumno para la vista buscar
        if (peliculaTemp.setPrecio(precio)
                && peliculaTemp.setCategoria(categoriaTemp)
                && peliculaTemp.setFormato4k(formato4k)
                && peliculaTemp.setNombre(nombre)) {
            String queryMySql = "UPDATE pelicula SET precio='" + precio
                    + "', id_categoria='" + id + "', formato4k='" + formato4k
                    + "' , nombre='" + nombre
                    + "' WHERE codigo='" + codigo + "'";
            String queryOracle = "UPDATE pelicula SET precio='" + precio
                    + "', id_categoria='" + id + "', formato4k='" + formato4k
                    + "' , nombre='" + nombre
                    + "' WHERE codigo='" + codigo + "'";
            try {
                PreparedStatement pstm;
                if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                        || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                    pstm = this.getConexionMySql(url, usuario, contrasena,
                            schema).prepareStatement(queryMySql);
                } else {
                    pstm = this.getConexionOracle(this.url, this.puerto,
                            this.usuario, this.contrasena).prepareStatement(queryOracle);
                }
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para verificar la existencia de una película en la tabla
     * respectiva de la base de datos
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna true si existe a lo menos un registro con el código
     * buscado
     */
    public boolean peliculaExiste(int codigo) {
        boolean encontrado = false;
        Pelicula peliculaTemp = new Pelicula();
        Pelicula[] peliculasTemp;
        peliculasTemp = obtenerPeliculas();
        /*verifica que codigo cumpla con la regla de negocio respectiva*/
        if (peliculaTemp.setCodigo(codigo)) {
            for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
                if (peliculasTemp[cont].getCodigo() == codigo) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    /**
     * Sobrecarga del método para verificar la existencia de una película en la
     * tabla respectiva de la base de datos
     *
     * @param texto corresponde al texto a buscar en el nombre de la película
     * @return retorna el número de películas que contienen el texto en su
     * nombre
     */
    public int peliculaExiste(String texto) {
        int encuentros = 0;
        Pelicula[] peliculasTemp;
        peliculasTemp = obtenerPeliculas();
        for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
            if (peliculasTemp[cont].getNombre().toLowerCase()
                    .contains(texto.toLowerCase())) {
                encuentros++;
            }
        }
        return encuentros;
    }

    /**
     * Método para verificar la existencia de una categoría en una película en
     * la tabla respectiva de la base de datos
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna true si existe a lo menos un registro con el código
     * buscado
     */
    public boolean categoriaEnPelicula(int id) {
        boolean encontrado = false;
        Pelicula[] peliculasTemp;
        peliculasTemp = obtenerPeliculas();
        for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
            if (peliculasTemp[cont].getCategoria().getId() == id) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    /**
     * Método para verificar la existencia de una categoría en la tabla
     * respectiva de la base de datos
     *
     * @param id corresponde al campo respectivo de la tabla
     * @return retorna true si existe a lo menos un registro con el código
     * buscado
     */
    public boolean categoriaExiste(int id) {
        boolean encontrado = false;
        Categoria categoriaTemp = new Categoria();
        Categoria[] categoriasTemp;
        categoriasTemp = obtenerCategorias();
        /*verifica que id cumpla con la regla de negocio respectiva*/
        if (categoriaTemp.setId(id)) {
            for (int cont = 0; cont <= categoriasTemp.length - 1; cont++) {
                if (categoriasTemp[cont].getId() == id) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    /**
     * Sobrecarga del método para verificar la existencia de una categoría en la
     * tabla respectiva de la base de datos
     *
     * @param descripcion corresponde al campo respectivo de la tabla
     * @return retorna true si existe a lo menos un registro con el código
     * buscado
     */
    public boolean categoriaExiste(String descripcion) {
        boolean encontrado = false;
        Categoria categoriaTemp = new Categoria();
        Categoria[] categoriasTemp;
        categoriasTemp = obtenerCategorias();
//        verifica que descripcion cumpla con una posible regla de negocio
        if (categoriaTemp.setDescripcion(descripcion)) {
            for (int cont = 0; cont <= categoriasTemp.length - 1; cont++) {
                if (categoriasTemp[cont].getDescripcion().equalsIgnoreCase(descripcion)) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    /**
     * Método para buscar la existencia de una tupla que contenga determinado
     * código en la tabla pelicula
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna un objeto película
     */
    public Pelicula buscarPelicula(int codigo) {
        Pelicula peliculaTmp = new Pelicula();
        Pelicula[] peliculasTemp;
        peliculasTemp = obtenerPeliculas();
        /*verifica si existe un registro con el código ingresado*/
        if (peliculaExiste(codigo)) {
            for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
                if (peliculasTemp[cont].getCodigo() == codigo) {
                    peliculaTmp = peliculasTemp[cont];
                }
            }
        }
        return peliculaTmp;
    }

    /**
     * Método para buscar la o las tuplas que contengan determinado texto en el
     * campo nombre en la tabla pelicula
     *
     * @param texto corresponde al texto a buscar
     * @return retorna un array de películas que cumplen con el criterio
     */
    public Pelicula[] buscarPeliculasTexto(String texto) {
        int encontradas = peliculaExiste(texto), indice = -1;
        Pelicula[] peliculasEncontradas = new Pelicula[1];
        Pelicula[] peliculasTemp = obtenerPeliculas();
        /*verifica si existe un registro con el código ingresado*/
        if (encontradas > 0) {
            peliculasEncontradas = new Pelicula[encontradas];
            for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
                if (peliculasTemp[cont].getNombre().toLowerCase()
                        .contains(texto.toLowerCase())) {
                    peliculasEncontradas[++indice] = peliculasTemp[cont];
                }
            }

        }
        return peliculasEncontradas;
    }

    /**
     * Método para buscar la o las tuplas que contengan determinada categoría en
     * la tabla pelicula
     *
     * @param descripcion corresponde a la descripción de la categoría
     * @return retorna un array de películas que cumplen con el criterio
     */
    public Pelicula[] buscarPeliculasCategoria(String descripcion) {
        int encontradas = 0, indice = -1;
        Pelicula[] peliculasEncontradas = new Pelicula[1];
        Pelicula[] peliculasTemp;
        peliculasTemp = obtenerPeliculas();
        if (categoriaExiste(descripcion)) {
            for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
                if (peliculasTemp[cont].getCategoria().getDescripcion()
                        .toLowerCase().contains(descripcion.toLowerCase())) {
                    encontradas++;
                }
            }
            peliculasEncontradas = new Pelicula[encontradas];
            for (int cont = 0; cont <= peliculasTemp.length - 1; cont++) {
                if (peliculasTemp[cont].getCategoria().getDescripcion()
                        .toLowerCase().contains(descripcion.toLowerCase())) {
                    peliculasEncontradas[++indice] = peliculasTemp[cont];
                }
            }

        }
        return peliculasEncontradas;
    }

    /**
     * Método para buscar la existencia de una tupla que contenga determinado id
     * en la tabla categoria
     *
     * @param id corresponde al campo respectivo de la tabla
     * @return retorna true sila busqueda fué exitosa
     */
    public Categoria buscarCategoria(int id) {
        Categoria categoriaTmp = new Categoria();
        Categoria[] categoriasTemp;
        categoriasTemp = obtenerCategorias();
        /*verifica si existe un registro con el id ingresado*/
        if (categoriaExiste(id)) {
            for (int cont = 0; cont <= categoriasTemp.length - 1; cont++) {
                if (categoriasTemp[cont].getId() == id) {
                    categoriaTmp = categoriasTemp[cont];
                }
            }
        }
        return categoriaTmp;
    }

    /**
     * Sobrecarga del método para buscar la existencia de una tupla que contenga
     * determinado id en la tabla categoria, esta vez usando el tributo
     * descripción
     *
     * @param descripcion corresponde al campo respectivo de la tabla
     * @return retorna true sila busqueda fué exitosa
     */
    public Categoria buscarCategoria(String descripcion) {
        Categoria categoriaTmp = new Categoria();
        Categoria[] categoriasTemp;
        categoriasTemp = obtenerCategorias();
        if (categoriaExiste(descripcion)) {
            for (int cont = 0; cont <= categoriasTemp.length - 1; cont++) {
                if (categoriasTemp[cont].getDescripcion().equalsIgnoreCase(descripcion)) {
                    categoriaTmp = categoriasTemp[cont];
                }
            }
        }
        return categoriaTmp;
    }

    /**
     * Método para eliminar registros en la tabla pelicula
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna true si la eliminación fué exitosa
     */
    public boolean eliminarPelicula(int codigo) {
        boolean resultado = false;
        /*verifica si existe un registro con el código ingresado*/
        if (peliculaExiste(codigo)) {
            String queryMySql = " DELETE FROM pelicula WHERE codigo=" + codigo;
            String queryOracle = " DELETE FROM pelicula WHERE codigo=" + codigo;
            try {
                PreparedStatement pstm;
                if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                        || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                    pstm = this.getConexionMySql(url, usuario, contrasena,
                            schema).prepareStatement(queryMySql);
                } else {
                    pstm = this.getConexionOracle(this.url, this.puerto,
                            this.usuario, this.contrasena).prepareStatement(queryOracle);
                }
                pstm.execute();
                pstm.close();
                resultado = true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
        return resultado;
    }

    /**
     * Método para eliminar registros en la tabla categoría
     *
     * @param id corresponde al campo respectivo de la tabla
     * @return retorna true si la eliminación fué exitosa
     */
    public boolean eliminarCategoria(int id) {
        boolean resultado = false;
        if (categoriaExiste(id) && !categoriaEnPelicula(id)) {
            String queryMySql = " DELETE FROM categoria WHERE id=" + id;
            String queryOracle = " DELETE FROM categoria WHERE id=" + id;
            try {
                PreparedStatement pstm;
                if (tipoConexionActiva.equalsIgnoreCase("MySqlLocal")
                        || tipoConexionActiva.equalsIgnoreCase("MySqlNube")) {
                    pstm = this.getConexionMySql(url, usuario, contrasena,
                            schema).prepareStatement(queryMySql);
                } else {
                    pstm = this.getConexionOracle(this.url, this.puerto,
                            this.usuario, this.contrasena).prepareStatement(queryOracle);
                }
                pstm.execute();
                pstm.close();
                resultado = true;
//                reordenaCategorias(id);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return resultado;
    }

    /**
     * Método para reordenar los id de las categorías cuando una de ellas es
     * eliminada
     *
     * @param id corresponde al campo respectivo de la tabla
     * @return retorna verdadero luego del reorden
     */
    public boolean reordenaCategorias(int id) {
        Categoria[] categoriasTmp = new Categoria[cantidadCategorias()];
        for (int cont = id; cont < cantidadCategorias(); cont++) {
            categoriasTmp[cont].setId(cont - 1);
        }

        return true;
    }

    /**
     * Método para fijar el tipo de acceso a la Base de Datos
     *
     * @param tipo corresponde al tipo de Base de Datos requerida
     */
    public void fijaTipoConexionDB(String tipoConexionActiva) {
        switch (tipoConexionActiva) {
            case "OracleLocal":
                this.url = "localhost";
                this.puerto = "1521";
                this.schema = schema;
                this.usuario = "modulo6taller4danielzuniga";
                this.contrasena = "modulo6taller4danielzuniga";
                this.tipoConexionActiva = "OracleLocal";
                break;
            case "OracleNube":
                this.url = "localhost";
                this.puerto = "1521";
                this.schema = schema;
                this.usuario = "usuario01";
                this.contrasena = "usuario01";
                this.tipoConexionActiva = "OracleNube";
                break;
            case "MySqlLocal":
                this.url = "localhost";
                this.puerto = puerto;
                this.schema = "modulo6taller4danielzuniga";
                this.usuario = "root";
                this.contrasena = "root";
                this.tipoConexionActiva = "MySqlLocal";
                break;
            case "MySqlNube":
                this.url = "localhost";
                this.puerto = puerto;
                this.schema = "modulo6taller4danielzuniga";
                this.usuario = "root";
                this.contrasena = "root";
                this.tipoConexionActiva = "MySqlNube";
                break;
        }
    }

    /**
     * Método para listar los errores cometidos en las reglas de negocio,
     * mediante captura de excepciones
     *
     * @param codigo corresponde al atributo respectivo de la película
     *
     * @return retorna un string con la lista de las reglas de negocio
     * vulneradas
     */
    public String excepcionadorCodigo(int codigo) {
        String texto = "";
        Pelicula peliculaTmp = new Pelicula();
        try {
            peliculaTmp.setCodigo(codigo);
        } catch (IllegalArgumentException codigoErroneo) {
            texto += "Se ingresó un código menor a 10.000 o mayor a 99.999\n";
        }
        return texto;
    }

    /**
     * Método para listar los errores cometidos en las reglas de negocio,
     * mediante captura de excepciones
     *
     * @param precio corresponde al atributo respectivo de la película
     * @return retorna un string con la lista de las reglas de negocio
     * vulneradas
     */
    public String excepcionadorPrecio(int precio) {
        String texto = "";
        Pelicula peliculaTmp = new Pelicula();
        try {
            peliculaTmp.setPrecio(precio);
        } catch (IllegalArgumentException precioErroneo) {
            texto += "Se ingresó un precio menor o igual $1.000\n";
        }
        return texto;
    }

    /**
     * Método para listar los errores cometidos en las reglas de negocio,
     * mediante captura de excepciones
     *
     * @param codigo corresponde al atributo respectivo de la película
     * @param precio corresponde al atributo respectivo de la película
     * @param nombre corresponde al atributo respectivo de la película
     * @return retorna un string con la lista de las reglas de negocio
     * vulneradas
     */
    public String excepcionadorNombre(String nombre) {
        String texto = "";
        Pelicula peliculaTmp = new Pelicula();
        try {
            peliculaTmp.setNombre(nombre);
        } catch (IllegalArgumentException nombreErroneo) {
            texto += "Se ingresó un nombre de 2 o menos letras\n";
        }
        return texto;
    }
}
