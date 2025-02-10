package com.example.routing

import com.example.model.Book
import com.example.services.BookService
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.bookRoutes() {

    val bookService = BookService()

    route("books") {
        get {
            call.respond(bookService.getBooks())
        }
        get("/{id}") {
            val bookIdFromQuery = call.parameters["id"] ?: kotlin.run {
                throw Exception("Please provide a valid id")
            }
            val book = bookService.getBook(bookIdFromQuery.toIntOrNull())
            if (book == null) {
                call.respond(HttpStatusCode.NotFound, "Book not found");
            } else {
                call.respond(book)
            }
        }
        post {
            val requestBody = call.receive<Book>()
            bookService.addBook(requestBody)
            call.respond(requestBody)
        }
        delete("/{id}") {
            val bookIdFromQuery = call.parameters["id"] ?: kotlin.run {
                throw Exception("Please provide a valid id")
            }
            bookService.deleteBook(bookIdFromQuery?.toIntOrNull())
            call.respond("Book is deleted")
        }
    }

}