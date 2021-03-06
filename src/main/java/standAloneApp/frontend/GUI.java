package standAloneApp.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standAloneApp.backend.entity.Address;
import standAloneApp.backend.entity.Contact;
import standAloneApp.backend.entity.Date;
import standAloneApp.backend.entity.Phone;
import standAloneApp.backend.service.ContactService;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Component
public class GUI implements ActionListener{
    String updateid = null;
    JFrame f = new JFrame();
    JFrame contact, searchResults;
    JButton addDate, addContact, searchButton, showContacts, addAddress, insertContact, updateContact, addPhone, deleteContact;
    JLabel blankLabel;

    List<JTextField> address, city, state, zipCode;
    List<JFormattedTextField> phone, date;
    JTextField searchBox, firstName, middleName, lastName;
    List<JComboBox> searchMenu, phoneMenu, dateMenu;


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
        date = new ArrayList<>();
        dateMenu = new ArrayList<>();

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
                JLabel addressLabel = new JLabel("Address" + (i[0]+1), SwingConstants.CENTER);
                JLabel cityLabel = new JLabel("City" + (i[0]+1), SwingConstants.CENTER);
                JLabel stateLabel = new JLabel("State" + (i[0]+1), SwingConstants.CENTER);
                JLabel zipCodeLabel = new JLabel("Zip Code" + (i[0]+1), SwingConstants.CENTER);
                
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

