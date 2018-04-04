package Integracion.Feria;

import Exceptions.DAOException;
import Negocio.Feria.Tferia;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class DAOFeriaImpTest {
	@Test
	public void create() throws Exception {
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

	@Test
	public void readAll() throws Exception {
	}

	@Test
	public void readByName() throws Exception {
	}

	@Test
	public void update() throws Exception {
	}

	@Test
	public void delete() throws Exception {
	}

}