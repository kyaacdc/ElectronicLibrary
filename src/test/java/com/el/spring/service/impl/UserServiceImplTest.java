package com.el.spring.service.impl;

import com.el.spring.entity.User;
import com.el.spring.service.UserService;
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
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.save(user);
        user = userService.findByUsername("testuser11111");

        assertThat(user.getUsername(), is(equalTo("testuser11111")));

        userService.removeUser(user.getId());
    }

    @Test
    public void testEditOrAddUser() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.addUser(user);
        int id = userService.findByUsername("testuser11111").getId();
        user = new User("testuser66666", "testpass1", "testpass1", null);
        user.setId(id);
        userService.updateUser(user);
        user = userService.findByUsername("testuser66666");

        assertTrue(user.getId() == id);

        userService.removeUser(id);
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.addUser(user);
        int id = userService.findByUsername("testuser11111").getId();
        userService.removeUser(user.getId());

        assertThat(userService.getUserById(id), is(nullValue()));
    }

    @Test
    public void testGetAllUser() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.addUser(user);

        assertThat(userService.listUsers(), is(not(nullValue())));

        int id = userService.findByUsername("testuser11111").getId();
        userService.removeUser(id);
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User("testuser11111", "testpass1", "testpass1", null);
        userService.addUser(user);
        int id = userService.findByUsername("testuser11111").getId();

        assertTrue(userService.getUserById(id).getUsername().equals("testuser11111"));

        userService.removeUser(id);
    }
}