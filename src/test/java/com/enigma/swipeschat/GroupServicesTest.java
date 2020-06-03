package com.enigma.swipeschat;

import com.enigma.swipeschat.dto.GroupPostUserDTO;
import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.enigma.swipeschat.repository.GroupRepository;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.services.GroupServices;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServicesTest {

    @TestConfiguration
    static class GroupServiceContextConfiguration{

        @Bean
        public GroupServices grpServices(){
            return new GroupServices();
        }

    }
    @Autowired
    GroupServices groupServices;

    @MockBean
    GroupRepository groupRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    UserServices userServices;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        User user = new User(Long.valueOf(69l),"test@email.com","test","testpw","test halo",null);
        User user2 = userRepo.findByUsername("dieng");
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user2);
        Group group = new Group(Long.valueOf(21),"swipes","grup ini cuman grup final project",users,null);
        List<Group> groups = Lists.newArrayList();
        groups.add(group);
        Mockito.when(groupRepo.save(group)).thenReturn(group);
        Mockito.when(groupRepo.findByName("swipes")).thenReturn(group);
        Mockito.when(groupRepo.findByName("swipes")).thenReturn(group);
    }

    @Test
    public void whenGroupPostUser_thenShouldReturnSuccess() throws Throwable {
        GroupPostUserDTO groupDto = new GroupPostUserDTO(Long.valueOf(77),"swipes");
        assertEquals(groupServices.addUser(groupDto).getName(),"swipes");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGroupPostUser_thenShouldReturnNotFoundException() throws NotFoundException {
        GroupPostUserDTO groupDto = new GroupPostUserDTO(Long.valueOf(77),"test");
        expectedException.expect(NotFoundException.class);
        groupServices.addUser(groupDto);
    }


}
