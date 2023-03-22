package api.pojos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String gender;
    private String ssn;
    private String dob;
    private String dom;
    private String emailAddress;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String address;
    private String locality;
    private String region;
    private String postalCode;
    private String country;
}