        MaskFormatter mask = null;
        try {
            // Create a MaskFormatter for accepting phone number, the # symbol accept
            // only a number. We can also set the empty value with a place holder
            // character.
            mask = new MaskFormatter("(###)-###-####");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        phone.add(new JFormattedTextField(mask));
        phone.get(0).setSize(300, 100);
        j[0].add(phoneLabel);
        j[0].add(phone.get(0));
        String phoneM[] = {"HOME", "WORK", "OTHER"};
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
                JLabel phoneLabel = new JLabel("Phone" + (phoneCounter[0]+1), SwingConstants.CENTER);
                j.setLayout(new GridLayout(1,3));
                MaskFormatter mask = null;
                try {
                    // Create a MaskFormatter for accepting phone number, the # symbol accept
                    // only a number. We can also set the empty value with a place holder
                    // character.
                    mask = new MaskFormatter("(###)-###-####");
                    mask.setPlaceholderCharacter('_');
                } catch (ParseException exception) {
                    exception.printStackTrace();
                }
                phone.add(new JFormattedTextField(mask));
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

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.Y_AXIS));
        JLabel dateLabel = new JLabel("Date", SwingConstants.CENTER);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter df = new DateFormatter(format);
        date.add(new JFormattedTextField(df));
        date.get(0).setSize(300, 100);
        j[0].add(dateLabel);
        j[0].add(date.get(0));
        String dateM[] = {"birthdate", "anniversary", "Other"};
        dateMenu.add(new JComboBox(dateM));
        j[0].add(dateMenu.get(0));
        datePanel.add(j[0]);

        j[0] = new JPanel();
        j[0].setLayout(new GridLayout(1,3));
        blankLabel = new JLabel("", SwingConstants.CENTER);
        j[0].add(blankLabel);
        blankLabel = new JLabel("", SwingConstants.CENTER);
        j[0].add(blankLabel);
        addDate = new JButton("Add Date");
        j[0].add(addDate);
        datePanel.add(j[0]);

        contactPanel.add(datePanel);

        final int[] dateCounter = {1};

        addDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j[0] = new JPanel();
                JLabel dateLabel = new JLabel("Date" + (dateCounter[0]+1), SwingConstants.CENTER);
                j[0].setLayout(new GridLayout(1,3));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                DateFormatter df = new DateFormatter(format);
                date.add(new JFormattedTextField(df));
                date.get(dateCounter[0]).setSize(300, 100);
                j[0].add(dateLabel);
                j[0].add(date.get(dateCounter[0]));
                String dateM[] = {"birthdate", "anniversary", "Other"};
                dateMenu.add(new JComboBox(dateM));
                j[0].add(dateMenu.get(dateCounter[0]));
                datePanel.add(j[0]);
                contact.repaint();
                contact.setVisible(true);
                dateCounter[0]++;
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

    public void showAllContacts(List<Contact> ret) {
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
                    //JOptionPane.showMessageDialog(null, table.getValueAt(row, column));
                    updateid = (String) table.getValueAt(row, column);
                    editContact(updateid);

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
            date = new ArrayList<>();
            dateMenu = new ArrayList<>();


            JLabel firstNameLabel = new JLabel("First Name", SwingConstants.CENTER);
            JLabel middleNameLabel = new JLabel("Middle Name", SwingConstants.CENTER);
            JLabel lastNameLabel = new JLabel("Last Name", SwingConstants.CENTER);



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
                j[0].setLayout(new GridLayout(1, 3));
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
                j[0].setLayout(new GridLayout(1, 3));
                city.add(new JTextField());
                city.get(i[0]).setText(a.getCity());
                blankLabel = new JLabel("", SwingConstants.CENTER);
                j[0].add(cityLabel);
                j[0].add(city.get(i[0]));
                j[0].add(blankLabel);
                addressPanel.add(j[0]);

                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1, 3));
                state.add(new JTextField());
                state.get(i[0]).setText(a.getState());
                blankLabel = new JLabel("", SwingConstants.CENTER);
                j[0].add(stateLabel);
                j[0].add(state.get(i[0]));
                j[0].add(blankLabel);
                addressPanel.add(j[0]);

                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1, 3));
                zipCode.add(new JTextField());
                zipCode.get(i[0]).setText(a.getZip());
                j[0].add(zipCodeLabel);
                j[0].add(zipCode.get(i[0]));
                if (!it.hasNext()) {
                    addAddress = new JButton("Add Address");
                    j[0].add(addAddress);

                } else {
                    blankLabel = new JLabel("", SwingConstants.CENTER);
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

            Set<Phone> phoneNumbers = contact1.getPhone();
            Iterator<Phone> itPhone = phoneNumbers.iterator();

            final int[] phoneCounter = {0};
            while(itPhone.hasNext()) {
                JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);
                Phone p = itPhone.next();
                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1,3));
                phone.add(new JFormattedTextField());
                phone.get(phoneCounter[0]).setSize(300, 100);
                phone.get(phoneCounter[0]).setText("(" + p.getAreaCode() + ")" + "-" + p.getNumber());
                j[0].add(phoneLabel);
                j[0].add(phone.get(phoneCounter[0]));
                String phoneM[] = {"HOME", "WORK", "OTHER"};
                phoneMenu.add(new JComboBox(phoneM));
                phoneMenu.get(phoneCounter[0]).setSelectedItem(p.getPhoneType());
                j[0].add(phoneMenu.get(phoneCounter[0]));
                phonePanel.add(j[0]);
                phoneCounter[0]++;
            }



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


            addPhone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel j = new JPanel();
                    JLabel phoneLabel = new JLabel("Phone", SwingConstants.CENTER);
                    j.setLayout(new GridLayout(1,3));
                    MaskFormatter mask = null;
                    try {
                        // Create a MaskFormatter for accepting phone number, the # symbol accept
                        // only a number. We can also set the empty value with a place holder
                        // character.
                        mask = new MaskFormatter("(###)-###-####");
                        mask.setPlaceholderCharacter('_');
                    } catch (ParseException exception) {
                        exception.printStackTrace();
                    }
                    phone.add(new JFormattedTextField(mask));
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

            JPanel datePanel = new JPanel();
            datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.Y_AXIS));


            final int[] dateCounter = {0};

            Set<Date> dates = contact1.getDate();
            Iterator<Date> itDate = dates.iterator();

            while(itDate.hasNext()) {
                Date d = itDate.next();
                JLabel dateLabel = new JLabel("Date" + (dateCounter[0]+1), SwingConstants.CENTER);
                j[0] = new JPanel();
                j[0].setLayout(new GridLayout(1,3));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                DateFormatter df = new DateFormatter(format);
                date.add(new JFormattedTextField(df));
                date.get(dateCounter[0]).setText(d.getDate());
                date.get(dateCounter[0]).setSize(300, 100);
                j[0].add(dateLabel);
                j[0].add(date.get(dateCounter[0]));
                String dateM[] = {"birthdate", "anniversary", "Other"};
                dateMenu.add(new JComboBox(dateM));
                j[0].add(dateMenu.get(dateCounter[0]));
                dateMenu.get(dateCounter[0]).setSelectedItem(d.getDateType());
                datePanel.add(j[0]);

            }

            j[0] = new JPanel();
            j[0].setLayout(new GridLayout(1,3));
            blankLabel = new JLabel("", SwingConstants.CENTER);
            j[0].add(blankLabel);
            blankLabel = new JLabel("", SwingConstants.CENTER);
            j[0].add(blankLabel);
            addDate = new JButton("Add Date");
            j[0].add(addDate);
            datePanel.add(j[0]);

            contactPanel.add(datePanel);



            addDate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    j[0] = new JPanel();
                    JLabel dateLabel = new JLabel("Date" + dateCounter[0]+1, SwingConstants.CENTER);
                    j[0].setLayout(new GridLayout(1,3));
                    DateFormat format = new SimpleDateFormat("yyyy-MMMM-dd");
                    DateFormatter df = new DateFormatter(format);
                    date.add(new JFormattedTextField(df));
                    date.get(dateCounter[0]).setSize(300, 100);
                    j[0].add(dateLabel);
                    j[0].add(date.get(dateCounter[0]));
                    String dateM[] = {"birthdate", "anniversary", "Other"};
                    dateMenu.add(new JComboBox(dateM));
                    j[0].add(dateMenu.get(dateCounter[0]));
                    datePanel.add(j[0]);
                    contact.repaint();
                    contact.setVisible(true);
                    dateCounter[0]++;
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
            List<Contact> ret = contactService.getContacts();
            showAllContacts(ret);
        }
        if(e.getSource() == insertContact) {
            Set<Address> addresses = new HashSet<>();
            for(int i=0; i<address.size();i++) {
                addresses.add(new Address(searchMenu.get(i).getSelectedItem().toString(), address.get(i).getText(), city.get(i).getText(), state.get(i).getText(),zipCode.get(i).getText()));
            }
            System.out.println(addresses.size());
            Set<Phone> phones = new HashSet<>();
            for(int i=0; i< phone.size(); i++) {
                String[] phoneNumber = new String[3];
                phoneNumber = phone.get(i).getText().split("-");
                phones.add(new Phone(phoneMenu.get(i).getSelectedItem().toString(), phoneNumber[0].substring(1,4), phoneNumber[1] + "-" + phoneNumber[2]));
            }

            Set<Date> dates = new HashSet<>();
            for(int i=0; i<date.size();i++) {
                dates.add(new Date(dateMenu.get(i).getSelectedItem().toString(), date.get(i).getText()));
            }
            JOptionPane.showMessageDialog(null, contactService.insertContact(new Contact(firstName.getText(), middleName.getText(), lastName.getText(), addresses, dates, phones)));
            // System.out.println(contactService.insertContact(new Contact(firstName.getText(), middleName.getText(), lastName.getText(), addresses, dates, phones)));
        }
        if(e.getSource() == updateContact) {
            Set<Address> addresses = new HashSet<>();
            for(int i=0; i<address.size();i++) {
                if(address.get(i).getText().replaceAll("\\s", "") != "" || city.get(i).getText().replaceAll("\\s", "") != "" || state.get(i).getText().replaceAll("\\s", "") != "" || zipCode.get(i).getText().replaceAll("\\s", "") != "") {
                    System.out.print("Hello");
                    addresses.add(new Address(searchMenu.get(i).getSelectedItem().toString(), address.get(i).getText(), city.get(i).getText(), state.get(i).getText(),zipCode.get(i).getText()));
                }


            }
            System.out.println(addresses.size());
            Set<Phone> phones = new HashSet<>();
            for(int i=0; i< phone.size(); i++) {
                String[] phoneNumber = new String[3];
                phoneNumber = phone.get(i).getText().split("-");
                phones.add(new Phone(phoneMenu.get(i).getSelectedItem().toString(), phoneNumber[0].substring(1,4), phoneNumber[1] + "-" + phoneNumber[2]));
            }

            Set<Date> dates = new HashSet<>();
            for(int i=0; i<date.size();i++) {
                dates.add(new Date(dateMenu.get(i).getSelectedItem().toString(), date.get(i).getText()));
            }
            JOptionPane.showMessageDialog(null, contactService.updateContact(new Contact(updateid, firstName.getText(), middleName.getText(), lastName.getText(), addresses, dates, phones)));
            // System.out.println(contactService.insertContact(new Contact(updateid, firstName.getText(), middleName.getText(), lastName.getText(), addresses, dates, phones)));
            contact.dispatchEvent(new WindowEvent(contact, WindowEvent.WINDOW_CLOSING));
        }
        if(e.getSource() == deleteContact){
            JOptionPane.showMessageDialog(null, contactService.deleteContact(updateid));
            contact.dispatchEvent(new WindowEvent(contact, WindowEvent.WINDOW_CLOSING));
            searchResults.dispatchEvent(new WindowEvent(searchResults, WindowEvent.WINDOW_CLOSING));
            showAllContacts(contactService.getContacts());
        }
        if(e.getSource() == searchButton) {
            String[] search = searchBox.getText().split(" ");
            List<Contact> contactList = contactService.getContacts();
            List<Contact> resultList = new ArrayList<>();
            if(contactList.size() == 0){
                JOptionPane.showMessageDialog(f, "No Contacts found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                for(int i=0; i<contactList.size();i++) {
                    List<String> contact = new ArrayList<String>();
                    contact.add(contactList.get(i).getFname().toLowerCase());
                    contact.add(contactList.get(i).getMname().toLowerCase());
                    contact.add(contactList.get(i).getLname().toLowerCase());
                    Set<Address> address = contactList.get(i).getAddress();
                    Iterator<Address> itAddress = address.iterator();
                    while(itAddress.hasNext()) {
                        Address a = itAddress.next();
                        String[] aList = a.getAddress().split(" ");
                        for(int k=0;k<aList.length;k++) {
                            contact.add(aList[k].toLowerCase());
                        }
                        contact.add(a.getCity().toLowerCase());
                        contact.add(a.getState().toLowerCase());
                        contact.add(a.getZip().toLowerCase());
                    }
                    Set<Phone> phone = contactList.get(i).getPhone();
                    Iterator<Phone> itPhone = phone.iterator();
                    while(itPhone.hasNext()) {
                        Phone p = itPhone.next();
                        contact.add(p.getAreaCode());
                        String[] temp = p.getNumber().split("-");
                        contact.add(temp[0]+temp[1]);
                        contact.add(p.getAreaCode()+temp[0]+temp[1]);
                    }
                    for(int j=0; j<search.length;j++) {
                        if(contact.contains(search[j].toLowerCase())) {
                            resultList.add(contactList.get(i));
                            break;
                        }
                    }

                }
            }
            showAllContacts(resultList);
        }
    }

    public GUI(){

    }
}
