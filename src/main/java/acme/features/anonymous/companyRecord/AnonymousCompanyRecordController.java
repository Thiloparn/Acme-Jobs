
package acme.features.anonymous.companyRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/company-record/")
public class AnonymousCompanyRecordController extends AbstractController<Anonymous, CompanyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousCompanyRecordListService			listService;

	@Autowired
	private AnonymousCompanyRecordShowService			showService;

	@Autowired
	private AnonymousCompanyRecordListFiveStarsService	fiveStarService;

	@Autowired
	private AnonymousCompanyRecordCreateService			createService;

	@Autowired
	private AnonymousCompanyRecordUpdateService			updateService;

	@Autowired
	private AnonymousCompanyRecordDeleteService			deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

		super.addCustomCommand(CustomCommand.FIVE_STARS, BasicCommand.LIST, this.fiveStarService);
	}
}
