package br.com.teste.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.teste.model.Aluno;
import br.com.teste.model.Professor;
import br.com.teste.model.Turma;
import br.com.teste.negocio.CadastroAlunos;
import br.com.teste.negocio.CadastroTurmas;
import br.com.teste.negocio.exception.NegocioException;
import br.com.teste.repositorio.Alunos;
import br.com.teste.repositorio.Professores;
import br.com.teste.repositorio.Turmas;
import br.com.teste.util.DataSource;

@ManagedBean
@ViewScoped
public class TurmaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	EntityManager em = DataSource.getEntityManager();
	private Turma turma = new Turma();
	private List<Turma> turmasLista;
	private Turmas turmas = new Turmas(em);
	
	private Aluno alunoSelecionado = new Aluno();
	private List<SelectItem> alunosSelecionados;
	private Alunos alunos = new Alunos(em);
	
	private Professor professorSelecionado = new Professor();
	private List<SelectItem> professoresSelecionados;
	private Professores professores = new Professores(em);
	
	public void configInicial() {
		if(this.turma == null) {
			this.turma = new Turma();
		}
	}
	
	public void cadastrar() throws IOException{
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroTurmas cadastro = new CadastroTurmas(new Turmas(em));
			turma.setProfessor(professorSelecionado);
			List<Aluno> alunos = new ArrayList<Aluno>();
			alunos.add(alunoSelecionado);
			///for (int i = 0; i <= alunos.size(); i++) {
				
				///alunos.add(alunoSelecionado);
			
			///}
			
			turma.setAlunos(alunos);
			cadastro.salvar(turma);
			this.turma = new Turma();
			faces.addMessage(null, new FacesMessage("Turma cadastrada."));
			et.commit();
			
			Aluno alunoSalvar = new Aluno();
			CadastroAlunos cadastroAluno = new CadastroAlunos(new Alunos(em));
			alunoSalvar = alunoSelecionado;
			alunoSalvar.setTurma(turma);
			cadastroAluno.salvar(alunoSalvar);
			faces.addMessage(null, new FacesMessage("ok."));
			
			
			
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, message);
		} finally {
			em.close();
		}
	}
	
	public void listar() {
		EntityManager em = DataSource.getEntityManager();
		this.turmasLista = turmas.todos();
		em.close();
	}
	
	public void deletar() {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		CadastroTurmas turmas = new CadastroTurmas(new Turmas(em));
		
		try {
			et.begin();
			turmas.excluir(turma);
			faces.addMessage(null, new FacesMessage("Turma removida."));
			et.commit();
			this.listar();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, message);
		} finally {
			em.close();
		}
	}
	
	public List<SelectItem> getAlunosSelecionados() {
		if(alunosSelecionados == null) {
			alunosSelecionados = new ArrayList<SelectItem>();
			
			alunos = new Alunos(em);
			
			List<Aluno> listAlunos =  alunos.todos();
			
			if(listAlunos != null && !listAlunos.isEmpty()) {
				SelectItem item;
				
				for (Aluno aluno : listAlunos) {
					item = new SelectItem(aluno, aluno.getNome());
					
					alunosSelecionados.add(item);
				}
			}
		}
		
		return alunosSelecionados;
		
	}
	


	public List<SelectItem> getProfessoresSelecionados() {
		if(professoresSelecionados == null) {
			professoresSelecionados = new ArrayList<SelectItem>();
			
			professores = new Professores(em);
			
			List<Professor> listProfessores =  professores.todos();
			
			if(listProfessores != null && !listProfessores.isEmpty()) {
				SelectItem item;
				
				for (Professor professor : listProfessores) {
					item = new SelectItem(professor, professor.getNome());
					
					professoresSelecionados.add(item);
				}
			}
		}
		
		return professoresSelecionados;
	}
	

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public void setProfessoresSelecionados(List<SelectItem> professoresSelecionados) {
		this.professoresSelecionados = professoresSelecionados;
	}

	public Professores getProfessores() {
		return professores;
	}

	public void setProfessores(Professores professores) {
		this.professores = professores;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmasList() {
		return turmasLista;
	}

	public void setTurmasList(List<Turma> turmasList) {
		this.turmasLista = turmasList;
	}

	public Turmas getTurmas() {
		return turmas;
	}

	public void setTurmas(Turmas turmas) {
		this.turmas = turmas;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public void setAlunosSelecionados(List<SelectItem> alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}

	public Alunos getAlunos() {
		return alunos;
	}

	public void setAlunos(Alunos alunos) {
		this.alunos = alunos;
	}

}
