package Negocio.Asignacion;


import Exceptions.ASException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Pabellon.DAOPabellon;
import Integracion.Feria.DAOFeria;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Stand.Tstand;
import Negocio.Asignacion.Tasignacion;
import Negocio.Pabellon.Tpabellon;
import Negocio.Pabellon.IFDAOPabellon;
import Negocio.Feria.IFDAOFeria;

import java.util.Collection;

public class ASAsignacionImp implements ASAsignacion {
    public Integer create(Tasignacion asignacion) throws ASException {
        int id;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();

        //Comprobacion de que los datos del transfer asignacion son correctos
        if (asignacion != null && asignacion.getFair_id() != -1 && asignacion.getPavilion_id() != -1 && asignacion.getUsed_m2() != -1 && asignacion.getTotal_m2() > 0) {
            try {
                Tpabellon transferPavilion = daoPabellon.readById(asignacion.getPavilion_id()); //Leemos el pabellon por ID para obtener atributos de el
                Tferia transferFair = daoFeria.readById(asignacion.getFair_id());
                if (transferFair != null && transferPavilion != null) {
                    Tasignacion transferAssignation = daoAsignacion.readByFairIdPavilionId(asignacion.getFair_id(), asignacion.getPavilion_id());
                    //si el transferAsignacion es null, quiere decir que no ha encontrado ninguna feria con pabellon asociado a una asignacion por lo que podemos continuar
                    if (transferAssignation == null) {
                        //Si los metros cuadrados usados son > 0, los metros cuadrados contratados > m2 usados Y los m2 contratados son < m2 totales
                        //del pabellon  entonces podemos crear la asignación
                        if (asignacion.getUsed_m2() >= 0 && asignacion.getTotal_m2() >= asignacion.getUsed_m2() && asignacion.getTotal_m2() <= transferPavilion.getTotal_m2())
                            id = daoAsignacion.create(asignacion);
                        else
                            throw new ASException("ERROR: Los datos de la asignacion no son correctos.\n");
                    } else {
                        if (!transferAssignation.getActive() && asignacion.getActive())
                            id = daoAsignacion.update(asignacion);
                        else
                            throw new ASException("ERROR: La asignacion Feria(" + asignacion.getFair_id() + ") Pabellon(" + asignacion.getPavilion_id() + ") ya existe.\n");
                    }
                } else
                    throw new ASException("ERROR: La feria o el pabellon introducidos no existen.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");
        return id;
    }

    public Integer drop(Tasignacion asignacion) throws ASException {
        int id;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignacion != null) {
            try {
                Tasignacion transferAssignation = daoAsignacion.readById(asignacion.getId());
                //Si es distinto de null quiere decir que tenemos una asignacion activa con ese id, por lo que podemos borrarla.
                if (transferAssignation != null && transferAssignation.getActive()) {
                    transferAssignation.setActive(false);
                    id = daoAsignacion.update(transferAssignation);
                } else
                    throw new ASException("ERROR: La asignacion " + asignacion.getId() + "  no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");
        return id;
    }

    public Integer modify(Tasignacion asignacion) throws ASException {
        int id;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();

        if (asignacion != null && asignacion.getId() != -1 && asignacion.getFair_id() != -1 && asignacion.getPavilion_id() != -1 && asignacion.getUsed_m2() >= 0 && asignacion.getTotal_m2() > 0) {
            try {
                Tasignacion transferAssignation = daoAsignacion.readById(asignacion.getId());
                if (transferAssignation != null) {
                    if (asignacion.getFair_id() == transferAssignation.getFair_id() && asignacion.getPavilion_id() == transferAssignation.getPavilion_id()) {
                        if (!asignacion.getActive()) {
                            asignacion.setActive(false);
                            id = daoAsignacion.update(asignacion);
                        } else
                            id = daoAsignacion.update(asignacion);
                    } else
                        throw new ASException("ERROR: No se pueden modificar el pabellon y/o feria de una asignacion.\n");
                } else
                    throw new ASException("ERROR: La asignacion " + asignacion.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");
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
    public Tasignacion show(Integer asignation_id) throws ASException {
        Tasignacion asig;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignation_id > 0) {
            try {
                Tasignacion transferAssignation = daoAsignacion.readById(asignation_id);
                if (transferAssignation != null) //si la asignacion leida existe en la bbdd
                    asig = transferAssignation;
                else
                    throw new ASException("ERROR: La asignacion " + asignation_id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
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
            throw new ASException("ERROR: No se ha introducido el id del pabellon a leer.\n");
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
            throw new ASException("ERROR: No se ha introducido el id de la feria a leer.\n");
        return collection;
    }
}
