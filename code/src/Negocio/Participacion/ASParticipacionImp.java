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
    public Integer create(Tparticipacion participacion) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null && participacion.getId() != -1 && participacion.getFair_id() != -1 && participacion.getClient_id() != -1) {
            try {
                return daoParticipacion.create(participacion);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");
    }

    public Integer drop(Tparticipacion participacion) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null) {
            participacion.setActive(false);
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read != null)
                    return daoParticipacion.update(participacion);
                else
                    throw new ASException("ERROR: La participacion "+ participacion.getId() +" no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");
    }

    public Integer modify(Tparticipacion participacion) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null && participacion.getId() != -1 && participacion.getFair_id() != -1 && participacion.getClient_id() != -1 ) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read != null) {
                    return daoParticipacion.update(participacion);
                } else
                    throw new ASException("ERROR: La participacion "+ participacion.getId() +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");
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

    public Tparticipacion show(Integer participation_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Tparticipacion read = null;
        if (participation_id != -1) {
            try {
                read = daoParticipacion.readById(participation_id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La participacion "+ participation_id +" no existe.\n");
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
