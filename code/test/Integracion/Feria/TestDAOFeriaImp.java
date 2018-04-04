package TestIntegracion.Feria;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import org.junit.Test;

import Exceptions.DAOException;
import Integracion.Feria.DAOFeriaImp;
import Negocio.Feria.Tferia;


public class TestDAOFeriaImp {

	@Test 
	public void exitoCreate() {
		Date ini = new Date(2017,4,3);
		Date fin = new Date(2017,5,3);
		Tferia in = new Tferia("FITUR","Feria internacional de turismo",ini,fin);
		DAOFeriaImp dao = new DAOFeriaImp();
		try {
			int a = dao.create(in);
			assertEquals(1,a);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
