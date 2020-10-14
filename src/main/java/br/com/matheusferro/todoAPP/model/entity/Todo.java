package br.com.matheusferro.todoAPP.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor // Todos argumentos para o construtor
@NoArgsConstructor // Construtor sem argumentos
@Data // Métodos Getters and Setters
@Entity(name = "tb_todo")
public class Todo {

    @Id
    @Column(name="id_todo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="ds_todo", nullable = false)
    private String descricaoTodo;

    @Column(name="dt_insert")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInsercao;

    @Column(name="dt_last_update")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    private void setDateNow() { setDataInsercao(LocalDateTime.now()); }
}
