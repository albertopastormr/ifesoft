package Negocio.Asignacion;

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
    public void createAsignation() throws DAOException, SQLException {
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
        assertTrue(daoAsignation.create(transferAsignation) > 0);
    }
    //---------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------TEST DROP--------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------------------------
}