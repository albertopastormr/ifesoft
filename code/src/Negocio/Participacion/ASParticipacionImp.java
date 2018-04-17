package Negocio.Participacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Participacion.DAOParticipacion;
import Negocio.Feria.Tferia;
import Negocio.Participacion.ASParticipacion;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;

import java.util.Collection;

public class ASParticipacionImp implements ASParticipacion {
    public boolean create(Tparticipacion participacion) throws ASException {
        boolean create = false;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null && participacion.getFair_id() != -1 && participacion.getClient_id() != -1 && participacion.getStand_id() != -1) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getFair_id(), participacion.getClient_id(), participacion.getStand_id());
                if (read == null) {
                    create = daoParticipacion.create(participacion);
                } else
                    throw new ASException("ERROR: La participacion Feria(" + participacion.getFair_id() + ") Participante("+ participacion.getClient_id() + ") Stand("+ participacion.getStand_id() +") ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");
        return create;
    }

    public boolean drop(Tparticipacion participacion) throws ASException {
        boolean drop = false;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null) {
            participacion.setActive(false);
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getFair_id(), participacion.getClient_id(), participacion.getStand_id());
                if (read != null)
                    drop = daoParticipacion.update(participacion);
                else
                    throw new ASException("ERROR: La participacion Feria(" + participacion.getFair_id() + ") Participante("+ participacion.getClient_id() + ") Stand("+ participacion.getStand_id() +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");
        return drop;
    }

    public boolean modify(Tparticipacion participacion) throws ASException {
        boolean modify = false;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null && participacion.getFair_id() != -1 && participacion.getClient_id() != -1 && participacion.getStand_id() != -1 ) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getFair_id(), participacion.getClient_id(), participacion.getStand_id());
                if (read != null) {
                    modify =  daoParticipacion.update(participacion);
                } else
                    throw new ASException("ERROR: La participacion Feria(" + participacion.getFair_id() + ") Participante("+ participacion.getClient_id() + ") Stand("+ participacion.getStand_id() +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");
        return modify;
    }

    public Collection<Tparticipacion> list() throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        try {
            collection = daoParticipacion.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tparticipacion show(Integer fair_id, Integer client_id, Integer stand_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Tparticipacion read = null;
        if (fair_id != -1 && client_id != -1 && stand_id != -1) {
            try {
                read = daoParticipacion.readById(fair_id, client_id, stand_id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La participacion Feria(" + fair_id + ") Participante("+ client_id + ") Stand("+ stand_id +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
    }

    public Collection<Tparticipacion> showByClientId(Integer client_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        if(client_id  != 0) {
            try {
                collection = daoParticipacion.readByClientId(client_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        }else
            throw new ASException("ERROR: No se ha introducido el id del pabellon a leer.\n");
        return collection;
    }

    public Collection<Tparticipacion> showByFairId(Integer fair_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        if(fair_id  != 0) {
            try {
                collection = daoParticipacion.readByFairId(fair_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        }else
            throw new ASException("ERROR: No se ha introducido el id de la feria a leer.\n");
        return collection;
    }
}
