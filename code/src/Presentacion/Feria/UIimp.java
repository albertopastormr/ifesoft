package Presentacion.Feria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIimp extends JFrame implements UI{

    private ControllerImp control;

    public UIimp(){
        super("IFESOFT SOFTWARE");

        // Se le da un valor a controller
        control = new ControllerImp();

        start();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    @Override
    public void update() {

    }

    @Override
    public void start() {

        // Inicializamos la vista de home (Principal)
        buildHome();
    }

    /**
     ----------------- Paginas de APP ------------------------
     */

    private void buildHome(){
        Container homePanel = generateContainBox();
        homePanel.add(buildTitle("IFESOFT"));
        homePanel.add(buildPageButtonHome());
    }

    private JPanel buildPageButtonHome() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2,2, 15, 15));

        JButton buttonShow = new JButton("Visualizar");
        JButton buttonModify = new JButton("Modificar");
        JButton buttonCreate = new JButton("Crear");
        JButton buttonDrop = new JButton("Borrar");

        // Actions listeners de los diferentes botones

        buttonCreate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        control.execute(Event.WRITE_DATA_FERIA, null);
                    }
                }
        );


        bottomPanel.add (buttonShow);
        bottomPanel.add (buttonModify);
        bottomPanel.add (buttonCreate);
        bottomPanel.add (buttonDrop);

        bottomPanel.setBorder(new EmptyBorder(100, 50, 100, 50));

        return bottomPanel;
    }



    /**
       ----------------- Metodos auxiliares ------------------------
     */

    private Container generateContainBox(){

        JFrame.setDefaultLookAndFeelDecorated(true);
        Container homePanel = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        homePanel.setLayout(new BoxLayout(homePanel,BoxLayout.PAGE_AXIS));

        this.pack();
        return homePanel;
    }

    private JLabel buildTitle(String titleStr){
        JLabel title = new JLabel(titleStr,SwingConstants.CENTER);
        title.setFont(new Font("",1,75));
        return title;
    }

}
