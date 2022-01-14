package com.todo.api.service;

import com.todo.api.model.TodoEntity;
import com.todo.api.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        todoRepository.save(entity);

        TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);
        todoRepository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getId());
        return todoRepository.findByUserId(entity.getUserId());
    }

    @Transactional(readOnly = true)
    public List<TodoEntity> retrieve(final String userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> delete(final TodoEntity todoEntity) {
        validate(todoEntity);

        try {
            todoRepository.delete(todoEntity);
        } catch (Exception e) {
            log.error("error deleting entity", todoEntity.getId(), e);
            throw new RuntimeException("error deleting entity " + todoEntity.getId());
        }
        return retrieve(todoEntity.getUserId());
    }

    public List<TodoEntity> update(final TodoEntity todoEntity) {
        validate(todoEntity);

        final Optional<TodoEntity> original = todoRepository.findById(todoEntity.getId());
        original.ifPresent(todo -> {
            todo.setTitle(todoEntity.getTitle());
            todo.setDone(todo.isDone());
        });

        return retrieve(todoEntity.getUserId());
    }

    private void validate(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
