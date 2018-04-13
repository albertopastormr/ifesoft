package Negocio.Participante;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Participante.DAOParticipante;
import Negocio.Participante.ASParticipante;
import Negocio.Participante.IFDAOParticipante;

import java.util.Collection;

public class ASParticipanteImp implements ASParticipante {
    public Integer create(Tparticipante participante) throws ASException, DAOException {
        int id = -1;
        /*DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getTotal_m2() >= 0 && participante.getUtil_m2() >= 0 && participante.getCapacity() >= 0) {
            try {
                Tparticipante read = daoParticipante.readById(participante.getId());
                if (read == null) {
                    if (participante.getId() != -1 && participante.getTotal_m2() >= 0 && participante.getTotal_m2() >= participante.getUtil_m2())
                        id = daoParticipante.create(participante);
                    else
                        throw new ASException("ERROR: Los datos del participante no son correctos.\n");
                } else
                    throw new ASException("ERROR: El participante " + participante.getId() + "ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participante.\n");*/
        return id;
    }

    public Integer drop(Tparticipante participante) throws ASException, DAOException {
        int id = -1;
        /*DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null) {
            participante.setActive(false);
            try {
                Tparticipante read = daoParticipante.readById(participante.getId());
                if (read != null)
                    id = daoParticipante.update(participante);
                else
                    throw new ASException("ERROR: El participante " + participante.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participante.\n");*/
        return id;
    }

    public Integer modify(Tparticipante participante) throws ASException, DAOException {
        return null;
    }

    public Collection<Tparticipante> list() throws ASException, DAOException {
        return null;
    }

    public Tparticipante showById(Tparticipante participante) throws ASException, DAOException {
        return null;
    }
}
