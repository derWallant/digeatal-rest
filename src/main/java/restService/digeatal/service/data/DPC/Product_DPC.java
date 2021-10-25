package restService.digeatal.service.data.DPC;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.ODataRequest;

import restService.digeatal.service.metadata.MPC.Product_MPC;

public class Product_DPC extends DPC {

	private static final String TAB_NAME = "products";	
	
	public Product_DPC() throws Exception {
		super(TAB_NAME);
		modelProvider = new Product_MPC();
	}

	@Override
	public Entity create(ODataRequest request) throws ODataException {
		// TODO Auto-generated method stub
		return super.create(request);
	}

	@Override
	public Entity update(ODataRequest request) throws ODataException {
		// TODO Auto-generated method stub
		return super.update(request);
	}

	@Override
	public Entity delete(ODataRequest request) throws ODataException {
		// TODO Auto-generated method stub
		return super.delete(request);
	}

}
