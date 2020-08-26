package br.com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.teste.model.Turma;
import br.com.teste.repositorio.Turmas;
import br.com.teste.util.DataSource;

@FacesConverter(forClass = Turma.class)
public class TurmaConverter implements Converter{
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) 
	{
		Turma retorno = null;
		EntityManager em = DataSource.getEntityManager();
		
		try 
		{
			if(arg2 != null && !"".equals(arg2))
			{
				retorno = new Turmas(em).turmaId(new Long(arg2));
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
			Turma Turma = ((Turma) arg2);
			return Turma.getId() == null ? null : Turma.getId().toString();
			
		}
		return null;
	}


}
