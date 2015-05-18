package conceito.excecao;

/**
 * CampoInvalidoExcecao
 * Classe que representa a Excecao genérica para a campos com valores inválidos.
 * 
 * @category Excecao
 *
 * @see NegocioExcecao
 *
 * @author Marcos Fernando Costa.
 */
public class CampoInvalidoExcecao extends NegocioExcecao{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrão da Exceção
	 * 
	 * @param campo Nome do campo com valor inválido.
	 *
	 */	
	public CampoInvalidoExcecao(final String campo){
	    super("MSG002",campo);
	}
	
	/**
	 * 
	 * @param campo  Nome do campo com valor inválido.
	 * @param explicacao  Explicação do critério de invalidação.
	 */
	public CampoInvalidoExcecao(final String campo, final String explicacao){
	    super("MSG003",campo,explicacao);
	}
	
}