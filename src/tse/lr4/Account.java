package tse.lr4;

import java.io.Serializable;

/**
 * Класс хранения данных о пользователе.
 * @author aNNiMON
 */
public class Account implements Serializable {

    private String login;
    private String md5hash;

    public Account(String login, String md5hash) {
        this.login = login;
        this.md5hash = md5hash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMd5hash() {
        return md5hash;
    }

    public void setMd5hash(String md5hash) {
        this.md5hash = md5hash;
    }
}
