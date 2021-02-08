package model;

import java.io.*;

public class DataSaver {

    public static void saveUserDatabase(UserDatabase database){

        try {
            //Save the file
            FileOutputStream fs = new FileOutputStream("src/userdata.dat");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(database);

            System.out.println("User Database File Saved!");
            //Close streams to release system resources
            os.close();
            fs.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTrailsData(TrailsDatabase trailsDatabase){
        try {
            //Save the file
            FileOutputStream fs = new FileOutputStream("src/trailsdata.dat");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(trailsDatabase);

            System.out.println("Trails File Saved!");
            //Close streams to release system resources
            os.close();
            fs.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserDatabase loadUserData(){
        UserDatabase database;
        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream("src/userdata.dat");
            ois = new ObjectInputStream(fis);
            database = (UserDatabase) ois.readObject();

            System.out.println("User data loaded!");
            fis.close();
            ois.close();

            return database;
        }catch (IOException | ClassNotFoundException e){
            //e.printStackTrace();
        }
        return null;
    }

    public static TrailsDatabase loadTrailsData(){

        TrailsDatabase database;
        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream("src/trailsdata.dat");
            ois = new ObjectInputStream(fis);
            database = (TrailsDatabase) ois.readObject();

            System.out.println("Trials data loaded!");
            fis.close();
            ois.close();

            return database;
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;

    }

}
