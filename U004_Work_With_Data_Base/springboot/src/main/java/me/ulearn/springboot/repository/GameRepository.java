package me.ulearn.springboot.repository;

import me.ulearn.springboot.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//#region Task
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
//#endregion Task
