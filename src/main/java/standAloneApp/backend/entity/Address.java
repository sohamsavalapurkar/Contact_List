package standAloneApp.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Address {
    @Id
    private String addressId;
    private String addressType;
    private String address;
    private String city;
    private String state;
    private int zip;


    public Address(String addressType, String address, String city, String state, int zip) {
        this.setAddressId(UUID.randomUUID().toString());
        this.addressType = addressType;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }


}
