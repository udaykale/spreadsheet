package com.udaykale.spreadsheet.domain.rows;

import com.udaykale.spreadsheet.annotation.Cell;

import java.util.Date;

/**
 * @author uday
 */
public class StudentInfoDOJDOBRow {

    @Cell(position = 11)
    private Date dob;
    @Cell(position = 13)
    private Date doj;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentInfoDOJDOBRow that = (StudentInfoDOJDOBRow) o;

        if (dob != null ? !dob.equals(that.dob) : that.dob != null) return false;
        return doj != null ? doj.equals(that.doj) : that.doj == null;

    }

    @Override
    public int hashCode() {
        int result = dob != null ? dob.hashCode() : 0;
        result = 31 * result + (doj != null ? doj.hashCode() : 0);
        return result;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }
}
