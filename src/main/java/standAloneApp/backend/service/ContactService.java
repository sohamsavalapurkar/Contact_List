package standAloneApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import standAloneApp.backend.entity.Contact;
import standAloneApp.backend.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {

    }

    public List<Contact> getContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    public Contact getContactById(String id) {
        Contact contact =  contactRepository.findById(id).orElse(null);
        return contact;
    }

    public String insertContact(Contact contact) {
        contactRepository.save(contact);
        return "Inserted Contact Successfully";
    }

    public String updateContact(Contact contact){
        if (contactRepository.existsById(contact.getContactId())){
            contactRepository.save(contact);
            return "Contact Updated Successfully";
        }
        return "Failed to Update Contact";
    }

    public String deleteContact(String contactId){
        if (contactRepository.existsById(contactId)){
            contactRepository.deleteById(contactId);
            return "Contact Deleted Successfully";
        }
        return "Failed to Delete Contact";
    }

}
