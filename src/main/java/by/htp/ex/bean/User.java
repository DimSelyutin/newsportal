package by.htp.ex.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int id;
    private String login;
    private String phone;
    private String email;
    private String password;
    private String dateRegister;

    public User() {
    }

    public User(String login, String phone, String email, String password) {
        
        this.login = login;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.dateRegister = onCreate();
    }

    public User(int id, String login, String phone, String email, String password, String regDate) {
        this.id = id;
        this.login = login;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.dateRegister = regDate;
    }

    public String onCreate() {
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((dateRegister == null) ? 0 : dateRegister.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (dateRegister == null) {
            if (other.dateRegister != null)
                return false;
        } else if (!dateRegister.equals(other.dateRegister))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", phone=" + phone + ", email=" + email + ", password="
                + password + ", dateRegister=" + dateRegister + "]";
    }

    

}
