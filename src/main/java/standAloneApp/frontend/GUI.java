package standAloneApp.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standAloneApp.backend.entity.Contact;
import standAloneApp.backend.service.ContactService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Component
public class GUI implements ActionListener{
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    JFrame f = new JFrame();
    JFrame contact, searchResults;
    JButton login, addContact, searchButton, showContacts, addAddress, insertContact, addMultipleBook, addUser, addMultipleUser, showUserDetails, issuedBook, returnedBook, addedBook, addedUser, logout;
    JLabel blankLabel;

    JTextField searchBox, zipCode, firstName, middleName, lastName, address, phone, city, state;
    JPasswordField passwordField;
    JComboBox searchMenu, phoneMenu;
    String[] access = {"Admin", "Teacher", "Student"};
    JPanel panel;

    @Autowired
    ContactService contactService;

    public void setVisible(boolean b) {
        f.setVisible(b);
        try{
            f.setSize(1280,720);
            f.setResizable(true);
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainPage();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void mainPage() {
        f.setLayout(null);

        JLabel title = new JLabel("Contacts");
        title.setSize(1000, 200);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, Math.min(30, title.getHeight())));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setLocation((f.getWidth()-title.getWidth())/2, 50);

        searchBox = new JTextField();
        searchBox.setSize(800, 30);
        int x = (f.getWidth()-searchBox.getWidth())/2;
        searchBox.setLocation(x-50, 250);

        searchButton = new JButton("Search");
        searchButton.setSize(250, 30);
        searchButton.setLocation(x+850, 250);
        searchButton.addActionListener(this);

        addContact = new JButton("Add Contact");
        addContact.setSize(250, 30);
        addContact.setLocation(x+850, 100);
        addContact.addActionListener(this);

        showContacts = new JButton("Show all Contacts");
        showContacts.setSize(250, 30);
        showContacts.setLocation(x+850, 400);
        showContacts.addActionListener(this);

        f.add(title);
        f.add(addContact);
        f.add(searchBox);
        f.add(searchButton);
        f.add(showContacts);
        f.setVisible(true);

    }

    public void addContactPage() {
        contact = new JFrame();
        contact.setSize(800,600);
        contact.setLayout(new GridLayout(12,1));

        JLabel firstNameLabel = new JLabel("First Name", SwingConstants.CENTER);
        JLabel middleNameLabel = new JLabel("Middle Name", SwingConstants.CENTER);
        JLabel lastNameLabel = new JLabel("Last Name", SwingConstants.CENTER);
        JLabel addressLabel = new JLabel("Address", SwingConstants.CENTER);
        JLabel cityLabel = new JLabel("City", SwingConstants.CENTER);
        JLabel stateLabel = new JLabel("State", SwingConstants.CENTER);
        JLabel zipCodeLabel = new JLabel("Zip Code", SwingConstants.CENTER);

        JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);

        JPanel j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        firstName = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        j.add(firstNameLabel);
        j.add(firstName);
        j.add(blankLabel);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        middleName = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        middleName.setSize(300, 30);
        j.add(middleNameLabel);
        j.add(middleName);
        j.add(blankLabel);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        lastName = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        lastName.setSize(300, 30);
        j.add(lastNameLabel);
        j.add(lastName);
        j.add(blankLabel);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        address = new JTextField();
        address.setSize(300, 100);
        j.add(addressLabel);
        j.add(address);
        String menu[] = {"Home", "Work", "Other"};
        searchMenu = new JComboBox(menu);
        j.add(searchMenu);
        contact.add(j);


        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        city = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        j.add(cityLabel);
        j.add(city);
        j.add(blankLabel);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        state = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        j.add(stateLabel);
        j.add(state);
        j.add(blankLabel);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        zipCode = new JTextField();
        j.add(zipCodeLabel);
        j.add(zipCode);
        addAddress = new JButton("Add Address");
        j.add(addAddress);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,3));
        phone = new JTextField();
        phone.setSize(300, 100);
        j.add(phoneLabel);
        j.add(phone);
        String phoneM[] = {"Home", "Work", "Other"};
        phoneMenu = new JComboBox(menu);
        j.add(phoneMenu);
        contact.add(j);

        j = new JPanel();
        j.setLayout(new GridLayout(1,1));
        insertContact = new JButton("Add Contact");
        insertContact.addActionListener(this);
        j.add(insertContact);
        contact.add(j);


        contact.setVisible(true);

    }

    public void showAllContacts() {
        List<Contact> ret = new ArrayList<Contact>();
        ret = contactService.getContacts();
        if(ret.size() == 0){
            JOptionPane.showMessageDialog(f, "No Contacts found", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String[][] data = new String[ret.size() + 1][5];
            for (int i = 1; i <= ret.size(); i++) {
                Contact d = ret.get(i - 1);
                data[i][0] = d.getContactId();
                data[i][1] = d.getFname();
                data[i][2] = d.getMname();
                data[i][3] = d.getLname();
            }
            String[] columns = {"ID", "First Name", "Middle Name", "Last Name"};

            searchResults = new JFrame();
            searchResults.setSize(1280, 600);
            searchResults.setPreferredSize(new Dimension(1000, 500));
            searchResults.setLayout(null);

            JTable table = new JTable(data, columns);
            table.setBounds(20, 20, 1280, 570);
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(350);
            table.getColumnModel().getColumn(2).setPreferredWidth(350);
            table.getColumnModel().getColumn(3).setPreferredWidth(350);

            table.setRowSelectionAllowed(true);


            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                       // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow();
                    int column = 0;
                    JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.

                }
            });

            JScrollPane sp = new JScrollPane();
            sp.setBounds(0, 0, 1280, 570);
            sp.setViewportView(table);
            searchResults.add(sp);
            //searchResults.add(table);
            searchResults.setVisible(true);

        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addContact) {
            addContactPage();
        }

        if(e.getSource() == showContacts) {
            showAllContacts();
        }
    }



    public GUI(){

    }
}
