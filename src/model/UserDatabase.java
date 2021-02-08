package model;

import java.io.Serializable;
import java.util.TreeMap;

public class UserDatabase implements Serializable{

    private static final long serialVersionUID = 439299753267505107L;
    private TreeMap<String, Account> userDatabase; //Can support any amount of users

    public UserDatabase(){
        userDatabase = new TreeMap<String, Account>();
        addUserAccount(new AdminAccount("Vinny", "Verso", "Vin", "6311234567", "pass1234"));

    }

    //Checks to see if a user is already in the database.
    public boolean contains(String username){
        if(userDatabase.containsKey(username)){
            return true;
        }
        return false;
    }

    //Adds an account if it does not exist in the database, return true(successfully added)
    //If the account does exist return false.
    public boolean addUserAccount(Account account){
        if(!userDatabase.containsKey(account.getUsername())){
            userDatabase.put(account.getUsername(), account);
            return true;
        }
        return false;
    }

    //Returns true if the account was removed, false if not.
    public boolean removeAccount(String userName){
        if(userDatabase.containsKey(userName)){
            userDatabase.remove(userName);
            return true;
        }
        return false;
    }

    public Account getAccount(String userName){

        if(userDatabase.containsKey(userName)){
            return userDatabase.get(userName);
        }
        return null;
    }

}
