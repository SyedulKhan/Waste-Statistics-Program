
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/*
HCI FOR REGISTER CLASS

- COLOUR: My initial design for this class included default colours such as grey-coloured components and black text. 
However, the whole UI looked pretty dull and may not satisfy the user so I added some colours to enhance
the UI. After I added colours to the UI, I soon realised that having too many colours could affect the user if they
have visual impairments so I decided to use to use only shades of blue throughout the program to maintain
consistency. It would have been more safe to use the dull colours especially for those who have colour blindness but I 
made sure that all fonts are large enough to be read easily and use only blue to esure consistency.

- LAYOUT: I made sure that all buttons, textfields, password fields and labels are nicely in line with each other 
and displayed like an actual registration form that is common to the user which makes it easier for them to read. 

- FONT: I made sure that all text are slighlty large and clear so it is easier for the user to read, especially if some
users are short-sighted.

SECURITY FOR REGISTER CLASS

Security for REGISTRATION class was crucial to this program because this is where new users enter their personal
details to the program. I implemented a method for password validation where it checks to see if the password that the
new user attempts to create meets the criteria for strong passwords i.e. having at least one upper case letter and at 
least one number etc. I made sure that users are not able to register EMPTY accounts as this could cause the system to crash 
when logging in. I made use of other forms of validation such as a confirm password box which means that the user needs 
to enter their chosen password twice and if they are not the same, the program pops up a message box stating they 
need to correct it. Similar to the login class, I used password text fields instead of normal textfields so that the 
password entered are not visible to anyone around the user while they are registering. Finally, I implemented a hash 
function using MD5 to hash passwords ny converting the text format to hexadecimal format which is ideal to hide this 
sensitive piece of information from unauthorised access. It cannot be decrypted but only you can compare to see if 
the hashed version of the user input matches the saved hashed password.

REFERENCES:

Stackoverflow.(2016). decode md5 encrytion in java. Stackoverflow. Available from 
https://stackoverflow.com/questions/40503201/decode-md5-ecnrytion-in-java [Accessed 29 March 2018]
 */
public class Register extends javax.swing.JFrame {

    protected ArrayList<User> users = new ArrayList<>();
    protected Boolean valid;
    protected Boolean sameUsername = false;
    
    public Register() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtAValidation.setEditable(false);
        txtAValidation.setText("Please make sure you follow the password criteria below: \n\n" + "Password should be more than 8 characters in length \n"
                + "Password should not be same as user name \n" + "Password should contain at least one upper case alphabet \n"
                + "Password should contain at least one lower case alphabet \n" + "Password should contain at least one number \n"
                + "Password should contain atleast one special character \n");
        txtAValidation.setVisible(true);
    }

    //method to validate passwords to ensure that every password is strong
    public void passwordValidation(String username, String password) {
        valid = true;
        int error = 0;
        if (password.length() < 8) {
            valid = false;
            error = 1;
        }
        if (password.contains(username)) {
            valid = false;
            error = 1;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            valid = false;
            error = 1;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            valid = false;
            error = 1;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            valid = false;
            error = 1;
        }
        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (!password.matches(specialChars)) {
            valid = false;
            error = 1;
        }

        if (error == 1) {
            JOptionPane.showMessageDialog(null, "You have not followed the password criteria!");
            txtfUsername.setText("");
            txtfEmail.setText("");
            pasfRegPassword.setText("");
            pasfRegReenterPassword.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtfEmail = new javax.swing.JTextField();
        pasfRegPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pasfRegReenterPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtfUsername = new javax.swing.JTextField();
        btnRegCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAValidation = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        lblLoginTitle.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 36)); // NOI18N
        lblLoginTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblLoginTitle.setText("Registration");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblLoginTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel3.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Password:");

        btnRegister.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Re-enter Password:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Password Criteria:");

        btnRegCancel.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnRegCancel.setText("Cancel");
        btnRegCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegCancelActionPerformed(evt);
            }
        });

        txtAValidation.setColumns(20);
        txtAValidation.setRows(5);
        jScrollPane1.setViewportView(txtAValidation);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Username:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pasfRegPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pasfRegReenterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnRegister)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegCancel)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pasfRegPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pasfRegReenterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnRegCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String username = txtfUsername.getText();
        String email = txtfEmail.getText();
        String password = pasfRegPassword.getText();
        String reenterPassword = pasfRegReenterPassword.getText();

        if (username.equals("") || email.equals("") || password.equals("") || reenterPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "You have left some fields empty!");
        } else if (!email.contains("@")) {
            JOptionPane.showMessageDialog(null, "Your email does not contain the '@' symbol");
        } else if (pasfRegReenterPassword.getText() == null ? pasfRegPassword.getText() != null
                : !pasfRegReenterPassword.getText().equals(pasfRegPassword.getText())) {
            JOptionPane.showMessageDialog(null, "Your Passwords do not match! Please try again");
        } else {
            try {
                //this try block retrieves the list of user files to search if the username and password
                //matches any of the files
                File UserDetails = new File("src/UserDetails/");
                File[] UserFiles = UserDetails.listFiles();
                for (File UserFile1 : UserFiles) {
                    String Username;
                    String UsernamePassword = "";
                    try (BufferedReader file = new BufferedReader(new FileReader(UserFile1))) {
                        Username = file.readLine();
                        UsernamePassword += Username;
                    }
                    String[] content = UsernamePassword.split(",");
                    //this is to check if there is any match
                    if (content[0].equals(username)) {
                        JOptionPane.showMessageDialog(null, "Username has already been taken. Please choose another.");
                        sameUsername = true;
                    } else {
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (sameUsername == false) {
                passwordValidation(username, password);
                if (valid == true) {
                    txtAValidation.setText("");
                    try (BufferedWriter out = new BufferedWriter(new FileWriter("src/UserDetails/" + username + ".txt", true))) {
                        //this try block will hash the password for security purposes
                        //THIS IS REFERRED FROM A SOURCE BY WHICH THE REFERENCE IS WRITTEN ABOVE
                        String hashedPassword = null;
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            md.update(password.getBytes()); //get bytes from password
                            byte[] bytes = md.digest(); //digests the bytes of the password 
                            //it is converted to hexadecimal format
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < bytes.length; i++) {
                                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                            }
                            //Gets completed hashed password in hex format
                            hashedPassword = sb.toString();
                        } catch (NoSuchAlgorithmException e) {
                        }
                        out.write(username + "," + hashedPassword + "\n");
                        out.write("Email: " + email + "\n");
                        out.newLine();
                        showMessageDialog(null, "Successfully Registered!");
                        User user = new User(username, password);
                        users.add(user);
                        this.dispose();
                        Login LoginPage = new Login();
                        LoginPage.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnRegCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegCancelActionPerformed
        this.dispose();
        Login LoginPage = new Login();
        LoginPage.setVisible(true);
    }//GEN-LAST:event_btnRegCancelActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegCancel;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JPasswordField pasfRegPassword;
    private javax.swing.JPasswordField pasfRegReenterPassword;
    private javax.swing.JTextArea txtAValidation;
    private javax.swing.JTextField txtfEmail;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}
