package conceito.excecao;

/**
 * RegistroNaoEncontradoExcecao
 * Classe que representa a Excecao genérica para pesquisas que não retornam nada.
 *
 * @see NegocioExcecao
 *
 * @author Marcos Fernando Costa.
 */
public class OperacaoNaoPermitidaExcecao extends NegocioExcecao{

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param explicacao  Explicação pela negação.
	 */
	public OperacaoNaoPermitidaExcecao(final String explicacao){
	    super("MSG006",explicacao);
	}
	
}