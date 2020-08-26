package br.com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.teste.model.Professor;
import br.com.teste.repositorio.Professores;
import br.com.teste.util.DataSource;

@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) 
	{
		Professor retorno = null;
		EntityManager em = DataSource.getEntityManager();
		
		try 
		{
			if(arg2 != null && !"".equals(arg2))
			{
				retorno = new Professores(em).professorId(new Long(arg2));
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
			Professor Professor = ((Professor) arg2);
			return Professor.getId() == null ? null : Professor.getId().toString();
			
		}
		return null;
	}

}
