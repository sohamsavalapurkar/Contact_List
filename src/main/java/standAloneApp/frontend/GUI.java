package standAloneApp.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standAloneApp.backend.entity.Address;
import standAloneApp.backend.entity.Contact;
import standAloneApp.backend.service.ContactService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

@Component
public class GUI implements ActionListener{
    JFrame f = new JFrame();
    JFrame contact, searchResults;
    JButton login, addContact, searchButton, showContacts, addAddress, insertContact, updateContact, addPhone, deleteContact, issuedBook, returnedBook, addedBook, addedUser, logout;
    JLabel blankLabel;

    List<JTextField> address, phone, city, state, zipCode;
    JTextField searchBox, firstName, middleName, lastName;
    JPasswordField passwordField;
    List<JComboBox> searchMenu, phoneMenu;


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
        contact.setLayout(new GridLayout(1,1));
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));

        address = new ArrayList<>();
        phone = new ArrayList<>();
        zipCode = new ArrayList<>();
        city = new ArrayList<>();
        state = new ArrayList<>();
        phoneMenu = new ArrayList<>();
        searchMenu = new ArrayList<>();

        JLabel firstNameLabel = new JLabel("First Name", SwingConstants.CENTER);
        JLabel middleNameLabel = new JLabel("Middle Name", SwingConstants.CENTER);
        JLabel lastNameLabel = new JLabel("Last Name", SwingConstants.CENTER);
        JLabel addressLabel = new JLabel("Address", SwingConstants.CENTER);
        JLabel cityLabel = new JLabel("City", SwingConstants.CENTER);
        JLabel stateLabel = new JLabel("State", SwingConstants.CENTER);
        JLabel zipCodeLabel = new JLabel("Zip Code", SwingConstants.CENTER);
        JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);

        final JPanel[] j = {new JPanel()};
        j[0].setLayout(new GridLayout(1,3));
        firstName = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        j[0].add(firstNameLabel);
        j[0].add(firstName);
        j[0].add(blankLabel);
        contactPanel.add(j[0]);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        middleName = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        middleName.setSize(300, 30);
        j[0].add(middleNameLabel);
        j[0].add(middleName);
        j[0].add(blankLabel);
        contactPanel.add(j[0]);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        lastName = new JTextField();
        blankLabel = new JLabel("",SwingConstants.CENTER);
        lastName.setSize(300, 30);
        j[0].add(lastNameLabel);
        j[0].add(lastName);
        j[0].add(blankLabel);
        contactPanel.add(j[0]);

        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel,BoxLayout.Y_AXIS));

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        address.add(new JTextField());
        address.get(0).setSize(300, 100);
        j[0].add(addressLabel);
        j[0].add(address.get(0));
        String menu[] = {"Home", "Work", "Other"};
        searchMenu.add(new JComboBox(menu));
        j[0].add(searchMenu.get(0));
        addressPanel.add(j[0]);


        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        city.add(new JTextField());
        blankLabel = new JLabel("",SwingConstants.CENTER);
        j[0].add(cityLabel);
        j[0].add(city.get(0));
        j[0].add(blankLabel);
        addressPanel.add(j[0]);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        state.add(new JTextField());
        blankLabel = new JLabel("",SwingConstants.CENTER);
        j[0].add(stateLabel);
        j[0].add(state.get(0));
        j[0].add(blankLabel);
        addressPanel.add(j[0]);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        zipCode.add(new JTextField());
        j[0].add(zipCodeLabel);
        j[0].add(zipCode.get(0));
        addAddress = new JButton("Add Address");
        j[0].add(addAddress);
        addressPanel.add(j[0]);

        contactPanel.add(addressPanel);
        final int[] i = {1};
        addAddress.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JLabel addressLabel = new JLabel("Address", SwingConstants.CENTER);
                JLabel cityLabel = new JLabel("City", SwingConstants.CENTER);
                JLabel stateLabel = new JLabel("State", SwingConstants.CENTER);
                JLabel zipCodeLabel = new JLabel("Zip Code", SwingConstants.CENTER);
                
                JPanel j = new JPanel();
                j.setLayout(new GridLayout(1,3));
                address.add(new JTextField());
                address.get(i[0]).setSize(300, 100);
                j.add(addressLabel);
                j.add(address.get(i[0]));
                String menu[] = {"Home", "Work", "Other"};
                searchMenu.add(new JComboBox(menu));
                j.add(searchMenu.get(i[0]));
                addressPanel.add(j);


                j = new JPanel();
                j.setLayout(new GridLayout(1,3));
                city.add(new JTextField());
                blankLabel = new JLabel("",SwingConstants.CENTER);
                j.add(cityLabel);
                j.add(city.get(i[0]));
                j.add(blankLabel);
                addressPanel.add(j);

                j = new JPanel();
                j.setLayout(new GridLayout(1,3));
                state.add(new JTextField());
                blankLabel = new JLabel("",SwingConstants.CENTER);
                j.add(stateLabel);
                j.add(state.get(i[0]));
                j.add(blankLabel);
                addressPanel.add(j);

                j = new JPanel();
                j.setLayout(new GridLayout(1,3));
                zipCode.add(new JTextField());
                blankLabel = new JLabel("",SwingConstants.CENTER);
                j.add(zipCodeLabel);
                j.add(zipCode.get(i[0]));
                j.add(blankLabel);
                addressPanel.add(j);


                contact.repaint();
                contact.setVisible(true);
                i[0]++;
            }

        });

        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new BoxLayout(phonePanel,BoxLayout.Y_AXIS));

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        phone.add(new JTextField());
        phone.get(0).setSize(300, 100);
        j[0].add(phoneLabel);
        j[0].add(phone.get(0));
        String phoneM[] = {"Home", "Work", "Other"};
        phoneMenu.add(new JComboBox(menu));
        j[0].add(phoneMenu.get(0));
        phonePanel.add(j[0]);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        blankLabel = new JLabel("", SwingConstants.CENTER);
        j[0].add(blankLabel);
        blankLabel = new JLabel("", SwingConstants.CENTER);
        j[0].add(blankLabel);
        addPhone = new JButton("Add Phone");
        j[0].add(addPhone);


        phonePanel.add(j[0]);
        contactPanel.add(phonePanel);

        final int[] phoneCounter = {1};
        addPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel j = new JPanel();
                JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);
                j.setLayout(new GridLayout(1,3));
                phone.add(new JTextField());
                phone.get(phoneCounter[0]).setSize(300, 100);
                j.add(phoneLabel);
                j.add(phone.get(phoneCounter[0]));
                String phoneM[] = {"Home", "Work", "Other"};
                phoneMenu.add(new JComboBox(menu));
                j.add(phoneMenu.get(phoneCounter[0]));
                phonePanel.add(j);
                contact.repaint();
                contact.setVisible(true);
                phoneCounter[0]++;
            }
        });

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,1));
        insertContact = new JButton("Add Contact");
        insertContact.addActionListener(this);
        j[0].add(insertContact);
        contactPanel.add(j[0]);

        JScrollPane sp = new JScrollPane();
        sp.setBounds(0, 0, 800, 570);

        sp.setViewportView(contactPanel);

        contact.add(sp);
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
            for (int i = 0; i < ret.size(); i++) {
                Contact d = ret.get(i);
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
                    JOptionPane.showMessageDialog(null, table.getValueAt(row, column));
                    editContact((String) table.getValueAt(row, column));

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

    public void editContact(String id) {
        Contact contact1 = contactService.getContactById(id);
        if(contact1 != null) {
            contact = new JFrame();
            contact.setSize(800,600);
            contact.setLayout(new GridLayout(1,1));
            JPanel contactPanel = new JPanel();
            contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));

            address = new ArrayList<>();
            phone = new ArrayList<>();
            zipCode = new ArrayList<>();
            city = new ArrayList<>();
            state = new ArrayList<>();
            phoneMenu = new ArrayList<>();
            searchMenu = new ArrayList<>();

            JLabel firstNameLabel = new JLabel("First Name", SwingConstants.CENTER);
            JLabel middleNameLabel = new JLabel("Middle Name", SwingConstants.CENTER);
            JLabel lastNameLabel = new JLabel("Last Name", SwingConstants.CENTER);

            JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);

            final JPanel[] j = {new JPanel()};
            j[0].setLayout(new GridLayout(1,3));
            firstName = new JTextField();
            firstName.setText(contact1.getFname());
            blankLabel = new JLabel("",SwingConstants.CENTER);
            j[0].add(firstNameLabel);
            j[0].add(firstName);
            j[0].add(blankLabel);
            contactPanel.add(j[0]);

            j[0] = new JPanel();
            j[0].setLayout(new GridLayout(1,3));
            middleName = new JTextField();
            middleName.setText(contact1.getMname());
            blankLabel = new JLabel("",SwingConstants.CENTER);
            middleName.setSize(300, 30);
            j[0].add(middleNameLabel);
            j[0].add(middleName);
            j[0].add(blankLabel);
            contactPanel.add(j[0]);

            j[0] = new JPanel();
            j[0].setLayout(new GridLayout(1,3));
            lastName = new JTextField();
            lastName.setText(contact1.getLname());
            blankLabel = new JLabel("",SwingConstants.CENTER);
            lastName.setSize(300, 30);
            j[0].add(lastNameLabel);
            j[0].add(lastName);
            j[0].add(blankLabel);
            contactPanel.add(j[0]);

            JPanel addressPanel = new JPanel();
            addressPanel.setLayout(new BoxLayout(addressPanel,BoxLayout.Y_AXIS));

            Set<Address> addresses = contact1.getAddress();
            Iterator<Address> it = addresses.iterator();
            final int[] i = {0};

            while(it.hasNext()) {
                JLabel addressLabel = new JLabel("Address", SwingConstants.CENTER);
                JLabel cityLabel = new JLabel("City", SwingConstants.CENTER);
                JLabel stateLabel = new JLabel("State", SwingConstants.CENTER);
                JLabel zipCodeLabel = new JLabel("Zip Code", SwingConstants.CENTER);
                Address a = it.next();
                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1,3));
                address.add(new JTextField());
                address.get(i[0]).setSize(300, 100);
                address.get(i[0]).setText(a.getAddress());
                j[0].add(addressLabel);
                j[0].add(address.get(i[0]));
                String menu[] = {"HOME", "WORK", "OTHER"};
                searchMenu.add(new JComboBox(menu));
                searchMenu.get(i[0]).setSelectedItem(a.getAddressType());
                System.out.println(a.getAddressType());
                j[0].add(searchMenu.get(i[0]));
                addressPanel.add(j[0]);


                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1,3));
                city.add(new JTextField());
                city.get(i[0]).setText(a.getCity());
                blankLabel = new JLabel("",SwingConstants.CENTER);
                j[0].add(cityLabel);
                j[0].add(city.get(i[0]));
                j[0].add(blankLabel);
                addressPanel.add(j[0]);

                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1,3));
                state.add(new JTextField());
                state.get(i[0]).setText(a.getState());
                blankLabel = new JLabel("",SwingConstants.CENTER);
                j[0].add(stateLabel);
                j[0].add(state.get(i[0]));
                j[0].add(blankLabel);
                addressPanel.add(j[0]);

                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1,3));
                zipCode.add(new JTextField());
                zipCode.get(i[0]).setText(a.getZip());
                j[0].add(zipCodeLabel);
                j[0].add(zipCode.get(i[0]));
                if(!it.hasNext()) {
                    addAddress = new JButton("Add Address");
                    j[0].add(addAddress);

                }
                else {
                    blankLabel = new JLabel("",SwingConstants.CENTER);
                    j[0].add(blankLabel);
                }
                addressPanel.add(j[0]);

                i[0]++;
            }


            contactPanel.add(addressPanel);

            addAddress.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JLabel addressLabel = new JLabel("Address", SwingConstants.CENTER);
                    JLabel cityLabel = new JLabel("City", SwingConstants.CENTER);
                    JLabel stateLabel = new JLabel("State", SwingConstants.CENTER);
                    JLabel zipCodeLabel = new JLabel("Zip Code", SwingConstants.CENTER);

                    JPanel j = new JPanel();
                    j.setLayout(new GridLayout(1,3));
                    address.add(new JTextField());
                    address.get(i[0]).setSize(300, 100);
                    j.add(addressLabel);
                    j.add(address.get(i[0]));
                    String menu[] = {"HOME", "WORK", "OTHER"};
                    searchMenu.add(new JComboBox(menu));
                    j.add(searchMenu.get(i[0]));
                    addressPanel.add(j);


                    j = new JPanel();
                    j.setLayout(new GridLayout(1,3));
                    city.add(new JTextField());
                    blankLabel = new JLabel("",SwingConstants.CENTER);
                    j.add(cityLabel);
                    j.add(city.get(i[0]));
                    j.add(blankLabel);
                    addressPanel.add(j);

                    j = new JPanel();
                    j.setLayout(new GridLayout(1,3));
                    state.add(new JTextField());
                    blankLabel = new JLabel("",SwingConstants.CENTER);
                    j.add(stateLabel);
                    j.add(state.get(i[0]));
                    j.add(blankLabel);
                    addressPanel.add(j);

                    j = new JPanel();
                    j.setLayout(new GridLayout(1,3));
                    zipCode.add(new JTextField());
                    blankLabel = new JLabel("",SwingConstants.CENTER);
                    j.add(zipCodeLabel);
                    j.add(zipCode.get(i[0]));
                    j.add(blankLabel);
                    addressPanel.add(j);


                    contact.repaint();
                    contact.setVisible(true);
                    i[0]++;
                }

            });

            JPanel phonePanel = new JPanel();
            phonePanel.setLayout(new BoxLayout(phonePanel,BoxLayout.Y_AXIS));



            j[0] = new JPanel();
            j[0].setLayout(new GridLayout(1,3));
            phone.add(new JTextField());
            phone.get(0).setSize(300, 100);
            j[0].add(phoneLabel);
            j[0].add(phone.get(0));
            String phoneM[] = {"HOME", "WORK", "OTHER"};
            phoneMenu.add(new JComboBox(phoneM));
            j[0].add(phoneMenu.get(0));
            phonePanel.add(j[0]);

            j[0] = new JPanel();
            j[0].setLayout(new GridLayout(1,3));
            blankLabel = new JLabel("", SwingConstants.CENTER);
            j[0].add(blankLabel);
            blankLabel = new JLabel("", SwingConstants.CENTER);
            j[0].add(blankLabel);
            addPhone = new JButton("Add Phone");
            j[0].add(addPhone);


            phonePanel.add(j[0]);
            contactPanel.add(phonePanel);

            final int[] phoneCounter = {1};
            addPhone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel j = new JPanel();
                    JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);
                    j.setLayout(new GridLayout(1,3));
                    phone.add(new JTextField());
                    phone.get(phoneCounter[0]).setSize(300, 100);
                    j.add(phoneLabel);
                    j.add(phone.get(phoneCounter[0]));
                    String phoneM[] = {"HOME", "WORK", "OTHER"};
                    phoneMenu.add(new JComboBox(phoneM));
                    j.add(phoneMenu.get(phoneCounter[0]));
                    phonePanel.add(j);
                    contact.repaint();
                    contact.setVisible(true);
                    phoneCounter[0]++;
                }
            });

            j[0] = new JPanel();
            j[0].setLayout(new GridLayout(1,2));
            updateContact = new JButton("Update Contact");
            updateContact.addActionListener(this);

            deleteContact = new JButton("Delete Contact");
            deleteContact.addActionListener(this);
            j[0].add(updateContact);
            j[0].add(deleteContact);
            contactPanel.add(j[0]);

            JScrollPane sp = new JScrollPane();
            sp.setBounds(0, 0, 800, 570);

            sp.setViewportView(contactPanel);

            contact.add(sp);
            contact.setVisible(true);

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
