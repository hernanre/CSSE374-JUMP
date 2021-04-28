import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Authors: Ricardo H, Carlos F, and Cehong W.


public class JUMPUI {

    private JFrame myFrame;

    public JUMPUI() {
        myFrame = new JFrame();
        myFrame.setSize(600, 300);
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
            }
        });


        //Reagent Based
        JPanel reagentBasedTab = new JPanel();
        tabPanel.addTab("Reagent Based", null, reagentBasedTab,
                "Reagent Based");
        reagentBasedTab.setBorder(new TitledBorder(new EtchedBorder(), "Reagent Based Experiments"));


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

            }
        });

        myFrame.add(tabPanel);
        myFrame.setVisible(true);
    }
}
