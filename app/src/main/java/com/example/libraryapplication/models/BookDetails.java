package com.example.libraryapplication.models;

public class BookDetails {
    String Title;
    String AuthorName;
    int NumberOfPages;

    public BookDetails(String title, String authorName, int numberOfPages) {
        Title = title;
        AuthorName = authorName;
        NumberOfPages = numberOfPages;
    }

    public BookDetails() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public int getNumberOfPages() {
        return NumberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        NumberOfPages = numberOfPages;
    }
}
