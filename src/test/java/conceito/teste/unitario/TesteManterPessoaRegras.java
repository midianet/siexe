package conceito.teste.unitario;

import conceito.entidade.Pessoa;
import conceito.excecao.*;
import conceito.negocio.ManterPessoaBO;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Quando;
import cucumber.api.java.pt.Entao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TesteManterPessoaRegras {

    private Pessoa pessoa = null;
    private ManterPessoaBO negocio = null;

    @Before
    @Dado("que tenha um cliente")
    public void construtor() {
        negocio = new ManterPessoaBO();
        pessoa  = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Marcos Fernando Costa");
        pessoa.setDevedor(false);
    }

    @Test
    @Quando("e informado o nome vazio")
    @Entao("o sistema lança exceção com a mensagem :O valor do campo Nome não foi informado.")
    public void testeRN1(){
        pessoa.setNome(null);
        try {
            negocio.salvar(pessoa);
        }catch (Exception e){
            assertTrue(ValorNaoInformadoExcecao.class.isInstance(e));
            assertEquals(e.getMessage(),"O valor do campo Nome não foi informado.");
        }
    }

    @Test
    @Quando("e informado um nome menor que 10 caracteres")
    @Entao("o sistema lança exceção com a mensagem :O valor do campo Nome esta inválido, deverá ter no mínimo 10 caracteres.")
    public void testeRN2() {
        pessoa.setNome("X");
        try{
            negocio.salvar(pessoa);
        }catch(Exception e) {
            assertTrue(CampoInvalidoExcecao.class.isInstance(e));
            assertEquals(e.getMessage(), "O valor do campo Nome esta inválido, deverá ter no mínimo 10 caracteres.");
        }
    }

    @Test
    @Quando("e informado um nome maior que 80 caracteres")
    @Entao("o sistema lança exceção com a mensagem :O valor do campo Nome esta inválido, deverá ter no máximo 80 caracteres.")
    public void testeRN3(){
        pessoa.setNome("FJASFJALFAFAFFDAJFLADFJAFJASLFJSALFJSALFJSDALKFJ;AFJALFJALFJLFJDALSDFJLSAKFJALSKFJSAKFAFJALFALKFALKFJAKLFJAKLFJALFJKLASFJDLKSAFJALKFJLAKSFJKLAFJKALFJKLADFJKLASFJLKASJFFDASKLFKSALDFJAKSFJ");
        try{
            negocio.salvar(pessoa);
        }catch(Exception e) {
            assertTrue(CampoInvalidoExcecao.class.isInstance(e));
            assertEquals(e.getMessage(), "O valor do campo Nome esta inválido, deverá ter no máximo 80 caracteres.");
        }
    }

    @Test
    @Quando("o mesmo possui débitos")
    @Entao("o sistema lança a exceção com a mensagem :Operação não permitida, não e possível excluir um cliente com débitos.")
    public void testeRN4(){
        pessoa.setDevedor(true);
        try {
            negocio.remover(pessoa);
        }catch(Exception e) {
            assertTrue(OperacaoNaoPermitidaExcecao.class.isInstance(e));
            assertEquals(e.getMessage(), "Operação não permitida, não e possível excluir um cliente com débitos.");
        }
    }

}