package com.cybervision;

//TODO: It is necessary to connect log4j jar or use a logger from java.util.logging
import org.apache.log4j.Logger;

//Optimize imports
import java.util.*;

//It's very likely that the User class is a POJO. Possible methods 'getUserHobbies' and 'calculateHobbyCosts' relate to the service layer.
public class User {

    //Must be 'private static final' and upper case
    private static final Logger LOGGER = Logger.getLogger(User.class.toString());

    //Fields must be camel case
    //Fields must be private
    //TODO: It may even be better to make them final (if you want to use these objects as keys in a hash-map for example) and remove default constructor
    private String userName;

    private String userSurName;

    //TODO: Why use the default constructor? If not for serialization, then delete
    public User() {
    }

    //It is preferable to use a chain of constructors
    public User(String userName) {
        this(userName, null);
    }

    //Arguments must be camel case
    public User(String userName, String userSurName) {
        this.userName = userName;
        this.userSurName = userSurName;
    }

    Collection<Hobby> getUserHobbies(DummyDao dao) {
        LOGGER.debug("Get " + userName + "'s hobbies from database");
        //Simplified
        return new TreeSet<>(dao.getHobbies(userName));
    }

    double calculateHobbyCosts(DummyDao dao) {
        //Simplified
        //Maybe preferred to use Java8 features
        return getUserHobbies(dao).stream().mapToDouble(dao::getHobbyAverageCost).sum();
    }

    //Use the correct method signature
    //Strings are compared by 'equals'
    //Additional checks: null, equality of objects, of classes
    //Null-safe checks of strings
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(userSurName, user.userSurName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userSurName);
    }

    //TODO: It is meaningless to have a class that contains only one string field. It is necessary to delete and use a simple String
    public class Hobby {

        String name;

        public Hobby(String name) {
            this.name = name;
        }

        //TODO: Why use the default constructor? If not for serialization, then delete
        public Hobby() {
        }
    }

    //TODO: extract to standalone class
    public abstract class DummyDao {

        //Use context-sensitive parameter names
        abstract List<User.Hobby> getHobbies(String userName);

        //Use context-sensitive parameter names
        abstract Double getHobbyAverageCost(User.Hobby hobby);
    }
}
