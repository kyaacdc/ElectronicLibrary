package com.el.spring.controller;

import com.el.spring.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class}, locations = {"file:src/main/webapp/WEB-INF/appconfig-root.xml"})
@WebAppConfiguration
public class TagControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TagService tagServiceMock;

    //Add WebApplicationContext field here

    //The setUp() method is omitted.

    @Test
    public void findById_TodoEntryNotFound_ShouldRender404View() throws Exception {
        when(tagServiceMock.getTagById(2)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/todo/{id}", 2))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/error/404.jsp"));

        verify(tagServiceMock, times(2)).getTagById(2);
        verifyZeroInteractions(tagServiceMock);
    }
}
