package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.yaksha.assignment.controller.BookController;
import com.yaksha.assignment.utils.JavaParserUtils;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetBookById() throws Exception {
		// Perform GET request to /books/{id} endpoint and capture the response
		String response = mockMvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andReturn().getResponse().getContentAsString(); // Get the response body as a string

		// Assert using YakshaAssert that the response contains expected values
		boolean result = response.contains("\"id\":1");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testGetAllBooks() throws Exception {
		// Perform GET request to /books endpoint and capture the response
		String response = mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andReturn().getResponse().getContentAsString(); // Get the response body as a string

		// Assert using YakshaAssert that the response contains expected values
		boolean result = response.contains("\"title\":\"The Catcher in the Rye\"")
				&& response.contains("\"author\":\"J.D. Salinger\"");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testCreateBook() throws Exception {
		// Create a new book JSON object
		String bookJson = "{\"id\":3,\"title\":\"1984\",\"author\":\"George Orwell\",\"genre\":\"Dystopian\"}";

		// Perform POST request to create the new book
		String response = mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andReturn().getResponse().getContentAsString(); // Get the response body as a string

		// Assert using YakshaAssert that the response contains expected values
		boolean result = response.contains("\"id\":3") && response.contains("\"title\":\"1984\"")
				&& response.contains("\"author\":\"George Orwell\"");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testUpdateBook() throws Exception {
		// Create a new book JSON object to update an existing book
		String bookJson = "{\"id\":1,\"title\":\"1984\",\"author\":\"George Orwell\",\"genre\":\"Dystopian\"}";

		// Perform PUT request to update the book with ID 1
		String response = mockMvc.perform(put("/books/1").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andReturn().getResponse().getContentAsString(); // Get the response body as a string

		// Assert using YakshaAssert that the response contains updated values
		boolean result = response.contains("\"id\":1") && response.contains("\"title\":\"1984\"")
				&& response.contains("\"author\":\"George Orwell\"");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testRestControllerAnnotation() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the class is annotated with @RestController
		boolean result = JavaParserUtils.checkClassAnnotation(filePath, "RestController");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testReturnTypeOfGetBook() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the getBook method has the correct return type (Book)
		boolean result = JavaParserUtils.checkMethodReturnType(filePath, "getBook", "Book");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testGetBookAnnotation() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the getBook method has @GetMapping annotation with value "/{id}"
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getBook", "GetMapping")
				&& JavaParserUtils.checkMethodParameterAnnotation(filePath, "getBook", "id", "PathVariable");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testGetAllBooksAnnotation() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the getAllBooks method has @GetMapping annotation
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getAllBooks", "GetMapping");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testCreateBookAnnotation() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the createBook method has @PostMapping annotation
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "createBook", "PostMapping");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testUpdateBookAnnotation() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the updateBook method has @PutMapping annotation with value "/{id}"
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "updateBook", "PutMapping")
				&& JavaParserUtils.checkMethodParameterAnnotation(filePath, "updateBook", "id", "PathVariable");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testDeleteBookAnnotation() throws Exception {
		// Specify the file path to the BookController class
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookController.java"; // Update path to your
																								// controller

		// Check if the deleteBook method has @DeleteMapping annotation with value
		// "/{id}"
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "deleteBook", "DeleteMapping")
				&& JavaParserUtils.checkMethodParameterAnnotation(filePath, "deleteBook", "id", "PathVariable");

		// Assert the result using YakshaAssert
		yakshaAssert(currentTest(), result, businessTestFile);
	}

}
