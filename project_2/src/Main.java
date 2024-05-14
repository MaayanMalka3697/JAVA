import DAO.PersonDAODB;
import entities.Person;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello world!");
        PersonDAODB p=new PersonDAODB();
        System.out.println(p.getAllPersonsByAge(9));
        System.out.println(p.getAllPersons());
        System.out.println(p.getAllPersonsByFemale(true));
        System.out.println(p.getAllPersonsByDate(new Date(2011,12,11).toLocalDate()));
//        System.out.println();





    }
}