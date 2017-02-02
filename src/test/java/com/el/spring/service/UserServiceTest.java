package com.el.spring.service;

import com.el.spring.entity.Role;
import com.el.spring.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/appconfig-root.xml")
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.addUser(user);
        user = userService.findByUsername("testuser11111");
        assertThat(user.getUsername(), is(equalTo("testuser11111")));
        userService.removeUser(user.getId());
    }

    @Test
    public void testEditUser() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.addUser(user);
        user = userService.findByUsername("testuser11111");
        int id = user.getId();
        User user2 = new User("testuser22222", "testpass2", "testpass2", null);
        user2.setId(id);
        userService.updateUser(user2);
        user = userService.findByUsername("testuser22222");

        assertThat(user.getId(), is(equalTo(id)));

        userService.removeUser(id);
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User("testuser1", "testpass1", "testpass1", null);
        user.setId(555);
        userService.addUser(user);

        userService.removeUser(555);

        //assertFalse();
    }

    @Test
    public void testGetAllUser() throws Exception {
        userService.addUser(new User("testuser1", "testpass1", "testpass1", null));
        userService.addUser(new User("testuser2", "testpass1", "testpass1", null));
        userService.addUser(new User("testuser3", "testpass1", "testpass1", null));

        userService.listUsers();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User("testuser2", "testpass1", "testpass1", null);
        user.setId(555);
        userService.getUserById(555);

        assertTrue(userService.getUserById(555).equals(user));


        //assertEquals(user.getUsername(), "testuser1");
    }
}

