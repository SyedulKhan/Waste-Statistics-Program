
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*

HCI FOR WASTE MANAGEMENT UI CLASS

- COLOUR: My initial design for this class included default colours such as grey-coloured components and black text. 
However, the whole UI looked pretty dull and may not satisfy the user so I added some colours to enhance
the UI. After I added colours to the UI, I soon realised that having too many colours could affect the user if they
have visual impairments so I decided to use to use only shades of blue throughout the program to maintain
consistency. I added the colour orange to the bars of the bar chart for visual enhancement. This can be controversial
because it may not be ideal for users who have colour blindness but I chose to add the colour because the GUI would
have looked dull and unappealing to the user.

- LAYOUT: The layout was the most crucial part of this UI so I spent most of my time setting the layout for all buttons,
textfields, tables and labels. I did research on the gestalt principles and it suggested that the user prefers a simple 
layout with clear text and seeing compnonets in line which makes it easier for any user to use the program. 

- FONT: I made sure that all text are large enough and clear so it is easier for the user to read. 

SECURITY FOR WASTE MANAGEMENT UI CLASS

I implemented two methods to add security for the user logs. These tow methods are called encrypt() and
decrypt(). The encrypt() method uses the caesar cipher function to jumble words in the user log to make it harder
for people with unauthorised access to read. The decrypt() method converts the encyrpted format of the user log
back to normal text so that it can be viewed in the user log area.  

REFERENCES:
tutorials point simply easy learning.(2018). JFreeChart - Bar Chart. tutorials point simply easy learning. 
Available from: https://www.tutorialspoint.com/jfreechart/jfreechart_bar_chart.htm 
[Accessed 20 March 2018]

Stackoverflow.(2017). Caesar Cipher With Space characters left unchanged.Stackoverflow. Available from 
https://stackoverflow.com/questions/47847852/caesar-cipher-with-space-characters-left-unchanged 
[Accessed 29 March 2018]

 */
public class WasteManagementUI extends javax.swing.JFrame {

    protected TableRowSorter<TableModel> rowSorter;
    protected String[] columnNames;
    protected static String username;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public WasteManagementUI(String username) {
        initComponents();
        this.setLocationRelativeTo(null);

        WasteManagementUI.username = username; //Username variable for the "Logged in as: " feature
        lblUserLoggedIn.setText(WasteManagementUI.username); //Sets the text in the TextField to the user who is logged in
        CSVFile dataSet = new CSVFile(); //CSVFile refers to public class below
        CustomTableModel myModel = new CustomTableModel(); //CustomTableModel class extends the AbstractTableModel below
        tableWasteData.setModel(myModel); //Sets the new model into my table called "tableWasteData"
        File dataSetFile = new File("1827_wasteData.csv"); //Gets data from the csv file
        //Creates an array list for my rowsorter by reading the CSV file called "DataFile"
        ArrayList<String[]> rowSorterList = dataSet.readFromCSVFile(dataSetFile);
        myModel.AddCSVData(rowSorterList); //Adds CSVData into the rowsorter
        rowSorter = new TableRowSorter<>(tableWasteData.getModel());
        tableWasteData.setRowSorter(rowSorter);
        tableWasteData.setFillsViewportHeight(true);
        IntComparator compareInt = new IntComparator(); //sets comparator for sorting integer values
        rowSorter.setComparator(1, compareInt);
    }

    //Method for reading CSV file
    public class CSVFile {

        private final ArrayList<String[]> arrayList = new ArrayList<>(); //Array list for each row in the CSV file
        private String[] lineOfData;

        //Reads CSV file to fill the array
        public ArrayList<String[]> readFromCSVFile(File DataFile) {
            try {
                BufferedReader BufferedR = new BufferedReader(new FileReader(DataFile));
                //While loop which reads each value from compiler and seperates them with a comma
                while (BufferedR.ready()) {
                    String row = BufferedR.readLine();
                    lineOfData = row.split(",");    //Each value is split by a comma
                    arrayList.add(lineOfData);
                }
            } catch (IOException error) {
                String ErrorMessage = error.getMessage();
                System.out.println("File not found: " + ErrorMessage);
            }
            return arrayList;
        }
    }

    //class for my custom table model
    public class CustomTableModel extends AbstractTableModel {

        private ArrayList<String[]> Data = new ArrayList<>();

        //stores all titles for each column
        CustomTableModel() {
            columnNames = new String[]{"Sector Description", "Industrial Sludges", "Other Chemicals"};
        }

        //Add data from the csv file into the array
        public void AddCSVData(ArrayList<String[]> DataIn) {
            this.Data = DataIn;
            this.fireTableDataChanged();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return Data.size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                case 0:
                    return String.class;
                case 1:
                    return Integer.class;
                case 2:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (((Object[]) Data.get(row))[col] == null) {
                return null;
            } else {
                return ((Object[]) Data.get(row))[col];
            }
        }

