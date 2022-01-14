package com.todo.api.repository;

import com.todo.api.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    @Query("select * from Todo t where t.userId = :userId")
    List<TodoEntity> findByUserId(@Param("userId") String userId);
}
