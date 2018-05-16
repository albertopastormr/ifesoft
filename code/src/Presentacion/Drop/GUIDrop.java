package Presentacion.Drop;

import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.Tstand;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;

public class GUIDrop extends UIStructureFrame {

    private JDialog sureFrame;
    private boolean chosen = false;

    private JComboBox<String> comboBoxDrop;
    private JTextField textID;

    private Font fComboBox = new Font(Font.DIALOG, Font.PLAIN, 40);
    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fLabelSubId = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fTextField = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cOkButton = new Color(26, 184, 59);
    private Color cComboBoxActive = new Color(207, 216, 220);
    private Color cComboBoxInactive = new Color(187, 196, 200);
    private Color cComboBoxFont = new Color(84, 91, 94);
    private Color cComboBoxSelectedFont = new Color(52, 56, 58);
    private Color cTextFieldBG = new Color(243,243,243);

    GUIDrop() {
        super("Drop");

        this.helpMessage = "<html><h1>DROP PAGE HELP</1>Here you have the possibility to <b>Delete</b> a <u>Fair</u>" +
                " or other entities that you can choose by clicking on the comboBox." +
                "<br>Click <b>'Next'</b> to confirm or <b>'Cancel'</b> to go back to the previous page." +
                "</html>";

        initComponents();

        this.setVisible(true);
    }

        @Override
        protected void okButtonActionPerformed(ActionEvent e) {
            chosen = false;
            setupSure();
        }


        private void delete() throws Exception {

            switch (String.valueOf(comboBoxDrop.getSelectedItem())) {
                case "Fair":
                    this.setVisible(false);
                    Controller.getInstance().execute(Event.DROP_FAIR, new Tferia( Integer.parseInt(textID.getText()), null, null,null, null, null));
                    break;
                case "Pavilion":
                    this.setVisible(false);
                    Controller.getInstance().execute(Event.DROP_PAVILION, new Tpabellon(Integer.parseInt(textID.getText()), -1, -1, null));
                    break;
                case "Client":
                    this.setVisible(false);
                    Controller.getInstance().execute(Event.DROP_CLIENT, new Tparticipante(Integer.parseInt(textID.getText()), null, -1, null));
                    break;
                case "Assignation":
                    this.setVisible(false);
                    Controller.getInstance().execute(Event.DROP_ASSIGNATION, new Tasignacion(Integer.parseInt(textID.getText()), -1,-1, -1, -1, null));
                    break;
                case "Participation":
                    this.setVisible(false);
                    Controller.getInstance().execute(Event.DROP_PARTICIPATION, new Tparticipacion(Integer.parseInt(textID.getText()), -1, -1, null));
                    break;
                case "Stand":
                    this.setVisible(false);
                    Controller.getInstance().execute(Event.DROP_STAND, new Tstand(Integer.parseInt(textID.getText()), -1, -1, -1, -1,-1, null));
                    break;

             }
        }

