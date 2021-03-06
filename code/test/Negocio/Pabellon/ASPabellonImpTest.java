package Negocio.Pabellon;

import Exceptions.ASException;
import Integracion.Pabellon.DAOPabellon;
import Integracion.Pabellon.IFDAOPabellon;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ASPabellonImpTest {

    @Before
    public void setUp() throws Exception {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        daoPabellon.deleteAll();
    }
    //-------------------------------------TEST CREATE-------------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createPabellonExistingId() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(100, 200, 200, true);
        int id1 = asPabellon.create(pabellon);
        Tpabellon pabellon2 = new Tpabellon(id1, 50, 200, true);
        int id2 = asPabellon.create(pabellon2);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createPabellonWrongCapacity() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(1000, 20, 10, true);
        int id1 = asPabellon.create(pabellon);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void createPabellonNoData() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = null;
        int id1 = asPabellon.create(pabellon);
    }


    @Test
    public void activate() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(100, 200, 200, false);
        int id1 = asPabellon.create(pabellon);
        Tpabellon pabellon2 = new Tpabellon(id1, 100, 200, true);
        int id2 = asPabellon.create(pabellon2);
        assert (id1 > 0 && id2 == id1);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST DROP---------------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void dropPabellonNoId() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(100, 200, 200, true);
        int id2 = asPabellon.drop(pabellon.getId());
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void dropPabellonWhrongId() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(23456, 200, 200, true);
        int id1 = asPabellon.drop(pabellon.getId());
    }

    @Test
    public void dropPabellonOK() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(100, 200, 200, true);
        int id1 = asPabellon.create(pabellon);
        pabellon.setId(id1);
        int id2 = asPabellon.drop(pabellon.getId());
        assert (id2 == id1);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST MODIFY-------------------------------------------------------------

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyPabellonNoId() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon2 = new Tpabellon(-1, 50, 200, true);
        int id2 = asPabellon.modify(pabellon2);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyPabellonWrongCapacity() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon1 = new Tpabellon(100, 200, 200, true);
        int id1 = asPabellon.create(pabellon1);
        Tpabellon pabellon = new Tpabellon(id1, 20, 10, true);
        int id2 = asPabellon.modify(pabellon);
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void modifyPabellonNoData() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = null;
        int id1 = asPabellon.modify(pabellon);
    }


    @Test(expected = ASException.class) // Se crea un pabellon con un id ya existente
    public void modify() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(100, 200, 200, true);
        int id1 = asPabellon.create(pabellon);
        Tpabellon pabellon2 = new Tpabellon(id1, 50, 150, true);
        int id2 = asPabellon.create(pabellon2);
        assert (id1 > 0 && id2 == id1);
    }
    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------TEST SHOWBYID-----------------------------------------------------------
    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void showPabellonNoId() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(-1,100, 200, true);
        Tpabellon id2 = asPabellon.showById(pabellon.getId());
    }

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void showPabellonWhrongId() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(23456, 100, 200, true);
        Tpabellon id1 = asPabellon.showById(pabellon.getId());
    }

    @Test//Se pasa el test si se lanza la excepcion
    public void showPabellonCollection() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Collection<Tpabellon> id1 = asPabellon.list();
        assert (id1.isEmpty());
    }

    @Test
    public void showPabellonOK() throws Exception {
        ASPabellonImp asPabellon = new ASPabellonImp();

        Tpabellon pabellon = new Tpabellon(100, 200, 200, true);
        int id1 = asPabellon.create(pabellon);
        pabellon.setId(id1);
        Tpabellon id2 = asPabellon.showById(pabellon.getId());
        assert (id2.getId() == pabellon.getId() && id2.getActive() == pabellon.getActive() &&
                id2.getCapacity() == pabellon.getCapacity() && id2.getTotal_m2() == pabellon.getTotal_m2());
    }
    //-------------------------------------------------------------------------------------------------------------

}
