package conceito.entidade;

import javax.persistence.*;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends Entidade<Long> implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pess_id")
    private Long id;

    @Column(name = "pess_nome")
    private String nome;

    @Column(name = "pess_devedor")
    private boolean devedor;

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

    public boolean isDevedor() {
        return devedor;
    }

    public void setDevedor(final boolean flag) {
        this.devedor = flag;
    }

    public Pessoa clone() {
        final Pessoa p = new Pessoa();
        p.setId(this.getId());
        p.setNome(this.getNome());
        p.setDevedor(this.isDevedor());
        return p;
    }

}