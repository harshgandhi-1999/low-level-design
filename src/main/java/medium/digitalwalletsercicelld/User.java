package medium.digitalwalletsercicelld;

import java.util.ArrayList;
import java.util.List;

import static medium.digitalwalletsercicelld.Utility.generateUniqueId;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String mobileNumber;

    private final List<Account> accounts;

    public User(String name, String email, String mobileNumber) {
        this.id = generateUniqueId();
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
}
