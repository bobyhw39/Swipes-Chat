package com.enigma.swipeschat;

import com.enigma.swipeschat.dto.EventDto;
import com.enigma.swipeschat.entity.Events;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.repository.EventRepository;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.services.EventService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventServicesTest {
    @TestConfiguration
    static class PulsaServiceContextConfiguration{
        @Bean
        public EventService pulsaService() {
            return new EventService();
        }
    }

    @Autowired
    private EventService serv;

    @MockBean
    EventRepository repo;

    @MockBean
    UserRepository repoUser;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
//        User user = new User(Long.valueOf(69l),"test@email.com","test","testpw","test halo",null);
        User user = new User(Long.valueOf(1l), "a@mail.com", "a", "1234", "aang",null);
        Events evt = new Events((long) 1, "title", new Timestamp(1587866400), new Timestamp(1587870000), "location", "description", user);
        EventDto evtDto = new EventDto((long) 1, "title", new Timestamp(1587866400), new Timestamp(1587870000), "location", "description", user);

        List<Events> listEvent = new ArrayList<Events>();
        listEvent.add(evt);

        Mockito.when(repo.getById(evt.getId())).thenReturn(evt);

    }

    @Test
    public void whenGetAll_thenReturnAllEvents() {
        List<Events> event = repo.findAll();
        assertEquals(event, serv.getAllList());
    }

//    @Test
//    public void whenGetById_thenReturnEvent() {
//        Events evt = serv.getById((long) 1);
//        assertEquals(evt.getId(), 1l);
//    }

    @Test
    public void whenCreate_thenReturnEvents() {
        User user = new User(2l, "a@mail.com", "a", "1234", "aang",null);
        EventDto evt = new EventDto((long) 1, "title", new Timestamp(1587866400), new Timestamp(1587870000), "location", "description", user);
        serv.create(evt);
    }

    @Test
    public void whenUpdate_thenReturnUpdate() {
        Events evt = repo.getById((long) 1);
        serv.update(evt);
    }

    @Test
    public void whenDelete_thenEventDelete() {
        Events evt = repo.getById((long) 1);
        serv.delete(evt.getId());
    }


}
