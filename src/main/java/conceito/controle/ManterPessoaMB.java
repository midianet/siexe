package conceito.controle;

import conceito.excecao.InfraExcecao;
import conceito.excecao.NegocioExcecao;
import conceito.excecao.RegistroNaoEncontradoExcecao;
import conceito.jsf.JSFUtil;
import conceito.negocio.ManterPessoaBO;
import conceito.entidade.Pessoa;
import sun.security.validator.ValidatorException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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
//
//    public void setFormulario(final Pessoa formulario) {
//        this.formulario = formulario;
//    }

    public void salvar() {
        try {
            if(formulario.getId() == 0L){
                formulario.setId(null);
            }
            negocio.salvar(formulario);
            criar();
            pesquisar();
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
        } catch (InfraExcecao e) {
           JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public void editar(final Pessoa pessoa){
        this.formulario = pessoa; //TODO:Clonar objeto
    }

    public void remover(final Pessoa pessoa){
        try {
            negocio.remover(pessoa);
            criar();
            pesquisar();
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

//    public void validaNome(FacesContext contexto, UIComponent componente , Object valor) throws ValidatorException{
//        if(valor.toString().trim().isEmpty()){
//            FacesMessage mensagem = new FacesMessage("Nome não informado!");
//            throw new ValidatorException(mensagem);
//        }
//    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public void criar(){
        formulario = new Pessoa();
    }

}