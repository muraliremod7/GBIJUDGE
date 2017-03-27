package gbijudge.murali.gbijudge.judges;

import android.graphics.Bitmap;

/**
 * Created by Hari Prahlad on 15-05-2016.
 */
public class JudgesCommonClass {
    private String userName,mobileNum,email,companyName,designation;
    private Bitmap profilepic;

    public JudgesCommonClass(){

    }
    public JudgesCommonClass(String userName, String mobileNum, String email, String companyName, String designation, Bitmap profilepic){
        this.userName = userName;
        this.mobileNum = mobileNum;
        this.email = email;
        this.companyName = companyName;
        this.designation = designation;
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Bitmap getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(Bitmap profilepic) {
        this.profilepic = profilepic;
    }
}
