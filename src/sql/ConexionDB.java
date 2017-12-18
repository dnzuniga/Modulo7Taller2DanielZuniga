/**
 * Resuelve el Taller número 4 del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, 2017-12-13 (yyyy-mm-dd)
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;

/*importación de las librerías correspondientes*/


/**
 * Clase que contiene el método necesario para conectar el programa a la base de
 * datos respectiva
 *
 * @author daniel Zúñiga Correa, 2017-12-13 (yyyy-mm-dd)
 */
public class ConexionDB {

    /**
     * Método que permite la conexión a una base de datos MySql con el schema ya
     * existente
     *
     * @param url corresponde al url de la base de datos
     * @param usuario corresponde al usuario
     * @param contrasena corresponde a la contraseña
     * @param schema corresponde al schema donde se incluyen las tablas
     * @return retorna un objeto clase Connection
     */
    public static Connection getConexionMySql(String url, String usuario, String contrasena, String schema) {
        Connection connection = null;
        url = "jdbc:mysql://" + url + "/" + schema;
        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, usuario, contrasena);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Sobreescritura del método que permite la conexión a una base de datos
     * MySql pero sin el schema para poder eliminar y volver a crear las tablas
     *
     * @param url corresponde al url de la base de datos
     * @param usuario corresponde al usuario
     * @param contrasena corresponde a la contraseña
     * @return retorna un objeto clase Connection
     */
    public static Connection getConexionMySql(String url, String usuario, String contrasena) {
        //String driverUrl = "jdbc:mysql://localhost/modulo6taller3danielzuniga";
        //usuario="root";
        //password="root";
        Connection connection = null;
        url = "jdbc:mysql://" + url;
        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, usuario, contrasena);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConexionOracle(String url, String puerto, String usuario, String contrasena) {
        Connection connection = null;
        url = "jdbc:oracle:thin:@" + url + ":" + puerto + ":XE";
        //usuario="root";
        //contrasena="root";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, usuario, contrasena);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
