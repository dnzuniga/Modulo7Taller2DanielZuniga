/**
 * Resuelve el Taller número 4 del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, 2017-12-13 (yyyy-mm-dd)
 */
package controlador;

//importación de las librerías correspondientes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 * Clase Controlador que contiene las clases correspondientes al "Controlador"
 * del patrón MVC requerido en el documento del Taller. Implementa los Listener
 * respectivos
 *
 * @author Daniel Zúñiga Correa
 */
public class ControladorVideoBuster implements ActionListener, MouseListener {

//    declara e inicializa las vistas
    private MenuPrincipal vistaPrincipal = new MenuPrincipal();
    private MenuAgregarPelicula vistaAgregarPelicula = new MenuAgregarPelicula();
    private MenuEliminarPelicula vistaEliminarPelicula = new MenuEliminarPelicula();
    private MenuBuscarPelicula vistaBuscarPelicula = new MenuBuscarPelicula();
    private MenuModificarPellicula vistaModificarPelicula = new MenuModificarPellicula();
    private MenuListarPelicula vistaListarPelicula = new MenuListarPelicula();
    private MenuModificarPellicula vistaModificarCategoria = new MenuModificarPellicula();
    private MenuManejarCategoria vistaManejarCategoria = new MenuManejarCategoria();

//    declara e inicializa el objeto que contiene los métodos para leer y
//    grabar información en la base de datos
    public Registro registro = new Registro();

//    Constructor sin parámetros
    public ControladorVideoBuster() {
    }

    /**
     * Método para inicializar las vistas e implementar los Listeners
     */
    public void iniciar() {
//        inicializa la estructura de vistas
        this.vistaPrincipal.setLocationRelativeTo(null);
        this.vistaPrincipal.setVisible(true);

//        utilización de Listener para los ítems correspondientes al JMenú
//        de la vista principal     
        this.vistaPrincipal.mnuArchivoSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        this.vistaPrincipal.mnuOpcionesAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //despliega vista agregar
                vistaPrincipal.setVisible(false);
                limpiarAgregar();
                if (registro.cantidadCategorias() == 0) {
                    despliegaVistaManejarCategorias();
                } else {
                    vistaAgregarPelicula.setLocationRelativeTo(null);
                    llenarCategorias("vistaAgregarPelicula", 0);
                    vistaAgregarPelicula.setVisible(true);
                }

            }
        });
        this.vistaPrincipal.mnuOpcionesEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //desliega vista eliminar
                vistaEliminarPelicula.setLocationRelativeTo(null);
                vistaEliminarPelicula.txtCodigo.setText("0");
                vistaEliminarPelicula.setVisible(true);
            }
        });
        this.vistaPrincipal.mnuOpcionesModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //desliega vista buscar para modificar
                vistaBuscarPelicula.setLocationRelativeTo(null);
                vistaBuscarPelicula.txtCodigo.setText("0");
                vistaBuscarPelicula.setVisible(true);
            }
        });
        this.vistaPrincipal.mnuOpcionesListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (registro.cantidadPeliculas() > 0) {
                    //despliega vista listar películas
                    vistaListarPelicula.setLocationRelativeTo(null);
                    vistaListarPelicula.tbListar.setModel(registro.mostrarPeliculas());
                    llenarCategorias("vistaListarPelicula", 0);
                    vistaListarPelicula.setVisible(true);
                    asignaValoresTabla("vistaListarPelicula", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "No se han ingresado"
                            + " películas al registro");
                }
            }
        });
        this.vistaPrincipal.mnuOpcionesManejar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //despliega vista manejar categoría
                limpiarManejar();
                if (registro.cantidadCategorias() == 0) {
                    noCategorias();
                    vistaManejarCategoria.txtIdCategoria.setText("0");
                } else {
                    vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
                    vistaManejarCategoria.txtIdCategoria.setText(String.valueOf(vistaManejarCategoria.tbListar.getValueAt(0, 0)));
                    vistaManejarCategoria.txtDescripcionCategoria.setText(String.valueOf(vistaManejarCategoria.tbListar.getValueAt(0, 1)));
                    activarBotonesManejar();
                    asignaValoresTabla("vistaManejarCategoria", 0);
                }
                vistaManejarCategoria.setLocationRelativeTo(null);
                vistaManejarCategoria.setVisible(true);

            }
        });
        this.vistaPrincipal.mnuConexionMySqlLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                registro.fijaTipoConexionDB("MySqlLocal");
                JOptionPane.showMessageDialog(null, "Se modificaron las variables"
                        + "para conexión a Base de Datos MySql, a nivel local");
            }
        });
        this.vistaPrincipal.mnuConexionMySqlNube.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                registro.fijaTipoConexionDB("MySqlNube");
                JOptionPane.showMessageDialog(null, "Se modificaron las variables"
                        + "para conexión a Base de Datos MySql, en la nuve");
            }
        });
        this.vistaPrincipal.mnuConexionOracleLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                registro.fijaTipoConexionDB("OracleLocal");
                JOptionPane.showMessageDialog(null, "Se modificaron las variables"
                        + "para conexión a Base de Datos Oracle, a nivel local");
            }
        });
        this.vistaPrincipal.mnuConexionOracleNube.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                registro.fijaTipoConexionDB("OracleNube");
                JOptionPane.showMessageDialog(null, "Se modificaron las variables"
                        + "para conexión a Base de Datos Oracle, en la nuve");
            }
        });
        this.vistaPrincipal.mnuCreacionTablas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (pideConfirmacion("ESTA ACCION ELIMINARA TODOS LOS DATOS EXISTENTES"
                        + " EN LAS TABLAS") == JOptionPane.YES_OPTION) {
                    registro.eliminaTablas();
                    if (registro.creaTablas()) {
                        JOptionPane.showMessageDialog(null, "Las tablas fueron"
                                + " eliminadas y vueltas a crea correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en el reinicio de"
                                + " las tablas.");
                    }
                }
            }
        });

