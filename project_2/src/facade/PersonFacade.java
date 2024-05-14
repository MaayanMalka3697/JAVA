package facade;

import com.mysql.cj.x.protobuf.Mysqlx;
import entities.Person;

import java.sql.SQLException;
import java.time.LocalDate;

public class PersonFacade extends ClientFacade{


    protected PersonFacade() throws SQLException, ClassNotFoundException {
    }

    @Override
    public boolean Login(String userName, String password) {

       try {
           if(personDAO.isExist(userName,password))
               return true;
           return false;

       }
       catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public String getPersonDetails(int id)
    {
       Person p= personDAO.getPerson(id);
      if(p!=null)
          return p.toString();
      return "id not found";
    }

//    public boolean delete(){
//        return true;
//}
    public String getDetails(int id){
        Person p = personDAO.getPerson(id);
        if(p != null){
            return p.toString();
        }
        return "This Id does not exist in this system";
    }

    public int calculateAge(int id){
        Person p = personDAO.getPerson(id);
        if(p != null){
            return  LocalDate.now().getYear()-p.getBirthDate().getYear();
        }
        return -1;

    }



}
