
package acme.features.authenticated.solicits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.solicits.Solicit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSolicitRepository extends AbstractRepository {

	@Query("select s from Solicit s where s.id =?1")
	Solicit findOnebyId(int id);

	@Query("select s from Solicit s")
	Collection<Solicit> findManyAll();

}
