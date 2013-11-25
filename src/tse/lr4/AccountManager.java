package tse.lr4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Менеджер аккаунтов.
 * @author aNNiMON
 */
public class AccountManager {
    
    public static final byte STATE_ACCOUNT_NOT_EXISTS = 0;
    public static final byte STATE_PASSWORD_INCORRECT = 1;
    public static final byte STATE_AUTH_SUCCESSFULL = 2;
    
    private static final String FILENAME = "accman";

    private static AccountManager instance;

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    private ArrayList<Account> accounts;
    
    public AccountManager() {
        accounts = (ArrayList<Account>) deserialize(FILENAME);
        if (accounts == null) accounts = new ArrayList<>();
    }
    
    public boolean isAccountExists(String login) {
        for (Account account : accounts) {
            if (login.equals(account.getLogin())) return true;
        }
        return false;
    }
    
    public byte checkAuth(String login, String md5hash) {
        for (Account account : accounts) {
            if (login.equals(account.getLogin())) {
                if (md5hash.equals(account.getMd5hash())) {
                    return STATE_AUTH_SUCCESSFULL;
                } else return STATE_PASSWORD_INCORRECT;
            }
        }
        return STATE_ACCOUNT_NOT_EXISTS;
    }
    
    public void createNewAccount(String login, String md5hash) {
        accounts.add(new Account(login, md5hash));
        serialize(FILENAME, accounts);
    }
    
    private void serialize(String filename, Object object) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private Object deserialize(String filename) {
        Object object = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;
    }
}
