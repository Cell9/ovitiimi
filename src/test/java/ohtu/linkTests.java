/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Roni
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(
  locations = "classpath:application-test.properties")
@Transactional
public class linkTests {
    
    @Autowired
    private LinkRepository linkrepo;
    
    @Before
    public void setUp() {
    }

    @Test
    public void linksCanBeCreatedEmptyConstructor() {
        Link link = new Link();
        assertNotNull(link);
    }

    @Test
    public void linksCanBeCreatedNonEmptyConstructor() {
        Link link = new Link("title", "url", new ArrayList<>(), new ArrayList<>());
        assertNotNull(link);
        assertEquals(link.getTitle(), "title");
    }

    @Test
    public void linksValuesCanBeSetAndGotten() {
        Link link = new Link();
        link.setUrl("url");
        link.setTitle("title");
        ArrayList<String> courses = new ArrayList<>();
        courses.add("tkt101");
        link.setCourses(courses);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        link.setTags(tags);
        assertEquals(link.getUrl(), "url");
        assertTrue(link.getCourses().contains("tkt101"));
        assertTrue(link.getTags().contains("educational"));
        assertEquals(link.getTitle(), "title");
    }
    
    @Test
    public void linksCanBeSavedAndRetrievedFromRepo(){
        Link link = new Link();
        link.setUrl("url");
        link.setTitle("title");
        ArrayList<String> courses = new ArrayList<>();
        courses.add("tkt101");
        link.setCourses(courses);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("educational");
        link.setTags(tags);
        Link savedlink = linkrepo.save(link);
        Long id = savedlink.getId();
        savedlink = linkrepo.getOne(id);
        assertEquals(link.getUrl(), savedlink.getUrl());
        assertEquals(link.getTitle(), savedlink.getTitle());
        assertEquals(link.getCourses(), savedlink.getCourses());
        assertEquals(link.getTags(), savedlink.getTags());
    }
}
