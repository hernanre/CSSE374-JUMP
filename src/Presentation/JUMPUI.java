package Presentation;
import Business.*;
import Data.*;
import javafx.scene.shape.Box;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.PriorityQueue;

//Authors: Ricardo H, Carlos F, and Cehong W.


public class JUMPUI {

    private JFrame myFrame;

    private ExperimentCompiler experimentCompiler;
    private ExperimentManager experimentManager;
    private PackageCompiler packageCompiler;
    private CommandCompiler commandCompiler;

    public JUMPUI(ExperimentCompiler experimentCompiler, PackageCompiler packageCompiler,
                  ExperimentManager experimentManager, CommandCompiler commandCompiler) {
        this.experimentCompiler = experimentCompiler;
        this.packageCompiler = packageCompiler;
        this.experimentManager = experimentManager;
        this.commandCompiler = commandCompiler;

        myFrame = new JFrame();
        myFrame.setSize(800, 400);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("Scientist UI");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabPanel = new JTabbedPane();

        //Sample Only
        JPanel sampleOnlyTab = new JPanel(new GridLayout(7,2));
        tabPanel.addTab("Sample Only", null, sampleOnlyTab,
                "Sample Only");
        sampleOnlyTab.setBorder(new TitledBorder(new EtchedBorder(), "Sample Only Experiments"));

        JLabel sampleExID = new JLabel("ID: ");
        sampleOnlyTab.add(sampleExID);

        JTextField IDTextField = new JTextField();
        sampleOnlyTab.add(IDTextField);

        JLabel sampleExName = new JLabel("Name: ");
        sampleOnlyTab.add(sampleExName);

        JTextField nameTextField = new JTextField();
        sampleOnlyTab.add(nameTextField);

        JLabel type = new JLabel("Sample Type: ");
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
                experimentCompiler.compileSampleOnly(nameTextField.getText(), IDTextField.getText(),
                        typeTextField.getText(), quantity, unitTextField.getText(),
                        locationTextField.getText());
                IDTextField.setText("");
                nameTextField.setText("");
                typeTextField.setText("");
                quantityTextField.setText("");
                unitTextField.setText("");
                locationTextField.setText("");
            }
        });


        //Data.Reagent Based


        JPanel reagentBasedTab = new JPanel(new GridLayout(8,2));
        tabPanel.addTab("Reagent Based", null, reagentBasedTab,
                "Reagent Based");
        reagentBasedTab.setBorder(new TitledBorder(new EtchedBorder(), "Data.Reagent Based Experiments"));
        JLabel reagentExID = new JLabel("ID: ");
        reagentBasedTab.add(reagentExID);

        JTextField reagentIDTextField = new JTextField();
        reagentBasedTab.add(reagentIDTextField);

        JLabel reagentExName = new JLabel("Exp Name: ");
        reagentBasedTab.add(reagentExName);

        JTextField reagentNameTextField = new JTextField();
        reagentBasedTab.add(reagentNameTextField);

        JLabel reagentName = new JLabel("Data.Reagent Name: ");
        reagentBasedTab.add(reagentName);
        JTextField reagentNameField = new JTextField();
        reagentBasedTab.add(reagentNameField);
        JLabel reagentQuantity = new JLabel("Data.Reagent Quantity: ");
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
                    Integer.valueOf(reagentQuantityField.getText());
                    Integer.valueOf(reagentTimeField.getText());
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
                try {
                    ArrayList<String> reagent = new ArrayList<>();
                    try {
                        Integer.valueOf(reagentQuantityField.getText());
                        Integer.valueOf(reagentTimeField.getText());
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
                    experimentCompiler.compileReagentExperiment(reagentNameTextField.getText(), reagentIDTextField.getText(),
                            reagents);
                    reagentNameTextField.setText("");
                    reagentIDTextField.setText("");
                    reagentNameField.setText("");
                    reagentQuantityField.setText("");
                    reagentTimeField.setText("");
                    detailsArea.setText("");
                    measurementsArea.setText("");
                    reagents.clear();
                    System.out.println("Data.Reagent Data.Experiment created");
                } catch (NumberFormatException e1) {
                    System.out.println("Please input a number for quantity and time");
                } catch (IndexOutOfBoundsException e2) {
                    System.out.println("Failed to add");
                }
            }
        });
        reagentBasedTab.add(reagentSubmit);

        //Complex
        JPanel complexTab = new JPanel();
        complexTab.setLayout(new BoxLayout(complexTab, BoxLayout.PAGE_AXIS));
        tabPanel.addTab("Complex", null, complexTab,
                "Complex");
        JLabel complexName = new JLabel("Name: ");
        complexTab.add(complexName);
        JTextField complexNameTextField = new JTextField();
        complexTab.add(complexNameTextField);
        JLabel complexID = new JLabel("ID: ");
        complexTab.add(complexID);
        JTextField complexIDTextField = new JTextField();
        complexTab.add(complexIDTextField);
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
                experimentCompiler.compileComplexExperiment(complexIDTextField.getText(),
                        complexNameTextField.getText(),
                        commandListArea.getText());
            }
        });

        //Business.Manager
        JPanel managerTab = new JPanel();
        tabPanel.addTab("Manager", null, managerTab,
                "Manager");
        PriorityQueue<Experiment> experiments = new PriorityQueue<>(
                (o1, o2) -> o1.getPriority() - o2.getPriority());
        managerTab.setBorder(new TitledBorder(new EtchedBorder(), "Business.Manager"));
        JButton managerSubmit = new JButton("Submit");
        managerSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(experiments.size() +" experiments sent");
                packageCompiler.sendExperiment(experiments);
                experiments.clear();
            }
        });
        JButton refresh = new JButton("Refresh");
        managerTab.add(refresh);
        managerTab.add(managerSubmit);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerTab.removeAll();
                managerTab.add(refresh);
                for(Experiment experiment : experimentManager.getExperiments()) {
                    if(experiment.getStatus().equals("Entered")) {
                        JCheckBox checkBox = new JCheckBox();
                        JLabel exp = new JLabel();
                        exp.setText(experiment.getLabel());
                        checkBox.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (checkBox.isSelected()) {
                                    System.out.println("Add an experiment to the Package");
                                    experiments.add(experiment);
                                } else {
                                    System.out.println("Delete an experiment from the Package");
                                    experiments.remove(experiment);
                                }
                            }
                        });
                        managerTab.add(checkBox);
                        managerTab.add(exp);
                    }
                }
                managerTab.add(managerSubmit);
            }
        });

        //Macro Tab
        JPanel macroTab = new JPanel(new GridLayout(5,2));
        tabPanel.addTab("Macro", null, macroTab,
                "Macro Command");
        JLabel macroName = new JLabel("Macro Name: ");
        macroTab.add(macroName);
        JTextField macroNameTextField = new JTextField();
        macroTab.add(macroNameTextField);
        JLabel commandID = new JLabel("Basic Command ID: ");
        macroTab.add(commandID);
        JTextField commandIDField = new JTextField();
        macroTab.add(commandIDField);
        JLabel commandInputs = new JLabel("Inputs: ");
        macroTab.add(commandInputs);
        JTextField commandsInputField = new JTextField();
        macroTab.add(commandsInputField);
        JButton addBasicCommand = new JButton("Add Basic Command");
        macroTab.add(addBasicCommand);
        addBasicCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    commandCompiler.addBasicCommand(macroNameTextField.getText(), commandIDField.getText(), commandsInputField.getText());
                } catch (IndexOutOfBoundsException e1) {
                    System.out.println("Please input the correct number of inputs");
                } catch (NumberFormatException e2) {
                    System.out.println("Please input numbers");
                }
                commandIDField.setText("");
                commandsInputField.setText("");
            }
        });
        JButton macroCreate = new JButton("Create Macro");
        macroCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    commandCompiler.addBasicCommand(macroNameTextField.getText(), commandIDField.getText(), commandsInputField.getText());
                } catch (IndexOutOfBoundsException e1) {
                    System.out.println("Please input the correct number of inputs");

                } catch (NumberFormatException e2) {
                    System.out.println("Please input numbers");
                }
                System.out.println(commandCompiler.getMacro(macroNameTextField.getText()).toJson().toJSONString());
                commandIDField.setText("");
                commandsInputField.setText("");
                macroNameTextField.setText("");
            }
        });
        macroTab.add(macroCreate);
        JButton macroDelete = new JButton("Delete Macro");
        macroDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(commandCompiler.getMacro(macroNameTextField.getText()).toJson().toJSONString());
                } catch (NullPointerException e1) {
                    e1.printStackTrace();
                    System.out.println("Macro Not existing");
                }
                commandCompiler.deleteMacro(macroNameTextField.getText());
                commandIDField.setText("");
                commandsInputField.setText("");
                macroNameTextField.setText("");
            }
        });
        macroTab.add(macroDelete);


        myFrame.add(tabPanel);
        myFrame.setVisible(true);
    }
}
