package br.com.matheusferro.todoAPP.model.rest;


import br.com.matheusferro.todoAPP.model.entity.Todo;
import br.com.matheusferro.todoAPP.model.repository.TodoRepository;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // Vai enviar e receber HTTP
@RequestMapping("/api/todos") // Mapear a url

@CrossOrigin("http://localhost:4200")
public class TodoController {

    @Autowired
    private final TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) { this.repository = repository; }

    @PostMapping
    public Todo addTodo( @RequestBody Todo newTodo ) {
        return repository.save(newTodo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Todo getTodoById( @PathVariable("id") Integer id ) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("{id}")
    public Todo editTodoById( @PathVariable("id") Integer id, @RequestBody Todo todoUpdated ) {
        return repository.findById(id)
                .map( todo -> {
                    todo.setId(todoUpdated.getId());
                    return repository.save(todoUpdated);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public Todo deleteTodoById( @PathVariable("id") Integer id ) {
        return repository.findById(id)
                .map( todo -> {
                    repository.delete(todo);
                    return todo;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