//        implementa Listener para botones en vista agregar película
        this.vistaAgregarPelicula.btnAgregarAgregar.setActionCommand("btnAgregarAgregar");
        this.vistaAgregarPelicula.btnAgregarAgregar.addActionListener(this);
        this.vistaAgregarPelicula.btnCerrarAgregar.setActionCommand("btnCerrarAgregar");
        this.vistaAgregarPelicula.btnCerrarAgregar.addActionListener(this);

//        implementa Listener para botón en vista eliminar película
        this.vistaEliminarPelicula.btnEliminarEliminar.setActionCommand("btnEliminarEliminar");
        this.vistaEliminarPelicula.btnEliminarEliminar.addActionListener(this);

//        implementa Listener para botones en vista buscar película
        this.vistaBuscarPelicula.btnBuscarBuscar.setActionCommand("btnBuscarBuscar");
        this.vistaBuscarPelicula.btnBuscarBuscar.addActionListener(this);

//        implementa Listener para botones en vista modificar película
        this.vistaModificarPelicula.btnModificarModificar.setActionCommand("btnModificarModificar");
        this.vistaModificarPelicula.btnModificarModificar.addActionListener(this);
        this.vistaModificarPelicula.btnNuevaBusquedaModificar.setActionCommand("btnNuevaBusquedaModificar");
        this.vistaModificarPelicula.btnNuevaBusquedaModificar.addActionListener(this);
        this.vistaModificarPelicula.btnCerrarModificar.setActionCommand("btnCerrarModificar");
        this.vistaModificarPelicula.btnCerrarModificar.addActionListener(this);

//        implementa Listener para botones y mouse click en vista listar película
        this.vistaListarPelicula.btnCerrarListar.setActionCommand("btnCerrarListar");
        this.vistaListarPelicula.btnCerrarListar.addActionListener(this);
        this.vistaListarPelicula.btnRefrescarListar.setActionCommand("btnRefrescarListar");
        this.vistaListarPelicula.btnRefrescarListar.addActionListener(this);
        this.vistaListarPelicula.btnModificarListar.setActionCommand("btnModificarListar");
        this.vistaListarPelicula.btnModificarListar.addActionListener(this);
        this.vistaListarPelicula.tbListar.addMouseListener(this);

