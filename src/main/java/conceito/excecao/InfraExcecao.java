
package conceito.excecao;

/**
 * <p><b>InfraExcecao</b></p>
 * Classe que representa a Excecao genérica para a camdada de persistencia.
 *
 * @see Exception
 *
 * @author Marcos Fernando Costa.
 */
public class InfraExcecao extends Exception {
	private static final long serialVersionUID = -6472789855877680893L;
	
	/**
	 * Construtor que recebe uma mensagem pura como argumento.
	 * 
	 * @param mensagem mensagem da Excecao.
	 */
	public InfraExcecao(final String mensagem) {
		super(mensagem);
	}
	
	/**
	 * Construtor que recebe uma mensagem pura como argumento a Excecao que originou.
	 * 
	 * @param mensagem mensagem da Excecao.
	 * @param origem  Excecao original.
	 */
	public InfraExcecao(final String mensagem, final Throwable origem) {
		super(mensagem, origem);
	}
	
	/**
	 * Construtor que recebe como argumento a Excecao que originou.
	 * 
	 * @param origem  Excecao original.
	 */	
	public InfraExcecao(final Throwable origem) {
		super(origem);
	}
	
}