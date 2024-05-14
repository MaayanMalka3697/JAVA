package DAO;

import dbManager.ConnectionPool;
import entities.Person;
import entities.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PersonDAO {


    boolean isExist(int id) throws SQLException;
    boolean isExist(String userName,String password) throws SQLException;
    boolean isExist(User user) throws SQLException;

    void addPerson(Person person);

    void updatePerson(Person person);
    void deletePerson(int id);
    Person getPerson(int id);
    Person getPerson(String userName,String password);
    Person getPerson(User user);



    List<Person> getAllPersons();
    List<Person> getAllPersonsByDate(LocalDate date);
    List<Person> getAllPersonsByAge(int age);
    List<Person> getAllPersonsByFemale(boolean bool);
}
