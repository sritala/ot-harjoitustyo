package domain;

import wine.domain.Wine;
import wine.dao.WineDao;
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

    @Override
    public void delete(String name) throws Exception {
        for (int i = 0; i < this.wines.size(); i++) {
            Wine wine = this.wines.get(i);
            if (wine.getName().equalsIgnoreCase(name)) {
                wines.remove(i);
            }
        }
    }

    @Override
    public void updateWine(int id, int year, String country, String name, String grape, String food) throws Exception {
        Wine found = this.wines.get(id);
        found.setYear(year);
        found.setCountry(country);
        found.setName(name);
        this.wines.set(id, found);
    }

}
