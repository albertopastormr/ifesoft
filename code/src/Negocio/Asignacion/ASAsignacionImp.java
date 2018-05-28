package Negocio.Asignacion;


import Exceptions.ASException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Asignacion.IFDAOAsignacion;
import Integracion.Pabellon.DAOPabellon;
import Integracion.Feria.DAOFeria;
import Integracion.Stand.DAOStand;
import Negocio.Feria.Tferia;
import Integracion.Stand.IFDAOStand;
import Negocio.Stand.Tstand;
import Negocio.Pabellon.Tpabellon;
import Integracion.Pabellon.IFDAOPabellon;
import Integracion.Feria.IFDAOFeria;

import java.util.ArrayList;
import java.util.Collection;

public class ASAsignacionImp implements ASAsignacion {
    public Integer create(Tasignacion asignacion) throws ASException {
        int id;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();

        //Comprobacion de que los datos del transfer asignacion son correctos
        if (asignacion != null && asignacion.getFair_id() > 0 && asignacion.getPavilion_id() > 0 && asignacion.getUsed_m2() >= 0 && asignacion.getTotal_m2() >= 0) {
            try {
                Tpabellon transferPavilion = daoPabellon.readById(asignacion.getPavilion_id()); //Leemos el pabellon por ID para obtener atributos de el
                Tferia transferFair = daoFeria.readById(asignacion.getFair_id());
                if (transferFair != null && transferPavilion != null) {
                    Tasignacion transferAssignation = daoAsignacion.readByFairIdPavilionId(asignacion.getFair_id(), asignacion.getPavilion_id());
                    //si el transferAsignacion es null, quiere decir que no ha encontrado ninguna feria con pabellon asociado a una asignacion por lo que podemos continuar
                    if (transferAssignation == null) {
                        //Si los metros cuadrados usados son > 0, los metros cuadrados contratados > m2 usados Y los m2 contratados son < m2 totales
                        //del pabellon  entonces podemos crear la asignación
                        if (asignacion.getUsed_m2() >= 0 && asignacion.getTotal_m2() >= asignacion.getUsed_m2() && asignacion.getTotal_m2() <= transferPavilion.getTotal_m2() && transferFair.getActive() && transferPavilion.getActive())
                            id = daoAsignacion.create(asignacion);
                        else
                            throw new ASException("ERROR: Los datos de la asignacion no son correctos. (m2 Incorrectos o pabellon desactivado)\n");
                    } else {
                        if (!transferAssignation.getActive() && asignacion.getActive() && transferPavilion.getActive() && transferFair.getActive()) {
                            asignacion.setId(transferAssignation.getId());
                            id = daoAsignacion.update(asignacion);
                        } else
                            throw new ASException("ERROR: La asignacion Feria(" + asignacion.getFair_id() + ") Pabellon(" + asignacion.getPavilion_id() + ") ya existe.\n");
                    }
                } else
                    throw new ASException("ERROR: La feria o el pabellon introducidos no existen.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion o los datos de la asignacion no son correctos\n");
        return id;
    }

    //Al realizar borrado en cascada borramos asignacion y a su vez los stands asociados a esa asignacion
    public Integer drop(Integer id) throws ASException {
        int idr;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        ArrayList<Tstand> readStandList;

        if (id > 0) {
            try {
                Tasignacion transferAssignation = daoAsignacion.readById(id);
                //Si es distinto de null quiere decir que tenemos una asignacion  con ese id, por lo que podemos borrarla.
                if (transferAssignation != null && transferAssignation.getActive()) {
                    transferAssignation.setActive(false);
                    readStandList = (ArrayList<Tstand>) daoStand.readByAssignation(id);
                    //Borramos para una asignacion en concreto, todos sus stands
                    for (int j = 0; j < readStandList.size(); j++) {
                        Tstand tStand = readStandList.get(j);
                        tStand.setActive(false);
                        daoStand.update(tStand);
                    }
                    idr = daoAsignacion.update(transferAssignation);
                } else
                    throw new ASException("ERROR: La asignacion " + id + "  no existe o ya esta desactivada.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return idr;
    }

    public Integer modify(Tasignacion asignacion) throws ASException {
        int id;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();

        if (asignacion != null && asignacion.getId() > 0 && asignacion.getFair_id() > 0 && asignacion.getPavilion_id() > 0 && asignacion.getUsed_m2() >= 0 && asignacion.getTotal_m2() >= 0) {
            try {
                Tasignacion transferAssignation = daoAsignacion.readById(asignacion.getId());
                if (transferAssignation != null) {
                    if (asignacion.getFair_id() == transferAssignation.getFair_id() && asignacion.getPavilion_id() == transferAssignation.getPavilion_id()) {
                        Tferia feria = daoFeria.readById(transferAssignation.getFair_id());
                        Tpabellon pabellon = daoPabellon.readById(transferAssignation.getPavilion_id());
                        if (!asignacion.getActive() && transferAssignation.getActive())
                            asignacion.setActive(true);
                        if (asignacion.getActive() && !transferAssignation.getActive())
                            if (!feria.getActive() || !pabellon.getActive())
                                asignacion.setActive(false);
                        id = daoAsignacion.update(asignacion);
                    } else
                        throw new ASException("ERROR: No se pueden modificar el pabellon y/o feria de una asignacion.\n");
                } else
                    throw new ASException("ERROR: La asignacion " + asignacion.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: Los datos introducidos no son correctos.\n");
        return id;
    }

    public Collection<Tasignacion> list() throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        Collection<Tasignacion> collection;
        try {
            collection = daoAsignacion.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    //Tomado como show by id asignation
    //public Tasignacion show(Integer fair_id, Integer pavilion_id, Integer stand_id) throws ASException {
    public Tasignacion show(Integer assignation_id) throws ASException {
        Tasignacion asig;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (assignation_id > 0) {
            try {
                Tasignacion transferAssignation = daoAsignacion.readById(assignation_id);
                if (transferAssignation != null) //si la asignacion leida existe en la bbdd
                    asig = transferAssignation;
                else
                    throw new ASException("ERROR: La asignacion " + assignation_id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return asig;
    }

    public Collection<Tasignacion> showByIdPavilion(Integer pavilion_id) throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        Collection<Tasignacion> collection;
        //Si el id es correcto
        if (pavilion_id > 0) {
            try {
                Tpabellon transferPavilion = daoPabellon.readById(pavilion_id);
                if (transferPavilion != null)
                    collection = daoAsignacion.readByPavilionId(pavilion_id);
                else
                    throw new ASException("ERROR: El pabellon con ID: (" + pavilion_id + ") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return collection;
    }

    public Collection<Tasignacion> showByIdFair(Integer fair_id) throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Collection<Tasignacion> collection;

        //Si el id es correcto
        if (fair_id > 0) {
            try {
                Tferia transferFair = daoFeria.readById(fair_id);
                if (transferFair != null)
                    collection = daoAsignacion.readByFairId(fair_id);
                else
                    throw new ASException("ERROR: La feria con ID: (" + fair_id + ") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return collection;
    }
}
