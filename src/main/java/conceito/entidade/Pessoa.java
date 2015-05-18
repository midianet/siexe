package conceito.entidade;

import javax.persistence.*;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends Entidade<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pess_id")
    private Long id;

    @Column(name = "pess_nome")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

}
