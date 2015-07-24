package conceito.unitario;

import conceito.entidade.Pessoa;
import conceito.excecao.*;
import conceito.negocio.ManterPessoaBO;
import conceito.persistencia.PessoaDAO;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class TesteManterPessoaFluxos {

    private Pessoa pessoa = null;
    private PessoaDAO daoMock = null;
    private ManterPessoaBO negocio = null;

    @Before
    public void construtor() {
        daoMock = createMock(PessoaDAO.class);
        negocio = new ManterPessoaBO(daoMock);
        pessoa  = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Marcos Fernando Costa");
        pessoa.setDevedor(false);
    }

    @Test
    public void testeListarTodos(){
        try {
            expect(daoMock.listarTodos()).andReturn(new ArrayList<Pessoa>()).once();
            replay(daoMock);
            pessoa.setNome(null);
            negocio.pesquisar(pessoa);
            verify(daoMock);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testeIncluir(){
        try {
            expect(daoMock.incluir(pessoa)).andReturn(pessoa).once();
            pessoa.setId(null);
            replay(daoMock);
            negocio.salvar(pessoa);
            verify(daoMock);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testeAlterar(){
        try {
            expect(daoMock.alterar(pessoa)).andReturn(pessoa).once();
            replay(daoMock);
            negocio.salvar(pessoa);
            verify(daoMock);
        }catch(Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistroNaoEncontradoExcecao.class)
    public void testaObter() throws RegistroNaoEncontradoExcecao {
        try {
            expect(daoMock.obterPorId(18L)).andReturn(null).once();
            replay(daoMock);
            negocio.obterPorId(18L);
            verify(daoMock);
        }catch(InfraExcecao e){
            fail();
        }
    }

    @Test(expected = NenhumRegistroEncontradoExcecao.class )
    public void testePesquisa() throws NenhumRegistroEncontradoExcecao{
        try {
            expect(daoMock.listarPorNome("AAA")).andReturn(new ArrayList<Pessoa>()).once();
            replay(daoMock);
            pessoa.setNome("AAA");
            negocio.pesquisar(pessoa);
            verify(daoMock);
        }catch(InfraExcecao e){
            fail(e.getMessage());
        }
    }

}