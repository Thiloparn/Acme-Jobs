
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalAnnouncement", "totalCompanyRecord", "totalInvestorRecords", "minRewardsSolicit", "maxRewardsSolicit", "avgRewardsSolicit", "stdRewardsSolicit", "minRewardsOffer", "maxRewardsOffer", "avgRewardsOffer",
			"stdRewardsOffer", "sectorNumberCompanyRecord", "sectorNumberInvestorRecord");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		result.setTotalAnnouncement(this.repository.getTotalAnnouncement());
		result.setTotalCompanyRecord(this.repository.getTotalCompanyRecord());
		result.setTotalInvestorRecords(this.repository.getTotalInvestorRecord());

		result.setMinRewardsSolicit(this.repository.getMinRewardSolicit());
		result.setMaxRewardsSolicit(this.repository.getMaxRewardSolicit());
		result.setAvgRewardsSolicit(this.repository.getAvgRewardSolicit());
		result.setStdRewardsSolicit(this.repository.getStdRewardSolicit(result.getAvgRewardsSolicit()));

		result.setMinRewardsOffer(this.repository.getMinRewardOffer());
		result.setMaxRewardsOffer(this.repository.getMaxRewardOffer());
		result.setAvgRewardsOffer(this.repository.getAvgRewardOffer());
		result.setStdRewardsOffer(this.repository.getStdRewardOffer(result.getAvgRewardsOffer()));

		result.setSectorNumberCompanyRecord(this.repository.getSectorNumberCompanyRecord());
		result.setSectorNumberInvestorRecord(this.repository.getSectorNumberInvestorRecord());

		return result;
	}
}
