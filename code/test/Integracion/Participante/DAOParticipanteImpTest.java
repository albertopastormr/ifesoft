package Integracion.Participante;

import Integracion.Participacion.DAOParticipacionImp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOParticipanteImpTest {
	@Before
	public  void setUp() throws Exception {
		DAOParticipacionImp dao = new DAOParticipacionImp();
		dao.deleteAll();
	}

	@Test
	public void create() throws Exception {
	}

	@Test
	public void readAll() throws Exception {
	}

	@Test
	public void readById() throws Exception {
	}

	@Test
	public void update() throws Exception {
	}

	@Test
	public void delete() throws Exception {
	}

}