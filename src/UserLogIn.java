import Business.ExperimentCompiler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogIn {

    private final String scientistUsername = "scientist";
    private final String scientistPassword = "123456";
    private final String managerUsername = "manager";
    private final String managerPassword = "123456";

    private JFrame myFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static String user = null;

    private ExperimentCompiler experimentCompiler;

    public UserLogIn(ExperimentCompiler experimentCompiler) {

        this.experimentCompiler = experimentCompiler;

        myFrame = new JFrame();
        myFrame.setSize(400, 200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("Please log in");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2));

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton submit = new JButton("Log in");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        myFrame.add(panel);
        myFrame.setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.equals(scientistUsername) && password.equals(scientistPassword) ||
                username.equals(managerUsername) && password.equals(managerPassword)) {
            System.out.println("Login Successfully");
            myFrame.setVisible(false);
            myFrame.dispose();
            //new Presentation.JUMPUI(this.experimentCompiler);
        } else {
            System.out.println("Wrong username/password");
        }
    }

}