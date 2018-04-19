package Negocio.Asignacion;

import Integracion.Asignacion.DAOAsignacion;
import Negocio.Feria.IFDAOFeria;
import org.junit.Before;

public class ASAsignacionImpTest {

    @Before
    public void setUp() throws Exception {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll();
    }
}
