package br.com.teste.negocio;

import java.io.Serializable;

import br.com.teste.model.Professor;
import br.com.teste.negocio.exception.NegocioException;
import br.com.teste.repositorio.Professores;

public class CadastroProfessores implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Professores professores;
	
	public CadastroProfessores(Professores professores) 
	{
		this.professores = professores;
	}
	
	public void salvar(Professor professor) throws NegocioException
	{
		this.professores.guardar(professor);
	}
	
	public void excluir(Professor professor) throws NegocioException
	{
		professor = this.professores.professorId(professor.getId());
		this.professores.remover(professor);
	}

}
