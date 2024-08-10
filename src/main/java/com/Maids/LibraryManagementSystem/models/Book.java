package com.Maids.LibraryManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.*;

@Entity
public class Book extends BaseEntity<Long>{

    @NotNull(message = "title cannot be null")
    @Size(min = 3, max = 50, message = "title must be between 3 and 50 characters")
    private String title;
    @NotNull(message = "author name cannot be null")
    @Size(min = 3, max = 50, message = "author name must be between 3 and 50 characters")
    private String author;

    @NotNull(message = "Publication year cannot be null")
    @Min(value = 1450, message = "Publication year must be greater than 1449")
    @Max(value = 2100, message = "Publication year must be less than 2101")
    @JsonProperty("publication_year")
    private int publicationYear;
    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^(97[89])?\\d{9}(\\d|X)$", message = "Invalid ISBN format")
    private String isbn;
    private Boolean available;

    @PrePersist
    public void prePersist() {
        if (this.available == null) {
            this.available = true;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
