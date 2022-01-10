package models;

import interfaces.IBookAuthor;

public class BookAuthor implements IBookAuthor {
    String name;

    public BookAuthor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(IBookAuthor o) {
        return this.name.compareTo(o.getName());
    }
}
