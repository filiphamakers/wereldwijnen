package be.vdab.repositories;

import java.util.Collections;
import java.util.List;

import be.vdab.entities.Wijn;

public class WijnRepository extends AbstractRepository {
	public List<Wijn> findBySoort(long soortid) {
		return Collections.unmodifiableList(getEntityManager().createNamedQuery(Wijn.FIND_BY_SOORT, Wijn.class)
				.setParameter("soortid", soortid).getResultList());
	}
}
