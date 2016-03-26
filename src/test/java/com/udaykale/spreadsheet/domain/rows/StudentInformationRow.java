package com.udaykale.spreadsheet.domain.rows;

import com.udaykale.spreadsheet.annotation.Cell;

import java.util.Date;

/**
 * @author uday
 */
public class StudentInformationRow {

    @Cell(position = 1)
    private Integer regNumber;
    @Cell(position = 2)
    private Integer rollNumber;
    @Cell(position = 3)
    private Integer grade;
    @Cell(position = 4)
    private String className;
    @Cell(position = 5)
    private String name;
    @Cell(position = 6)
    private String gender;
    @Cell(position = 7)
    private String fathersName;
    @Cell(position = 8)
    private String mothersName;
    @Cell(position = 9)
    private String guardiansName;
    @Cell(position = 10)
    private String surname;
    @Cell(position = 11)
    private Date dob;
    @Cell(position = 12)
    private String phoneNumber;
    @Cell(position = 13)
    private Date doj;
    @Cell(position = 14)
    private Boolean isActive;
    @Cell(position = 15)
    private Double average;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentInformationRow that = (StudentInformationRow) o;

        if (regNumber != null ? !regNumber.equals(that.regNumber) : that.regNumber != null) return false;
        if (rollNumber != null ? !rollNumber.equals(that.rollNumber) : that.rollNumber != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (fathersName != null ? !fathersName.equals(that.fathersName) : that.fathersName != null) return false;
        if (mothersName != null ? !mothersName.equals(that.mothersName) : that.mothersName != null) return false;
        if (guardiansName != null ? !guardiansName.equals(that.guardiansName) : that.guardiansName != null)
            return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (dob != null ? !dob.equals(that.dob) : that.dob != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (doj != null ? !doj.equals(that.doj) : that.doj != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;
        return average != null ? average.equals(that.average) : that.average == null;

    }

    @Override
    public int hashCode() {
        int result = regNumber != null ? regNumber.hashCode() : 0;
        result = 31 * result + (rollNumber != null ? rollNumber.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (fathersName != null ? fathersName.hashCode() : 0);
        result = 31 * result + (mothersName != null ? mothersName.hashCode() : 0);
        result = 31 * result + (guardiansName != null ? guardiansName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (doj != null ? doj.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (average != null ? average.hashCode() : 0);
        return result;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Integer regNumber) {
        this.regNumber = regNumber;
    }

    public Integer getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getGuardiansName() {
        return guardiansName;
    }

    public void setGuardiansName(String guardiansName) {
        this.guardiansName = guardiansName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }
}
