package Negocio.Participacion;

import Exceptions.ASException;
import Integracion.Feria.DAOFeria;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Participante.DAOParticipante;
import Negocio.Feria.IFDAOFeria;
import Negocio.Feria.Tferia;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participante.IFDAOParticipante;
import Negocio.Participante.Tparticipante;

import java.util.Collection;

public class ASParticipacionImp implements ASParticipacion {
    public Integer create(Tparticipacion participacion) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();

        if (participacion != null && participacion.getFair_id() != -1 && participacion.getClient_id() != -1) {
            try {
                Tferia fRead = daoFeria.readById(participacion.getFair_id());
                Tparticipante fParticipante = daoParticipante.readById(participacion.getClient_id());
                if (fRead != null && fParticipante != null) {
                    Tparticipacion read = daoParticipacion.readByFairIdClientId(participacion.getFair_id(), participacion.getClient_id());
                    if (read == null)
                        return daoParticipacion.create(participacion);
                    else{
                        if(!read.getActive() && participacion.getActive())
                            return daoParticipacion.update(participacion);
                        else
                            throw new ASException("ERROR: El participante " + participacion.getClient_id() + " ya participa en la feria " + participacion.getFair_id() + ".\n");
                    }
                } else
                    throw new ASException("ERROR: La feria o el participante introducido no existen.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion o son erroneos.\n");
    }

    public Integer drop(Tparticipacion participacion) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read != null) {
                    read.setActive(false);
                    return daoParticipacion.update(read);
                } else
                    throw new ASException("ERROR: La participacion " + participacion.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la participacion.\n");
    }

    public Integer modify(Tparticipacion participacion) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();

        if (participacion != null && participacion.getId() != -1 && participacion.getFair_id() != -1 && participacion.getClient_id() != -1) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read != null) {
                    if (participacion.getFair_id() == read.getFair_id() && participacion.getClient_id() == read.getClient_id()) {
                        if(participacion.getActive() == false){
                            participacion.setActive(false);
                            return daoParticipacion.update(participacion);
                        }
                        else
                            return daoParticipacion.update(participacion);
                    }
                    else
                        throw new ASException("ERROR: No pueden ser modificados el participante y/o feria de una participacion.\n");
                } else
                    throw new ASException("ERROR: La participacion " + participacion.getId() + ") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la participacion.\n");
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
        if (participation_id != -1) {
            try {
                Tparticipacion read = daoParticipacion.readById(participation_id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La participacion " + participation_id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la participacion.\n");
    }

    public Collection<Tparticipacion> showByClientId(Integer client_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        if (client_id != -1) {
            try {
                collection = daoParticipacion.readByClientId(client_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se ha introducido el id del cliente por el que leer.\n");
        return collection;
    }

    public Collection<Tparticipacion> showByFairId(Integer fair_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        if (fair_id != -1) {
            try {
                collection = daoParticipacion.readByFairId(fair_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se ha introducido el id de la feria por el que leer.\n");
        return collection;
    }
}
