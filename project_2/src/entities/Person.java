package entities;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    int id;
    String name;
    int age;
    boolean female;
    LocalDate birthDate;
    User user;

    public Person() {
        this.female= false;
       // this.birthDate=new Date();
        this.user=new User("","");
    }

    public Person(String name, int age, boolean female, LocalDate birthDate, User user) {
        this.name = name;
        this.age = age;
        this.female = female;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Person(int id, String name, int age, boolean female, LocalDate birthDate, User user) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.female = female;
        this.birthDate = birthDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", female=" + female +
                ", birthDate=" + birthDate +
                ", user=" + user +
                '}';
    }
}
