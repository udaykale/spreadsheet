package com.udaykale.spreadsheet.domain.rows;

import com.udaykale.spreadsheet.annotation.Cell;

/**
 * @author uday
 */
public class StudentInfoRegRollGradeRow {

    @Cell(position = 1)
    private Integer regNumber;
    @Cell(position = 2)
    private Integer rollNumber;
    @Cell(position = 3)
    private Integer grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentInfoRegRollGradeRow that = (StudentInfoRegRollGradeRow) o;

        if (regNumber != null ? !regNumber.equals(that.regNumber) : that.regNumber != null) return false;
        if (rollNumber != null ? !rollNumber.equals(that.rollNumber) : that.rollNumber != null) return false;
        return grade != null ? grade.equals(that.grade) : that.grade == null;
    }

    @Override
    public int hashCode() {
        int result = regNumber != null ? regNumber.hashCode() : 0;
        result = 31 * result + (rollNumber != null ? rollNumber.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
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
}
