package dbpack;
public class User { 
    protected String user_id;
    protected String username;
    protected String password;
    protected String email;
    protected String first_name;
    protected String last_name;
    protected String address_street_num;
    protected String address_street;
    protected String address_city;
    protected String address_state;
    protected String address_zip_code;
    protected String birthday;
    protected String credit_card_number;
    protected String credit_card_expiration_date;
    protected String credit_card_security_code;

    public User() {

    }
    public User(String user_id, String username, String password, String email, String first_name, String last_name, String address_street_num, String address_street, String address_city, String address_state, String address_zip_code, String birthday, String credit_card_number,String credit_cart_expiration_date,String credit_card_security_code) {	
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address_street_num = address_street_num;
        this.address_street = address_street;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip_code = address_zip_code;
        this.birthday = birthday;
        this.credit_card_number = credit_card_number;
        this.credit_card_expiration_date = credit_cart_expiration_date;
        this.credit_card_security_code = credit_card_security_code;
    }
    public User(String user_id, String username, String password, String email, String first_name, String last_name) {	
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;

    }


    public String getUserId() {
        return user_id;
    }
    public void setUserId(String user_id) {
        this.user_id = user_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getAddressStreetNum() {
        return address_street_num;
    }
    public void setAddressStreetNum(String address_street_num) {
        this.address_street_num = address_street_num;
    }
    public String getAddressStreet() {
        return address_street;
    }
    public void setAddressStreet(String address_street) {
        this.address_street = address_street;
    }
    public String getAddressCity() {
        return address_city;
    }
    public void setAddressCity(String address_city) {
        this.address_city = address_city;
    }
    public String getAddressState() {
        return address_state;
    }
    public void setAddressState(String address_state) {
        this.address_state = address_state;
    }
    public String getAddressZipCode() {
        return address_zip_code;
    }
    public void setAddressZipCode(String address_zip_code) {
        this.address_zip_code = address_zip_code;
    }
    public String getCreditCardNumber() {
        return credit_card_number;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.credit_card_number = creditCardNumber;
    }
    public String getCreditCardExpirationDate() {
        return credit_card_expiration_date;
    }
    public void setCreditCardExpirationDate(String creditCardExpirationDate) {
        this.credit_card_expiration_date = creditCardExpirationDate;
    }
    public String getCreditCardSecurityCode() {
        return credit_card_security_code;
    }
    public void setCreditCardSecurityCode(String creditCardSecurityCode) {
        this.credit_card_security_code = creditCardSecurityCode;
    }
}