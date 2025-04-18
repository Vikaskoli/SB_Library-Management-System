package com.library_mgt.Service;

import com.library_mgt.Entity.Author;
import com.library_mgt.Entity.Book;
import com.library_mgt.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    //Fetching author data
     public List<Author> findAllAuthors(){
        return authorRepository.findAll();
     }

     //fetching specific author
     public Author findAuthorById(Long id){
         Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author bot Found"));
         return author;
     }

    //Add new Book
    public void createAuthor(Author author){
        authorRepository.save(author);
    }
    public void updateAuthor(Author author){
        authorRepository.save(author);
    }

    //Deleting book
    public void deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author Not Found"));
        authorRepository.deleteById(author.getId());
    }

}
