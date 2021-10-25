package standAloneApp.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Phone {
    @Id
    private String phoneId;
    private String phoneType;
    private String areaCode;
    private String number;

    public Phone() {

    }

    public Phone(String phoneType, String areaCode, String number) {
        this.setPhoneId(UUID.randomUUID().toString());
        this.phoneType = phoneType;
        this.areaCode = areaCode;
        this.number = number;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }


    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
