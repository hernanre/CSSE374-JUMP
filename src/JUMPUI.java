import javax.swing.*;
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

        JPanel panel = new JPanel(new GridLayout(3,2));

        JButton submit = new JButton("Log in");

        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        myFrame.add(panel);
        myFrame.setVisible(true);
    }
}
