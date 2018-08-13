package com.web2team.graphql.repository.Grid;

import com.web2team.graphql.model.Grid.Grid;
import org.springframework.data.repository.CrudRepository;

public interface GridRepository extends CrudRepository<Grid, Long> {
    public Iterable<Grid> getAllByUserIdEquals(Long userId);
}
