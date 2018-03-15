package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu,Long> {
    Menu getGuestById(long id);
}
