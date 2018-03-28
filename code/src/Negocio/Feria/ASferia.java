package Negocio.Feria;

public interface ASferia {
    Integer create(Tferia feria);

    Integer drop(Tferia feria);

    Integer modify(Tferia feria);

    void list();

    Tferia show(Tferia feria);
}
