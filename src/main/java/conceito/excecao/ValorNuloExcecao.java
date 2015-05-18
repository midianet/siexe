
package conceito.excecao;

/**
 * <p><b>InfraExcecao</b></p>
 * Classe que representa a Excecao de um valor nulo não negocial, para um objeto esperado pelo negócio.
 *
 * @see InfraExcecao
 *
 * @author Marcos Fernando Costa.
 */
public class ValorNuloExcecao extends InfraExcecao {
	private static final long serialVersionUID = -6472789855877680893L;

	/**
	 * Construtor que recebe uma mensagem pura como argumento.
	 *
	 * @param nome Nome do objeto nulo.
	 */
	public ValorNuloExcecao(final String nome) {
		super(nome);
	}
}