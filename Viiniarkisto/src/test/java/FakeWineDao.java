
import Wine.domain.Wine;
import Wine.dao.WineDao;
import java.util.ArrayList;
import java.util.List;

public class FakeWineDao implements WineDao {

    List<Wine> wines;

    public FakeWineDao() {
        this.wines = new ArrayList<>();
    }

    @Override
    public Wine create(Wine wine) throws Exception {

        wine.setId(wines.size() + 1);
        wines.add(wine);
        return wine;

    }

    @Override
    public List<Wine> getAll() {
        return this.wines;
    }

}
