package conceito.excecao;

/**
 * RegistroNaoEncontradoExcecao
 * Classe que representa a Excecao genérica para pesquisas que não retornam nada.
 *
 * @see NegocioExcecao
 *
 * @author Marcos Fernando Costa.
 */
public class RegistroNaoEncontradoExcecao extends NegocioExcecao{

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public RegistroNaoEncontradoExcecao(){
	    super("MSG005");
	}
	
}