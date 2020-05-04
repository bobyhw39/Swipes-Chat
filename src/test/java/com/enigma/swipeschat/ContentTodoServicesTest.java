package com.enigma.swipeschat;

import com.enigma.swipeschat.entity.ContentTodo;
import com.enigma.swipeschat.entity.Todo;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.repository.ContentTodoRepository;
import com.enigma.swipeschat.repository.TodoRepository;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.services.ContentTodoService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContentTodoServicesTest {
    @TestConfiguration
    static class PulsaServiceContextConfiguration{
        @Bean
        public ContentTodoService pulsaService() {
            return new ContentTodoService();
        }
    }

    @Autowired
    private ContentTodoService serv;


    @MockBean
    ContentTodoRepository repo;

    @MockBean
    UserRepository repoUser;

    @MockBean
    TodoRepository repoTodo;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        ContentTodo ct = new ContentTodo((long)1, "content", 1);
        User user = new User(1L, "a@mail.com", "a", "1234", "aang",null);

        List<ContentTodo> listContent = new ArrayList<ContentTodo>();
        listContent.add(ct);

        Todo td = new Todo((long) 1, "title", listContent, user);

        Mockito.when(repo.getById(td.getId())).thenReturn(ct);
        Mockito.when(serv.getById(ct.getId())).thenReturn(ct);

    }

    @Test
    public void whenGetAll_thenReturnAllEvents() {
        List<ContentTodo> ct = repo.findAll();
        assertEquals(ct, serv.getAll());
    }

//    @Test
//    public void whenGetById_thenReturnEvent() {
//        ContentTodo ct = serv.getById((long) 1);
//        assertEquals(ct.getId(), 1);
//    }

    @Test
    public void whenCreateAll_thenReturnEvents() {
        List<ContentTodo> content = serv.getAll();
        serv.create(content);
    }

    @Test
    public void whenCreate_thenReturnEvents() {
        ContentTodo ct = repo.getById((long) 1);
        serv.create(ct);
    }

    @Test
    public void whenUpdate_thenReturnUpdate() throws BadRequestException {
        ContentTodo evt = repo.getById((long) 1);
        serv.update(evt);
    }

    @Test
    public void whenDelete_thenEventDelete() {
        ContentTodo evt = repo.getById((long) 1);
        serv.delete(evt.getId());
    }

//    @Test
//    public void whenContentTodoNotExist_thenReturnNotExist() {
//        ContentTodo ct = repo.getById((long) 1);
//        expectedException.expect(BadRequestException.class);
//        assertEquals(1, ct.getId());
//    }

    @Test
    public void setContentAndCentang() {
        ContentTodo ct = repo.getById((long) 1);
        ContentTodo cc = new ContentTodo("bluk", 0);
        serv.setContentAndCentang(ct, cc);
    }

//	@Test
//	public void whenContentTodoExist_thenReturnExist() {
//		ContentTodo ct = repo.getById((long) 1);
//		expectedException.expect(BadRequestException.class);
//		assertEquals(1, serv.contentTodoExist(ct.getId()));
//	}

}
