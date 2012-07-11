package com.thoughtworks.go.sample.controllers;

import com.thoughtworks.go.sample.models.Book;
import com.thoughtworks.go.sample.persistance.BooksRepository;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BooksControllerTest {

    @Test
    public void shouldGetBooks() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        BooksRepository repository = mock(BooksRepository.class);
        List<Book> expected = Arrays.asList(new Book("isbn", "name", "author", "publisher"));
        when(repository.allBooks()).thenReturn(expected);

        ModelAndView modelAndView = new BooksController(repository).allBooks(request);
        assertThat((List<Book>) modelAndView.getModel().get("books"), is(expected));
    }

    @Test
    public void shouldShowMessageIfInSession() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("flash")).thenReturn("Message");

        BooksRepository repository = mock(BooksRepository.class);
        when(repository.allBooks()).thenReturn(new ArrayList<Book>());

        ModelAndView modelAndView = new BooksController(repository).allBooks(request);
        assertThat((String) modelAndView.getModel().get("message"), is("Message"));
    }
}
