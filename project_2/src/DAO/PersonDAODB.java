package DAO;

import dbManager.ConnectionPool;
import entities.Person;
import entities.User;

import javax.swing.text.html.parser.Parser;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDAODB implements PersonDAO {
    ConnectionPool pool= ConnectionPool.getInstance();

    public PersonDAODB() throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean isExist(int id)  {

        String sql="select id from persons where id="+id;
       try {
           ResultSet rs= pool.runGetQuery(sql);
           return rs.next();
       }
       catch (SQLException e){
            throw new RuntimeException(e);

        }


    }

    @Override
    public boolean isExist(String userName, String password){
        String sql="select id from persons where username="+userName+",password="+password;
        try {
            ResultSet rs= pool.runGetQuery(sql);
            return rs.next();
        }
        catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean isExist(User user)  {
        String sql="select id from persons where username="+ user.getUserName() +",password="+user.getPassword();
        try {
            ResultSet rs= pool.runGetQuery(sql);
            return rs.next();
        }
        catch (SQLException e){
            throw new RuntimeException(e);

        }
    }

    @Override
    public void addPerson(Person person) {
        String sql="insert into persons (name,age,female,birthdate,username,password) values ('"+person.getName()+"','"+person.getAge()+"','"+person.isFemale()+"','"+person.getBirthDate()+"','"+person.getUser().getUserName()+"','"+person.getUser().getPassword()+"')";
        try {
            int id= pool.runInsertQuerry(sql);
            person.setId(id);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePerson(Person person) {
        String sql = "update persons set (name,age,female,birthdate,username,password) values('"+person.getName()+"','"+person.isFemale()+"','"+person.getBirthDate()+"','"+person.getUser().getUserName()+"','"+person.getUser().getPassword()+"') where id = "+person.getId() ;
        try {
            pool.runUpdateQuerry(sql);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePerson(int id) {
        String sql="delete from persons where id ="+id;
        try {
            pool.runDeleteQuerry(sql);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person getPerson(int id) {
        String sql="select * from persons where id ="+id;
        try {
            ResultSet rs=pool.runGetQuery(sql);
            if(rs!=null) {
                Person p = new Person();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setFemale(rs.getBoolean("female"));
                p.setUser(new User(rs.getString("username"), rs.getString("password")));
                return p;
            }
            return null;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Person getPerson(String userName, String password) {
        String sql="select * from persons where username ="+userName+",password="+password;
        try {
            ResultSet rs=pool.runGetQuery(sql);
            if(rs!=null) {
                Person p = new Person();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setFemale(rs.getBoolean("female"));
                p.setUser(new User(rs.getString("username"), rs.getString("password")));
                return p;
            }
            return null;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person getPerson(User user) {

        return getPerson(user.getUserName(),user.getPassword());
    }

    @Override
    public List<Person> getAllPersons() {
        String sql="select * from persons";
        try {
            ResultSet rs=pool.runGetQuery(sql);
             ArrayList<Person> persons =new ArrayList<>();
            while(rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setFemale(rs.getBoolean("female"));
                p.setBirthDate(rs.getDate("birthdate").toLocalDate());
                User u = new User();
                u.setPassword(rs.getString("password"));
                u.setUserName(rs.getString("userName"));
                p.setUser(u);
                persons.add(p);
            }
            return persons;


        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Person> getAllPersonsByDate(LocalDate date) {
        String sql="select * from persons where birthdate= '"+date+"'";
        try {
            ResultSet rs = pool.runGetQuery(sql);
            ArrayList<Person> persons =new ArrayList<>();
            while(rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setFemale(rs.getBoolean("female"));
                p.setBirthDate(rs.getDate("birthdate").toLocalDate());
                User u = new User();
                u.setPassword(rs.getString("password"));
                u.setUserName(rs.getString("userName"));
                p.setUser(u);
                persons.add(p);
            }
            return persons;


        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Person> getAllPersonsByAge(int age) {
        String sql="select * from persons where age= "+age;
        try {
            ResultSet rs = pool.runGetQuery(sql);
            ArrayList<Person> persons =new ArrayList<>();
            while(rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setFemale(rs.getBoolean("female"));
                p.setBirthDate(rs.getDate("birthdate").toLocalDate());
                User u = new User();
                u.setPassword(rs.getString("password"));
                u.setUserName(rs.getString("userName"));
                p.setUser(u);
                persons.add(p);
            }
            return persons;


        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> getAllPersonsByFemale(boolean bool) {
        String sql="select * from persons where female= ";
        if(bool)
            sql+="1";
        else
            sql+="0";
        try {
            ResultSet rs = pool.runGetQuery(sql);
            ArrayList<Person> persons =new ArrayList<>();
            while(rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setFemale(rs.getBoolean("female"));
                p.setBirthDate(rs.getDate("birthdate").toLocalDate());
                User u = new User();
                u.setPassword(rs.getString("password"));
                u.setUserName(rs.getString("userName"));
                p.setUser(u);
                persons.add(p);
            }
            return persons;

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
