package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest,Long> {
    Guest getGuestById(long id);
}
