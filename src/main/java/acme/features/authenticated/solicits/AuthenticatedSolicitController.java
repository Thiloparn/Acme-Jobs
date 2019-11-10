
package acme.features.authenticated.solicits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.solicits.Solicit;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/solicit/")
public class AuthenticatedSolicitController extends AbstractController<Authenticated, Solicit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSolicitListService	listService;

	@Autowired
	private AuthenticatedSolicitShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
