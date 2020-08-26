package br.com.teste.negocio;

import java.io.Serializable;

import br.com.teste.model.Aluno;
import br.com.teste.negocio.exception.NegocioException;
import br.com.teste.repositorio.Alunos;

public class CadastroAlunos implements Serializable{


	private static final long serialVersionUID = 1L;
	
private Alunos alunos;
	
	public CadastroAlunos(Alunos alunos) 
	{
		this.alunos = alunos;
	}
	
	public void salvar(Aluno aluno) throws NegocioException
	{
		this.alunos.guardar(aluno);
	}
	
	public void excluir(Aluno aluno) throws NegocioException
	{
		aluno = this.alunos.alunoId(aluno.getId());
		this.alunos.remover(aluno);
	}

}
