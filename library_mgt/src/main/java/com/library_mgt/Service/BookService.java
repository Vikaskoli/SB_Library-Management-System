package com.library_mgt.Service;

import com.library_mgt.Entity.Book;
import com.library_mgt.Entity.Publisher;
import com.library_mgt.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //returns all the instances of the book
   public List<Book> findAllBooks(){
       return bookRepository.findAll();
   }

   //returns specific book from db
    public Book findBookById(Long id){
       Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        return book;
    }

    //Add new Book
    public void createBook(Book book){
       bookRepository.save(book);
    }

/*
The error "Multiple representations of the same entity are being merged" happened because:

When you submitted your form without changing authors/publishers, the form contained references to the same author/publisher entities already in the database.
Hibernate saw two versions of the same entity:

The managed one (already in the persistence context)
The detached one (coming from your form submission)


With CascadeType.ALL, Hibernate tried to persist these relationships again, causing conflicts.

By fetching the managed entity first and manually updating its properties and relationships, we avoid this conflict entirely. We're working with a single instance of each entity that Hibernate is already tracking.
This solution works particularly well with Many-to-Many relationships where the same author or publisher can be associated with multiple books.
*/
    public void updateBook(Book updatedBook) {
        Book existingBook = bookRepository.findById(updatedBook.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update the fields
        existingBook.setName(updatedBook.getName());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setDescription(updatedBook.getDescription());

        // Handle relationships properly
        existingBook.getAuthors().clear();
        existingBook.getCategories().clear();
        existingBook.getPublishers().clear();

        if (updatedBook.getAuthors() != null) {
            updatedBook.getAuthors().forEach(existingBook::addAuthor);
        }

        if (updatedBook.getCategories() != null) {
            updatedBook.getCategories().forEach(existingBook::addCategory);
        }

        if (updatedBook.getPublishers() != null) {
            updatedBook.getPublishers().forEach(existingBook::addPublisher);
        }

        bookRepository.save(existingBook);
    }

    //Deleting book
    public void deleteBook(Long id){
       Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
       bookRepository.deleteById(book.getId());
    }
}
