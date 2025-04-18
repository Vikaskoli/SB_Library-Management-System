package com.library_mgt.Controller;

import com.library_mgt.Entity.Book;
import com.library_mgt.LibraryMgtApplication;
import com.library_mgt.Service.AuthorService;
import com.library_mgt.Service.BookService;
import com.library_mgt.Service.CategoryService;
import com.library_mgt.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import java.util.List;

@Controller

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/books")
    public String findAllBooks(Model model){
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books",books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String findBook(@PathVariable Long id, Model model){
        Book book = bookService.findBookById(id);
        model.addAttribute("book",book);
        return "list-book";
    }

    @GetMapping("remove-book/{id}")
    public String deleteBook(@PathVariable Long id, Model model){
            bookService.deleteBook(id);
            model.addAttribute("books" , bookService.findAllBooks()); //after refreshing we will see the remaining books on UI
            return "books";
    }

    //update book byId
    @GetMapping("/update-book/{id}")
    public  String updateBook(@PathVariable Long id, Model model){
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);

        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("publishers",publisherService.findAllPublishers());
        model.addAttribute("authors",authorService.findAllAuthors());
        return "update-book";
    }
    //save-update byId

    @PostMapping("/save-update/{id}")
    public String updateBook(@PathVariable Long id, Book updatedBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-book";
        }

        Book existingBook = bookService.findBookById(id);
        existingBook.setName(updatedBook.getName());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setDescription(updatedBook.getDescription());

        // Handle relationships (similar to above)

        bookService.updateBook(existingBook);
        return "redirect:/books";
    }

    @GetMapping("/add-book")
    public  String addBook(Book book,Model model){
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("publishers",publisherService.findAllPublishers());
        model.addAttribute("authors",authorService.findAllAuthors());
        return "add-book";
    }

    @PostMapping("/save-book")
    public String saveBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.createBook(book);
        model.addAttribute("books",bookService.findAllBooks());
        return "redirect:/books";
    }
}
