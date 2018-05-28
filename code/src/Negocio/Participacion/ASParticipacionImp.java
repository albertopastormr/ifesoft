package Negocio.Participacion;

import Exceptions.ASException;
import Integracion.Feria.DAOFeria;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Participacion.IFDAOParticipacion;
import Integracion.Participante.DAOParticipante;
import Integracion.Feria.IFDAOFeria;
import Integracion.Stand.DAOStand;
import Negocio.Feria.Tferia;
import Integracion.Participante.IFDAOParticipante;
import Integracion.Stand.IFDAOStand;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.Tstand;

import java.util.ArrayList;
import java.util.Collection;

public class ASParticipacionImp implements ASParticipacion {
    public Integer create(Tparticipacion participacion) throws ASException {
        int id;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();

        if (participacion != null && participacion.getFair_id() > 0 && participacion.getClient_id() > 0) {
            try {
                Tferia fRead = daoFeria.readById(participacion.getFair_id());
                Tparticipante fParticipante = daoParticipante.readById(participacion.getClient_id());
                if (fRead != null && fParticipante != null) {
                    Tparticipacion read = daoParticipacion.readByFairIdClientId(participacion.getFair_id(), participacion.getClient_id());
                    if (read == null) {
                        if (fRead.getActive() && fParticipante.getActive())
                            id = daoParticipacion.create(participacion);
                        else
                            throw new ASException("La feria o el participante no estan activos.\n");
                    } else {
                        if (!read.getActive() && participacion.getActive() && fRead.getActive() && fParticipante.getActive()) {
                            participacion.setId(read.getId());
                            id = daoParticipacion.update(participacion);
                        } else
                            throw new ASException("ERROR: El participante " + participacion.getClient_id() + " ya participa en la feria " + participacion.getFair_id() + ".\n");
                    }
                } else
                    throw new ASException("ERROR: La feria o el participante introducido no existen.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: Los datos introducidos no son correctos.\n");
        return id;
    }

    public Integer drop(Integer id) throws ASException {
        int idr;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        ArrayList<Tstand> readStandList;

        if (id > 0) {
            try {
                Tparticipacion read = daoParticipacion.readById(id);
                if (read != null && read.getActive()) {
                    read.setActive(false);
                    readStandList = (ArrayList<Tstand>) daoStand.readByParticipation(id);
                    //Borramos para una asignacion en concreto, todos sus stands
                    for (int j = 0; j < readStandList.size(); j++) {
                        Tstand tStand = readStandList.get(j);
                        tStand.setActive(false);
                        daoStand.update(tStand);
                    }
                    idr = daoParticipacion.update(read);
                } else
                    throw new ASException("ERROR: La participacion " + id + " no existe o ya esta desactivada.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return idr;
    }

    public Integer modify(Tparticipacion participacion) throws ASException {
        int id;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();

        if (participacion != null && participacion.getId() != -1 && participacion.getFair_id() != -1 && participacion.getClient_id() != -1) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read != null) {
                    if (participacion.getFair_id() == read.getFair_id() && participacion.getClient_id() == read.getClient_id()) {
                        if (!participacion.getActive()) {
                            if (read.getActive())
                                participacion.setActive(true);
                            id = daoParticipacion.update(participacion);
                        } else {
                            if (!read.getActive()) {
                                Tferia feria = daoFeria.readById(participacion.getFair_id());
                                Tparticipante participante = daoParticipante.readById(participacion.getClient_id());
                                if (!feria.getActive() || !participante.getActive())
                                    participacion.setActive(false);
                            }
                            id = daoParticipacion.update(participacion);
                        }
                    } else
                        throw new ASException("ERROR: No pueden ser modificados el participante y/o feria de una participacion.\n");
                } else
                    throw new ASException("ERROR: La participacion " + participacion.getId() + ") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: Los datos introducidos no son correctos.\n");
        return id;
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
        Tparticipacion part;
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participation_id > 0) {
            try {
                Tparticipacion read = daoParticipacion.readById(participation_id);
                if (read != null)
                    part = read;
                else
                    throw new ASException("ERROR: La participacion " + participation_id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return part;
    }

    public Collection<Tparticipacion> showByClientId(Integer client_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        if (client_id > 0) {
            try {
                collection = daoParticipacion.readByClientId(client_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es correcto.\n");
        return collection;
    }

    public Collection<Tparticipacion> showByFairId(Integer fair_id) throws ASException {
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        Collection<Tparticipacion> collection;
        if (fair_id > 0) {
            try {
                collection = daoParticipacion.readByFairId(fair_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es correcto.\n");
        return collection;
    }
}
