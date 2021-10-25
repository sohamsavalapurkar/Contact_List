package standAloneApp.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standAloneApp.backend.entity.Contact;
import standAloneApp.backend.service.ContactService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Component
public class GUI{
    JFrame f = new JFrame();
    JFrame librarian, searchResults;
    JButton login, loginSelect, searchButton, issueBook, returnBook, addBook, addMultipleBook, addUser, addMultipleUser, showUserDetails, issuedBook, returnedBook, addedBook, addedUser, logout;
    JTextField loginField, searchBox, regId, bookId, bookName, authorName, numberOfCopies, userName;
    JPasswordField passwordField;
    JComboBox searchMenu, accessLevel;
    String[] access = {"Admin", "Teacher", "Student"};

    @Autowired
    ContactService contactService;



    private void mainPage(){
        f.setLayout(null);

        JLabel title = new JLabel("Library Management");
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


        String menu[] = {"Search By Book ID", "Search By Title", "Search By Author"};
        searchMenu = new JComboBox(menu);
        searchMenu.setSize(170, 30);
        searchMenu.setLocation(x-250, 250);

        loginSelect = new JButton("Login");
        loginSelect.setSize(250, 30);
        loginSelect.setLocation(x+850, 100);

        List<Contact> temp = new ArrayList<>();
        temp = contactService.getContacts();
        for(int i=0; i<15; i++) {
            System.out.println(temp.get(i).getFname());
        }

        f.add(title);
        f.add(loginSelect);
        f.add(searchMenu);
        f.add(searchBox);
        f.add(searchButton);
        f.setVisible(true);
    }




    public GUI(){
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
}
