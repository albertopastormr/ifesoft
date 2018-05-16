package Presentacion.Events;

public class Event {

    /** HOME */
    public static final int HOME = 0;

    /** PAGINAS INTERMEDIAS SELECCIONADORAS DE MODULO */
    public static final int CREATE_HALF = 1;
    public static final int SHOW_HALF = 2;
    public static final int MODIFY_HALF = 3;
    public static final int DROP_HALF = 4;

    /** PAGINAS INTERMEDIAS INSERCION DE VALORES */

    // FERIA
    public static final int DROP_HALF_FERIA = 5;

    // PABELLON
    public static final int DROP_HALF_PABELLON = 6;

    // STAND
    public static final int DROP_HALF_STAND = 7;

    // PARTICIPACION
    public static final int DROP_HALF_PARTICIPACION = 8;

    // PARTICIPANTE
    public static final int DROP_HALF_PARTICIPANTE = 9;

    // ASIGNACION
    public static final int DROP_HALF_ASIGNACION = 10;

    public static final int DROP_FAIR = 11;
    public static final int DROP_STAND = 12;
    public static final int DROP_ASSIGNATION = 13;
    public static final int DROP_CLIENT = 14;
    public static final int DROP_PAVILION = 15;
    public static final int DROP_PARTICIPATION = 16;

    /** PAGINAS MAS ESPECIFICAS DE CADA ACCION */
    public static final int INSERT_FORM_FAIR = 17;
    public static final int MODIFY_FORM_FAIR = 18;
    public static final int INSERT_FORM_ASSIGNATION = 19;
    public static final int MODIFY_FORM_ASSIGNATION = 20;
    public static final int INSERT_FORM_PAVILION = 21;
    public static final int MODIFY_FORM_PAVILION = 22;
    public static final int INSERT_FORM_PARTICIPATION = 23;
    public static final int MODIFY_FORM_PARTICIPATION = 24;
    public static final int INSERT_FORM_STAND = 25;
    public static final int MODIFY_FORM_STAND = 26;
    public static final int INSERT_FORM_CLIENT = 27;
    public static final int MODIFY_FORM_CLIENT = 28;

    /** EVENTOS FINALES CON NEGOCIO */
    public static final int INSERT_FAIR = 29;
    public static final int MODIFY_FAIR = 30;
    public static final int INSERT_CLIENT = 31;
    public static final int MODIFY_CLIENT = 32;
    public static final int INSERT_PAVILION = 33;
    public static final int MODIFY_PAVILION = 34;
    public static final int INSERT_ASSIGNATION = 35;
    public static final int MODIFY_ASSIGNATION = 36;
    public static final int INSERT_STAND = 37;
    public static final int MODIFY_STAND = 38;
    public static final int INSERT_PARTICIPATION = 39;
    public static final int MODIFY_PARTICIPATION = 40;

    /** EVENTOS SHOWS DE LISTAR E INDIVIDUALES */

    public static final int SHOW_FAIR_INDIVIDUAL = 41;
    public static final int SHOW_FAIR_LIST = 42;

    public static final int SHOW_CLIENT_INDIVIDUAL = 43;
    public static final int SHOW_CLIENT_LIST = 44;

    public static final int SHOW_FAIR_LIST_DATES = 45;
    public static final int SHOW_PAVILION_INDIVIDUAL = 46;
    public static final int SHOW_PAVILION_LIST = 47;
    public static final int SHOW_ASSIGANTION_FAIR = 48;
    public static final int SHOW_ASSIGNATION_PAVILION = 49;
    public static final int SHOW_PARTICIPATION_FAIR = 50;
    public static final int SHOW_PARTICIPATION_CLIENT = 51;
    public static final int SHOW_STAND_INDIVIDUAL = 52;
    public static final int SHOW_STAND_LIST = 53;
    public static final int SHOW_REGION_PABELLON = 54;
    public static final int SHOW_PAIS_PABELLON = 55;
    public static final int SHOW_ASSIGNATION_INDIVIDUAL = 56;
    public static final int SHOW_PARTICIPATION_INDIVIDUAL = 57;
    public static final int SHOW_STAND_ASSIGNATION = 58;
    public static final int SHOW_STAND_PARTICIPATION = 59;
    public static final int SHOW_ASSIGANTION_LIST = 60;
    public static final int SHOW_PARTICIPATION_LIST = 61;
}
