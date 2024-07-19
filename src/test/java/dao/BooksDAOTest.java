package dao;

import org.example.dao.impl.BooksDAO;
import org.example.dto.impl.BookDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class BooksDAOTest {

    private static BooksDAO dao;
    public static BookDTO bookDTO;

    @BeforeEach
    public void setUp() {
        dao = new BooksDAO(System.getProperty("user.dir") + "/src/main/webapp");
        dao.createDatabase();
        dao.dropTables();
        dao.createTables();

        bookDTO = new BookDTO("Title", "Description");

        System.out.println("Setting up completed\n");
    }

    @Test
    void testAddBook() {
        dao.create(bookDTO);
        BookDTO result = dao.read(1);
        assertEquals(bookDTO.getBookTitle(), result.getBookTitle());
    }

    @Test
    void testUpdateBook() {
        dao.create(bookDTO);
        bookDTO.setBookTitle("New title");
        bookDTO.setDescription("New description");
        dao.update(bookDTO, 1);

        assertEquals(bookDTO.getBookTitle(), dao.read(1).getBookTitle());
        assertEquals(bookDTO.getDescription(), dao.read(1).getDescription());
    }

    @Test
    void testDeleteBook() {
        dao.create(bookDTO);

        dao.delete(1);

        assertNull(dao.read(1));
    }

    @Test
    void testReadBook() {
        dao.create(bookDTO);
        assertEquals(bookDTO.getBookTitle(), dao.read(1).getBookTitle());
    }

    @Test
    void testReadBook_withBadIndex() {
        dao.create(bookDTO);
        assertNull(dao.read(-1));
    }

    @Test
    void testReadBook_withBadIndexTest2() {
        dao.create(bookDTO);
        assertNull(dao.read(10));
    }

    @AfterAll
    public static void tearDown() {
        dao.dropTables();
        dao.createTables();

    }
}