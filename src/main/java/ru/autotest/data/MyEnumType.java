package ru.autotest.data;

public enum MyEnumType {
    MORE("больше"),
    EQUALS("равно"),
    LESS("меньше");
    private String title;

    MyEnumType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
