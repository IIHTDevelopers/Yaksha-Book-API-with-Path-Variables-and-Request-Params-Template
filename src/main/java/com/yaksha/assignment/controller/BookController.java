package com.yaksha.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaksha.assignment.dto.Book;
import com.yaksha.assignment.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// Get book by ID
	@GetMapping("/{id}")
	public Book getBook(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	// Get all books
	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	// Create a new book
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.createBook(book);
	}

	// Update book
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable int id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}

	// Delete book by ID
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
	}
}
