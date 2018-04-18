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
    public static final int DROP_HALF_FERIA = 9;

    // PABELLON
    public static final int DROP_HALF_PABELLON = 9345;

    // STAND
    public static final int DROP_HALF_STAND = 9768;

    // PARTICIPACION
    public static final int DROP_HALF_PARTICIPACION = 9121;

    // PARTICIPANTE
    public static final int DROP_HALF_PARTICIPANTE = 9435;

    // ASIGNACION
    public static final int DROP_HALF_ASIGNACION = 92143;

    public static final int DROP_FERIA = 9213;
    public static final int DROP_STAND = 921398;
    public static final int DROP_ASIGNACION = 921334;
    public static final int DROP_CLIENT = 921343;
    public static final int DROP_PABELLON = 921312;
    public static final int DROP_PARTICIPACION = 921376;

    /** PAGINAS MAS ESPECIFICAS DE CADA ACCION */
    public static final int INSERT_FORM_FERIA = 10;
    public static final int MODIFY_FORM_FERIA = 11;
    public static final int INSERT_FORM_ASIGNACION = 100;
    public static final int MODIFY_FORM_ASIGNACION = 1123;
    public static final int INSERT_FORM_PABELLON = 102141;
    public static final int MODIFY_FORM_PABELLON= 114324;
    public static final int INSERT_FORM_PARTICIPACION = 103463;
    public static final int MODIFY_FORM_PARTICIPACION = 11123123;
    public static final int INSERT_FORM_STAND = 1011334;
    public static final int MODIFY_FORM_STAND = 119899;
    public static final int INSERT_FORM_PARTICIPANTE = 3242324;
    public static final int MODIFY_FORM_PARTICIPANTE = 98349;

    /** EVENTOS FINALES CON NEGOCIO */
    public static final int INSERT_FAIR = 12;
    public static final int MODIFY_FAIR = 15;
    public static final int INSERT_CLIENT = 200;
    public static final int MODIFY_CLIENT = 240;
    public static final int INSERT_PAVILION = 234234;
    public static final int MODIFY_PAVILION = 32523;
    public static final int INSERT_ASSIGNATION = 111112;
    public static final int MODIFY_ASSIGNATION = 325235;
    public static final int INSERT_STAND = 23455234;
    public static final int MODIFY_STAND = 3432523;
    public static final int INSERT_PARTICIPACION = 44345;
    public static final int MODIFY_PARTICIPACION = 56789;

    /** EVENTOS SHOWS DE LISTAR E INDIVIDUALES */

    public static final int SHOW_FAIR_INDIVIDUAL = 34325623;
    public static final int SHOW_FAIR_LIST = 343256363;

    public static final int SHOW_CLIENT_INDIVIDUAL = 50;
    public static final int SHOW_CLIENT_LIST = 120;

    public static final int SHOW_FAIR_LIST_DATES = 432;
    public static final int SHOW_PAVILION_INDIVIDUAL = 543;
    public static final int SHOW_PAVILION_LIST = 765;
    public static final int SHOW_ASSIGANTION_FAIR = 989;
    public static final int SHOW_ASSIGNATION_PAVILION = 8787;
    public static final int SHOW_PARTICIPATION_FAIR = 6545;
    public static final int SHOW_PARTICIPACION_CLIENT = 334123;
    public static final int SHOW_STAND_INDIVIDUAL = 34574;
    public static final int SHOW_STAND_LIST = 99987;
    public static final int SHOW_REGION_PABELLON = 234231;
    public static final int SHOW_PAIS_PABELLON = 123412;
}
