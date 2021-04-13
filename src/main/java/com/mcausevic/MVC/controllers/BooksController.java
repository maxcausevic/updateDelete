package com.mcausevic.MVC.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcausevic.MVC.models.book;
import com.mcausevic.MVC.services.BookService;

@Controller
public class BooksController {
 private final BookService bookService;
 
 public BooksController(BookService bookService) {
     this.bookService = bookService;
 }
 
 @RequestMapping("/books")
 public String index(Model model) {
     List<book> books = bookService.allBooks();
     model.addAttribute("books", books);
     return "/books/index.jsp";
 }
 @RequestMapping("/books/new")
 public String newBook(@ModelAttribute("book") book book) {
     return "/books/new.jsp";
 }
 @RequestMapping(value="/books", method=RequestMethod.POST)
 public String create(@Valid @ModelAttribute("book") book book, BindingResult result) {
     if (result.hasErrors()) {
         return "/books/new.jsp";
     } else {
         bookService.createBook(book);
         return "redirect:/books";
     }
 }
 @RequestMapping("/books/{id}")
 public String show(Model model, @PathVariable("id") Long id) {
	 book sbook = bookService.findBook(id);
	 model.addAttribute("book", sbook);
	 return "books/show.jsp";
 }
}

