package standAloneApp.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Contact {
    @Id
    private String contactId;
    private String Fname;
    private String Mname;
    private String Lname;

    public Contact(String fname, String mname, String lname) {
        this.setContactId(UUID.randomUUID().toString());
        this.Fname = fname;
        this.Mname = mname;
        this.Lname = lname;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }
}
