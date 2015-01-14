package PersonDB;

import javax.persistence.*;

/**
 * Created by Александр on 22.12.14.
 */
@Entity
@Table(name = "Phones")
public class Phone
{
    private int phone_id;
    private String phoneNumber;
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_Id", nullable = false)
    public Person getPerson_id() {
        return person;
    }

    public void setPerson_id(Person person_id) {
        this.person = person_id;
    }

    public void addPhone()
    {
        phone_id++;
    }
    public void delPhone()
    {
        phone_id--;
    }
}
