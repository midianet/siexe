package conceito.negocio;


import conceito.entidade.Pessoa;
import conceito.excecao.*;
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

    public Pessoa obterPorId(final Long id) throws RegistroNaoEncontradoExcecao, InfraExcecao {
        final Pessoa p = dao.obterPorId(id);
        if(p == null){
            throw new RegistroNaoEncontradoExcecao(id);
        }else{
            return p;
        }
    }

    public List<Pessoa> pesquisar(final Pessoa modelo) throws NenhumRegistroEncontradoExcecao, InfraExcecao {
        List<Pessoa> retorno = null;
        if (modelo.getNome() != null && !modelo.getNome().isEmpty()) {
            retorno = dao.listarPorNome(modelo.getNome());
            if(retorno.isEmpty()){
                throw new NenhumRegistroEncontradoExcecao();
            }
        } else {
            retorno = dao.listarTodos();
        }
        return retorno;
    }

    @Transactional
    public void salvar(final Pessoa pessoa) throws ValorNaoInformadoExcecao, InfraExcecao{
        if(pessoa.getNome() == null || pessoa.getNome().isEmpty()){
            throw new ValorNaoInformadoExcecao("Nome");
        }
        if(pessoa.getId() == null){
            dao.incluir(pessoa);
        } else {
            dao.alterar(pessoa);
        }
    }

    @Transactional
    public void remover(final Pessoa pessoa) throws OperacaoNaoPermitidaExcecao,InfraExcecao{
        if(pessoa.isDevedor()){
            throw new OperacaoNaoPermitidaExcecao();
        }
        dao.excluir(pessoa);
    }

}