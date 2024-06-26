package Homeworks_And_Labs.L09_Java_Advanced_IteratorsAndComparators_LAB.Library;

import java.util.Iterator;

public class Library implements Iterable<Book> {

    private Book[] books;

    public Library(Book... books) {
        //нова библиотека
        this.books = books;
    }

    @Override
    public Iterator<Book> iterator() {
        return new LibraryIterator();
    }

    //ITERATOR
    private class LibraryIterator implements Iterator<Book> {
        private int count = 0;
        @Override
        public boolean hasNext() {
            return count < books.length;
        }

        @Override
        public Book next() {
            return books[count++];
        }
    }
}
