package com.library_mgt.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter

@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "isbn", unique = true,nullable = false, length = 50)
    private String isbn;

    @Column(name = "book_name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", unique = true, length = 250)
    private String description;

    public Book(String isbn, String bookName, String myFirstBook) {
        this.isbn = isbn;
        this.name = bookName;
    }


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_authors",

                joinColumns = {@JoinColumn(name = "book_id")},
                inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_categories",

                joinColumns = {@JoinColumn(name ="book_id" )},
                inverseJoinColumns ={@JoinColumn(name = "category_id")} )
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_publishers",

            joinColumns = {@JoinColumn(name ="book_id" )},
            inverseJoinColumns ={@JoinColumn(name = "publisher_id")} )
    private Set<Publisher> publishers = new HashSet<Publisher>();


    //Biderectional Methods
    //In object-oriented programming (especially in JPA), bidirectional relationships mean that both entities know about each other.
    //So the connection is two-way:
    //➡️ Book ↔ Publisher
    //➡️ Book ↔ Author
    //➡️ Book ↔ Category

    //Publisher add and remove
    public void removePublisher(Publisher publisher){    //getting publisher object
        this.publishers.remove(publisher);
        publisher.getBooks().remove(publisher);
    }

    public void addPublisher(Publisher publisher){
        this.publishers.add(publisher);     // Add publisher to current book
        publisher.getBooks().add(this);     // Add current book to publisher
    }

    //Author add and remove
    public void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(author);
    }

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    //Categorr add and remove
    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getBooks().remove(this);
    }
    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }

    //constructor

    public Book() {
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {
        this.publishers = publishers;
    }
}
