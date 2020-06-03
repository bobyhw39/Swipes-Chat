package com.enigma.swipeschat;

import com.enigma.swipeschat.dto.TodoDto;
import com.enigma.swipeschat.entity.ContentTodo;
import com.enigma.swipeschat.entity.Todo;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.repository.ContentTodoRepository;
import com.enigma.swipeschat.repository.TodoRepository;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.services.ContentTodoService;
import com.enigma.swipeschat.services.TodoService;
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

public class TodoServiceTest {

    @TestConfiguration
    static class PulsaServiceContextConfiguration{
        @Bean
        public TodoService pulsaService() {
            return new TodoService();
        }
    }

    @Autowired
    private TodoService serv;

    @MockBean
    private ContentTodoService servContent;

    @MockBean
    TodoRepository repo;

    @MockBean
    UserRepository repoUser;

    @MockBean
    ContentTodoRepository repoContent;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        ContentTodo ct = new ContentTodo((long)1, "content", 1);
        User user = new User((long)1, "a@mail.com", "a", "1234", "aang",null);

        List<ContentTodo> listContent = new ArrayList<ContentTodo>();
        listContent.add(ct);

        TodoDto tdDto = new TodoDto((long) 1, "title", listContent, user);
        Todo td = new Todo( 1l, "title", listContent, user);

        Mockito.when(repo.getById(1l)).thenReturn(td);
        Mockito.when(servContent.getById(ct.getId())).thenReturn(ct);

    }

    @Test
    public void whenGetAll_thenReturnAllTodo() {
        List<Todo> event = repo.findAll();
        assertEquals(event, serv.getAllList());
    }

    @Test
    public void whenGetById_thenReturnTodo() {
        Todo evt = serv.getById((long) 1);
        assertEquals(evt.getId(), evt);
    }

    @Test
    public void whenCreate_thenReturnTodo() {
        List<ContentTodo> content = servContent.getAll();
        User user = new User((long)2, "a@mail.com", "a", "1234", "aang",null);
        TodoDto evt = new TodoDto((long) 1, "title", content, user);
        serv.create(evt);
    }

    @Test
    public void whenUpdate_thenReturnUpdate() throws BadRequestException {
        Todo evt = repo.getById((long) 1);
        serv.update(evt);
    }

    @Test
    public void whenDelete_thenEventDelete() {
        Todo evt = repo.getById((long) 1);
        serv.delete(evt.getId());
    }

    @Test
    public void setContentTodo() {
        List<ContentTodo> content = servContent.getAll();
        Todo evt = serv.getById((long) 1);
        serv.setContentTodo(content, evt);
    }

    @Test
    public void setContent() {
        serv.setContent(servContent.getById((long) 1), repo.getById((long) 1));
    }

}
