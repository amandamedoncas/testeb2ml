package br.com.teste.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.teste.model.Aluno;
import br.com.teste.util.DataSource;

public class Alunos implements Serializable {

	
	private static final long serialVersionUID = 1L;

private EntityManager em = DataSource.getEntityManager();
	
	public Alunos(EntityManager em) {
		this.em = em;
	}
	
	public Aluno alunoId(Long id) {
		return em.find(Aluno.class, id);
	}
	
	public List<Aluno> todos(){
		TypedQuery<Aluno> query = em.createQuery("from Aluno a order by a.id", Aluno.class);
		return query.getResultList();
	}
	
	public void adicionar(Aluno aluno)
	{
		this.em.persist(aluno);
	}
	
	public void guardar(Aluno aluno)
	{
		this.em.merge(aluno);
	}
	
	public void remover(Aluno aluno)
	{
		this.em.remove(aluno);
	}
}
