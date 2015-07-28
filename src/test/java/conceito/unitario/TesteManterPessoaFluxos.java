package conceito.unitario;

import conceito.entidade.Pessoa;
import conceito.excecao.InfraExcecao;
import conceito.excecao.NenhumRegistroEncontradoExcecao;
import conceito.excecao.RegistroNaoEncontradoExcecao;
import conceito.negocio.ManterPessoaBO;
import conceito.persistencia.PessoaDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class TesteManterPessoaFluxos {

    private Pessoa pessoa = null;

    @Mock
    private PessoaDAO daoMock = null;

    @InjectMocks
    private ManterPessoaBO negocio = null;

    @Before
    public void construtor() {
        MockitoAnnotations.initMocks(this);
        pessoa  = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Marcos Fernando Costa");
        pessoa.setDevedor(false);
    }

    @Test
    public void testeListarTodos(){
        try {
            when(daoMock.listarTodos()).thenReturn(new ArrayList<Pessoa>());
            pessoa.setNome(null);
            negocio.pesquisar(pessoa);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testeIncluir(){
        try {
            when(daoMock.incluir(pessoa)).thenReturn(pessoa);
            pessoa.setId(null);
            negocio.salvar(pessoa);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testeAlterar(){
        try {
            when(daoMock.alterar(pessoa)).thenReturn(pessoa);
            negocio.salvar(pessoa);
        }catch(Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = RegistroNaoEncontradoExcecao.class)
    public void testaObter() throws RegistroNaoEncontradoExcecao {
        try {
            when(daoMock.obterPorId(18L)).thenReturn(null);
            negocio.obterPorId(18L);
        }catch(InfraExcecao e){
            fail();
        }
    }

    @Test(expected = NenhumRegistroEncontradoExcecao.class )
    public void testePesquisa() throws NenhumRegistroEncontradoExcecao{
        try {
            when(daoMock.listarPorNome("AAA")).thenReturn(new ArrayList<Pessoa>());
            pessoa.setNome("AAA");
            negocio.pesquisar(pessoa);
        }catch(InfraExcecao e){
            fail(e.getMessage());
        }
    }

}