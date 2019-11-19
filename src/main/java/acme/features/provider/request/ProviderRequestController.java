
package acme.features.provider.solicits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Provider;
import acme.entities.solicits.Solicit;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/solicit/")
public class ProviderSolicitController extends AbstractController<Provider, Solicit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProviderSolicitCreateService createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}
}
