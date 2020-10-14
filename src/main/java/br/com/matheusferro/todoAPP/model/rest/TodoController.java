package br.com.matheusferro.todoAPP.model.rest;


import br.com.matheusferro.todoAPP.model.entity.Todo;
import br.com.matheusferro.todoAPP.model.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController // Vai enviar e receber HTTP
@RequestMapping("/api/todos") // Mapear a url
public class TodoController {

    @Autowired
    private final TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) { this.repository = repository; }

    @PostMapping
    public Todo addTodo( @RequestBody Todo newTodo ) {
        return repository.save(newTodo);
    }

    @GetMapping("{id}")
    public Todo getTodoById( @PathVariable("id") Integer id ) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi encontrado nenhum todo com esse ID"));
    }

}
