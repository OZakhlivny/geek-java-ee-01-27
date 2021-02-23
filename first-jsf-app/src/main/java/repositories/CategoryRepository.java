package repositories;
import entities.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CategoryRepository {
    private final Map<Long, Category> categoryMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Category(null, "Category 1", "Description of category 1"));
        this.saveOrUpdate(new Category(null, "Category 2", "Description of category 2"));
        this.saveOrUpdate(new Category(null, "Category 3", "Description of category 3"));
    }

    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    public Category findById(Long id) {
        return categoryMap.get(id);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) category.setId(identity.incrementAndGet());
        categoryMap.put(category.getId(), category);
    }

    public void deleteById(Long id) {
        categoryMap.remove(id);
    }
}
