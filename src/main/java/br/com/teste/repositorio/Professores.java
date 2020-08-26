package br.com.teste.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.teste.model.Professor;
import br.com.teste.util.DataSource;

public class Professores implements Serializable {

	private static final long serialVersionUID = 1L;
	
private EntityManager em = DataSource.getEntityManager();
	
	public Professores(EntityManager em) {
		this.em = em;
	}
	
	public Professor professorId(Long id) {
		return em.find(Professor.class, id);
	}
	
	public List<Professor> todos(){
		TypedQuery<Professor> query = em.createQuery("from Professor p order by p.id", Professor.class);
		return query.getResultList();
	}
	
	public void adicionar(Professor professor)
	{
		this.em.persist(professor);
	}
	
	public void guardar(Professor professor)
	{
		this.em.merge(professor);
	}
	
	public void remover(Professor professor)
	{
		this.em.remove(professor);
	}

}
