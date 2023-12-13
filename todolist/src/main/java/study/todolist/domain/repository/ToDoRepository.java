package study.todolist.domain.repository;

import lombok.Getter;
import study.todolist.domain.ToDo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ToDoRepository extends Repository {
    private final ConcurrentHashMap<Long, ToDo> todoDB = new ConcurrentHashMap<>();

    public ToDo save(Long id, ToDo todo) {
        return (ToDo) super.save(id, todo, todoDB);
    }

    public Optional<ToDo> findById(Long id) {
        return Optional.ofNullable((ToDo) super.findById(id, todoDB).orElse(null));
    }

    public void deleteById(Long id) {
        super.deleteById(id, todoDB);
    }

    public List<ToDo> findAll() {
        return super.findAll(todoDB).stream()
                                    .filter(Objects::nonNull)
                                    .map(o -> (ToDo)o)
                                    .toList();
    }
}
