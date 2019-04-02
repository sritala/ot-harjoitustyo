import auth.FileUserDao;
import auth.User;
import auth.UserDao;
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


public class FileUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    UserDao dao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("Matti;Matti Virtanen\n");
        }
        
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
   
    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Matti Virtanen", user.getName());
        assertEquals("Matti", user.getUsername());
    }
    
    @Test
    public void existingUserIsFound() {
        User user = dao.findByUsername("testitesti");
        assertEquals("Matti Virtanen", user.getName());
        assertEquals("Matti", user.getUsername());
    }
    
    @Test
    public void nonExistingUserIsFound() {
        User user = dao.findByUsername("virtanen");
        assertEquals(null, user);
    }
  
    @After
    public void tearDown() {
        userFile.delete();
    }
}
    

