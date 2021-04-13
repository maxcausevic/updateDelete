package com.mcausevic.MVC.controllers;

import com.mcausevic.MVC.models.book;
import com.mcausevic.MVC.services.BookService;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksApi {
 private final BookService bookService;
 public BooksApi(BookService bookService) {
	 this.bookService = bookService;
 }
 @RequestMapping("/api/books")
 public List<book> index() {
     return bookService.allBooks();
 }
 
 @RequestMapping(value="/api/books", method=RequestMethod.POST)
 public book create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
     book book = new book(title, desc, lang, numOfPages);
     return bookService.createBook(book);
 }
 
 @RequestMapping("/api/books/{id}")
 public book show(@PathVariable("id") Long id) {
     book book = bookService.findBook(id);
     return book;
 }

 @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
 public book update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
	 book book = bookService.updatebook(id, title, desc, lang, numOfPages);
	 return book;
 }
 @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
 public void destroy(@PathVariable("id") Long id) {
	 bookService.deletebook(id);
 }
}

