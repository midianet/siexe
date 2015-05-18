package conceito.negocio;


import conceito.entidade.Pessoa;
import conceito.excecao.InfraExcecao;
import conceito.excecao.RegistroNaoEncontradoExcecao;
import conceito.excecao.ValorNaoInformadoExcecao;
import conceito.excecao.ValorNuloExcecao;
import conceito.persistencia.PessoaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManterPessoaBO {

    @Autowired
    private PessoaDAO dao;

    public Pessoa obterPorId(final Long id) throws ValorNuloExcecao, RegistroNaoEncontradoExcecao, InfraExcecao {
        if(id == null){
            throw new ValorNuloExcecao("Pessoa.id");
        }
        return dao.obterPorId(id);
    }

    public List<Pessoa> pesquisar(final Pessoa modelo) throws InfraExcecao, ValorNuloExcecao {

        if(modelo == null){
            throw new ValorNuloExcecao("Pessoa");
        }

        if(modelo.getId() != null){
           final Pessoa p = dao.obterPorId(modelo.getId()) ;
           final List<Pessoa> l = new ArrayList<>();
            l.add(p);
            return l;
        }else {
            if (modelo.getNome() != null && !modelo.getNome().isEmpty()) {
                return dao.listarPorNome(modelo.getNome());
            } else {
                return dao.listarTodos();
            }
        }

    }

    @Transactional
    public void salvar(final Pessoa pessoa) throws ValorNaoInformadoExcecao, InfraExcecao{
        if(pessoa == null){
            throw new ValorNuloExcecao("Pessoa");
        }
        if(pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()){
            throw new ValorNaoInformadoExcecao("Nome");
        }
        if(pessoa.getId() == null){
            dao.incluir(pessoa);
        } else {
            dao.alterar(pessoa);
        }
    }

    @Transactional
    public void remover(final Pessoa pessoa) throws InfraExcecao{
        if(pessoa == null){
            throw new ValorNuloExcecao("Pessoa");
        }
        if(pessoa.getId() == null){
            throw new ValorNuloExcecao("Pessoa.id");
        }
        dao.excluir(pessoa);
    }

}