package standAloneApp.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Date {
    @Id
    private String dateID;
    private String contactID;
    private String dateType;
    private String date;

    public Date(String contactID, String dateType, String date) {
        this.setDateID(UUID.randomUUID().toString());
        this.contactID = contactID;
        this.dateType = dateType;
        this.date = date;
    }

    public String getDateID() {
        return dateID;
    }

    public void setDateID(String dateID) {
        this.dateID = dateID;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
