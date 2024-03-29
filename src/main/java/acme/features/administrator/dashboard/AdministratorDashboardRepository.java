
package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer getTotalAnnouncement();

	@Query("select count(c) from CompanyRecord c")
	Integer getTotalCompanyRecord();

	@Query("select count(i) from InvestorRecord i")
	Integer getTotalInvestorRecord();

	@Query("select min(s.reward.amount) from Request_ s")
	Double getMinRewardRequest();

	@Query("select max(s.reward.amount) from Request_ s")
	Double getMaxRewardRequest();

	@Query("select avg(s.reward.amount) from Request_ s")
	Double getAvgRewardRequest();

	@Query("select sqrt(sum((s.reward.amount-?1)*(s.reward.amount-?1))/count(s)) from Request_ s")
	Double getStdRewardRequest(Double avg);

	@Query("select min(o.minMoney.amount) from Offer o")
	Double getMinRewardOffer();

	@Query("select max(o.maxMoney.amount) from Offer o")
	Double getMaxRewardOffer();

	@Query("select (avg(o.minMoney.amount)+avg(o.maxMoney.amount))/2 from Offer o")
	Double getAvgRewardOffer();

	@Query("select sqrt(sum(((o.minMoney.amount + o.maxMoney.amount)/2-?1)*((o.minMoney.amount + o.maxMoney.amount)/2-?1))/count(o)) from Offer o")
	Double getStdRewardOffer(Double avg);

	@Query("select c.sector,count(c) from CompanyRecord c group by c.sector")
	Collection<Object[]> getSectorNumberCompanyRecord();

	@Query("select i.sector,count(i) from InvestorRecord i group by i.sector")
	Collection<Object[]> getSectorNumberInvestorRecord();

}
