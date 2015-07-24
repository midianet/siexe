package conceito.unitario;

import conceito.entidade.Pessoa;
import conceito.excecao.*;
import conceito.negocio.ManterPessoaBO;
import conceito.persistencia.PessoaDAO;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import cucumber.api.java.pt.Entao;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ManterPessoaTest {

    private Pessoa pessoa = null;
    private PessoaDAO daoMock = null;
    private ManterPessoaBO negocio = null;

    @Dado("que eu tenha uma pessoa")
    @Before
    public void construtor() {
        daoMock = createMock(PessoaDAO.class);
        negocio = new ManterPessoaBO(daoMock);
        pessoa  = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Marcos Fernando Costa");
        pessoa.setDevedor(false);
    }


    @Quando("eu informo um nome nulo")
    @Entao("o sistema lança exceção")
    @Test
    public void testarRN1(){
        pessoa.setNome(null);
        try {
            negocio.salvar(pessoa);
        }catch (Exception e){
            assertTrue(ValorNaoInformadoExcecao.class.isInstance(e));
        }
    }

//    @Test(expected = CampoInvalidoExcecao.class)
//    public void testarRN2() throws CampoInvalidoExcecao{
//        pessoa.setNome("X");
//        try{
//            negocio.salvar(pessoa);
//        }catch(ValorNaoInformadoExcecao e) {
//            fail();
//        }catch(CampoInvalidoExcecao e){
//            assertEquals(e.getMessage(), "O valor do campo Nome esta inválido, deverá ter no mínimo 10 e no máximo 80 caracteres.");
//            throw e;
//        }catch(InfraExcecao e){
//            fail();
//        }
//    }

//    @Test(expected = CampoInvalidoExcecao.class)
//    public void testarRN3() throws CampoInvalidoExcecao{
//        pessoa.setNome("FJASFJALFAFAFFDAJFLADFJAFJASLFJSALFJSALFJSDALKFJ;AFJALFJALFJLFJDALSDFJLSAKFJALSKFJSAKFAFJALFALKFALKFJAKLFJAKLFJALFJKLASFJDLKSAFJALKFJLAKSFJKLAFJKALFJKLADFJKLASFJLKASJFFDASKLFKSALDFJAKSFJ");
//        try{
//            negocio.salvar(pessoa);
//        }catch(ValorNaoInformadoExcecao e) {
//            fail(e.getMessage());
//        }catch(CampoInvalidoExcecao e){
//            assertEquals(e.getMessage(), "O valor do campo Nome esta inválido, deverá ter no mínimo 10 e no máximo 80 caracteres.");
//            throw e;
//        }catch(InfraExcecao e){
//            fail();
//        }
//    }
//
//    @Test(expected = OperacaoNaoPermitidaExcecao.class)
//    public void testarRN4() throws OperacaoNaoPermitidaExcecao{
//        pessoa.setDevedor(true);
//        try {
//            negocio.remover(pessoa);
//        }catch(OperacaoNaoPermitidaExcecao e){
//            assertEquals(e.getMessage(), "Operação não permitida, não e possível excluir um cliente com débitos.");
//            throw e;
//        }catch(InfraExcecao e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testarFB1(){
//        try {
//            expect(daoMock.listarTodos()).andReturn(new ArrayList<Pessoa>()).once();
//            replay(daoMock);
//            pessoa.setNome(null);
//            negocio.pesquisar(pessoa);
//            verify(daoMock);
//        }catch(NenhumRegistroEncontradoExcecao e){
//            fail(e.getMessage());
//        }catch(InfraExcecao e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testarFB2(){
//        try {
//            expect(daoMock.incluir(pessoa)).andReturn(pessoa).once();
//            pessoa.setId(null);
//            replay(daoMock);
//            negocio.salvar(pessoa);
//            verify(daoMock);
//        }catch(ValorNaoInformadoExcecao e){
//            fail(e.getMessage());
//        }catch (CampoInvalidoExcecao e) {
//            fail(e.getMessage());
//        }catch(InfraExcecao e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void testarFB3(){
//        try {
//            expect(daoMock.alterar(pessoa)).andReturn(pessoa).once();
//            replay(daoMock);
//            negocio.salvar(pessoa);
//            verify(daoMock);
//        }catch(ValorNaoInformadoExcecao e){
//            fail(e.getMessage());
//        }catch (CampoInvalidoExcecao e) {
//            fail(e.getMessage());
//        }catch(InfraExcecao e){
//            fail();
//        }
//    }
//
//    @Test(expected = RegistroNaoEncontradoExcecao.class)
//    public void testarFE01() throws RegistroNaoEncontradoExcecao {
//        try {
//            expect(daoMock.obterPorId(18L)).andReturn(null).once();
//            replay(daoMock);
//            negocio.obterPorId(18L);
//            verify(daoMock);
//        }catch(InfraExcecao e){
//            fail();
//        }
//    }
//
//    @Test(expected = NenhumRegistroEncontradoExcecao.class )
//    public void testarFE02() throws NenhumRegistroEncontradoExcecao{
//        try {
//            expect(daoMock.listarPorNome("AAA")).andReturn(new ArrayList<Pessoa>()).once();
//            replay(daoMock);
//            pessoa.setNome("AAA");
//            negocio.pesquisar(pessoa);
//            verify(daoMock);
//        }catch(InfraExcecao e){
//            fail(e.getMessage());
//        }
//    }

}
