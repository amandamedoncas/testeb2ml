package br.com.teste.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.teste.model.Turma;
import br.com.teste.util.DataSource;

public class Turmas {
	
	private EntityManager em = DataSource.getEntityManager();
	
	public Turmas(EntityManager em) {
		this.em = em;
	}
	
	public Turma turmaId(Long id) {
		return em.find(Turma.class, id);
	}
	
	public List<Turma> todos(){
		TypedQuery<Turma> query = em.createQuery("from Turma t order by t.id", Turma.class);
		return query.getResultList();
	}
	
	public void adicionar(Turma turma)
	{
		this.em.persist(turma);
	}
	
	public void guardar(Turma turma)
	{
		this.em.merge(turma);
	}
	
	public void remover(Turma turma)
	{
		this.em.remove(turma);
	}

}
