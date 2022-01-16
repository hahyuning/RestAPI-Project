package com.todo.api.repository;

import com.todo.api.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    @Query("select t from TodoEntity t where t.userId = :userId")
    List<TodoEntity> findByUserId(@Param("userId") String userId);
}
