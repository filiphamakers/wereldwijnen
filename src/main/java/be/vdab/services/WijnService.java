package be.vdab.services;

import java.util.List;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService extends AbstractService {
	private final WijnRepository wijnRepository = new WijnRepository();

	public List<Wijn> findBySoort(long soortid) {
		return wijnRepository.findBySoort(soortid);
	}
}
