package com.yaksha.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yaksha.assignment.dto.Book;

@Service
public class BookService {

	private final List<Book> books = new ArrayList<>();

	public BookService() {
		// Adding sample data
		books.add(new Book(1, "The Catcher in the Rye", "J.D. Salinger", "Fiction"));
		books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction"));
	}

	public Book getBookById(int id) {
		return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
	}

	public List<Book> getAllBooks() {
		return books;
	}

	public Book createBook(Book book) {
		books.add(book);
		return book;
	}

	public Book updateBook(int id, Book book) {
		for (Book b : books) {
			if (b.getId() == id) {
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
				b.setGenre(book.getGenre());
				return b;
			}
		}
		return null;
	}

	public void deleteBook(int id) {
		books.removeIf(book -> book.getId() == id);
	}
}
