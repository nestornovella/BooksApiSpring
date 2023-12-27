package com.market.superMarket.models;

import jakarta.persistence.Entity;


public class AsociateEntities {
    private int authorId;
    private int bookId;
    private int genreId;
    private int lenguageId;

    public AsociateEntities() {

    }
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getLenguageId() {
        return lenguageId;
    }

    public void setLenguageId(int lenguageId) {
        this.lenguageId = lenguageId;
    }
}