        @Override
        protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
            this.setVisible(false);
            Controller.getInstance().execute(Event.HOME, null);
        }

        private void closeOkFrame(ActionEvent e) throws Exception {
            sureFrame.setVisible(false);
            delete();
        }

        private void closeNotOkFrame(ActionEvent e){
            sureFrame.setVisible(false);
        }



    private JPanel setupSureButtonBar(){
            Dimension buttonDim = new Dimension(150, 80);

            JPanel sureButtonBar;

            //---- cancelButton ----
            JButton denyButton = new JButton();
            denyButton.setText("Cancel");
            denyButton.setFont(fButton);
            denyButton.setBackground(cCancelButton);
            denyButton.setForeground(Color.WHITE);
            denyButton.setPreferredSize(buttonDim);
            denyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeNotOkFrame(e);
                }
            });


            //---- okButton ----
            JButton acceptButton = new JButton();
            acceptButton.setText("Drop");
            acceptButton.setFont(fButton);
            acceptButton.setBackground(cOkButton);
            acceptButton.setForeground(Color.WHITE);
            acceptButton.setPreferredSize(buttonDim);
            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        closeOkFrame(e);
                    }catch (NumberFormatException e2){
                        new PanelProblemUser("Enter valid characters.");
                    } catch (Exception e1){
                        new PanelProblemUser(e1.getMessage());
                    }
                }
            });

            sureButtonBar = new JPanel();
            FlowLayout layout = new FlowLayout();
            layout.setHgap(25);
            sureButtonBar .setLayout(layout);
            sureButtonBar .add(denyButton);
            sureButtonBar .add(Box.createHorizontalStrut(200));
            sureButtonBar .add(acceptButton);

            return sureButtonBar;
        }

        private void setupSure(){
            sureFrame = new JDialog();
            sureFrame.setResizable(false);
            sureFrame.setAlwaysOnTop(true);
            sureFrame.setSize(new Dimension(1000, 300));
            sureFrame.setLocationRelativeTo(null);
            sureFrame.setLocationRelativeTo(null);


            JPanel surePane = new JPanel();
            surePane.setBorder(new EmptyBorder(12, 12, 12, 12));
            surePane.setLayout(new BorderLayout());

            JLabel labelQuestion = new JLabel();
            labelQuestion.setText("Are you sure that you want to permanently delete this module?");
            labelQuestion.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
            labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);
            labelQuestion.setPreferredSize(new Dimension(1000, 100));
            surePane.add(labelQuestion, BorderLayout.CENTER);

            surePane.add(setupSureButtonBar(), BorderLayout.PAGE_END);
            sureFrame.add(surePane);

            pack();
            if(chosen){
                sureFrame.setVisible(false);
            }
            else{
                sureFrame.setVisible(true);
            }
        }

        @Override
        protected void setUpTitle(){

            title = new JLabel();
            title .setText("Drop");
            title .setFont(fTitle);
            title .setHorizontalAlignment(JLabel.CENTER);
            title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

        }

        @Override
        protected void setUpCenter(){

            centerPanel = new JPanel();
            BoxLayout centerLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
            centerPanel.setLayout(centerLayout);

            //===== JComboBox =====

            UIManager.put("JTextField.background", new ColorUIResource(cComboBoxInactive));
            UIManager.put("ComboBox.selectionBackground", new ColorUIResource(cComboBoxActive));
            UIManager.put("ComboBox.selectionForeground", new ColorUIResource(cComboBoxSelectedFont));
            UIManager.put("ComboBox.disabledBackground", new ColorUIResource(cComboBoxInactive));
            UIManager.put("ComboBox.disabledForeground", new ColorUIResource(cComboBoxFont));


            comboBoxDrop = new JComboBox<>();
            comboBoxDrop.getEditor().getEditorComponent().setBackground(cComboBoxActive);
            comboBoxDrop.setFont(fComboBox);
            comboBoxDrop.setForeground(cComboBoxFont);
            comboBoxDrop.setMinimumSize(new Dimension(200, 50));
            comboBoxDrop.setMaximumSize(new Dimension(800, 50));

            comboBoxDrop.addItem("Assignation");
            comboBoxDrop.addItem("Client");
            comboBoxDrop.addItem("Fair");
            comboBoxDrop.addItem("Participation");
            comboBoxDrop.addItem("Pavilion");
            comboBoxDrop.addItem("Stand");

            comboBoxDrop.setBorder(BorderFactory.createEmptyBorder(0,0, 20, 0));
            centerPanel.add(comboBoxDrop);

            //===== TextField =====

            JPanel textFieldPanel = new JPanel();
            FlowLayout textFieldPanelLayout = new FlowLayout();
            textFieldPanel.setLayout(textFieldPanelLayout);

            JLabel labelSubID = new JLabel();
            labelSubID.setText("ID:");
            labelSubID.setFont(fLabelSubId);

            textFieldPanel.add(labelSubID);

            textID = new JTextField();
            textID.setFont(fTextField);
            textID.setBackground(cTextFieldBG);
            textID.setMinimumSize(new Dimension(200, 50));
            textID.setPreferredSize(new Dimension(400, 50));
            textID.setMaximumSize(new Dimension(400, 50));

            textFieldPanel.add(textID);

            centerPanel.add(textFieldPanel);

        }

        @Override
        protected void setUpButtonBar(){
            super.setUpButtonBar();

            Dimension buttonDim = new Dimension(150, 80);

            //---- okButton ----
            JButton okButton = new JButton();
            okButton.setText("Drop");
            okButton.setFont(fButton);
            okButton.setBackground(cOkButton);
            okButton.setForeground(Color.WHITE);
            okButton.setPreferredSize(buttonDim);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    okButtonActionPerformed(e);
                }
            });

            buttonBar.add(okButton);

        }

    @Override
    public void update(int event, Object data) {
        if(data != null) JOptionPane.showMessageDialog(null,"The module has been dropped successfully");
        else JOptionPane.showMessageDialog(null, "A problem in the drop process occurred, insert module's data another time please", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