//        implementa Listener para botones y mouse click en vista manejar de categorias
        this.vistaManejarCategoria.btnSalirManejar.setActionCommand("btnCerrarManejar");
        this.vistaManejarCategoria.btnSalirManejar.addActionListener(this);
        this.vistaManejarCategoria.btnRefrescarManejar.setActionCommand("btnRefrescarManejar");
        this.vistaManejarCategoria.btnRefrescarManejar.addActionListener(this);
        this.vistaManejarCategoria.btnAgregarManejar.setActionCommand("btnAgregarManejar");
        this.vistaManejarCategoria.btnAgregarManejar.addActionListener(this);
        this.vistaManejarCategoria.btnModificarManejar.setActionCommand("btnModificarManejar");
        this.vistaManejarCategoria.btnModificarManejar.addActionListener(this);
        this.vistaManejarCategoria.btnEliminarManejar.setActionCommand("btnEliminarManejar");
        this.vistaManejarCategoria.btnEliminarManejar.addActionListener(this);
        this.vistaManejarCategoria.btnLimpiarManejar.setActionCommand("btnLimpiarManejar");
        this.vistaManejarCategoria.btnLimpiarManejar.addActionListener(this);
        this.vistaManejarCategoria.tbListar.addMouseListener(this);
    }

    /**
     * Método para controlar las ocurrencia de seleccionar un botón en diversas
     * vistas
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override

    public void actionPerformed(ActionEvent e) {
        /*maneja que botón se precionó*/
        switch (e.getActionCommand()) {
            case "btnAgregarAgregar":
//                asigna a variable el radio button escogido
                if (validaNumerosAgregar()) {
                    char formato4k;
                    if (vistaAgregarPelicula.opt4k.isSelected()) {
                        formato4k = 'S';
                    } else {
                        formato4k = 'N';
                    }
//                    verifica la regla de negocio correspondiente a que el 
//                    código de la película a ingresar no exista
                    if (!registro.peliculaExiste(
                            Integer.parseInt(vistaAgregarPelicula.txtCodigo.getText()))) {
//                        verifica que los datos a ingresar cumplan con las 
//                        reglas de negocio de cada uno de ellos, y de ser así la
//                        ingresa a la base de datosa
                        if (registro.agregarPelicula(Integer.parseInt(vistaAgregarPelicula.txtCodigo.getText()),
                                Integer.parseInt(vistaAgregarPelicula.txtPrecio.getText()),
                                vistaAgregarPelicula.cboCategorias.getSelectedItem().toString(),
                                formato4k, vistaAgregarPelicula.txtNombre.getText())) {
                            JOptionPane.showMessageDialog(null, "Película agregada correctamente.\n");
                            limpiarAgregar();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se agregó "
                                    + "la película por los siguientes errores cometidos en las reglas de negocio:\n"
                                    + registro.excepcionadorCodigo(Integer.parseInt(vistaAgregarPelicula.txtCodigo.getText()))
                                    + registro.excepcionadorPrecio(Integer.parseInt(vistaAgregarPelicula.txtPrecio.getText()))
                                    + registro.excepcionadorNombre(vistaAgregarPelicula.txtNombre.getText()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe una pelicula con el código"
                                + vistaAgregarPelicula.txtCodigo.getText() + "\n"
                                + "No se ingreso la película al registro.\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en el formato "
                            + "de los campos numéricos");
                }
                break;
            case "btnEliminarEliminar":
//                verifica que el código que se desea eliminar sea convertible 
//                a número
                if (esEntero(vistaEliminarPelicula.txtCodigo.getText())) {
//                    verifica que la película a eliminar exista en la base de 
//                    datos
                    if (registro.peliculaExiste(
                            Integer.parseInt(vistaEliminarPelicula.txtCodigo.getText()))) {
//                       dada la importancia de la eliminación, solicita 
//                       verificación previa
                        if (pideConfirmacion("ESTA ACCION ELIMINARA"
                                + " PERMANENTEMENTE LA PELICULA") == JOptionPane.YES_OPTION) {
//                           elimina la película de la base de datos y lo 
//                           verifica
                            if (registro.eliminarPelicula(
                                    Integer.parseInt(vistaEliminarPelicula.txtCodigo.getText()))) {
                                JOptionPane.showMessageDialog(null, "La película "
                                        + "código " + vistaEliminarPelicula.txtCodigo.getText()
                                        + " fué eliminada correctamente.\n");
                                vistaEliminarPelicula.txtCodigo.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo "
                                        + "eliminar la película\n");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La Película código "
                                + vistaEliminarPelicula.txtCodigo.getText() + " no existe en el registro.\n"
                                + "La Película no fué eliminada.");
                    }
                } else {
                }
                break;
            case "btnBuscarBuscar":
//               verifica que el código que se desea buscar sea convertible 
//               a número
                if (esEntero(vistaBuscarPelicula.txtCodigo.getText())) {
//                   verifica que la película a eliminar exista en la base de 
//                   datos
                    if (registro.peliculaExiste(
                            Integer.parseInt(vistaBuscarPelicula.txtCodigo.getText()))) {
                        vistaModificarPelicula.setLocationRelativeTo(null);
//                        llena los campos correspondientes de la vista 
//                        modificar, con los datos correspondientes a la película
//                        a modificar
                        llenarCategorias("vistaModificarPelicula", 0);
                        agregarDatosModificar(Integer.parseInt(vistaBuscarPelicula.txtCodigo.getText()));
                        vistaModificarPelicula.setVisible(true);
                        vistaBuscarPelicula.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "La Película código "
                                + vistaBuscarPelicula.txtCodigo.getText() + " no existe en el registro.\n");
                    }
                }
                break;
            case "btnCerrarAgregar":
                vistaPrincipal.setLocationRelativeTo(
                        null);
                vistaPrincipal.setVisible(
                        true);
                vistaAgregarPelicula.setVisible(
                        false);
//                limpia los campos de la vista agregar
                limpiarAgregar();
                break;
            case "btnCerrarModificar":
                vistaPrincipal.setLocationRelativeTo(
                        null);
                vistaPrincipal.setVisible(
                        true);
                vistaModificarPelicula.setVisible(
                        false);
//                limpia los campos de la vista modificar
                limpiarModificar();
                break;
            case "btnCerrarListar":
                vistaPrincipal.setLocationRelativeTo(
                        null);
                vistaPrincipal.setVisible(
                        true);
                vistaListarPelicula.setVisible(
                        false);
//                limpia los campos de la vista listar
                limpiarListar();
                break;
            case "btnCerrarManejar":
                vistaPrincipal.setLocationRelativeTo(
                        null);
                vistaPrincipal.setVisible(
                        true);
                vistaManejarCategoria.setVisible(
                        false);
//                limpia los campos de la vista listar
                limpiarManejar();
                break;
//            refresca los datos de la JTable contenida en la vista listar,
//            pensando en un acceso multiusuario a la base de datos
            case "btnRefrescarListar":
//                verifica que existan películas en la base de datos
                if (registro.cantidadPeliculas() > 0) {
                    this.vistaListarPelicula.tbListar.setModel(registro.mostrarPeliculas());
//                    limpia los campos de la vista listar
                    limpiarListar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se han ingresado"
                            + " películas al registro");
                }
                break;
//            refresca los datos de la JTable contenida en la vista manejar categoría,
//            pensando en un acceso multiusuario a la base de datos
            case "btnRefrescarManejar":
//                verifica que existan películas en la base de datos
                limpiarManejar();
                if (registro.cantidadCategorias() > 0) {
                    this.vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
//                    limpia los campos de la vista manejar categorías
                } else {
                    JOptionPane.showMessageDialog(null, "No hay categorias ingresadas.\n"
                            + "Ingrese la descripción de la primera categoría,\n"
                            + "presione el boton 'Agregar'.");
                    desactivarBotonesManejar();
                }
                break;
            case "btnLimpiarManejar":
                vistaManejarCategoria.txtDescripcionCategoria.setText("");
                break;
            case "btnNuevaBusquedaModificar":
//                despliega la vista de búsqueda
                vistaModificarPelicula.setVisible(
                        false);
//                limpia los campos de la vista modificar
                limpiarModificar();
//                inicializa el valor del campo de la vista buscar
                vistaBuscarPelicula.txtCodigo.setText(
                        "0");
                vistaBuscarPelicula.setLocationRelativeTo(
                        null);
                vistaBuscarPelicula.setVisible(
                        true);
                break;
//          botón contenido en la vista modificar
            case "btnModificarModificar":
//                verifica que no se ingresen datos no numericos en los 
//                    campos respectivos
                if (validaNumerosModificar()) {
                    char formato4k;
                    if (vistaModificarPelicula.opt4k.isSelected()) {
                        formato4k = 'S';
                    } else {
                        formato4k = 'N';
                    }
//                  modifica los datos de la película en la base de datos,
//                  salvo el código
                    if (registro.modificarPelicula(Integer.parseInt(vistaModificarPelicula.txtCodigo.getText()),
                            Integer.parseInt(vistaModificarPelicula.txtPrecio.getText()),
                            vistaModificarPelicula.cboCategorias.getSelectedItem().toString(),
                            formato4k, vistaModificarPelicula.txtNombre.getText())) {
                        JOptionPane.showMessageDialog(null, "Película modificada correctamente.\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se modificó "
                                + "la película por los siguientes errores cometidos en las reglas de negocio:\n"
                                + registro.excepcionadorCodigo(Integer.parseInt(vistaModificarPelicula.txtCodigo.getText()))
                                + registro.excepcionadorPrecio(Integer.parseInt(vistaModificarPelicula.txtPrecio.getText()))
                                + registro.excepcionadorNombre(vistaModificarPelicula.txtNombre.getText()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en el formato "
                            + "de los campos numéricos");
                }

                break;
//            botón contenido en la vista listar
            case "btnModificarListar":
//              verifica que no se ingresen datos no numericos en los campos
//              Frespectivos
                if (validaNumerosListar()) {
                    char formato4k;
                    if (vistaListarPelicula.opt4k.isSelected()) {
                        formato4k = 'S';
                    } else {
                        formato4k = 'N';
                    }
//                  modifica los datos de la película en la base de datos,
//                  salvo el código
                    if (registro.modificarPelicula(Integer.parseInt(vistaListarPelicula.txtCodigo.getText()),
                            Integer.parseInt(vistaListarPelicula.txtPrecio.getText()),
                            vistaListarPelicula.cboCategorias.getSelectedItem().toString(),
                            formato4k, vistaListarPelicula.txtNombre.getText())) {
                        this.vistaListarPelicula.tbListar.setModel(registro.mostrarPeliculas());
                        limpiarListar();
                        JOptionPane.showMessageDialog(null, "Película modificada correctamente.\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se modificó "
                                + "la película por los siguientes errores cometidos en las reglas de negocio:\n"
                                + registro.excepcionadorCodigo(Integer.parseInt(vistaListarPelicula.txtCodigo.getText()))
                                + registro.excepcionadorPrecio(Integer.parseInt(vistaListarPelicula.txtPrecio.getText()))
                                + registro.excepcionadorNombre(vistaListarPelicula.txtNombre.getText()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en el formato "
                            + "de los campos numéricos");
                }
                break;
//            botón contenido en la vista manejar categorías
            case "btnModificarManejar":
//                  modifica la descripción de la categoría
                if (!registro.categoriaExiste(vistaManejarCategoria.txtDescripcionCategoria.getText())) {
                    if (registro.modificarCategoria(Integer.parseInt(
                            vistaManejarCategoria.txtIdCategoria.getText()),
                            vistaManejarCategoria.txtDescripcionCategoria.getText())) {
                        this.vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
                        limpiarManejar();
                        JOptionPane.showMessageDialog(null, "Categoría modificada correctamente.\n");
                        vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una categoría"
                            + " con la descripción"
                            + vistaManejarCategoria.txtDescripcionCategoria.getText() + ".\n"
                            + "No se ingreso la categoría al registro.\n");
                }
                break;
//            botón contenido en la vista manejar categorías
            case "btnAgregarManejar":
//                  agrega la nueva categoría
                if (vistaManejarCategoria.txtDescripcionCategoria.getText()
                        .length() > 0 && !vistaManejarCategoria.txtDescripcionCategoria
                                .getText().equalsIgnoreCase(" ")) {
                    if (!registro.categoriaExiste(
                            vistaManejarCategoria.txtDescripcionCategoria.getText())) {
//                        verifica que los datos a ingresar cumplan con las 
//                        reglas de negocio de cada uno de ellos, y de ser así la
//                        ingresa a la base de datosa
                        if (registro.agregarCategoria(vistaManejarCategoria.txtDescripcionCategoria.getText())) {
                            activarBotonesManejar();
                            JOptionPane.showMessageDialog(null, "Categoría ingresada"
                                    + " correctamente.\n");
                            limpiarManejar();
                            vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
                            asignaValoresTabla("vistaManejarCategoria", 0);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se agregó la"
                                    + " categoría por que la tabla categorías"
                                    + " no se ha creado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe una categoría"
                                + " con la descripción"
                                + vistaManejarCategoria.txtDescripcionCategoria.getText() + ".\n"
                                + "No se ingreso la categoría al registro.\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar datos en"
                            + " la descripción de la categoría.\n");
                }
                break;
            case "btnEliminarManejar":
//                verifica que el id que se desea eliminar sea convertible 
//                a número
                if (registro.categoriaExiste(
                        Integer.parseInt(vistaManejarCategoria.txtIdCategoria.getText()))) {
//                       dada la importancia de la eliminación, solicita 
//                       verificación previa
                    if (!registro.categoriaEnPelicula(Integer.parseInt(vistaManejarCategoria.txtIdCategoria.getText()))) {
                        if (pideConfirmacion("ESTA ACCION ELIMINARA"
                                + " PERMANENTEMENTE LA CATEGORIA") == JOptionPane.YES_OPTION) {
//                           elimina la categoría de la base de datos y lo 
//                           verifica
                            if (registro.eliminarCategoria(
                                    Integer.parseInt(vistaManejarCategoria.txtIdCategoria.getText()))) {
                                JOptionPane.showMessageDialog(null, "La categoría "
                                        + "código " + vistaManejarCategoria.txtIdCategoria.getText()
                                        + " fué eliminada correctamente.\n");
                                limpiarManejar();
                                if (registro.cantidadCategorias() == 0) {
                                    noCategorias();
                                } else {
                                    vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
                                    asignaValoresTabla("vistaManejarCategoria", 0);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo "
                                        + "eliminar la película\n");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Existe a lo menos"
                                + " una película con esta categoría ingresada."
                                + " Para eliminar una categoría es necesario"
                                + " que ninguna película la tenga ingresada ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La Película código "
                            + vistaEliminarPelicula.txtCodigo.getText() + " no existe en el registro.\n"
                            + "La Película no fué eliminada.");
                }
                break;
        }
    }

    /**
     * Sobreescritura del método que permite asignar acciones a los "clicks" del
     * mouse, utilizado en conjunto con la JTable de la vista listar.
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseClicked(MouseEvent e
    ) {
        if (e.getButton() == 1)//boton izquierdo
        {
//            Muestro datos de producto a modificar
            if (vistaListarPelicula.isVisible()) {
                int fila = vistaListarPelicula.tbListar.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    asignaValoresTabla("vistaListarPelicula", fila);
                }
            }
            if (vistaManejarCategoria.isVisible()) {
                int fila = vistaManejarCategoria.tbListar.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    asignaValoresTabla("vistaManejarCategoria", fila);
                }
            }
        }
    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mousePressed(MouseEvent e
    ) {

    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseReleased(MouseEvent e
    ) {

    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseExited(MouseEvent e
    ) {

    }

    /**
     * Método para limpiar la vista agregar
     */
    private void limpiarAgregar() {
        vistaAgregarPelicula.txtCodigo.setText("0");
        vistaAgregarPelicula.txtNombre.setText("");
        vistaAgregarPelicula.txtPrecio.setText("0");
        vistaAgregarPelicula.optNormal.setSelected(true);
    }

    /**
     * Método para limpiar la vista modificar
     */
    private void limpiarModificar() {
        vistaModificarPelicula.txtCodigo.setText("0");
        vistaModificarPelicula.txtNombre.setText("");
        vistaModificarPelicula.txtPrecio.setText("0");
        vistaModificarPelicula.optNormal.setSelected(true);
    }

    /**
     * Método para limpiar la vista listar
     */
    private void limpiarListar() {
        vistaListarPelicula.txtCodigo.setText("0");
        vistaListarPelicula.txtNombre.setText("");
        vistaListarPelicula.txtPrecio.setText("0");
        vistaListarPelicula.optNormal.setSelected(true);
    }

    /**
     * Método para limpiar la vista manejar categorías
     */
    private void limpiarManejar() {
        vistaManejarCategoria.txtIdCategoria.setText("");
        vistaManejarCategoria.txtDescripcionCategoria.setText("");
    }

    /**
     * Método para reemplazar los valores en los campos de la vista modificar,
     * por los correspondientes a la película que se desea modificar
     *
     * @param codigo corresponde al código de la película
     */
    private void agregarDatosModificar(int codigo) {
        Pelicula peliculaTmp = registro.buscarPelicula(codigo);
        vistaModificarPelicula.txtCodigo.setText(String.valueOf(peliculaTmp.getCodigo()));
        vistaModificarPelicula.txtNombre.setText(peliculaTmp.getNombre());
        vistaModificarPelicula.txtPrecio.setText(String.valueOf(peliculaTmp.getPrecio()));
        vistaListarPelicula.cboCategorias.setSelectedItem(peliculaTmp.getCategoria().getDescripcion());
        if (peliculaTmp.getFormato4k() == 'S') {
            vistaModificarPelicula.opt4k.setSelected(true);
        } else {
            vistaModificarPelicula.optNormal.setSelected(true);
        }
    }

    /**
     * Método para solicitar confirmación ante un evento de modificación
     * permanente de la base de datos
     *
     * @param mensaje texto a desplegar
     * @return retorna el valor correspondiente a la opcion elegida
     */
    private int pideConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje + "\n",
                "¿?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para validar que un string sea transformable a entero
     *
     * @param palabra corresponde al dato a validar
     * @return devuelve true si el número es entero, false si no lo es
     */
    private boolean esEntero(String palabra) {
        try {
            Integer.parseInt(palabra);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Método para validar la entrada de numeros enteros en la vista agregar
     *
     * @return devuelve true si todos los campos contienen strings
     * transformables a enteros
     */
    private boolean validaNumerosAgregar() {
        if (esEntero(vistaAgregarPelicula.txtCodigo.getText())
                && esEntero(vistaAgregarPelicula.txtPrecio.getText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para validar la entrada de numeros enteros en la vista modificar
     *
     * @return devuelve true si todos los campos contienen strings
     * transformables a enteros
     */
    private boolean validaNumerosModificar() {
        if (esEntero(vistaModificarPelicula.txtCodigo.getText())
                && esEntero(vistaModificarPelicula.txtPrecio.getText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para validar la entrada de numeros enteros en la vista listar
     *
     * @return devuelve true si todos los campos contienen strings
     * transformables a enteros
     */
    private boolean validaNumerosListar() {
        if (esEntero(vistaListarPelicula.txtCodigo.getText())
                && esEntero(vistaListarPelicula.txtPrecio.getText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que permite modificar el combo box que lista las categorías
     *
     * @param vista corresponde a la vista donde se encuentra el combo box
     * @param id corresponde al atributo respectivo de la tabla categoria
     * @return retorna true si la modificación de combo box fue exitosa
     */
    private boolean llenarCategorias(String vista, int id) {
        boolean exito = false;
        //inicializa el combo box
        Categoria[] categoriaTmp = registro.obtenerCategorias();
        switch (vista) {
            case "vistaAgregarPelicula":
                vistaAgregarPelicula.cboCategorias.removeAllItems();
                break;
            case "vistaModificarPelicula":
                vistaModificarPelicula.cboCategorias.removeAllItems();
                break;
            case "vistaListarPelicula":
                vistaListarPelicula.cboCategorias.removeAllItems();
                break;
        }
        //ingresa elementos al combo box
        if (categoriaTmp.length > 0) {
            for (int cont = 0; cont < registro.cantidadCategorias(); cont++) {
                switch (vista) {
                    case "vistaAgregarPelicula":
                        vistaAgregarPelicula.cboCategorias.addItem(categoriaTmp[cont].getDescripcion());
                        break;
                    case "vistaModificarPelicula":
                        vistaModificarPelicula.cboCategorias.addItem(categoriaTmp[cont].getDescripcion());
                        break;
                    case "vistaListarPelicula":
                        vistaListarPelicula.cboCategorias.addItem(categoriaTmp[cont].getDescripcion());
                        break;
                }
            }
        } else {
            switch (vista) {
                case "vistaAgregarPelicula":
                    vistaAgregarPelicula.cboCategorias.addItem("NO HAY CATEGORIAS CREADAS");
                    vistaAgregarPelicula.cboCategorias.setEnabled(false);
                    break;
                case "vistaModificarPelicula":
                    vistaAgregarPelicula.cboCategorias.addItem("NO HAY CATEGORIAS CREADAS");
                    vistaAgregarPelicula.cboCategorias.setEnabled(false);
                    break;
                case "vistaListarPelicula":
                    vistaAgregarPelicula.cboCategorias.addItem("NO HAY CATEGORIAS CREADAS");
                    vistaAgregarPelicula.cboCategorias.setEnabled(false);
                    break;
            }
        }
        switch (vista) {
            case "vistaAgregarPelicula":
                vistaAgregarPelicula.cboCategorias.setSelectedIndex(id);
                break;
            case "vistaModificarPelicula":
                vistaModificarPelicula.cboCategorias.setSelectedIndex(id);
                break;
            case "vistaListarPelicula":
                vistaListarPelicula.cboCategorias.setSelectedIndex(id);
                break;
        }
        return exito;
    }

    /**
     * Método para desactivar los botones innecesarios de la vista manejar
     * categoria, para permitir el ingreso de una categoría
     */
    private void desactivarBotonesManejar() {
        vistaManejarCategoria.btnModificarManejar.setEnabled(false);
        vistaManejarCategoria.btnEliminarManejar.setEnabled(false);
        vistaManejarCategoria.btnRefrescarManejar.setEnabled(false);
    }

    /**
     * Método para activar los botones de la vista manejar categoria
     */
    private void activarBotonesManejar() {
        vistaManejarCategoria.btnModificarManejar.setEnabled(true);
        vistaManejarCategoria.btnEliminarManejar.setEnabled(true);
        vistaManejarCategoria.btnRefrescarManejar.setEnabled(true);
    }

    /**
     * Método para mostrar mensaje y deactivar botones al no haber categorias
     * ingresadas
     */
    private void noCategorias() {
        JOptionPane.showMessageDialog(null, "No hay categorias ingresadas.\n"
                + "Ingrese la descripción de la primera categoría,\n"
                + "presione el boton 'Agregar'.");
        desactivarBotonesManejar();
    }

    /**
     * Método para leer y asignar valores desde la jTable de la vista listar, a
     * los campos respectivos
     *
     * @param fila corresponde a la fila seleccionada en la JTable
     */
    private void asignaValoresTabla(String vista, int fila) {
        if (vista.equals("vistaListarPelicula")) {
            vistaListarPelicula.txtCodigo.setText(String.valueOf(vistaListarPelicula.tbListar.getValueAt(fila, 0)));
            vistaListarPelicula.txtNombre.setText(String.valueOf(vistaListarPelicula.tbListar.getValueAt(fila, 1)));
            vistaListarPelicula.cboCategorias.setSelectedItem(String.valueOf(vistaListarPelicula.tbListar.getValueAt(fila, 2)));
            vistaListarPelicula.txtPrecio.setText(String.valueOf(vistaListarPelicula.tbListar.getValueAt(fila, 4)));
            if (String.valueOf(vistaListarPelicula.tbListar.getValueAt(fila, 5)).equalsIgnoreCase("4K")) {
                vistaListarPelicula.opt4k.setSelected(true);
            } else {
                vistaListarPelicula.optNormal.setSelected(true);
            }
        } else {
            vistaManejarCategoria.txtIdCategoria.setText(String.valueOf(vistaManejarCategoria.tbListar.getValueAt(fila, 0)));
            vistaManejarCategoria.txtDescripcionCategoria.setText(String.valueOf(vistaManejarCategoria.tbListar.getValueAt(fila, 1)));
        }
    }

    /**
     * Método para desplegar la vista manejarCategorias
     */
    private void despliegaVistaManejarCategorias() {
        //despliega vista manejar categoría
        limpiarManejar();
        if (registro.cantidadCategorias() == 0) {
            noCategorias();
            vistaManejarCategoria.txtIdCategoria.setText("0");
        } else {
            vistaManejarCategoria.tbListar.setModel(registro.mostrarCategorias());
            vistaManejarCategoria.txtIdCategoria.setText(String.valueOf(vistaManejarCategoria.tbListar.getValueAt(0, 0)));
            vistaManejarCategoria.txtDescripcionCategoria.setText(String.valueOf(vistaManejarCategoria.tbListar.getValueAt(0, 1)));
            activarBotonesManejar();
            asignaValoresTabla("vistaManejarCategoria", 0);
        }
        vistaManejarCategoria.setLocationRelativeTo(null);
        vistaManejarCategoria.setVisible(true);
    }
}
