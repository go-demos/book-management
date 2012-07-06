package com.thoughtworks.go.sample.controllers.api;

import com.thoughtworks.go.sample.models.Book;
import com.thoughtworks.go.sample.persistance.BooksRepository;
import com.thoughtworks.go.sample.views.XmlView;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.XmlViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiBooksController {

    private BooksRepository booksRepository;

    @Autowired
    public ApiBooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public ModelAndView allBooks(HttpServletRequest request) {
        Map model = new HashMap();
        model.put("books", booksRepository.allBooks());
        XStream xStream = new XStream();
        xStream.alias("book", Book.class);
        return new ModelAndView(new XmlView(xStream), model);
    }
}
