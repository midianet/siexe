
package conceito.excecao;

/**
 * CampoNaoInformadoExcecao
 * Classe que representa a Excecao genérica para um campo que esteja com o valor vazio ou nulo.
 *
 * @see NegocioExcecao
 *
 * @author Marcos Fernando Costa.
 */
public class ValorNaoInformadoExcecao extends NegocioExcecao{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrão da Excessão
	 * 
	 * @param campo Nome do campo não informado ou nulo.
	 */
	public ValorNaoInformadoExcecao(final String campo){
	    super("MSG001",campo);
	}
	
}