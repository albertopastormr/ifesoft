package Negocio.Participante;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Participante.DAOParticipante;
import Negocio.Participante.ASParticipante;
import Negocio.Participante.IFDAOParticipante;

import java.util.Collection;

public class ASParticipanteImp implements ASParticipante {
    public Integer create(Tparticipante participante) throws ASException {
        int id;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getName() != null && participante.getPhone() >= 0) {
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

    public Integer drop(Integer id) throws ASException {
        int idr;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (id > -1) {
            try {
                Tparticipante read = daoParticipante.readById(id);
                if (read != null) {
                    read.setActive(false);
                    idr = daoParticipante.update(read);
                } else
                    throw new ASException("ERROR: El participante " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return idr;
    }

    public Integer modify(Tparticipante participante) throws ASException {
        int id;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getName() != null && participante.getId() > 0 && participante.getPhone() >= 0) {
            try {
                Tparticipante read = daoParticipante.readById(participante.getId());
                if (read != null) {
                    Tparticipante nameOK = daoParticipante.readByName(participante.getName());
                    if (nameOK == null || nameOK.getName().equals(read.getName())) {
                        if (!participante.getActive()) {
                            participante.setActive(false);
                            id = daoParticipante.update(participante);
                        } else
                            id = daoParticipante.update(participante);
                    } else
                        throw new ASException("ERROR: El nuevo nombre para el participante " + participante.getName() + " ya esta siendo usado.\n");
                } else
                    throw new ASException("ERROR: El participante " + participante.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido datos correctos.\n");
        return id;
    }

    public Collection<Tparticipante> list() throws ASException {
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        Collection<Tparticipante> collection;
        try {
            collection = daoParticipante.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tparticipante showByName(String name) throws ASException {
        Tparticipante part;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (name != null) {
            try {
                Tparticipante read = daoParticipante.readByName(name);
                if (read != null)
                    part = read;
                else
                    throw new ASException("ERROR: El participante " + name + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se ha introducido ningun nombre.\n");
        return part;
    }

    public Tparticipante showById(Integer id) throws ASException {
        Tparticipante part;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (id > 0) {
            try {
                Tparticipante read = daoParticipante.readById(id);
                if (read != null)
                    part = read;
                else
                    throw new ASException("ERROR: El participante " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return part;
    }
}
