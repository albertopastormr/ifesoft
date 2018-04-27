package Negocio.Stand;


import Exceptions.ASException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Stand.ASStand;
import Negocio.Stand.IFDAOStand;
import Negocio.Stand.Tstand;

import java.util.Collection;

public class ASStandImp implements ASStand {
    public Integer create(Tstand stand) throws ASException {
        int id = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            try {
                if (stand.getTotal_m2() >= 0 && stand.getAssignation_id() != -1 && stand.getParticipation_id() != -1 && stand.getCost() >= 0 && stand.getNum_at_fair() >= 0) {
                    // Lectura Asignacion referenciada
                    DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
                    Tasignacion tasignacionRead = daoAsignacion.readById(stand.getAssignation_id());
                    // Lectura Participacion referenciada
                    DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
                    Tparticipacion tparticipacionRead = daoParticipacion.readById(stand.getParticipation_id());

                    if (tasignacionRead != null && tparticipacionRead != null) {
                        if (tasignacionRead.getUsed_m2() + stand.getTotal_m2() <= tasignacionRead.getTotal_m2()) {
                            id = daoStand.create(stand);
                            tasignacionRead.setTotal_m2(tasignacionRead.getTotal_m2() + stand.getTotal_m2());
                            daoAsignacion.update(tasignacionRead);
                        } else
                            throw new ASException("ERROR: Los m2 solicitados superan el limite de la asignacion contratada para la feria " + tasignacionRead.getFair_id() + " en el pabellon " + tasignacionRead.getPavilion_id() + "\n");
                    } else
                        throw new ASException("ERROR: Asignacion y Participacion referenciadas no existen en la base de datos\n");
                } else
                    throw new ASException("ERROR: Los datos del stand no son correctos.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
        return id;
    }

    public Integer drop(Tstand stand) throws ASException {
        int id = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            stand.setActive(false);
            try {
                Tstand read = daoStand.readById(stand.getId());
                if (read != null)
                    id = daoStand.update(stand);
                else
                    throw new ASException("ERROR: El stand " + stand.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
        return id;
    }

    public Integer modify(Tstand stand) throws ASException {
        int id = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            try {
                Tstand read = daoStand.readById(stand.getId());
                if (read != null) {
                    if (stand.getTotal_m2() >= 0 && stand.getAssignation_id() != -1 && stand.getParticipation_id() != -1 && stand.getCost() >= 0 && stand.getNum_at_fair() >= 0) {
                        // Lectura Asignacion referenciada
                        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
                        Tasignacion tasignacionRead = daoAsignacion.readById(stand.getAssignation_id());
                        // Lectura Participacion referenciada
                        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
                        Tparticipacion tparticipacionRead = daoParticipacion.readById(stand.getParticipation_id());

                        if (tasignacionRead != null && tparticipacionRead != null) {
                            if (tasignacionRead.getUsed_m2() + stand.getTotal_m2() <= tasignacionRead.getTotal_m2()) {
                                id = daoStand.update(stand);
                                tasignacionRead.setTotal_m2(tasignacionRead.getTotal_m2() + stand.getTotal_m2());
                                daoAsignacion.update(tasignacionRead);
                            } else
                                throw new ASException("ERROR: Los m2 solicitados superan el limite de la asignacion contrada para la feria " + tasignacionRead.getFair_id() + " en el pabellon " + tasignacionRead.getPavilion_id() + "\n");
                        } else
                            throw new ASException("ERROR: Asignacion y Participacion referenciadas no existen en la base de datos\n");
                    } else
                        throw new ASException("ERROR: No se han introducido los datos del stand.\n");
                } else
                    throw new ASException("ERROR: El stand " + stand.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
        return id;
    }

    public Collection<Tstand> list() throws ASException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        Collection<Tstand> collection;
        try {
            collection = daoStand.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tstand showById(Integer id) throws ASException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id != -1) {
            try {
                Tstand read = daoStand.readById(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: El stand " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
    }


    public Collection<Tstand> showByAssignation(Integer id) throws ASException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id != -1) {
            try {
                Collection<Tstand> read = daoStand.readByAssignation(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: No existe una asignacion" + id + ".\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
    }


    public Collection<Tstand> showByParticipation(Integer id) throws ASException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id != -1) {
            try {
                Collection<Tstand> read = daoStand.readByAssignation(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: No existe una participacion " + id + ".\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
    }


}