        public void removeRow(int row) {
            Data.remove(row);
            fireTableRowsDeleted(row, row);
        }

    }

    //Comparator class compares values for sorting which is crucial
    //to calculate the median
    public class IntComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            String int1 = (String) o1;
            String int2 = (String) o2;
            Integer i = Integer.parseInt(int1);
            Integer ii = Integer.parseInt(int2);
            return i.compareTo(ii);
        }

        @Override
        public boolean equals(Object o2) {
            return this.equals(o2);
        }
    }

    //method to calculate sum of searched values
    public void getSum() {
        int sum = 0;
        for (int i = 0; i < tableWasteData.getRowCount(); i++) {
            if (tableWasteData.getValueAt(i, 1).equals("")) {
                sum = sum + 0;
            } else {
                sum = sum + Integer.parseInt((String) tableWasteData.getValueAt(i, 1));
            }
        }
        lblSumValue.setText(Integer.toString(sum));
    }

    //method to calculate the median of the searched terms
    public void getMedian() {
        int median = 0;
        //this is to check if the median has two values so this will add the two values and divide it by two
        if ((tableWasteData.getRowCount() % 2) == 0) {
            median = Math.round((Integer.parseInt((String) tableWasteData.getValueAt(tableWasteData.getRowCount() / 2, 1))
                    + Integer.parseInt((String) tableWasteData.getValueAt(tableWasteData.getRowCount() / 2 - 1, 1))) / 2);
        } else {
            median = Integer.parseInt((String) tableWasteData.getValueAt(tableWasteData.getRowCount() / 2, 1));
        }
        lblMedianValue.setText(Integer.toString(median));
    }

    //method to calculate the mean of the searched terms
    public void getMean() {
        int total = 0;
        int counter = 0;
        for (int i = 0; i < tableWasteData.getRowCount(); i++) {
            if (tableWasteData.getValueAt(i, 1).equals("")) {
                total = total + 0;
            } else {
                total = total + Integer.parseInt((String) tableWasteData.getValueAt(i, 1));
            }
            counter++;
        }
        int mean = Math.round(total / counter);
        lblMeanValue.setText(Integer.toString(mean));
    }

    //method for encrypting user search logs
    //THIS IS REFERRED FROM A SOURCE BY WHICH THE REFERENCE IS WRITTEN ABOVE
    public String encrypt(String plainText, int shiftKey) {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            int charPosition = ALPHABET.indexOf(plainText.charAt(i));
            if (charPosition == -1) { // or define a constant NOT_FOUND = -1
                cipherText += plainText.charAt(i);
            } else {

                int keyVal = (shiftKey + charPosition) % 26;
                char replaceVal = this.ALPHABET.charAt(keyVal);
                cipherText += replaceVal;
            }
        }
        return cipherText;
    }

    //method for decrypting encrypted user search logs
    //THIS IS REFERRED FROM A SOURCE BY WHICH THE REFERENCE IS WRITTEN ABOVE
    public String decrypt(String cipherText, int shiftKey) {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int charPosition = this.ALPHABET.indexOf(cipherText.charAt(i));
            //this is to check if the character is a number, special character or space
            if (charPosition == -1) {
                plainText += cipherText.charAt(i);
            } else {
                int keyVal = (charPosition - shiftKey) % 26;
                if (keyVal < 0) {
                    keyVal = this.ALPHABET.length() + keyVal;
                }
                char replaceVal = this.ALPHABET.charAt(keyVal);
                plainText += replaceVal;
            }
        }
        return plainText;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableWasteData = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblSumValue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMedianValue = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMeanValue = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnBarChart = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblLoginTitle = new javax.swing.JLabel();
        lblUserLoggedIn = new javax.swing.JLabel();
        lblLoggedInAs = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        btnUserLog = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 255));
        setForeground(new java.awt.Color(0, 204, 255));
        setMaximumSize(new java.awt.Dimension(1144, 503));

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tableWasteData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableWasteData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tableWasteData);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Sum:");

        lblSumValue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSumValue.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Median:");

        lblMedianValue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMedianValue.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Mean:");

        lblMeanValue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMeanValue.setText("0");

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnBarChart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBarChart.setText("Display Bar Chart");
        btnBarChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarChartActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        lblLoginTitle.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 36)); // NOI18N
        lblLoginTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblLoginTitle.setText("Waste Management Interface");

        lblUserLoggedIn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUserLoggedIn.setText("***");

        lblLoggedInAs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLoggedInAs.setText("Logged in as:");

        btnLogOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnUserLog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUserLog.setText("User Log");
        btnUserLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblLoginTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLoggedInAs))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUserLog)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogOut)
                    .addComponent(lblUserLoggedIn))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoginTitle)
                    .addComponent(lblUserLoggedIn)
                    .addComponent(lblLoggedInAs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogOut)
                    .addComponent(btnUserLog))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBarChart)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(lblSumValue))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMedianValue, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMeanValue, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnReset)
                    .addComponent(btnBarChart))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblSumValue))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblMedianValue))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblMeanValue))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.dispose();
        Login loginPage = new Login();
        //the user logs out and returns to login page
        loginPage.setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnUserLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserLogActionPerformed
        UserLog log = new UserLog();
        log.setVisible(true);
        //Inputs all user logs into the UserLog.txt file
        File inputFile = new File("src/UserLog/UserLog.txt");
        String logText = "";
        try {
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                //retrieves each log in a new line to be displayed in user log text area
                logText += input.nextLine() + "\r\n";
            }
            String decryptedLog = decrypt(logText, 1);
            //Adds decrypted log to the user log text area
            log.txtAreaUserSearchLogs.setText(decryptedLog + "\r\n");
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }
    }//GEN-LAST:event_btnUserLogActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchedTerm = txtfSearch.getText();
        String user = lblUserLoggedIn.getText();
        Date date = new Date();
        //Checks what the user searches
        if (searchedTerm.trim().length() == 0) {
            //if searched term is empty then a pop up message will be displayed
            JOptionPane.showMessageDialog(null, "Please enter a search criterion");
        } else {
            if (searchedTerm.contains("non")) {
                for (int i = tableWasteData.getRowCount() - 1; i >= 0; i--) {
                    String checkSearch = tableWasteData.getValueAt(i, 0).toString();
                    // this will remove the rows that contain the word 'manufacture' when user searches
                    // for 'non manufacturing' rows
                    if (checkSearch.contains("manufacture") || checkSearch.contains("manufacturing")) {
                        ((CustomTableModel) tableWasteData.getModel()).removeRow(i);
                    }
                }
                //these calculations will be performed
                getSum();
                getMedian();
                getMean();
            } else {
                //Sets the row filter to whatever the user searched
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchedTerm));
                //these calculations will be performed
                getSum();
                getMedian();
                getMean();
            }
        }
        BufferedWriter BuffWriter1 = null;
        FileWriter FileWriter1 = null;
        try {
            File file = new File("src/UserLog/UserLog.txt");
            if (!file.exists()) {
            }
            FileWriter1 = new FileWriter(file.getAbsoluteFile(), true);
            BuffWriter1 = new BufferedWriter(FileWriter1);
            //encrypts the search log
            String encryptedLog = encrypt("User: " + user + " Searched for: " + searchedTerm + " on "
                    + DateFormat.getInstance().format(date) + "\r\n", 1);
            //Writes each encrypted search into the .txt file
            BuffWriter1.write(encryptedLog);
        } catch (IOException e) {
        } finally {
            try {
                if (BuffWriter1 != null) {
                    BuffWriter1.close();
                }
                if (FileWriter1 != null) {
                    FileWriter1.close();
                }
            } catch (IOException ex) {
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        String username1 = lblUserLoggedIn.getText();
        WasteManagementUI wasteManagementUI = new WasteManagementUI(username1);
        this.dispose();
        //Resets the form back to its original state
        wasteManagementUI.setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBarChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarChartActionPerformed
        //THIS IS REFERRED FROM SOURCE BY WHICH THE REFERENCE IS WRITTEN ABOVE
        int barPanelSum = Integer.parseInt(lblSumValue.getText()); //retrieves the sum value from textfield
        int barPanelMedian = Integer.parseInt(lblMedianValue.getText()); //retrieves the median value from textfield
        int barPanelMean = Integer.parseInt(lblMeanValue.getText()); //retrieves the mean value from textfield

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); //these set the values for each bar
        dataSet.setValue(barPanelSum, "Industrial Sludges", "Sum");
        dataSet.setValue(barPanelMedian, "Industrial Sludges", "Median");
        dataSet.setValue(barPanelMean, "Industrial Sludges", "Mean");

        JFreeChart barChart = ChartFactory.createBarChart("Data Analysis", "Calculation Types",
                "Industrial Sludges Amount", dataSet, PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot barChrt = barChart.getCategoryPlot(); //this creates the bar chart with the axis names and bars
        barChrt.setRangeGridlinePaint(Color.ORANGE); //this sets the colours of the bars
        //these lines of code will show the user the bar chart
        ChartFrame chartFrame = new ChartFrame("Data Analysis", barChart, true);
        chartFrame.setVisible(true);
        chartFrame.setSize(700, 600);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.validate();
    }//GEN-LAST:event_btnBarChartActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WasteManagementUI(username).setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarChart;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUserLog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLoggedInAs;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblMeanValue;
    private javax.swing.JLabel lblMedianValue;
    private javax.swing.JLabel lblSumValue;
    private javax.swing.JLabel lblUserLoggedIn;
    private javax.swing.JTable tableWasteData;
    private javax.swing.JTextField txtfSearch;
    // End of variables declaration//GEN-END:variables
}
