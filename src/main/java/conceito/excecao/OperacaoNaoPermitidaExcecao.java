package conceito.excecao;

/**
 * RegistroNaoEncontradoExcecao
 * Classe que representa a Excecao genérica para pesquisas que não retornam nada.
 * 
 * @category Excecao
 *
 * @see NegocioExcecao
 *
 * @author Marcos Fernando Costa.
 */
public class OperacaoNaoPermitidaExcecao extends NegocioExcecao{

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param chave  Nome da chave pesquisada.
	 */
	public OperacaoNaoPermitidaExcecao(){
	    super("MSG003");
	}
	
}