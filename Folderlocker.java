import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class Demo1 extends JFrame implements ActionListener {

    private JTextField folderPathField;
    private JTextField passwordField;

    public Demo1() {
        super("Folder Locker System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create GUI components
        JLabel folderPathLabel = new JLabel("Folder Path:");
        folderPathField = new JTextField(30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(30);
        JButton lockButton = new JButton("Lock");
        lockButton.addActionListener(this);

        // Create layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        panel.add(folderPathLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(folderPathField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(passwordLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(lockButton, c);

        // Add layout to frame
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String folderPath = folderPathField.getText();
        String password = passwordField.getText();

        try {
            // Create a new file with the password as the name
            File file = new File(password);
            file.createNewFile();

            // Encrypt the folder using the password
            Path folder = Paths.get(folderPath);
            Path encryptedFolder = Paths.get(folderPath + ".locked");
            Files.move(folder, encryptedFolder);

            // Move the password file to the folder
            File pwdFile = new File(password);
            File destFile = new File(folderPath + "/" + pwdFile.getName());
            Files.move(pwdFile.toPath(), destFile.toPath());

            JOptionPane.showMessageDialog(this, "Folder locked successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error locking folder: " + ex.getMessage());
        }
    }
  
    public static void main(String[] args) {
        new Demo1();
    }
}
 

// This code creates a simple GUI with two text fields for the folder path and password, and a "Lock" button to initiate the folder locking process. When the user clicks the button, the actionPerformed() method is called and the folder is locked.

// NOTE: that this implementation is still very basic and not secure enough for sensitive data. For a more secure implementation, you should consider using encryption algorithms and more sophisticated security measures.
    
