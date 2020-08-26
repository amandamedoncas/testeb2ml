package br.com.teste.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.teste.model.Aluno;
import br.com.teste.repositorio.Alunos;
import br.com.teste.util.DataSource;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) 
	{
		Aluno retorno = null;
		EntityManager em = DataSource.getEntityManager();
		
		try 
		{
			if(arg2 != null && !"".equals(arg2))
			{
				retorno = new Alunos(em).alunoId(new Long(arg2));
			}
			
			return retorno;
		}

		finally 
		{
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) 
	{
		if(arg2 != null)
		{
			Aluno aluno = ((Aluno) arg2);
			return aluno.getId() == null ? null : aluno.getId().toString();
			
		}
		return null;
	}

}
