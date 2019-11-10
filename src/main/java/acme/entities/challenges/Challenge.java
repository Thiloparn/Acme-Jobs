
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date				deadline;

	@NotNull
	@Valid
	private Money				rewardGold;

	@NotNull
	@Valid
	private Money				rewardSilver;

	@NotNull
	@Valid
	private Money				rewardBronze;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
