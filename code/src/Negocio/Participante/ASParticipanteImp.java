package Negocio.Participante;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Participante.DAOParticipante;
import Negocio.Participante.ASParticipante;
import Negocio.Participante.IFDAOParticipante;

import java.util.Collection;

public class ASParticipanteImp implements ASParticipante {
    public Integer create(Tparticipante participante) throws ASException {
        int id = -1;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getName() != null && participante.getPhone() > -1) {
            try {
                Tparticipante read = daoParticipante.readByName(participante.getName());
                if (read == null)
                    id = daoParticipante.create(participante);
                else
                    throw new ASException("ERROR: El participante " + participante.getName() + "ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participante.\n");
        return id;
    }

    public Integer drop(Tparticipante participante) throws ASException {
        int id = -1;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getId() > -1) {
            try {
                Tparticipante read = daoParticipante.readById(participante.getId());
                if (read != null) {
                    read.setActive(false);
                    id = daoParticipante.update(read);
                } else
                    throw new ASException("ERROR: El participante " + participante.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participante.\n");
        return id;
    }

    public Integer modify(Tparticipante participante) throws ASException {
        int id = -1;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getName() != null && participante.getId() != -1 && participante.getPhone() != -1) {
            try {
                Tparticipante read = daoParticipante.readById(participante.getId());
                if (read != null) {
                    Tparticipante nameOK = daoParticipante.readByName(participante.getName());
                    if (nameOK == null) {
                            id = daoParticipante.update(participante);
                    } else
                        throw new ASException("ERROR: El nuevo nombre para el participante " + participante.getName() + " ya esta siendo usado.\n");
                } else
                    throw new ASException("ERROR: El participante " + participante.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participante.\n");
        return id;
    }

    public Collection<Tparticipante> list() throws ASException {
        return null;
    }

    public Tparticipante showById(Tparticipante participante) throws ASException {
        return null;
    }
}
