/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author weiyi.ngow.2012
 */
public class Student {
    private String studentID;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String admissionYear;
    private String tutorialGroup;
    private String adminID;

    public Student(String studentID, String password, String firstName, String lastName, String gender, String email, String admissionYear, String tutorialGroup, String adminID) {
        this.studentID = studentID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.admissionYear = admissionYear;
        this.tutorialGroup = tutorialGroup;
        this.adminID = adminID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
    
    
    
}
