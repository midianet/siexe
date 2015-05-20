package conceito.controle;

import conceito.excecao.InfraExcecao;
import conceito.excecao.NegocioExcecao;
import conceito.excecao.RegistroNaoEncontradoExcecao;
import conceito.jsf.JSFUtil;
import conceito.negocio.ManterPessoaBO;
import conceito.entidade.Pessoa;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class ManterPessoaMB {

    @ManagedProperty(value = "#{manterPessoaBO}")
    private ManterPessoaBO negocio;

    private Pessoa formulario;
    private List<Pessoa> lista;

    @PostConstruct
    public void inicializar(){
        criar();
        pesquisar();
    }

    public void setNegocio(final ManterPessoaBO negocio) {
        this.negocio = negocio;
    }

    public Pessoa getFormulario() {
        return formulario;
    }

    public void salvar() {
        try {
            negocio.salvar(formulario);
            criar();
            pesquisar();
            JSFUtil.adicionarMensagemInformacao("Pessoa salva com sucesso.");
        }catch(NegocioExcecao e){
            JSFUtil.adicionarMensagemAlterta(e.getMessage());
        }catch(Exception e){
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public void pesquisar(){
        try {
            formulario.setId(null);
            lista = negocio.pesquisar(formulario);
        }catch(NegocioExcecao e){
            JSFUtil.adicionarMensagemAlterta(e.getMessage());
        } catch (InfraExcecao e) {
           JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public void editar(final Pessoa pessoa){
        this.formulario = pessoa.clone();
    }

    public void remover(final Pessoa pessoa){
        try {
            negocio.remover(pessoa);
            criar();
            pesquisar();
            JSFUtil.adicionarMensagemInformacao("Pessoa removida com sucesso.");
        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public void obterPorId() {
        try {
            if (formulario.getId() != null) {
                formulario = negocio.obterPorId(formulario.getId());
            }
        }catch(RegistroNaoEncontradoExcecao e){
            JSFUtil.adicionarMensagemAlterta(e.getMessage());
            criar();
        } catch (InfraExcecao e) {
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public void criar(){
        formulario = new Pessoa();
    }

}