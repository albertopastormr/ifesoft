package Negocio.Asignacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Feria.DAOFeria;
import Integracion.Pabellon.DAOPabellon;
import Negocio.Feria.IFDAOFeria;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.IFDAOPabellon;
import Negocio.Pabellon.Tpabellon;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class ASAsignacionImpTest {

    @Before
    public void setUp() throws Exception {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll();
    }

    //-----------------------------------------------TEST CREATE----------------------------------------------------------------------
    //Metodo donde comprobamos que se puede crear una asignacion correctaente a partir de un id de feria y un id de pabellon
    @Test
    public void createAsignation() throws DAOException, SQLException, ASException {
        Integer idFair = -1, idPavilion = -1, idAsignation = -1;
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();

        //Generamos DAO y transfer para feria para insertar una feria en la bbdd
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Date dateIni = new Date((4016-1900), 1, 12);
        Date dateEnd = new Date((4016-1900), 1, 18);
        Tferia transferFair = new Tferia(idFair, "IBM", "ExampleFair", dateIni, dateEnd, false);
        idFair =  daoFeria.create(transferFair); //Creamos la feria y la insertamos en la bbdd

        //Generamos DAO y transfer para pabellon e insertarlo en la bbdd
        DAOPabellon daoPavilion = IFDAOPabellon.getInstance().generateDAOpabellon();
        Tpabellon transferPavilion = new Tpabellon(idPavilion, 5000, 5000, false);
        idPavilion = daoPavilion.create(transferPavilion);

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation = new Tasignacion(idAsignation, idFair, idPavilion, 4000, 3000, false);
        assertTrue(asAsignation.create(transferAsignation) > 0);
    }

    //Metodo donde comprobamos que no se puede crear una asignacion ya existente en la bbdd
    @Test(expected = ASException.class) //Se pasará el test si se lanza una excepcion
    public void createAsignationExistingId() throws DAOException, SQLException, ASException{
        Integer idFair = -1, idPavilion = -1, idAsignation = -1;
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();

        //Generamos DAO y transfer para feria para insertar una feria en la bbdd
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Date dateIni = new Date((4016-1900), 1, 12);
        Date dateEnd = new Date((4016-1900), 1, 18);
        Tferia transferFair = new Tferia(idFair, "IBM", "ExampleFair", dateIni, dateEnd, false);
        idFair =  daoFeria.create(transferFair); //Creamos la feria y la insertamos en la bbdd

        //Generamos DAO y transfer para pabellon e insertarlo en la bbdd
        DAOPabellon daoPavilion = IFDAOPabellon.getInstance().generateDAOpabellon();
        Tpabellon transferPavilion = new Tpabellon(idPavilion, 5000, 5000, false);
        idPavilion = daoPavilion.create(transferPavilion);

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation1 = new Tasignacion(idAsignation, idFair, idPavilion, 4000, 3000, false);
        Tasignacion transferAsignation2 = new Tasignacion(idAsignation, idFair, idPavilion, 4000, 3000, false);

        //Creamos una asignacion
        asAsignation.create(transferAsignation1);
        asAsignation.create(transferAsignation2);

    }

    //Metodo donde comprobaremos que no se puede crear una asignacion con un ID de feria incorrecto
    @Test(expected = ASException.class)
    public void createAsignationWithIncorrectIdFair() throws SQLException, DAOException, ASException{
        Integer idFair = -1, idPavilion = -1, idAsignation = -1;

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        //Generamos DAO y transfer para pabellon e insertarlo en la bbdd
        DAOPabellon daoPavilion = IFDAOPabellon.getInstance().generateDAOpabellon();
        Tpabellon transferPavilion = new Tpabellon(idPavilion, 5000, 5000, false);
        idPavilion = daoPavilion.create(transferPavilion);

        //El id de feria será -1 ya que no se ha generado ninguna feria
        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation1 = new Tasignacion(idAsignation, idFair, idPavilion, 4000, 3000, false);
        asAsignation.create(transferAsignation1);

    }

    //Metodo donde comprobaremos que no se puede crear una asignacion con un ID de pabellon incorrecto
    @Test(expected = ASException.class)
    public void createAsignationWithIncorrectIdPavilion() throws SQLException, DAOException, ASException{
        Integer idFair = -1, idPavilion = -1, idAsignation = -1;

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        //Generamos DAO y transfer para feria para insertar una feria en la bbdd
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Date dateIni = new Date((4016-1900), 1, 12);
        Date dateEnd = new Date((4016-1900), 1, 18);
        Tferia transferFair = new Tferia(idFair, "IBM", "ExampleFair", dateIni, dateEnd, false);
        idFair =  daoFeria.create(transferFair); //Creamos la feria y la insertamos en la bbdd

        //El id de pabellon será -1, ya que no se ha creado ningun pabellon
        //4000m2 contratatdos o totales, 3000m2 asignados a es stand en ese pabellon
        Tasignacion transferAsignation1 = new Tasignacion(idAsignation, idFair, idPavilion, 4000, 3000, false);
        asAsignation.create(transferAsignation1);
    }

    //Metodo donde comprobamos que los metros cuadrados de pabellon deben ser mas grandes que los contratados en la asignacion, luego saltara excepcion
    @Test(expected = ASException.class)
    public void createAsignationWithDataIncorrect() throws ASException, DAOException, SQLException{
        Integer idFair = -1, idPavilion = -1, idAsignation = -1;

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        //Generamos DAO y transfer para feria para insertar una feria en la bbdd
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Date dateIni = new Date((4016-1900), 1, 12);
        Date dateEnd = new Date((4016-1900), 1, 18);
        Tferia transferFair = new Tferia(idFair, "IBM", "ExampleFair", dateIni, dateEnd, false);
        idFair =  daoFeria.create(transferFair); //Creamos la feria y la insertamos en la bbdd

        //Generamos DAO y transfer para pabellon e insertarlo en la bbdd
        DAOPabellon daoPavilion = IFDAOPabellon.getInstance().generateDAOpabellon();
        Tpabellon transferPavilion = new Tpabellon(idPavilion, 5000, 5000, false);
        idPavilion = daoPavilion.create(transferPavilion);

        //Los metros cuadrados que le introducimos al total de la asignacion son 40000, y deberían ser menores que los que tiene pabellon en la bbdd que son 5000
        Tasignacion transferAsignation1 = new Tasignacion(idAsignation, idFair, idPavilion, 40000, 3000, false);
        asAsignation.create(transferAsignation1);
    }
    //---------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------TEST DROP--------------------------------------------------------------------------

    @Test(expected = ASException.class)
    //Metodo donde intentamos borrr un id inexistente en la bbdd
    public void dropIdIncorrect() throws ASException, SQLException, DAOException{
        Integer asignationId = 223344;

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        Tasignacion transferAsignation = new Tasignacion(asignationId, 223344, 223344, 40000, 3000, true);
        asAsignation.drop(transferAsignation);
    }

    @Test(expected = ASException.class)
    //Metodo donde queremos borrar una asignacion nula
    public void dropAsignationNull() throws ASException, SQLException, DAOException{

        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        Tasignacion transferAsignation = null;
        asAsignation.drop(transferAsignation);
    }

    @Test(expected = ASException.class)
    //Metodo donde queremos borrar una asignacion que supuestamente esta borrada ya, tiene el booleano a false
    public void dropAsignationWithActiveFalse() throws ASException, SQLException, DAOException{
        int asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        Tasignacion transferAsignation = new Tasignacion(asignationId, 223344, 223344, 40000, 3000, false);
        asignationId = daoAsignation.create(transferAsignation); //Creamos la asignacion primero con sus datos y nos pondra el booleano a true

        daoAsignation.delete(asignationId); //Borramos esa asignacion poniendo el booleano a false
        asAsignation.drop(transferAsignation); //Aqui deberia saltar la excepcion ya que ya esta a false el booleano
    }

    //HAY QUE COMPROBAR SI SE EJECUT BIEN ESTE TEST YA QUE SE HARIA BORRADO LOGICO RECURSIVO DE LOS STANDS ASOCIADOS.
    @Test(expected = ASException.class)
    //Metodo donde borramos correctamente una asignacion
    public void dropAsignationCorrectly() throws ASException, SQLException, DAOException{
        Integer asignationId = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignation = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignation.deleteAll(); //Vaciamos la bbdd de asignaciones

        Tasignacion transferAsignation = new Tasignacion(asignationId, 223344, 223344, 40000, 3000, false);
        asAsignation.create(transferAsignation); //Creamos la asignacion
        assertTrue(asAsignation.drop(transferAsignation) > 0); //si es mayor que 0 se borra correctamente
    }
    //----------------------------------------------------------------------------------------------------------------------------------
}