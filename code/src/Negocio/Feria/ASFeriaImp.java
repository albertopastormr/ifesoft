package Negocio.Feria;

import Exceptions.ASException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Feria.DAOFeria;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Stand.IFDAOStand;
import Negocio.Stand.Tstand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ASFeriaImp implements ASFeria { // Try-Catch solo si hay que capturar excepciones del DAO
    public Integer create(Tferia feria) throws ASException {
        int id;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null && feria.getName() != null && feria.getDescription() != null && feria.getIniDate() != null && feria.getEndDate() != null) {
            try {
                Tferia read = daoFeria.readByName(feria.getName());
                if (read == null) {
                    Date currentDate = new Date();
                    if (feria.getIniDate().after(currentDate) && feria.getEndDate().after(feria.getIniDate()))
                        id = daoFeria.create(feria);
                    else
                        throw new ASException("ERROR: El intervalo de fechas no es correcto.\n");
                } else {
                    if (read.equals(feria)) {
                        if (!read.getActive()) {
                            read.setActive(true);
                            id = daoFeria.update(read);
                        } else
                            throw new ASException("ERROR: La feria " + feria.getName() + " ya esta activa.\n");
                    } else
                        throw new ASException("ERROR: La feria " + feria.getName() + " ya esta siendo utilizada con id " + read.getId() + ".\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria correctamente.\n");
        return id;
    }

    public Integer drop(Integer id) throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        ArrayList<Tasignacion> listaAsignaciones;
        ArrayList<Tstand> readStandList;

        int idr;

        if (id > 0) {
            try {
                Tferia read = daoFeria.readById(id);
                if (read != null) {
                    listaAsignaciones = (ArrayList<Tasignacion>) daoAsignacion.readByFairId(read.getId());
                    for (int i = 0; i < listaAsignaciones.size(); i++) {
                        Tasignacion tAssignation = listaAsignaciones.get(i);
                        tAssignation.setActive(false);
                        daoAsignacion.update(tAssignation);
                        readStandList = (ArrayList<Tstand>) daoStand.readByAssignation(tAssignation.getId());
                        for (int j = 0; j < readStandList.size(); j++) {
                            Tstand tStand = readStandList.get(j);
                            //Desactivamos ese stand
                            tStand.setActive(false);
                            daoStand.update(tStand);
                            Tparticipacion tParticipation = daoParticipacion.readById(tStand.getParticipation_id());
                            tParticipation.setActive(false);
                            daoParticipacion.update(tParticipation);
                        }
                    }

                    read.setActive(false);

                    idr = daoFeria.update(read);
                } else
                    throw new ASException("ERROR: La feria " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID no es valido.\n");
        return idr;
    }

    public Integer modify(Tferia feria) throws ASException {
        int id;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null && feria.getName() != null && feria.getId() > 0) {
            try {
                Tferia read = daoFeria.readById(feria.getId());
                if (read != null) {
                    Tferia nameOK = daoFeria.readByName(feria.getName());
                    if ((nameOK == null) || nameOK.getName().equals(read.getName())) {
                        Date currentDate = new Date();
                        if (feria.getIniDate().after(currentDate) && feria.getEndDate().after(feria.getIniDate())) {
                            if(!feria.getActive() && read.getActive())
                                feria.setActive(true);
                            id = daoFeria.update(feria);
                        } else
                            throw new ASException("ERROR: El intervalo de fechas no es correcto.\n");
                    } else
                        throw new ASException("ERROR: El nuevo nombre para la feria " + feria.getName() + " ya esta siendo usado.\n");
                } else
                    throw new ASException("ERROR: La feria " + feria.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID no es valido.\n");
        return id;
    }

    public Collection<Tferia> list() throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Collection<Tferia> collection;
        try {
            collection = daoFeria.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    @Override
    @SuppressWarnings("deprecation")
    public Collection<Tferia> listDates(Tferia feria) throws ASException {
        Date ini, end;
        Collection<Tferia> collection;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria == null || feria.getIniDate() == null || feria.getEndDate() == null) {
            ini = new Date();
            ini.setMonth(0);
            ini.setDate(1);
            end = new Date();
            end.setMonth(11);
            end.setDate(31);
        } else {
            ini = feria.getIniDate();
            end = feria.getEndDate();
        }
        try {
            collection = daoFeria.readByDates(ini, end);
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tferia showByName(String name) throws ASException {
        Tferia feria;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (name != null) {
            try {
                Tferia read = daoFeria.readByName(name);
                if (read != null)
                    feria = read;
                else
                    throw new ASException("ERROR: La feria " + name + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se ha introducido un nombre.\n");
        return feria;
    }

    public Tferia showById(Integer id) throws ASException {
        Tferia feria;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (id > 0) {
            try {
                Tferia read = daoFeria.readById(id);
                if (read != null)
                    feria = read;
                else
                    throw new ASException("ERROR: La feria " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return feria;
    }
}
