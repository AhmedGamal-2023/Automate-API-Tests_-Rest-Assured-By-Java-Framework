package Pojo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
public class LoginPojo {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
    public LoginPojo(String email,String password){
        this.email=email;
        this.password=password;
    }
}
