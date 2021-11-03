package standAloneApp.backend.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Contact {
    @Id
    private String contactId;
    private String Fname;
    private String Mname;
    private String Lname;

    public Contact() {

    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Set<Date> getDate() {
        return date;
    }

    public void setDate(Set<Date> date) {
        this.date = date;
    }

    public Set<Phone> getPhone() {
        return phone;
    }

    public void setPhone(Set<Phone> phone) {
        this.phone = phone;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Set<Address> address;

    @OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Set<Date> date;

    @OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Set<Phone> phone;

    public Contact( String fname, String mname, String lname, Set<Address> address, Set<Date> date, Set<Phone> phone) {
        this.setContactId(UUID.randomUUID().toString());
        Fname = fname;
        Mname = mname;
        Lname = lname;
        this.address = address;
        this.date = date;
        this.phone = phone;
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
