package br.com.teste.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.teste.model.Professor;
import br.com.teste.negocio.CadastroProfessores;
import br.com.teste.negocio.exception.NegocioException;
import br.com.teste.repositorio.Professores;
import br.com.teste.util.DataSource;

@ManagedBean
@SessionScoped
public class ProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManager em = DataSource.getEntityManager();
	private Professor professor;
	private Professores professores = new Professores(em);
	
	public void configInicial() {
		if(this.professor == null) {
			this.professor = new Professor();
		}
	}
	
	public void cadastrar() throws IOException{
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroProfessores cadastro = new CadastroProfessores(new Professores(em));
			cadastro.salvar(professor);
			this.professor = new Professor();
			faces.addMessage(null, new FacesMessage("Professor cadastrado."));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, mensagem);
		} finally {
			em.close();
		}
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professores getProfessores() {
		return professores;
	}

	public void setProfessores(Professores professores) {
		this.professores = professores;
	}

}
