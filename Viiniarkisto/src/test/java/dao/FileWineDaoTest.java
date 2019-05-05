package dao;

import wine.dao.FileWineDao;
import wine.domain.Wine;
import wine.dao.WineDao;
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
        dao.create(new Wine(2014, "Saksa", "Schloss", "Maija", "Valkoviini", "Sushi"));

        List<Wine> wines = dao.getAll();
        assertEquals(1, wines.size());
        Wine wine = wines.get(0);

        assertEquals(2014, wine.getYear());
        assertEquals("Saksa", wine.getCountry());
        assertEquals("Schloss", wine.getName());
        assertEquals("Valkoviini", wine.getGrape());
        assertEquals("Sushi", wine.getFood());

    }

    @Test
    public void correctListingAfterDeletedWine() throws Exception {
        dao.create(new Wine(2014, "Saksa", "Schloss", "Maija", "Valkoviini", "Sushi"));
        List<Wine> wines = dao.getAll();
        assertEquals(1, wines.size());

        dao.delete("Schloss");
        wines = dao.getAll();
        assertEquals(0, wines.size());
    }

    @Test
    public void correctListingAfterDeletedNonExistingWine() throws Exception {
        dao.create(new Wine(2014, "Saksa", "Schloss", "Maija", "Valkoviini", "Sushi"));
        List<Wine> wines = dao.getAll();
        assertEquals(1, wines.size());

        dao.delete("Random");
        wines = dao.getAll();
        assertEquals(1, wines.size());
    }

    @Test
    public void winesEmptyByDefault() {
        List<Wine> wines = dao.getAll();
        assertEquals(0, wines.size());

    }

    @Test
    public void correctListingAfterUpdatedWine() throws Exception {
        dao.create(new Wine(2014, "Saksa", "Schloss", "Maija", "Valkoviini", "Sushi"));
        List<Wine> wines = dao.getAll();
        dao.updateWine(0, 1999, "Pakistan", "öalksdöalksd", "rftgh", "gyhuj");
        Wine updatedFirst = wines.get(0);
        assertEquals(1999, updatedFirst.getYear());
    }

    @After
    public void tearDown() {
        wineFile.delete();
    }

}
