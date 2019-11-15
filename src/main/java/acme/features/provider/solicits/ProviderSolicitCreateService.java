
package acme.features.provider.solicits;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Provider;
import acme.entities.solicits.Solicit;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderSolicitCreateService implements AbstractCreateService<Provider, Solicit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ProviderSolicitRepository repository;


	// AbstractListService<Provider, Solicit> interface --------------------

	@Override
	public boolean authorise(final Request<Solicit> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Solicit> request, final Solicit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Solicit> request, final Solicit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "reward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

	}

	@Override
	public Solicit instantiate(final Request<Solicit> request) {
		Solicit result;

		result = new Solicit();
		Date hoy = new Date();
		result.setDeadline(hoy);
		return result;
	}

	@Override
	public void validate(final Request<Solicit> request, final Solicit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimunDeadLine;
		Solicit existing;

		Money money;
		money = entity.getReward();
		boolean euro;
		boolean isAccepted;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimunDeadLine = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimunDeadLine), "deadline", "provider.solicit.form.error.too-close");

		}

		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneSolicitByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "provider.solicit.form.error.duplicate");
		}
		if (!errors.hasErrors("reward")) {
			euro = money.getCurrency().equals("EUR") || money.getCurrency().equals("€");
			errors.state(request, euro, "reward", "provider.solicit.form.error.currency");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "anonymous.user-account.error.must-accept");
	}

	@Override
	public void create(final Request<Solicit> request, final Solicit entity) {

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}