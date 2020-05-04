package com.enigma.swipeschat;

import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.dto.UserPostDTO;
import com.enigma.swipeschat.entity.ChatChannel;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.repository.ChatChannelRepository;
import com.enigma.swipeschat.services.ChatChannelServices;
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
public class ChatChannelServicesTest {
    @TestConfiguration
    static class ChatChannelServicesContextConfiguration{

        @Bean
        public ChatChannelServices chtServices(){
            return new ChatChannelServices();
        }
    }

    @Autowired
    ChatChannelServices chatChannelServices;

    @MockBean
    ChatChannelRepository chatChannelRepository;

    @MockBean
    UserServices userServices;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        User user1 = new User(Long.valueOf(69l),"test@email.com","test","testpw","test halo",null);
        User user2 = new User(Long.valueOf(70l),"test1@email.com","test1","testpw1","test halo",null);
        ChatChannel chatChannel = new ChatChannel(1l,user1,user2);
        List<ChatChannel> friendList = Lists.newArrayList();
        friendList.add(chatChannel);

        Mockito.when(userServices.getUser(user1.getUsername())).thenReturn(new UserGetDTO(user1.getId(),user1.getEmail(),user1.getUsername(), user1.getFullName()));
        Mockito.when(userServices.getUser(user2.getUsername())).thenReturn(new UserGetDTO(user2.getId(),user2.getEmail(),user2.getUsername(), user2.getFullName()));
        Mockito.when(chatChannelRepository.findAllByUserOne(user1)).thenReturn(friendList);
    }

    @Test
    public void whenUserGetListFriends_thenShouldListFriends(){
        User user1 = new User(Long.valueOf(69l),"test@email.com","test","testpw","test halo",null);
        User user2 = new User(Long.valueOf(70l),"test1@email.com","test1","testpw1","test halo",null);
        ChatChannel chatChannel = new ChatChannel(1l,user1,user2);
        List<ChatChannel> friendList = Lists.newArrayList();
        friendList.add(chatChannel);
        assertEquals(chatChannelServices.getListFriends("test").get(1).getUserTwo().getUsername(), "test1");
    }
}
