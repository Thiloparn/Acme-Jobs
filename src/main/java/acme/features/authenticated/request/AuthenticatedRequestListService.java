
package acme.features.authenticated.solicits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.solicits.Solicit;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedSolicitListService implements AbstractListService<Authenticated, Solicit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedSolicitRepository repository;


	// AbstractListService<Administrator, Shout> interface --------------------

	@Override
	public boolean authorise(final Request<Solicit> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Solicit> request, final Solicit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "deadline", "title");//atributos del modelo al listado(los que apareceran en el listado)
	}

	@Override
	public Collection<Solicit> findMany(final Request<Solicit> request) {
		assert request != null;

		Collection<Solicit> result;

		result = this.repository.findManyAll();

		return result;
	}
}
