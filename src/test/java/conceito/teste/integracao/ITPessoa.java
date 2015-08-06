package conceito.teste.integracao;

import conceito.entidade.Pessoa;
import conceito.excecao.InfraExcecao;
import conceito.persistencia.PessoaDAO;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring-integracao-test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         TransactionalTestExecutionListener.class,
                         DirtiesContextTestExecutionListener.class})
@TransactionConfiguration(transactionManager="txManager", defaultRollback= false)
@Transactional
public class ITPessoa {

    @PersistenceContext
    protected EntityManager persistencia;

    @Autowired
    private PessoaDAO pessoaDAO;

    private IDatabaseConnection conexao;
    private IDataSet dataSet;
    private Pessoa pessoa;
    private Pessoa p10;
    private Pessoa p11;
    private Pessoa p12;

    @BeforeClass
    public static void initClass() {
    }

    @Before
    public void iniciar() {
        try {
            final Connection c = ((SessionFactoryImpl) ((Session) persistencia.getDelegate()).getSessionFactory()).getConnectionProvider().getConnection();
            conexao  = new DatabaseConnection(c);
            conexao.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
            final InputStream is = new FileInputStream("src/test/resources/dataset.xml");
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            dataSet = builder.build(is);
            DatabaseOperation.CLEAN_INSERT.execute(conexao, dataSet);

            pessoa = new Pessoa();
            pessoa.setNome("Pessoa Teste Padrão");
            pessoa.setDevedor(false);

            p10 = new Pessoa();
            p10.setId(10L);

            p11 = new Pessoa();
            p11.setId(11L);

            p12 = new Pessoa();
            p12.setId(12L);

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void incluir() throws InfraExcecao {
       pessoa.setNome("Pessoa Teste Incluída");
       pessoa = pessoaDAO.incluir(pessoa);
       assertTrue(pessoa.getId() != null);
    }

    @Test
    public void alterar() throws InfraExcecao {
        p10 = pessoaDAO.obterPorId(10L);
        p10.setNome("Pessoa Teste Alterado");
        pessoaDAO.alterar(p10);
        p10 = pessoaDAO.obterPorId(10L);
        assertNotNull(p10);
        assertTrue(p10.getNome().equals("Pessoa Teste Alterado"));
    }

    @Test
    public void listar() throws InfraExcecao{
        final List<Pessoa> lista = pessoaDAO.listarTodos();
        assertTrue(lista.size() == 3);
    }


    @Test
    public void excluir() throws InfraExcecao {
        pessoaDAO.excluir(p10);
        p10 = pessoaDAO.obterPorId(10L);
        assertNotNull(pessoa);
    }

    @Test
    public void listarPorNome() throws InfraExcecao{
       final List<Pessoa> lista = pessoaDAO.listarPorNome("Pessoa");
       assertTrue(lista.size() == 3);
    }

}