package ru.alishev.springcourse.FirstSecurityApp.util;

public class StringArrayWrapper {
    private String[][] array;

    public StringArrayWrapper() {
    }

    public StringArrayWrapper(String[][] array) {
        this.array = array;
    }

    public String[][] getArray() {
        return array;
    }

    public void setArray(String[][] array) {
        this.array = array;
    }
}
