
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* 
HCI FOR LOGIN CLASS

- COLOURS: I used the same colours of blue compared to the other pages to maintain consistency. 

- LAYOUT: Similar concept to the REGISTRATION page, I made sure that the general layout was similar to other registration
forms i.e. a vertical layout with labels at the side to gain familiarity and also to make it easy to read and fill in.

- FONT: I enlarged the font for the title of the LOGIN page with a suitable font style so it can read easily. As for the labels,
I enlarged it slightly so help those who have troubl with sight.

SECURITY FOR LOGIN CLASS

A simple security measure I implemented is using a password field text box which hides the text that the user types, 
they appear as black dots and are not visible to people around. Although, it is easy to get the text of password by using
the getText() method which is not ideal for security purposes. To add additonal security for passwords, I implemented 
the hash function using MD5 (same method from REGISTER class) to convert the password into hexadecimal format. 
For logging in, the compare() method will compare the hashed formats of the saved password from the file to user input 
to see if the hexadecimal format of both matches. Is is not possible to decrypt hashed passwords so the compare() method 
is created for comparison purposes. This is good because anyone else will not be able to read the password from the 
text file as it is in hexadecimal format.

REFERENCES:

Stackoverflow.(2016). decode md5 encrytion in java. Stackoverflow. Available from 
https://stackoverflow.com/questions/40503201/decode-md5-ecnrytion-in-java [Accessed 29 March 2018]
 */
public class Login extends javax.swing.JFrame {

    protected ArrayList<User> users;

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null); // centers form in the screen
    }
    //method to compare hashed password wih hashed user input password
    //same code used from REGISTER class
    //THIS IS REFERRED FROM A SOURCE BY WHICH THE REFERENCE IS WRITTEN ABOVE
    public String compare(String password) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hashedPassword;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtfUsername = new javax.swing.JTextField();
        pasfPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnLoginPageRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        lblLoginTitle.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 36)); // NOI18N
        lblLoginTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblLoginTitle.setText("Login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblLoginTitle)
                .addContainerGap(386, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblLoginTitle)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Password:");

        btnLogin.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnLoginPageRegister.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnLoginPageRegister.setText("Register");
        btnLoginPageRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginPageRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtfUsername)
                            .addComponent(pasfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLoginPageRegister)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogin)
                        .addGap(60, 60, 60)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pasfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnLoginPageRegister))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtfUsername.getText();
        String password = String.valueOf(pasfPassword.getPassword());

        users = new ArrayList<>();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have left some fields empty!");
        } else {
            try {
                //this try block retrieves the list of user files to search if the username and password
                //matches any of the files
                File UserDetails = new File("src/UserDetails/");
                File[] UserFiles = UserDetails.listFiles();
                boolean flag = false;
                for (File UserFile1 : UserFiles) {
                    String Username;
                    String UsernamePassword = "";
                    try (BufferedReader file = new BufferedReader(new FileReader(UserFile1))) {
                        Username = file.readLine();
                        UsernamePassword += Username;
                    }
                    String[] content = UsernamePassword.split(",");
                    //this is to check if there is any match
                    if (content[0].equals(username) && content[1].equals(compare(password))) {
                        new WasteManagementUI(username).setVisible(true);
                        flag = true;
                    } else {
                    }
                }
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, "You have successfully logged in");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed! Please try again");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginPageRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginPageRegisterActionPerformed
        Register regForm = new Register();
        regForm.setVisible(true);
        regForm.pack();
        regForm.setLocationRelativeTo(null);
        regForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnLoginPageRegisterActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginPageRegister;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JPasswordField pasfPassword;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}
