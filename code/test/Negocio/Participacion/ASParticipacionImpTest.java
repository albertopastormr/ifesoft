package Negocio.Participacion;

import Integracion.Feria.DAOFeria;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Participante.DAOParticipante;
import Negocio.Feria.ASFeriaImp;
import Negocio.Feria.IFDAOFeria;
import Negocio.Feria.Tferia;
import Negocio.Participante.*;

import Exceptions.ASException;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ASParticipacionImpTest {
    private Integer idFeria1, idFeria2, idParticipanteInternacional, idParticipanteNacional;

    private static Date dat = new Date(4000, 1, 12);
    private static Date dat2 = new Date(4000, 1, 21);
    private static Date dat3 = new Date(4000, 2, 10);
    private static Date dat4 = new Date(4000, 2, 13);

    // Tferia para probar
    private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo", dat,dat2,true);
    private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",dat3,dat4,true);

    private static TparticipanteInternacional tpartInternacional = new TparticipanteInternacional("IBM", 666666666, true,"Octolandia");
    private static TparticipanteNacional tpartNacional = new TparticipanteNacional("JaponShop", 916666666, true,"weabos");

    private static Tparticipacion participacion1 = new Tparticipacion(1, 1, 1, true);
    private static Tparticipacion participacion2 = new Tparticipacion(2, 2, 2, true);


    @Before
    @SuppressWarnings("deprecation")
    public void setUp() throws Exception {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        daoParticipacion.deleteAll();

        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        daoFeria.deleteAll();
        ASFeriaImp asFeria = new ASFeriaImp();
        idFeria1  = asFeria.create(tferiaTest1);
        idFeria2 = asFeria.create(tferiaTest2);

        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        daoParticipante.deleteAll();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        idParticipanteInternacional = asParticipante.create(tpartInternacional);
        idParticipanteNacional = asParticipante.create(tpartNacional);
    }

    //-------------------------------------TEST CREATE-------------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createParticipacionExistingId() throws ASException {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(1, idFeria1, idParticipanteInternacional, true);
        int id1 = asParticipacion.create(participacion);
        Tparticipacion participacion2 = new Tparticipacion(id1, idFeria1, idParticipanteInternacional, true);
        asParticipacion.create(participacion2);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    @SuppressWarnings("all")
    public void createParticipacionNoData() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = null;
        int id1 = asParticipacion.create(participacion);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createParticipacionWrongFair() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipacion participacion = new Tparticipacion(222, idParticipanteInternacional, true);
        int id1 = asParticipacion.create(participacion);
    }

    @Test
    public void activate() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        int id1 = asParticipacion.create(participacion1);
        int id2 = asParticipacion.create(participacion2);
        assert (id1 > 0 && id2 > 0);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST DROP---------------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void dropParticipacionNoId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(-1, idFeria2, idParticipanteNacional, true);
        int id2 = asParticipacion.drop(participacion);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void dropParticipacionWrongId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(23456, idFeria2, idParticipanteInternacional, true);
        int id1 = asParticipacion.drop(participacion);
    }

    @Test
    public void dropParticipacionOK() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        int id1 = asParticipacion.create(participacion1);
        int id2 = asParticipacion.drop(participacion1);
        assert (id1 > 0 && id2 > 0);
        assert !asParticipacion.show(id2).getActive();
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST MODIFY-------------------------------------------------------------

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyParticipacionNoId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        Tparticipacion participacion2 = new Tparticipacion(-1, idFeria1, idParticipanteInternacional, true);
        asParticipacion.modify(participacion2);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyParticipacionWrongFairOrClient() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion( idFeria1, idParticipanteInternacional, true);
        int id1 = asParticipacion.create(participacion);
        Tparticipacion participaciontest = new Tparticipacion(id1, idFeria1, -1, false);
        int id2 = asParticipacion.modify(participaciontest);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    @SuppressWarnings("all")
    public void modifyParticipacionNoData() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = null;
        int id1 = asParticipacion.modify(participacion);
    }

    @Test
    public void modify() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion( idFeria1, idParticipanteInternacional, true);
        int id1 = asParticipacion.create(participacion);
        Tparticipacion participaciontest = new Tparticipacion( id1,idFeria1, idParticipanteInternacional, true);

        int id_mod = asParticipacion.modify(participaciontest);
        assert (id1 > 0);
        tparticipacionEquals(participaciontest, asParticipacion.show(id_mod));
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST SHOWBYID-----------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void showParticipacionNoId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(idFeria2, idParticipanteInternacional, true);
        asParticipacion.create(participacion);

        asParticipacion.show(225); //No existe una participacion con ese id
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void showParticipacionWrongId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(23456, idFeria1, idParticipanteInternacional, true);
        Integer idP = asParticipacion.create(participacion);
       asParticipacion.show(-1);
    }

    @Test//Se pasa el test si se lanza la excepcion
    public void showParticipacionCollection() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Collection<Tparticipacion> id1 = asParticipacion.list();
        assert (id1.isEmpty());
    }

    @Test
    public void showParticipacionOK() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        Tparticipacion participacion = new Tparticipacion( idFeria1, idParticipanteInternacional, true);
        int id1 = asParticipacion.create(participacion);
        participacion.setId(id1);
        Tparticipacion read = asParticipacion.show(participacion.getId());
        tparticipacionEquals(participacion, read);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------TEST SHOW_BY CLIENT ID---------------------------------
    //Comprobamos que no se puede mostrar una asignacion por id de pabellon con un id incorrecto
    @Test(expected = ASException.class)
    public void showByIdParticipanteIncorrect() throws ASException{
        Integer idParticipante = -1;
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        asParticipacion.showByClientId(idParticipante);
    }

    @Test
    public void showByIdParticipante() throws ASException{
        Integer idParticipante = -1;
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(ASParticipacionImpTest.participacion1.getId(), idFeria1, idParticipanteInternacional, true);
        asParticipacion.create(participacion);
        idParticipante = idParticipanteInternacional;
        tparticipacionEquals((Tparticipacion) asParticipacion.showByClientId(idParticipante).toArray()[0], participacion);
    }
    //---------------------------------------------------------------------------------------------------------------


    //-----------------------------------------------------------TEST SHOW_BY FAIR ID---------------------------------
    //Comprobamos que no se puede mostrar una asignacion por id de pabellon con un id incorrecto
    @Test(expected = ASException.class)
    public void showByIdFairIncorrect() throws ASException{
        Integer idFeria = -1;
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        asParticipacion.showByClientId(idFeria);
    }

    @Test
    public void showByIdFeria() throws ASException{
        Integer idFeria = -1;
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        Tparticipacion participacion = new Tparticipacion(ASParticipacionImpTest.participacion1.getId(), idFeria1, idParticipanteInternacional, true);
        asParticipacion.create(participacion);
        idFeria = idFeria1;
        tparticipacionEquals((Tparticipacion) asParticipacion.showByClientId(idFeria).toArray()[0], participacion);

    }
    //---------------------------------------------------------------------------------------------------------------

    private void tparticipacionEquals(Tparticipacion first, Tparticipacion second){
        assertEquals(first.getFair_id(), second.getFair_id());
        assertEquals(first.getClient_id(), second.getClient_id());

        assertEquals(first.getActive(), second.getActive());
    }
}
