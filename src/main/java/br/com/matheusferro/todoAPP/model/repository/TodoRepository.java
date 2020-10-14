package br.com.matheusferro.todoAPP.model.repository;

import br.com.matheusferro.todoAPP.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
