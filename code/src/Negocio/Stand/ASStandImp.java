package Negocio.Stand;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;

import java.util.Collection;

public class ASStandImp implements ASStand {
    public Integer create(Tstand stand) throws ASException, DAOException {
        int id;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            try {
                if (stand.getTotal_m2() >= 1 && stand.getAssignation_id() > 0 && stand.getParticipation_id() > 0 && stand.getCost() >= 0 && stand.getNum_at_fair() >= 0) {

                    // Lectura Asignacion referenciada
                    DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
                    Tasignacion tasignacionRead = daoAsignacion.readById(stand.getAssignation_id());
                    // Lectura Participacion referenciada
                    DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
                    Tparticipacion tparticipacionRead = daoParticipacion.readById(stand.getParticipation_id());

                    if (tasignacionRead != null && tparticipacionRead != null) {
                        if (tasignacionRead.getUsed_m2() + stand.getTotal_m2() <= tasignacionRead.getTotal_m2()) {
                            if (!tasignacionRead.getActive() || !tparticipacionRead.getActive())
                                stand.setActive(false);
                            id = daoStand.create(stand);
                            if (stand.getActive()) {
                                tasignacionRead.setTotal_m2(tasignacionRead.getTotal_m2() + stand.getTotal_m2());
                                daoAsignacion.update(tasignacionRead);
                            }
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

    public Integer drop(Integer id) throws ASException {
        int idr;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (id > 0) {
            try {
                Tstand read = daoStand.readById(id);
                if (read != null) {
                    if(read.getActive() == true) {
                        read.setActive(false);
                        idr = daoStand.update(read);
                        Tasignacion asig = daoAsignacion.readById(read.getAssignation_id());
                        asig.setUsed_m2(asig.getUsed_m2() - read.getTotal_m2());
                        daoAsignacion.update(asig);
                    }
                    else
                        throw new ASException("ERROR: El stand " + id + " ya esta desactivado.\n");

                } else
                    throw new ASException("ERROR: El stand " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return idr;
    }

    public Integer modify(Tstand stand) throws ASException {
        int id;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            try {
                Tstand read = daoStand.readById(stand.getId());
                if (read != null) {
                    if (stand.getTotal_m2() >= 0 && stand.getAssignation_id() >= 0 && stand.getParticipation_id() >= 0 && stand.getCost() >= 0 && stand.getNum_at_fair() >= 0) {
                        // Lectura Asignacion referenciada
                        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
                        Tasignacion tasignacionRead = daoAsignacion.readById(stand.getAssignation_id());
                        // Lectura Participacion referenciada
                        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
                        Tparticipacion tparticipacionRead = daoParticipacion.readById(stand.getParticipation_id());

                        if (tasignacionRead != null && tparticipacionRead != null) {
                            if (read.getActive()) {
                                if (tasignacionRead.getUsed_m2() + stand.getTotal_m2() - read.getTotal_m2() <= tasignacionRead.getTotal_m2()) {
                                    if (!stand.getActive()) {
                                        id = daoStand.update(stand);
                                        tasignacionRead.setUsed_m2(tasignacionRead.getTotal_m2() - read.getTotal_m2());
                                        daoAsignacion.update(tasignacionRead);
                                    } else {
                                        id = daoStand.update(stand);
                                        tasignacionRead.setUsed_m2(tasignacionRead.getTotal_m2() + stand.getTotal_m2() - read.getTotal_m2());
                                        daoAsignacion.update(tasignacionRead);
                                    }
                                } else
                                    throw new ASException("ERROR: Los m2 solicitados superan el limite de la asignacion contrada para la feria " + tasignacionRead.getFair_id() + " en el pabellon " + tasignacionRead.getPavilion_id() + "\n");
                            } else {
                                if (tasignacionRead.getUsed_m2() + stand.getTotal_m2() <= tasignacionRead.getTotal_m2()) {
                                    if (!stand.getActive()) {
                                        id = daoStand.update(stand);
                                    } else {
                                        if (tasignacionRead.getActive() && tparticipacionRead.getActive()) {
                                            id = daoStand.update(stand);
                                            tasignacionRead.setUsed_m2(tasignacionRead.getTotal_m2() + stand.getTotal_m2());
                                            daoAsignacion.update(tasignacionRead);
                                        } else
                                            throw new ASException("La participacion y/o la asignacion a la que pertenece el stand " + stand.getId() + " esta(n) desactivada(s).\n");
                                    }
                                } else
                                    throw new ASException("ERROR: Los m2 solicitados superan el limite de la asignacion contrada para la feria " + tasignacionRead.getFair_id() + " en el pabellon " + tasignacionRead.getPavilion_id() + "\n");

                            }
                        } else
                            throw new ASException("ERROR: Asignacion y Participacion referenciadas no existen en la base de datos\n");
                    } else
                        throw new ASException("ERROR: Los datos introducidos no son correctos.\n");
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
        Tstand stand;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id > 0) {
            try {
                Tstand read = daoStand.readById(id);
                if (read != null)
                    stand = read;
                else
                    throw new ASException("ERROR: El stand " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return stand;
    }

    public Collection<Tstand> showByAssignation(Integer id) throws ASException {
        Collection<Tstand> stands;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id > 0) {
            try {
                Collection<Tstand> read = daoStand.readByAssignation(id);
                if (read != null)
                    stands = read;
                else
                    throw new ASException("ERROR: No existe una asignacion" + id + ".\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return stands;
    }

    public Collection<Tstand> showByParticipation(Integer id) throws ASException {
        Collection<Tstand> stands;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id > 0) {
            try {
                Collection<Tstand> read = daoStand.readByParticipation(id);
                if (read != null)
                    stands = read;
                else
                    throw new ASException("ERROR: No existe una participacion " + id + ".\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return stands;
    }

}
