package conceito.excecao;

/**
 * <p><b>NegocioExcecao</b></p>
 * Classe que representa a Excecao generica para a camada de Negocio.
 *
 * @see Exception
 *
 * @author Marcos Fernando Costa.
 */
public abstract class NegocioExcecao extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor que recebe a chave da mensagem no arquivo de mensagens
	 * e seus parametros que sao opcionais.
	 * 
	 * Caso deseje mostrar uma mensagem fora do arquivo de mensagens
	 * basta apenas passar a mensagem na integra.
	 * 
	 * @param mensagem chave da mensagem no arquivo de mensagens ou mensagem na integra.
	 * @param parametros lista de parametros opcionais da mensagem.
	 */	
	public NegocioExcecao(final String mensagem ,final Object... parametros){
		super(ContainerMensagens.obterMensagem(mensagem, parametros));
	}
	
}