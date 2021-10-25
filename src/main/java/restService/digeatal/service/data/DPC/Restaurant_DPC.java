package restService.digeatal.service.data.DPC;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.ODataRequest;

import restService.digeatal.service.metadata.MPC.Restaurant_MPC;

public class Restaurant_DPC extends DPC {
	
	private static final String TAB_NAME = "restaurants";	
	
	public Restaurant_DPC() throws Exception {
		super(TAB_NAME);
		modelProvider = new Restaurant_MPC();
	}

	@Override
	public Entity create(ODataRequest request) throws ODataException {
		return super.create(request);
	}

	@Override
	public Entity update(ODataRequest request) throws ODataException {
		return super.update(request);
	}

	@Override
	public Entity delete(ODataRequest request) throws ODataException {
		return super.delete(request);
	}

}
