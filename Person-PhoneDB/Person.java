package PersonDB;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person{
    private int Id;
    private String FName;
    private String LName;
    private int Age;
    private List<Phone> phoneList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String fName) {
        FName = fName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String lName) {
        LName = lName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public void setPhoneNumber(String phone)
    {
        phoneList = getPhoneList();
        Iterator<Phone> iter = phoneList.iterator();
        while( iter.hasNext() )
        {
            Phone p = iter.next();

        }
    }
}
