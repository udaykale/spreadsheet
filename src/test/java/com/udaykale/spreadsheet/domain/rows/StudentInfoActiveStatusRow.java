package com.udaykale.spreadsheet.domain.rows;

import com.udaykale.spreadsheet.annotation.Cell;

/**
 * @author uday
 */
public class StudentInfoActiveStatusRow {

    @Cell(position = 14)
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentInfoActiveStatusRow that = (StudentInfoActiveStatusRow) o;

        return isActive != null ? isActive.equals(that.isActive) : that.isActive == null;

    }

    @Override
    public int hashCode() {
        return isActive != null ? isActive.hashCode() : 0;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
