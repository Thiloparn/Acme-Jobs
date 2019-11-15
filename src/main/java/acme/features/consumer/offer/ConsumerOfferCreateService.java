
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ConsumerOfferRepository repository;


	// AbstractListService<Consumer,Offer> interface --------------------

	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "text", "deadline", "maxMoney", "minMoney", "title", "moment", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;

		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimunDeadLine;
		Offer existing;

		Money minMoney;
		Money maxMoney;
		minMoney = entity.getMinMoney();
		maxMoney = entity.getMaxMoney();
		boolean MinEuro;
		boolean MaxEuro;
		boolean isAccepted;
		boolean esMenor;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimunDeadLine = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimunDeadLine), "deadline", "consumer.offer.form.error.too-close");

		}

		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneOfferByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "consumer.offer.form.error.duplicate");
		}

		if (!errors.hasErrors("minMoney")) {
			MinEuro = minMoney.getCurrency().equals("EUR") || minMoney.getCurrency().equals("€");
			esMenor = minMoney.getAmount() <= maxMoney.getAmount();
			errors.state(request, esMenor, "minMoney", "consumer.offer.form.error.min.amount");
			errors.state(request, MinEuro, "minMoney", "consumer.offer.form.error.currency");

		}

		if (!errors.hasErrors("maxMoney")) {
			MaxEuro = maxMoney.getCurrency().equals("EUR") || maxMoney.getCurrency().equals("€");
			errors.state(request, MaxEuro, "maxMoney", "consumer.offer.form.error.currency");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "consumer.offer.form.error.must-accept");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
