package services;

import org.example.dao.impl.BooksDAO;
import org.example.dto.impl.BookDTO;
import org.example.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BooksDAO dao;

    @InjectMocks
    private BookService service;

    private BookDTO dto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BookService("mockPath");
        service.dao = dao;

        dto = new BookDTO("Test Book", "Test Description");
        when(dao.read(1L)).thenReturn(dto);

    }

    @Test
    void testGetById() {
        String id = "1";


        String result = service.getById(id);
        verify(dao).read(Long.parseLong(id));
        assertEquals("Book Title: Test Book\nBook Description: Test Description\n", result);
    }

    @Test
    void testUpdateById() {
        String id = "1";

        String result = service.updateById(id, dto);

        assertEquals("Book updated successfully", result);
        verify(dao).update(dto, Long.parseLong(id));
    }



    @Test
    void testDeleteById() {
        String id = "1";

        when(dao.read(1L)).thenReturn(null);

        String result = service.deleteById(id);
        verify(dao).delete(Long.parseLong(id));
        assertEquals("Book deleted successfully", result);
    }
}
