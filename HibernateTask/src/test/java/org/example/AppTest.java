package org.example;

import org.example.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private App app;

    @Test
    public void testApp() {
        assertNotNull(app);
    }

    @Test
    public void testMain() {
        String[] args = new String[0];
        app.main(args);
    }
}