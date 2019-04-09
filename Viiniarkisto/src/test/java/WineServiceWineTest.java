
import Wine.domain.Wine;
import Wine.domain.WineService;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WineServiceWineTest {

    FakeWineDao wineDao;
    WineService service;

    @Before
    public void setUp() throws Exception {
        wineDao = new FakeWineDao();

        Wine w1 = new Wine(2013, "Italia", "Toscana");
        Wine w2 = new Wine(2016, "Espanja", "Rioja");
        wineDao.create(w1);
        wineDao.create(w2);
        service = new WineService(wineDao);
    }

    @Test
    public void atStartListContainsTwoWinesAndReturnsCorrectSecondWine() {
        List<Wine> wines = service.getWines();
        assertEquals(2, wines.size());
        Wine wine2 = wines.get(1);
        assertEquals("Rioja", wine2.getName());

    }

}
