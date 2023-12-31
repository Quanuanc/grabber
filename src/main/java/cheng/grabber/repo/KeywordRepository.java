package cheng.grabber.repo;

import cheng.grabber.domain.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Integer> {
}
