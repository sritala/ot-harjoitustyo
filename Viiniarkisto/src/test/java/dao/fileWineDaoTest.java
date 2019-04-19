package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Wine.dao.FileWineDao;
import Wine.domain.Wine;
import Wine.dao.WineDao;
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

public class FileWineDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File wineFile;
    WineDao dao;

    @Before
    public void setUp() throws Exception {
        wineFile = testFolder.newFile("testfile_wines.txt");

        dao = new FileWineDao(wineFile.getAbsolutePath());
    }

    @Test
    public void createdWinesAreListedCorrectly() throws Exception {
        dao.create(new Wine(2014, "Saksa", "Schloss"));

        List<Wine> wines = dao.getAll();
        assertEquals(1, wines.size());
        Wine wine = wines.get(0);

        assertEquals(2014, wine.getYear());
        assertEquals("Saksa", wine.getCountry());
        assertEquals("Schloss", wine.getName());
    }

    @Test
    public void correctListingAfterDeletedWine() throws Exception {
        dao.create(new Wine(2014, "Saksa", "Schloss"));
        List<Wine> wines = dao.getAll();
        assertEquals(1, wines.size());
        
        dao.delete("Schloss");
        wines = dao.getAll();
        assertEquals(0, wines.size());
    }

    @Test
    public void winesEmptyByDefault() {
        List<Wine> wines = dao.getAll();
        assertEquals(0, wines.size());

    }

    @After
    public void tearDown() {
        wineFile.delete();
    }

}
