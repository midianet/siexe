package conceito.controle;

import conceito.excecao.InfraExcecao;
import conceito.excecao.NegocioExcecao;
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
        novo();
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
            if (formulario.getId() != null && formulario.getId().longValue() == 0L) {
                formulario.setId(null);
            }
            negocio.salvar(formulario);
            novo();
            pesquisar();
        }catch(NegocioExcecao e){
            JSFUtil.adicionarMensagemAlterta(e.getMessage());
        }catch(Exception e){
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public void pesquisar(){
        try {
            if(formulario.getId() != null){
               formulario = negocio.obterPorId(formulario.getId());
            }else{
                lista = negocio.pesquisar(formulario);
            }
        } catch (Exception e) {
           JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public void editar(final Pessoa pessoa){
        this.formulario = pessoa; //TODO:Clonar objeto
    }

    public void remover(final Pessoa pessoa){
        try {
            negocio.remover(pessoa);
            novo();
            pesquisar();
        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public Pessoa obterPorId(final Long id) {
        final Pessoa p = negocio.obterPorId(id);
        if(p != null){
            formulario = p;
        }
    }

    public void validaNome(FacesContext contexto, UIComponent componente , Object valor) throws ValidatorException{
        if(valor.toString().trim().isEmpty()){
            FacesMessage mensagem = new FacesMessage("Nome não informado!");
            throw new ValidatorException(mensagem);
        }
    }

    public List<Pessoa> getLista() {
        return lista;
    }
//
//    public void setLista(final List<Pessoa> lista) {
//        this.lista = lista;
//    }

    public void novo(){
        formulario = new Pessoa();
    }

}