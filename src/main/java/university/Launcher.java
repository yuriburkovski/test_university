package university;

import university.config.SessionProvider;
import university.config.SetupDB;

import static university.controller.CommandListener.run;

public class Launcher {

    public static void main(String[] args) throws Exception {
        SessionProvider.getSessionFactory().openSession();
        SetupDB.saveAll();
        run();
        SessionProvider.shutdown();
    }
}
