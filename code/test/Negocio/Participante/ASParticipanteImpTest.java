package Negocio.Participante;

import Exceptions.ASException;
import Integracion.Participante.DAOParticipante;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ASParticipanteImpTest {

    @Before
    public void setUp() throws Exception {
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        daoParticipante.deleteAll();
    }
    //-------------------------------------TEST CREATE-------------------------------------------------------------

    ///intenta crear una participante existente

    @Test(expected = ASException.class)
    //Metodo donde probamos que no se puede crear una participante ya existente en la bbdd
    public void createParticipanteExistente() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        System.out.println("Test Create_Modify Participante Existente"); //Probamos que la participante que intentamos crear existe ya en la bbdd

        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();

        Tparticipante participante = new TparticipanteNacional("IBM", 666666666, true,"Madrid"); //Generamos un transfer
        daoParticipante.create(participante); //Creamos una participante con los datos intoducidos en el transfer anterior

        Tparticipante participante2 = new TparticipanteNacional("IBM", 666666666, true,"Madrid");
        asParticipante.create(participante2); //A partir de aqui le estamos diciendo al test que si intentamos crear participante2 y se lanza una excepcion se 

        Tparticipante updated_part =  asParticipante.showById(participante2.getId());

        assertEquals(updated_part.getName(), participante2.getName());
        assertEquals(updated_part.getPhone(), participante2.getPhone());
        assertEquals(updated_part.getActive(), participante2.getActive());
    }

    //Metodo donde probamos que no se puede crear una participante cuando le pasamos algun datos incorrecto, es decir, a null o incompleto

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void creaParticipanteConDatosIncorrectos() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        Integer id = -1;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();

        //En este caso le pasamos la descripcion a null y necesitaria una descripcion para poder crearse correctamente
        Tparticipante participante = new Tparticipante("IBM", -1, true); //Generamos un transfer
        asParticipante.create(participante);
    }
    //Metodo donde probamos que no se puede crear una participante leida incorrectamente

    @Test(expected = ASException.class)//Se pasa el test si se lanza la excepcion
    public void creaParticipanteNullName() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        Integer id = -1;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();

        Tparticipante participante = new Tparticipante(null, 666666666, false); //Generamos un transfer
        //Intentara leer IBM en la bbdd y saltara una excepcion en create ya que la participante no existe
        asParticipante.create(participante);
    }
    //Metodo donde probamos que se crea una participante correctamente

    @Test
    public void createParticipante() throws Exception {
        Integer id = -1;
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new TparticipanteInternacional(id, "IBM", 666666666, true,"Andorra"); //Generamos un transfer
        assertTrue(asParticipante.create(participante) > 0);
    }

    //---------------------------------------------------------------------------------------------------------------

    //---------------------------------------TEST DROP---------------------------------------------------------------------
    @Test(expected = ASException.class)
    //Intentamos borrar una participante pasandole un id incorrecto(-1)
    public void dropWrongId() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new Tparticipante(-1, "IBM", 666666666, true); //Generamos un transfer
        asParticipante.drop(-1); //Creamos una participante con los datos intoducidos en el transfer anterior

    }

    //Intentamos borrar una participante con un id que no se encuentra en la bbdd
    @Test(expected = ASException.class)
    public void dropIdinexistente() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new Tparticipante(223344, "IBM", 666666666, true);
        asParticipante.drop(223344);

    }
    //Borramos una participante correctamente, existente en la bbdd

    @Test
    public void dropParticipante() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new TparticipanteNacional(1, "IBM", 666666666, false,"Andalucia");
        asParticipante.create(participante); //Creamos una participante IBM
        assertTrue(asParticipante.drop(1) > 0); //Si se ha podido borrar la participante correctamente, nos devuelve el id de la participante borrada logicamente
        //Y ademas el test sale correcto

    }

    //-----------------------------------------------------------------------------------------------------------------------------

    //------------------------------------------TEST MODIFY-------------------------------------------------------------------------
    //Test que comprueba que no se puede modificar una participante con un id incorrecto
    @Test(expected = ASException.class)
    public void testModifyIdIncorrecto() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        Tparticipante participante = new Tparticipante(-1, "IBM", 666666666, true);
        asParticipante.modify(participante);
    }

    //Test que comprueba que no se puede modificar una participante con un nombre que no existe  en la bddd

    @Test(expected = ASException.class)
    public void testModifyParticipanteInexistente() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new Tparticipante(1, "IBM", 666666666, true);
        asParticipante.modify(participante);
    }

    //Test que comprueba que se modifica una participante correctamente en la bbdd

    @Test
    public void testModify() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new TparticipanteNacional("IBM", 666666666, true,"Bon dia");
        int id = asParticipante.create(participante);

        Tparticipante participante2 = new TparticipanteNacional(id, "IBM", 666666667, true,"Bon dia");
        asParticipante.modify(participante2);
    }

    //-----------------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------TEST LIST----------------------------------------------------------------
    @Test
    //Test que comprueba que se lista correctamente dos participantes introducidas en la bbdd, si no hay participantes no muestra nada
    public void list() throws Exception {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        Collection<Tparticipante> collection;

        Tparticipante participante = new Tparticipante("IBM", 666666666, true);
        asParticipante.create(participante);

        Tparticipante participante2 = new Tparticipante("ACER", 666666666, true);
        asParticipante.create(participante2);

        collection = asParticipante.list();
        assertNotNull(collection);

    }
    //----------------------------------------------------------------------------------------------------------------------------------

    //--------------------------------------------------TEST SHOWBYNAME------------------------------------------------------------------
    @Test(expected = ASException.class)
    public void showBynameInexistente() throws ASException {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        Tparticipante participante = new Tparticipante("IBM", 666666666, true);

        asParticipante.showByName("IBM");
    }


    @Test
    //Test que comprueba que se muestra una participante con un nombre existente en la bbdd, si no existe lanza una excepcion
    public void showByname() throws ASException {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new TparticipanteNacional( "IBM", 666666666, true,"Tjtj");
        int id = asParticipante.create(participante);

        assertNotNull(asParticipante.showByName("IBM"));
    }
    //----------------------------------------------------------------------------------------------------------------------------------

    //--------------------------------------------------TEST SHOWBYID-----------------------------------------------------------------

    //Test que comprueba que no se puede mostrar una participante por id inexistente en la bbddd
    @Test(expected = ASException.class)
    public void showByIdInexistente() throws ASException {
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        Tparticipante participante = new Tparticipante(65780, "IBM", 666666666, null);

        asParticipante.showById(65780);
    }


    @Test
    //Test que comprueba que se muestra una participante con un id existente en la bbdd, si no existe lanza una excepcion
    public void showById() throws ASException {
        ASParticipanteImp asParticipante = new ASParticipanteImp();

        Tparticipante participante = new TparticipanteNacional( "IBM", 666666666, false,"Andalucia");
        int id = asParticipante.create(participante);

        assertNotNull(asParticipante.showById(id));
    }
    //---------------------------------------------------------------------------------------------------------------------------------

}
