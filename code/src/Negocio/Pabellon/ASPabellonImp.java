package Negocio.Pabellon;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.sql.SQLException;

import Integracion.Asignacion.DAOAsignacion;
import Integracion.Feria.DAOFeria;
import Integracion.Pabellon.DAOPabellon;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Stand.DAOStand;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.IFDAOFeria;
import Negocio.Feria.Tferia;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Stand.IFDAOStand;
import Negocio.Stand.Tstand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;

public class ASPabellonImp implements ASPabellon {
    public Integer create(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getTotal_m2() >= 0 && pabellon.getCapacity() >= 0) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read == null) {
                    if (pabellon.getTotal_m2() / pabellon.getCapacity() >= 1)
                        return daoPabellon.create(pabellon);
                    else
                        throw new ASException("ERROR: La capacidad del pabellon es demasiado alta respecto a los metros de este.\n");
                } else {
                    if (!read.getActive()) {
                        read.setActive(true);
                        return daoPabellon.update(read);
                    } else
                        throw new ASException("ERROR: El pabellon " + pabellon.getId() + " ya existe.\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
        	// introduzco la excepcion de arriba aqui
            throw new ASException("ERROR: No se han introducido datos correctos del pabellon\n");
    }

    public Integer drop(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        ArrayList<Tasignacion> listaAsignaciones = new ArrayList<>();
        ArrayList<Tstand> readStandList = new ArrayList<>();

        if (pabellon != null && pabellon.getId() > 0) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null) {
                    read.setActive(false);
                    //Guardamos la lista de asignaciones implicadas en ese pabellon
                    listaAsignaciones = (ArrayList<Tasignacion>)daoAsignacion.readByPavilionId(read.getId());
                    //Numero de asignaciones que tenemos en el arraylist para poder iterar
                    for(int i = 0; i < listaAsignaciones.size(); i++){
                         //Desactivacion de todas las asignaciones que se corresponden con ese pabellon
                         Tasignacion tAsignacion = listaAsignaciones.get(i);
                         tAsignacion.setActive(false);
                         daoAsignacion.update(tAsignacion);
                         //Desactivacion de stands referenciados en ese pabellon
                        readStandList = (ArrayList<Tstand>)daoStand.readByAssignation(tAsignacion.getId());
                        for(int j = 0; j < readStandList.size(); j++){
                            Tstand tStand = readStandList.get(j);
                            tStand.setActive(false);
                            daoStand.update(tStand);
                            //Para cada stand, borramos su participacion tambien.
                            Tparticipacion tParticipacion = daoParticipacion.readById(tStand.getParticipation_id());
                            tParticipacion.setActive(false);
                            daoParticipacion.update(tParticipacion);
                        }
                    }
                    return daoPabellon.update(read);
                } else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID del pabellon no es valido.\n");
    }

    public Integer modify(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getId() > 0) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null) {
                    if (pabellon.getCapacity()  > 0 && pabellon.getTotal_m2() >= pabellon.getCapacity()) {
                        if(!pabellon.getActive()){
                            pabellon.setActive(false);
                            return daoPabellon.update(pabellon);
                        }
                        else
                            return daoPabellon.update(pabellon);
                    } else
                        throw new ASException("ERROR: La capacidad del pabellon no es correcta.\n");
                } else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID del pabellon no es valido.\n");
    }

    public Collection<Tpabellon> list() throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        Collection<Tpabellon> collection;
        try {
            collection = daoPabellon.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tpabellon showById(Integer id) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (id > 0) {
            try {
                Tpabellon read = daoPabellon.readById(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: El pabellon " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID del pabellon no es valido.\n");
    }
}
