package be.vdab.repositories;

import java.util.Collections;
import java.util.List;

import be.vdab.entities.Soort;

public class SoortRepository extends AbstractRepository {
	public List<Soort> findByLand(long landid) {
		return Collections
				.unmodifiableList(getEntityManager().createNamedQuery(Soort.FIND_BY_LAND, Soort.class).getResultList());
	}
}
