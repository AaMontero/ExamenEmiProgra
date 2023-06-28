package org.example.db;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
@SequenceGenerator(name="booksSeq", sequenceName = "books_seq", allocationSize = 1)
public class Books {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String isbn;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Double price;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getAuthor() {
        return author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
