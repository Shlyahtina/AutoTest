package ru.autotest.data;

public enum StartPageHeaderData{
    SECOND_PAGE_HEADER("Learn new words and grammar"),
    FIRST_PAGE_HEADER("Chat to improve your English");

    private String header;

    StartPageHeaderData(String header){
        this.header =header;
    }

    public String getHeader(){
        return header;
    }
}
