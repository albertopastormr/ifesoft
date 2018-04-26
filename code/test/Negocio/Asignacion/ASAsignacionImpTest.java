package Negocio.Asignacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Pabellon.DAOPabellon;
import Integracion.Pabellon.DAOPabellonImp;
import Negocio.Feria.IFDAOFeria;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.IFDAOPabellon;
import Negocio.Pabellon.Tpabellon;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class ASAsignacionImpTest {

    //Creacion de variables globales con los IDs para poder trabajar con ellos
    private static Integer idFeria1, idFeria2, idPabellon1, idPabellon2;

    // Tasignacion para probar
    private static Tasignacion tasignacionTest1 = new Tasignacion(1, 1, 1, 1,true);
    private static Tasignacion tasignacionTest2 = new Tasignacion(2, 2, 2,2, true);

    // Tferia para probar
    private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new java.sql.Date(117,0,4),new java.sql.Date(117,0,4),true);
    private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new java.sql.Date(117,9,28),new java.sql.Date(117,10,4),true);

    // Tpabellon para probar
    private static Tpabellon tpabellonTest1 = new Tpabellon(1, 0,  4000,true);
    private static Tpabellon tpabellonTest2 = new Tpabellon(2, 2,  2,true);

    @Before
    public void setUp() throws Exception {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll();

        // Creacion de 2 tFeria para soportar el uso de asignaciones
        DAOFeriaImp daoFeriaImp = new DAOFeriaImp();
        daoFeriaImp.deleteAll();
        idFeria1 = daoFeriaImp.create(tferiaTest1);
        idFeria2 = daoFeriaImp.create(tferiaTest2);

        // Creacion de 2 tPabellon para soportar el uso de asignaciones
        DAOPabellonImp daoPabellonImp = new DAOPabellonImp();
        daoPabellonImp.deleteAll();
       idPabellon1 =  daoPabellonImp.create(tpabellonTest1);
        idPabellon2 = daoPabellonImp.create(tpabellonTest2);
    }

    //-----------------------------------------------TEST CREATE----------------------------------------------------------------------
    //Metodo donde comprobamos que se puede crear una asignacion correctaente a partir de un id de feria y un id de pabellon
    @Test
    public void createAsignation() throws DAOException, SQLException, ASException {
        ASAsignacionImp asAsignacion = new ASAsignacionImp();

        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignacion = new Tasignacion(ASAsignacionImpTest.tasignacionTest1.getId(), idFeria1, idPabellon1, 4000, 3000, false);
        asAsignacion.create(transferAsignacion);
    }

    //Metodo donde comprobamos que no se puede crear una asignacion ya existente en la bbdd
    @Test(expected = ASException.class) //Se pasará el test si se lanza una excepcion
    public void createAsignationExistingId() throws DAOException, SQLException, ASException{
        Integer idAsignacion = -1;
        ASAsignacionImp asAsignacion = new ASAsignacionImp();

        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation1 = new Tasignacion(tasignacionTest1.getId(), idFeria1, idPabellon1, 4000, 3000, false);
        //Creamos una asignacion
        idAsignacion = asAsignacion.create(transferAsignation1);
        //Generamos un transfer con el id de asignacion creado anteriormente e intentamos crear una nueva asignacion con ese id
        Tasignacion transferAsignation2 = new Tasignacion(idAsignacion, idFeria1, idPabellon1, 4000, 3000, false);
        asAsignacion.create(transferAsignation2);

    }

    //Metodo donde comprobaremos que no se puede crear una asignacion con un ID de feria incorrecto
    @Test(expected = ASException.class)
    public void createAsignationWithIncorrectIdFair() throws SQLException, DAOException, ASException{
        Integer idFair = -1;

        ASAsignacionImp asAsignation = new ASAsignacionImp();

        //El id de feria será -1 ya que no se ha generado ninguna feria
        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation1 = new Tasignacion(ASAsignacionImpTest.tasignacionTest1.getId(), idFair, idPabellon1, 4000, 3000, false);
        asAsignation.create(transferAsignation1);

    }

    //Metodo donde comprobaremos que no se puede crear una asignacion con un ID de pabellon incorrecto
    @Test(expected = ASException.class)
    public void createAsignationWithIncorrectIdPavilion() throws SQLException, DAOException, ASException{
        Integer idPavilion = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        //El id de pabellon será -1, ya que no se ha creado ningun pabellon
        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation1 = new Tasignacion(ASAsignacionImpTest.tasignacionTest1.getId(), idFeria1, idPavilion, 4000, 3000, false);
        asAsignation.create(transferAsignation1);
    }

    //Metodo donde comprobamos que los metros cuadrados de pabellon deben ser mas grandes que los contratados en la asignacion, luego saltara excepcion
    @Test(expected = ASException.class)
    public void createAsignationWithDataIncorrect() throws ASException, DAOException, SQLException{
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Los metros cuadrados que le introducimos al total de la asignacion son 40000, y deberían ser menores que los que tiene pabellon en la bbdd que son 5000
        Tasignacion transferAsignation1 = new Tasignacion(ASAsignacionImpTest.tasignacionTest1.getId(), idFeria1, idPabellon1, 40000, 3000, false);
        asAsignation.create(transferAsignation1);
    }
    //---------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------TEST DROP--------------------------------------------------------------------------

    @Test(expected = ASException.class)
    //Metodo donde intentamos borrr un id inexistente en la bbdd
    public void dropIdIncorrect() throws ASException, SQLException, DAOException{
        Integer asignationId = 223344;

        ASAsignacionImp asAsignation = new ASAsignacionImp();

        Tasignacion transferAsignation = new Tasignacion(asignationId, 223344, 223344, 40000, 3000, true);
        asAsignation.drop(transferAsignation);
    }

    @Test(expected = ASException.class)
    //Metodo donde queremos borrar una asignacion nula
    public void dropAsignationNull() throws ASException, SQLException, DAOException{

        ASAsignacionImp asAsignation = new ASAsignacionImp();

        Tasignacion transferAsignation = null;
        asAsignation.drop(transferAsignation);
    }

    @Test(expected = ASException.class) //Metodo donde queremos borrar una asignacion que supuestamente esta borrada ya
    public void dropAsignationWithActiveFalse() throws ASException, SQLException, DAOException{
        int asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        asignationId = asAsignation.create(tasignacionTest1); //Creamos la asignacion primero con sus datos y nos pondra el booleano a true
        Tasignacion tasignacion = new Tasignacion(asignationId, 1, 1, 1, 1,true);

        asAsignation.drop(tasignacion);
        asAsignation.drop(tasignacion); //Aqui deberia saltar la excepcion ya que ya esta a false el booleano, y no podemos borrar dos veces lo mismo
    }

    @Test
    //Metodo donde borramos correctamente una asignacion
    public void dropAsignationCorrectly() throws ASException, SQLException, DAOException{
        Integer asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        asignationId = asAsignation.create(tasignacionTest1); //Creamos la asignacion
        Tasignacion tasignacion = new Tasignacion(asignationId, 1, 1, 1, 1,true);

        assertTrue(asAsignation.drop(tasignacion) > 0); //si es mayor que 0 se borra correctamente
    }
    //----------------------------------------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------TEST MODIFY----------------------------------------------------------
    //Metodo donde comprobamos que no se puede modificar una asignacion inexistente en la bbdd
    @Test(expected = ASException.class)
    public void modifyAsignationNotExisting() throws ASException, DAOException, SQLException{
        Integer asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        Tasignacion transferAsignation = new Tasignacion(asignationId, idFeria1, idPabellon1, 40000, 3000, false);
        asAsignation.modify(transferAsignation);
    }

    //Comprobamos con el test que no se puede modificar una asignacion con IDs incorrectos
    @Test(expected = ASException.class)
    public void modifyAsignationDataIncorrect() throws ASException, DAOException, SQLException{
        Integer asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        //Le ponemos -1 al ID feria y pabellon
        Tasignacion transferAsignation = new Tasignacion(asignationId, -1, -1, 40000, 3000, false);
        asAsignation.modify(transferAsignation);
    }
    //Comprobamos con el test que no se puede modificar una asignacion con un pabellon inexistente en la bbdd
    @Test(expected = ASException.class)
    public void modifyAsignationNotExistPavilion() throws ASException, DAOException, SQLException{
        Integer asignationId = 1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        //Le pasamos un ID 223344 a pabellon inexistente en la bbddd
        Tasignacion transferAsignation = new Tasignacion(ASAsignacionImpTest.tasignacionTest1.getId(), idFeria1, 223344, 4000, 3000, false);
        asignationId = asAsignation.create(transferAsignation);
        Tasignacion transferAsignation2 = new Tasignacion(asignationId, idFeria1, 223344, 4000, 3000, false);

        asAsignation.modify(transferAsignation2);
    }

    //Comprobamos con el test que se puede realizar una modificacion correctamente
    @Test
    public void modifyAsignation() throws ASException, DAOException, SQLException{
        Integer asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        Tasignacion transferAsignation = new Tasignacion(asignationId, idFeria1, idPabellon1, 4000, 3000, false);
        //Introducimos la asignacion en la bbdd
        asignationId = asAsignation.create(transferAsignation);
        //Generamos un nuevo transfer con los datos de la asignacion anterior mas su id y  modificamos los m2 totales a 30000
        Tasignacion transferAsignation2 = new Tasignacion(asignationId, idFeria1, idPabellon1, 3000, 3000, true);

        //La modificamos
        asAsignation.modify(transferAsignation2);
    }

    //------------------------------------------------------------------------------------------------------------------------------------

    //---------------------------------------------------------TEST LIST---------------------------------------------------------------

    //test donde comprobamos que se lista correctamente una asignacion introducida en la bbdd, si hay mas se listan todas, si no hay no se lista nada
    @Test
    public void listAsignation() throws DAOException, ASException, SQLException{
        Collection<Tasignacion> collection;

        Integer asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        Tasignacion transferAsignation = new Tasignacion(asignationId, idFeria1, idPabellon1, 4000, 3000, false);
        asAsignation.create(transferAsignation);

        collection = asAsignation.list();
        assertTrue(collection != null);
    }

    //--------------------------------------------------------------------------------------------------------------------------------

    //--------------------------------------------TEST SHOW BY ID_PAVILION-----------------------------------------------------------

    //Comprobamos que no se puede mostrar una asignacion por id de pabellon con un id incorrecto
    @Test(expected = ASException.class)
    public void showByIdPavilionIncorrect() throws SQLException, ASException, DAOException{
        Integer idPavilion = -1;
        ASAsignacionImp asAsignacion = new ASAsignacionImp();

        asAsignacion.showByIdPavilion(idPavilion);
    }

    //Comprobamos que se muestra correctamente una asignacion por id de pabellon correctamente
    @Test
    public void showByIdPavilion() throws SQLException, ASException, DAOException{
        Integer  idAsignation = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        //Introducimos en la bbdd la asignacion
        Tasignacion transferAsignation = new Tasignacion(idAsignation, idFeria1, idPabellon1, 4000, 3000, false);
        asAsignation.create(transferAsignation);
        //Mostramos por ID de pabellon
        asAsignation.showByIdPavilion(idPabellon1);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------

    //--------------------------------------------TEST SHOW BY ID_FAIR-----------------------------------------------------------

    //Comprobamos que no se puede mostrar una asignacion por id de feria con un id incorrecto
    @Test(expected = ASException.class)
    public void showByIdFairIncorrect() throws SQLException, ASException, DAOException{
        Integer idFair = -1;
        ASAsignacionImp asAsignacion = new ASAsignacionImp();

        asAsignacion.showByIdFair(idFair);
    }

    //Comprobamos que se muestra correctamente una asignacion por id de feria correctamente
    @Test
    public void showByIdFair() throws SQLException, ASException, DAOException{
        Integer idAsignation = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();

        //Introducimos en la bbdd la asignacion
        Tasignacion transferAsignation = new Tasignacion(idAsignation, idFeria1, idPabellon1, 4000, 3000, false);
        asAsignation.create(transferAsignation);
        //Mostramos por ID de pabellon
        asAsignation.showByIdFair(idFeria1);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
}