package com.enigma.swipeschat;

import com.enigma.swipeschat.dto.UserPostDTO;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.services.UserServices;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServicesTest {

    @TestConfiguration
    static class UserServiceContextConfiguration{

        @Bean
        public UserServices usrServices(){
            return new UserServices();
        }

    }

    @Autowired
    private UserServices userServices;

    @MockBean
    private UserRepository userRepository;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        //////////Fake Data //////////////////////////////////////////////////////////////////////////////////////////////////////

        User user = new User(Long.valueOf(69l),"test@email.com","test","testpw","test halo",null);

        List<User> listest = Lists.newArrayList();
        listest.add(user);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Mockito.when(userRepository.findByUsername("test")).thenReturn(user);
    }

    @Test
    public void whenUserPostUser_thenShouldReturnSuccess(){
        UserPostDTO userPost = new UserPostDTO("test@test.com","test123","1234","Test From Junit");
//        User userPostDTO = new User(null,"test@test.com","test123","1234","Test From Junit",null);
        assertEquals(userServices.createUser(userPost).getUsername(),"test123");
    }

    @Test
    public void whenUserPostUser_thenShouldReturnBadRequestException(){
        UserPostDTO userPost = new UserPostDTO("test@test.com","test","1234","Test From Junit");
//        User userPostDTO = new User(null,"test@test.com","test123","1234","Test From Junit",null);
        expectedException.expect(BadRequestException.class);
        userServices.createUser(userPost);
    }



}