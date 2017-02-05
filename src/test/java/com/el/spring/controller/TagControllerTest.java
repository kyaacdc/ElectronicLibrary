package com.el.spring.controller;

import com.el.spring.entity.Tag;
import com.el.spring.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Locale;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/appconfig-root.xml")
public class TagControllerTest {


        @Mock
        private TagService tagService;

        @InjectMocks
        private TagController tagController;

        private MockMvc mockMvc;

        @Before
        public void setup() {

            // Process mock annotations
            MockitoAnnotations.initMocks(this);

            // Setup Spring test in standalone mode
            this.mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();

        }

    @Test
    public void testViewTags() throws Exception {

        when(tagService.addTag(any(Tag.class)))
        //when(tagService.saveFrom(any(Tag.class)))
                .thenThrow(new NoSuchElementException("For Testing"));

        //this.mockMvc.perform(get("tags").locale(Locale.ENGLISH).sessionAttr("tag", this)
         //       .andExpect(status().isOk())
         //       .andExpect(forwardedUrl("tags"))
         //       .andExpect(model().attributeExists("page_error"));
    }
}