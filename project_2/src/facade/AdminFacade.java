package facade;

import entities.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminFacade extends ClientFacade{

    protected AdminFacade() throws SQLException, ClassNotFoundException {
    }
    @Override
    protected boolean Login(String userName, String password) {

       if(userName=="admin@admin.com"&& password=="admin")
            return true;
        return false;
    }
    public void deletePerson(int id){
        personDAO.deletePerson(id);
    }

    public ArrayList<Person> getPersonsByBirthDate(LocalDate date){
        return (ArrayList<Person>) personDAO.getAllPersonsByDate(date);
    }

    public Person getPersonById(int id){
        return personDAO.getPerson(id);
    }




}
