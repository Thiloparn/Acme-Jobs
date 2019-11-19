
package acme.features.authenticated.solicits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.solicits.Solicit;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSolicitShowService implements AbstractShowService<Authenticated, Solicit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedSolicitRepository repository;


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

		request.unbind(entity, model, "ticker", "title", "moment", "deadline", "text", "reward");
	}

	@Override
	public Solicit findOne(final Request<Solicit> request) {
		assert request != null;

		Solicit result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOnebyId(id);

		return result;
	}

}
