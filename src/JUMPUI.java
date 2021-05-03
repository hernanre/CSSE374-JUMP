import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Authors: Ricardo H, Carlos F, and Cehong W.


public class JUMPUI {

    private JFrame myFrame;

    private ExperimentCompiler experimentCompiler;

    public JUMPUI(ExperimentCompiler experimentCompiler) {
        this.experimentCompiler = experimentCompiler;

        myFrame = new JFrame();
        myFrame.setSize(800, 400);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("Scientist UI");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabPanel = new JTabbedPane();

        //Sample Only
        JPanel sampleOnlyTab = new JPanel(new GridLayout(5,2));
        tabPanel.addTab("Sample Only", null, sampleOnlyTab,
                "Sample Only");
        sampleOnlyTab.setBorder(new TitledBorder(new EtchedBorder(), "Sample Only Experiments"));

        JLabel type = new JLabel("Type: ");
        sampleOnlyTab.add(type);

        JTextField typeTextField = new JTextField();
        sampleOnlyTab.add(typeTextField);

        JLabel quantity = new JLabel("Quantity: ");
        sampleOnlyTab.add(quantity);

        JTextField quantityTextField = new JTextField();
        sampleOnlyTab.add(quantityTextField);

        JLabel unit = new JLabel("Unit: ");
        sampleOnlyTab.add(unit);

        JTextField unitTextField = new JTextField();
        sampleOnlyTab.add(unitTextField);

        JLabel location = new JLabel("Location: ");
        sampleOnlyTab.add(location);

        JTextField locationTextField = new JTextField();
        sampleOnlyTab.add(locationTextField);

        JButton sampleOnlySubmit = new JButton("Submit");
        sampleOnlyTab.add(sampleOnlySubmit);
        sampleOnlySubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double quantity = 0;
                try {
                     quantity = Double.valueOf(quantityTextField.getText());
                } catch (NumberFormatException e1) {
                    quantity = -1;
                }
                experimentCompiler.compileSampleOnly(typeTextField.getText(),
                        quantity, unitTextField.getText(), locationTextField.getText());
            }
        });


        //Reagent Based
        JPanel reagentBasedTab = new JPanel(new GridLayout(6,2));
        tabPanel.addTab("Reagent Based", null, reagentBasedTab,
                "Reagent Based");
        reagentBasedTab.setBorder(new TitledBorder(new EtchedBorder(), "Reagent Based Experiments"));
        JLabel reagentName = new JLabel("Reagent Name: ");
        reagentBasedTab.add(reagentName);
        JTextField reagentNameField = new JTextField();
        reagentBasedTab.add(reagentNameField);
        JLabel reagentQuantity = new JLabel("Reagent Quantity: ");
        reagentBasedTab.add(reagentQuantity);
        JTextField reagentQuantityField = new JTextField();
        reagentBasedTab.add(reagentQuantityField);
        JLabel reagentTime = new JLabel("Time to wait: ");
        reagentBasedTab.add(reagentTime);
        JTextField reagentTimeField = new JTextField();
        reagentBasedTab.add(reagentTimeField);
        JLabel details = new JLabel("Details: ");
        reagentBasedTab.add(details);
        JTextArea  detailsArea = new JTextArea();
        reagentBasedTab.add(detailsArea);
        JLabel measurements = new JLabel("Measurements: ");
        reagentBasedTab.add(measurements);
        JTextArea measurementsArea = new JTextArea();
        reagentBasedTab.add(measurementsArea);
        JButton addReagent = new JButton("Add");
        ArrayList<ArrayList<String>> reagents = new ArrayList<>();
        addReagent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> reagent = new ArrayList<>();
                try {
                    Double.valueOf(reagentQuantityField.getText());
                    Double.valueOf(reagentTimeField.getText());
                } catch (NumberFormatException e1) {
                    System.out.println("Please input numbers for quantity and time");
                    return;
                }
                reagent.add(reagentNameField.getText());
                reagent.add(reagentQuantityField.getText());
                reagent.add(reagentTimeField.getText());
                reagent.add(detailsArea.getText());
                reagent.add(measurementsArea.getText());
                reagentNameField.setText("");
                reagentQuantityField.setText("");
                reagentTimeField.setText("");
                detailsArea.setText("");
                measurementsArea.setText("");
                reagents.add(reagent);
            }
        });
        reagentBasedTab.add(addReagent);
        JButton reagentSubmit = new JButton("Submit");
        reagentSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //compile the arraylist<arraylist<string>>
                //no data class no
            }
        });
        reagentBasedTab.add(reagentSubmit);

        //Complex
        JPanel complexTab = new JPanel(new GridLayout(3,1));
        tabPanel.addTab("Complex", null, complexTab,
                "Complex");
        JLabel commandList = new JLabel("Command List");
        complexTab.add(commandList);
        complexTab.setBorder(new TitledBorder(new EtchedBorder(), "Complex Experiments"));
        JTextArea commandListArea = new JTextArea(16, 36);
        commandListArea.setEditable(true);
        complexTab.add(commandListArea);

        JScrollPane scroll = new JScrollPane(commandListArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        complexTab.add(scroll);

        JButton complexSubmit = new JButton("Submit");
        complexTab.add(complexSubmit);

        complexSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                experimentCompiler.compileComplexExperiment(commandListArea.getText());
            }
        });

        //Manager
        JPanel managerTab = new JPanel(new GridLayout(5,2));
        tabPanel.addTab("Manager", null, managerTab,
                "Manager");
        managerTab.setBorder(new TitledBorder(new EtchedBorder(), "Manager"));


        myFrame.add(tabPanel);
        myFrame.setVisible(true);
    }
}
