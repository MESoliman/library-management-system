Library Management System API
Overview

The Library Management System API is a Spring Boot application designed to manage books, patrons, and borrowing records in a library. The system allows librarians to add, update, delete, and retrieve books and patrons, as well as track borrowing and returning of books.
Features

    Book Management: Add, update, delete, and retrieve book information.
    Patron Management: Add, update, delete, and retrieve patron information.
    Borrowing Records: Record borrowing and returning of books.
    Validation and Error Handling: Ensure data integrity with input validation and proper error handling.

Installation
Prerequisites

    Java 17 or higher
    Maven
    MySQL 8 or another supported database

Steps

    Clone the Repository

    bash

git clone https://github.com/MESoliman/library-management-system.git
cd library-management-system

Build the Project

bash

mvn clean install

Run the Application

bash

    mvn spring-boot:run

    The application will start on http://localhost:8081.

API Routes
Book Management

Retrieve All Books

http

    GET /api/books

Response: List of all books.

Retrieve a Book by ID

http

    GET /api/books/{bookId}

Response: Details of the book with the specified ID.

Add a New Book

http

    POST /api/books

Request Body:
    
json
    
    {
        "title": "Book Title",
        "author": "Author Name",
        "publication_year": 2024,
        "isbn": "1234567890123"
    }

Response: Confirmation of book creation.

Update an Existing Book

http

    PUT /api/books/{bookId}
    
Request Body:
    
json
    
    {
        "title": "Updated Title",
        "author": "Updated Author",
        "publication_year": 2024,
        "isbn": "1234567890123"
    }

Response: Confirmation of book update.

Delete a Book

http

    DELETE /api/books/{bookId}

    Response: Confirmation of book deletion.

Patron Management

Retrieve All Patrons

http

    GET /api/patrons

Response: List of all patrons.

Retrieve a Patron by ID

http

    GET /api/patrons/{patronId}

Response: Details of the patron with the specified ID.

Add a New Patron

http

    POST /api/patrons

Request Body:
    
json
    
    {
        "name": "test",
        "email": "test@test",
        "address": "test",
        "phone_number": "+2015358421"
    }

Response: Confirmation of patron creation.

Update an Existing Patron

http

    PUT /api/patrons/{patronId}

Request Body:

json

    {
        "name": "test",
        "email": "test@test",
        "address": "test",
        "phone_number": "+2015358421"
    }

Response: Confirmation of patron update.

Delete a Patron

http

    DELETE /api/patrons/{patronId}

    Response: Confirmation of patron deletion.

Borrowing Records

Borrow a Book

http

    POST /api/borrowingRecords/borrow/{bookId}/patron/{patronId}

Response: Confirmation of book borrowing.

Return a Book

http

    PUT /api/borrowingRecords/return/{bookId}/patron/{patronId}

Response: Confirmation of book return.