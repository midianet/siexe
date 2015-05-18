package conceito.entidade;

import java.io.Serializable;

public abstract class Entidade<ID extends Serializable> implements Serializable{
	private static final long serialVersionUID = 1L;

	public abstract ID getId();

	public abstract void setId(final ID id);
	
}