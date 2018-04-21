package Negocio.Stand;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Feria.DAOFeria;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.ASAsignacionImp;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.ASFeriaImp;
import Negocio.Pabellon.ASPabellonImp;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.ASParticipacionImp;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.ASParticipanteImp;
import Negocio.Participante.Tparticipante;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

public class ASStandImpTest {

    public int addFairToBbdd() throws SQLException, ASException, DAOException{
        int id = -1;
        ASFeriaImp asFeria = new ASFeriaImp();

        //Insertamos una feria en la bbdd
        Date dateIni = new Date((4018-1900), 2, 12);
        Date dateEnd = new Date((4018-1900), 2, 18);

        Tferia feria = new Tferia(id, "IBM", "IBM", dateIni , dateEnd, false); //Generamos un transfer
        id = asFeria.create(feria);
        return id;
    }

    public int addPavilionToBbdd() throws SQLException, ASException, DAOException{
        int id = -1;
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(id, 200000, 200000, true);
        id = asPabellon.create(pabellon);
        return id;
    }

    //---------------------------------------------TEST CREATE---------------------------------------------------------------

    //Comprobamos que no se puede crear un stand con datos incorrectos
    @Test(expected = ASException.class)
    public void createStandIncorrect() throws ASException, DAOException, SQLException {
        ASStandImp asStand = new ASStandImp();
        int idStand = -1;

        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        //Le pasamos una participacion incorrecta o total m2 incorrectos
        Tstand tStand = new Tstand(idStand, -1,-1, 223344, 200, -1, false);
        asStand.create(tStand);
    }

    //Comprobamos que no se puede crear un stand con una participacion incorrecta(null)
    @Test(expected = ASException.class)
    public void createStandIncorrectParticipation() throws ASException, DAOException, SQLException {
        ASStandImp asStand = new ASStandImp();
        int idStand = -1, idFair = -1, idPavilion = -1, idAsignation = -1, idParticipation = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll(); //Vaciamos la bbdd de asignaciones

        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands

        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacion
        idFair = addFairToBbdd();
        idPavilion = addPavilionToBbdd();

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignation, idFair, idPavilion, 4000, 3000, false);
        idAsignation = asAsignation.create(transferAsignation);

        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion no valido.
        Tstand tStand = new Tstand(idStand, idAsignation, idParticipation, 223344, 200, 20, false);
        asStand.create(tStand);
    }

    //Comprobamos que se puede crear un stand correctamente
    @Test(expected = ASException.class)
    public void createStandCorrectly() throws ASException, SQLException, DAOException {
        ASStandImp asStand = new ASStandImp();
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll(); //Vaciamos la bbdd de asignaciones
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands

        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacion
        idFeria = addFairToBbdd();
        idPabellon = addPavilionToBbdd();

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);



        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        asStand.create(tStand);
    }


    //-----------------------------------------------------------------------------------------------------------------------
}
