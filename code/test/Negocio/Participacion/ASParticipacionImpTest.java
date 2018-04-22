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

public class ASParticipacionImpTest {

    @Before
    @SuppressWarnings("deprecation")
    public void setUp() throws Exception {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        daoParticipacion.deleteAll();

        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        daoFeria.deleteAll();
        ASFeriaImp asFeria = new ASFeriaImp();
        Date dat = new Date(4000, 1, 12);
        Date dat2 = new Date(4000, 1, 21);
        Date dat3 = new Date(4000, 2, 10);
        Date dat4 = new Date(4000, 2, 13);
        asFeria.create(new Tferia("iElectronics", "Feria tech de electronica", dat, dat2, true));
        asFeria.create(new Tferia("ExpoManga", "Feria de Manga de Madrid", dat3, dat4, true));

        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        daoParticipante.deleteAll();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        asParticipante.create(new TparticipanteInternacional("IBM", 666666666, true,"Octolandia"));
        asParticipante.create(new TparticipanteNacional("JaponShop", 916666666, true,"weabos"));
    }

    //-------------------------------------TEST CREATE-------------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createParticipacionExistingId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");


        Tparticipacion participacion = new Tparticipacion(ielectronics.getId(), ibm.getId(), true);
        int id1 = asParticipacion.create(participacion);
        Tparticipacion participacion2 = new Tparticipacion(id1, ielectronics.getId(), ibm.getId(), true);
        int id2 = asParticipacion.create(participacion2);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    @SuppressWarnings("all")
    public void createParticipacionNoData() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = null;
        int id1 = asParticipacion.create(participacion);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createParticipacionWrongFair() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(222, japonShop.getId(), true);
        int id1 = asParticipacion.create(participacion);
    }

    @Test
    public void activate() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(expomanga.getId(), japonShop.getId(), false);
        int id1 = asParticipacion.create(participacion);
        Tparticipacion participacion2 = new Tparticipacion(id1, expomanga.getId(), japonShop.getId(), true);
        int id2 = asParticipacion.create(participacion2);
        assert (id1 > 0 && id2 == id1);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST DROP---------------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void dropParticipacionNoId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(-1, ielectronics.getId(), ibm.getId(), true);
        int id2 = asParticipacion.drop(participacion);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void dropParticipacionWhrongId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(23456, ielectronics.getId(), ibm.getId(), true);
        int id1 = asParticipacion.drop(participacion);
    }

    @Test
    public void dropParticipacionOK() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(100, ielectronics.getId(), ibm.getId(), false);
        int id1 = asParticipacion.create(participacion);
        participacion.setId(id1);
        int id2 = asParticipacion.drop(participacion);
        assert (id2 == id1);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST MODIFY-------------------------------------------------------------

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyParticipacionNoId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion2 = new Tparticipacion(-1, expomanga.getId(), ibm.getId(), true);
        int id2 = asParticipacion.modify(participacion2);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyParticipacionWrongFairOrClient() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion1 = new Tparticipacion( ielectronics.getId(), ibm.getId(), true);
        int id1 = asParticipacion.create(participacion1);
        Tparticipacion participacion = new Tparticipacion(id1, ielectronics.getId(), ibm.getId(), false);
        int id2 = asParticipacion.modify(participacion);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    @SuppressWarnings("all")
    public void modifyParticipacionNoData() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = null;
        int id1 = asParticipacion.modify(participacion);
    }

    @Test(expected = ASException.class) // Se crea un participacion con un id ya existente
    public void modify() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion( ielectronics.getId(), ibm.getId(), true);
        int id1 = asParticipacion.create(participacion);
        Tparticipacion participacion2 = new Tparticipacion(id1, ielectronics.getId(), ibm.getId(), false);
        int id2 = asParticipacion.create(participacion2);
        assert (id1 > 0 && id2 == id1);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST SHOWBYID-----------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void showParticipacionNoId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(-1, expomanga.getId(), ibm.getId(), true);
        Tparticipacion id2 = asParticipacion.show(participacion.getId());
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void showParticipacionWhrongId() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion(23456, ielectronics.getId(), japonShop.getId(), true);
        Tparticipacion id1 = asParticipacion.show(participacion.getId());
    }

    @Test//Se pasa el test si se lanza la excepcion
    public void showParticipacionCollection() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Collection<Tparticipacion> id1 = asParticipacion.list();
        assert (id1.isEmpty());
    }

    @Test
    public void showParticipacionOK() throws Exception {
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASFeriaImp asFeria = new ASFeriaImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante ibm = asParticipante.showByName("IBM");
        Tparticipante japonShop = asParticipante.showByName("JaponShop");
        Tferia ielectronics = asFeria.showByName("iElectronics");
        Tferia expomanga = asFeria.showByName("ExpoManga");

        Tparticipacion participacion = new Tparticipacion( ielectronics.getId(), japonShop.getId(), true);
        int id1 = asParticipacion.create(participacion);
        participacion.setId(id1);
        Tparticipacion id2 = asParticipacion.show(participacion.getId());
        assert (id2.getId() == participacion.getId() && id2.getActive() == participacion.getActive() &&
                id2.getClient_id() == participacion.getClient_id() && id2.getFair_id() == participacion.getFair_id());
    }
    //-------------------------------------------------------------------------------------------------------------

}
