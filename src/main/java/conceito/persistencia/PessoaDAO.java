package conceito.persistencia;

import conceito.entidade.Pessoa;
import conceito.excecao.InfraExcecao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PessoaDAO extends GenericoDAO<Pessoa> {
    private Logger log = Logger.getLogger(getClass().getName());

    public List<Pessoa> listarPorNome(final String nome) throws InfraExcecao {
        try {
            final Query pesquisa = persistencia.createQuery("Select p from Pessoa p where p.nome like :parametro order by p.nome");
            pesquisa.setParameter("parametro","%"+nome+"%");
            return pesquisa.getResultList();
        } catch (final Exception e) {
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

}