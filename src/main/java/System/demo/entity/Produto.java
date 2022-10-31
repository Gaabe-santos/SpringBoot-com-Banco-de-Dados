package System.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {

    @Id

    private Long id;
    private String nome;
    private Double precoUnitario;
    private Double precoTotal;
    private Integer quantidade;
}
