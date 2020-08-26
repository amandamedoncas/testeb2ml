package br.com.teste.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.teste.model.Aluno;
import br.com.teste.negocio.CadastroAlunos;
import br.com.teste.negocio.exception.NegocioException;
import br.com.teste.repositorio.Alunos;
import br.com.teste.util.DataSource;

@ManagedBean
@SessionScoped
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	EntityManager em = DataSource.getEntityManager();
	private Aluno aluno;
	private Alunos alunos = new Alunos(em);
	
	public void configInicial() {
		if(this.aluno == null) {
			this.aluno = new Aluno();
		}
	}
	
	public void cadastrar() throws IOException{
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroAlunos cadastro = new CadastroAlunos(new Alunos(em));
			cadastro.salvar(aluno);
			this.aluno = new Aluno();
			faces.addMessage(null, new FacesMessage("Aluno cadastrado."));
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Alunos getAlunos() {
		return alunos;
	}

	public void setAlunos(Alunos alunos) {
		this.alunos = alunos;
	}

}
