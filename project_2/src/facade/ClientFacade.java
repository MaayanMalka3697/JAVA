package facade;

import DAO.PersonDAO;
import DAO.PersonDAODB;
import entities.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ClientFacade {

    protected PersonDAO personDAO =new PersonDAODB();
    protected abstract boolean Login(String userName,String password) ;

    protected ClientFacade() throws SQLException, ClassNotFoundException {
    }
    public boolean addPerson(Person person){
        ArrayList<Person> persons = (ArrayList<Person>) personDAO.getAllPersons();
        for (Person p: persons) {
            if(p.getUser().getUserName().equals(person.getUser().getUserName()) || p.getUser().getPassword().equals(person.getUser().getPassword()))
                return false;
            if(p.getBirthDate().isAfter(LocalDate.now()) || LocalDate.now().getYear() - p.getBirthDate().getYear() < 18)
                return false;
        }
        personDAO.addPerson(person);
        return true;
    }

//    public boolean updatePerson(Person person){
//        Person p = personDAO.getPersonById(person.getId());
//        if(p != null){
//            person.setId(p.getId());
//            person.setDateOfBirth(p.getDateOfBirth());
//            person.setMan(p.isMan());
//            personDAO.updatePerson(person);
//            return true;
//        }
//        return false;
//    }

    public boolean updatePerson(Person person){
        Person p = personDAO.getPerson(person.getId());
        if(p != null){
            personDAO.updatePerson(person);
            return true;
        }
        return false;
    }




}
