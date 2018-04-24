package Presentacion.Shows;

import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.Tstand;
import Presentacion.Events.Event;
import Presentacion.Home.GUIHome;
import Presentacion.Shows.List.*;
import Presentacion.Shows.individual.*;
import Presentacion.UI;

import java.util.Collection;

public class IFViewListImp extends IFViewList {

    @Override
    public UI generateSpecificView(int event, Object data) {
        switch (event){

            case Event.SHOW_HALF:
                return new GUIShow();

            /** -------- individuals shows -------- */

            case Event.SHOW_FAIR_INDIVIDUAL:
                return new GUIViewFair((Tferia) data);
            case Event.SHOW_PAVILION_INDIVIDUAL:
                return new GUIViewPavilion((Tpabellon) data);
            case Event.SHOW_CLIENT_INDIVIDUAL:
                return new GUIViewClient((Tparticipante) data);
            case Event.SHOW_ASSIGNATION_INDIVIDUAL:
                return new GUIViewAssignation((Tasignacion) data);
            case Event.SHOW_PARTICIPATION_INDIVIDUAL:
                return new GUIViewParticipation((Tparticipacion) data);
            case Event.SHOW_STAND_INDIVIDUAL:
                return new GUIViewStand((Tstand) data);

            /** -------- List shows -------- */

            case Event.SHOW_FAIR_LIST:
                return new GUIListFairs((Collection<Tferia>) data);
            case Event.SHOW_FAIR_LIST_DATES:
                return new GUIListFairs((Collection<Tferia>) data);
            case Event.SHOW_PAVILION_LIST:
                return new GUIListPavilions((Collection<Tpabellon>) data);
            case Event.SHOW_CLIENT_LIST:
                return new GUIListClient((Collection<Tparticipante>) data);
            case Event.SHOW_ASSIGNATION_PAVILION:
                return new GUIListAssignation((Collection<Tasignacion>) data);
            case Event.SHOW_ASSIGANTION_FAIR:
                return new GUIListAssignation((Collection<Tasignacion>) data);
            case Event.SHOW_PARTICIPATION_CLIENT:
                return new GUIListParticipation((Collection<Tparticipacion>) data);
            case Event.SHOW_PARTICIPATION_FAIR:
                return new GUIListParticipation((Collection<Tparticipacion>) data);
            case Event.SHOW_STAND_LIST:
                return new GUIListStand((Collection<Tstand>) data);
            case Event.SHOW_STAND_ASSIGNATION:
                return new GUIListStand((Collection<Tstand>) data);
            case Event.SHOW_STAND_PARTICIPATION:
                return new GUIListStand((Collection<Tstand>) data);
            default:
                return new GUIHome();
        }
    }
}
