package com.library_mgt;

import com.library_mgt.Entity.Author;
import com.library_mgt.Entity.Book;
import com.library_mgt.Entity.Category;
import com.library_mgt.Entity.Publisher;
import com.library_mgt.Service.BookService;
import org.hibernate.engine.internal.Cascade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryMgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMgtApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner initialCreate(BookService bookService){
		return (args) ->{
			Book book1 = new Book("ABC001", "Mastering Java", "Comprehensive guide to Java programming");
			Author author1 = new Author("John Doe", "Expert Java developer and author");
			Category category1 = new Category("Programming");
			Publisher publisher1 = new Publisher("TechPress");

			book1.addAuthor(author1);
			book1.addCategory(category1);
			book1.addPublisher(publisher1);
			bookService.createBook(book1);

// Dummy Book 2
			Book book2 = new Book("ABC002", "Spring Boot in Action", "Step-by-step Spring Boot project guide");
			Author author2 = new Author("Jane Smith", "Spring expert and software architect");
			Category category2 = new Category("Frameworks");
			Publisher publisher2 = new Publisher("Springer Publishing");

			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.createBook(book2);

// Dummy Book 3
			Book book3 = new Book("ABC003", "Effective Communication", "How to communicate effectively at work");
			Author author3 = new Author("Mark Johnson", "Soft skills trainer and speaker");
			Category category3 = new Category("Self-help");
			Publisher publisher3 = new Publisher("LifeLearn Books");

			book3.addAuthor(author3);
			book3.addCategory(category3);
			book3.addPublisher(publisher3);
			bookService.createBook(book3);

// Dummy Book 4
			Book book4 = new Book("ABC004", "Data Structures Made Easy", "DSA fundamentals with Java");
			Author author4 = new Author("Emily Carter", "Computer Science professor and DSA specialist");
			Category category4 = new Category("Computer Science");
			Publisher publisher4 = new Publisher("CodeCrafters");

			book4.addAuthor(author4);
			book4.addCategory(category4);
			book4.addPublisher(publisher4);
			bookService.createBook(book4);

		};
	}
*/
}
