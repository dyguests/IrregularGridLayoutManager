package com.fanhl.irregulargridlayoutmanagersample;

import com.fanhl.irregulargridlayoutmanagersample.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhl on 15/12/4.
 */
public class MockUtil {

    public static List<Book> books() {
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Book book = new Book();

            book.title = "Book Title " + i;

            books.add(book);
        }

        return books;
    }
}
