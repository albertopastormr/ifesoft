package Presentacion;

import javax.swing.*;

public abstract class UI extends JFrame{

    private static UIimp ui;

    abstract void update(int event, Object data);
    abstract void start();

    public static synchronized UIimp getInstance(){
        if(ui==null)
            ui=new UIimp();
        return ui;
    }
}
