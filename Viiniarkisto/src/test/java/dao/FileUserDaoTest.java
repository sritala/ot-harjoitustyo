/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import wine.dao.FileUserDao;
import wine.dao.UserDao;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import wine.domain.User;

/**
 *
 * @author susanna
 */
public class FileUserDaoTest {
    
        @Rule
        public TemporaryFolder testFolder = new TemporaryFolder();

        File userFile;
        UserDao dao;

        @Before
        public void setUp() throws Exception {
            userFile = testFolder.newFile("testfile_users.txt");

            try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
                file.write("testi;\n");
            }

            dao = new FileUserDao(userFile.getAbsolutePath());
        }

        @Test
        public void usersAreReadCorrectlyFromFile() {
            List<User> users = dao.getAll();
            assertEquals(1, users.size());
            User user = users.get(0);
            assertEquals("testi", user.getUsername());
        }

        @Test
        public void existingUserIsFound() {
            User user = dao.findByUsername("testi");
            assertEquals("testi", user.getUsername());
        }

        @Test
        public void nonExistingUserIsFound() {
            User user = dao.findByUsername("matti");
            assertEquals(null, user);
        }

        @Test
        public void savedUserIsFound() throws Exception {
            User nawUser = new User("matti");
            dao.create(nawUser);

            User user = dao.findByUsername("matti");
            assertEquals("matti", user.getUsername());
        }

        @After
        public void tearDown() {
            userFile.delete();
        }
    
}
