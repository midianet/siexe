package conceito.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * JSFUtil
 * Classe que representa utilidades básicas de JSF (Web).
 *
 * @category JSF.
 *
 * @author Marcos Fernando Costa.
 */
public abstract class JSFUtil {

    /**
     * Construtor privado para garantir que a mesma não seja instânciada, dado a que sua utilização seja estática.
     */
    private JSFUtil(){}

    /**
     * Método que adiciona Mensagem de erro no contexto JSF
     *
     * @param mensagem Mensagem de erro a ser adicionada no contexto JSF
     *
     * @see FacesMessage
     * @see FacesContext
     */
    public static void adicionarMensagemErro(final String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
    }

    /**
     * Método que adiciona Mensagem de erro no contexto JSF
     *
     * @param mensagem Mensagem de erro a ser adicionada no contexto JSF
     * @param detalhe Detalhe de mensagem dee erro que poderá ser exibido na página.
     *
     * @see FacesMessage
     * @see FacesContext
     */
    public static void adicionarMensagemErro(final String mensagem, final String detalhe ){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, detalhe));
    }

    /**
     * Método que adiciona Mensagem informativa no contexto JSF
     *
     * @param mensagem Mensagem informativa a ser adicionada no contexto JSF
     *
     * @see FacesMessage
     * @see FacesContext
     */
    public static void adicionarMensagemInformacao(final String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
    }

    /**
     * Método que adiciona Mensagem de alerta no contexto JSF
     *
     * @param mensagem Mensagem de alerta a ser adicionada no contexto JSF
     *
     * @see FacesMessage
     * @see FacesContext
     */
    public static void adicionarMensagemAlterta(final String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
    }

    /**
     * Método que adiciona uma propriedade na sessao JSF
     *
     * @param propriedade Nome da propriedade a ser adicionada.
     * @param valor Valor da propriedade a ser adicionada.
     *
     * @see FacesContext
     */
    public static void adicionarPropriedadeSessao(final String propriedade, final Object valor){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(propriedade, valor);
    }

    /***
     *
     * @return String string
     */
    public static String obterIpUsuario(){
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
    }

    /**
     * Método que obtem a propriedade anteriormente adicionada na sessão JSF
     *
     * @param propriedade nome da propriedade a ser obtida.
     *
     * @return Object valo da propriedade retornado.
     *
     * @see FacesContext
     */
    public static Object obterPropriedadeSecao(final String propriedade){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(propriedade);
    }

    /**
     * Método que remove a propriedade anteriormente adicionada na sessão JSF
     *
     * @param propriedade nome da propriedade a ser removida.
     *
     * @see FacesContext
     */
    public static void removerPropriedadeSessao(final String propriedade){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(propriedade);
    }

}