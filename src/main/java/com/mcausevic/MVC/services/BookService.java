package com.mcausevic.MVC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mcausevic.MVC.models.book;
import com.mcausevic.MVC.repositories.BookRepositories;
import com.mcausevic.MVC.services.BookService;

@Service
public class BookService {
	private final BookRepositories bookRepository;
	
	public BookService(BookRepositories bookRepository) {
		this.bookRepository = bookRepository;
	}
	public List<book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public book createBook(book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public book findBook(Long id) {
        Optional<book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
	public book updatebook(Long id, String title, String desc, String lang, Integer numOfPages) {
		book ubook = findBook(id);
		ubook.setTitle(title);
		ubook.setDescription(desc);
		ubook.setLanguage(lang);
		ubook.setNumberOfPages(numOfPages);
		bookRepository.save(ubook);
		return ubook;
	}
	public void deletebook(Long id) {
		book ubook = findBook(id);
		bookRepository.delete(ubook);
		
	}

}
