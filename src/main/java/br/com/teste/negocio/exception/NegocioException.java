package br.com.teste.negocio.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String msg)
	{
		super(msg);
	}
}
