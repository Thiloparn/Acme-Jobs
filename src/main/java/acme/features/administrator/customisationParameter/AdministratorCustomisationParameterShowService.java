
package acme.features.administrator.customisationParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomisationParameterShowService implements AbstractShowService<Administrator, CustomisationParameter> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCustomisationParameterRepository repository;


	@Override
	public boolean authorise(final Request<CustomisationParameter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWordsEn", "spamWordsEs", "threshold");
	}

	@Override
	public CustomisationParameter findOne(final Request<CustomisationParameter> request) {
		assert request != null;

		List<CustomisationParameter> lista = new ArrayList<CustomisationParameter>(this.repository.findManyAll());

		CustomisationParameter result = lista.get(0);

		return result;
	}
}
