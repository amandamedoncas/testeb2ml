package br.com.teste.negocio;

import java.io.Serializable;

import br.com.teste.model.Turma;
import br.com.teste.negocio.exception.NegocioException;
import br.com.teste.repositorio.Turmas;

public class CadastroTurmas implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Turmas turmas;
	
	public CadastroTurmas(Turmas turmas) 
	{
		this.turmas = turmas;
	}
	
	public void salvar(Turma turma) throws NegocioException
	{
		this.turmas.guardar(turma);
	}
	
	public void excluir(Turma turma) throws NegocioException
	{
		turma = this.turmas.turmaId(turma.getId());
		this.turmas.remover(turma);
	}

}
