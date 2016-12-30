package org.ip.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class IPAddressControllerTest {
	
	private IPAddressController controller;
	private IPAddressModel model;
	
	@Before
	public void setUp(){
		mockModel();
		controller = new IPAddressController(model);
	}

	private void mockModel() {
		model = mock(IPAddressModel.class);
	}

	@Test
	public void testStartLogIPAddresses() {
		controller.startLogIPAddresses();
		verify(model).startLogIpAddresses();
	}
	
	@Test
	public void testStopLogIPAddresses() {
		controller.stopLogIPAddresses();
		verify(model).stopLogIPAddresses();;
	}
	
	
	@Test
	public void testRefreshIP(){
		controller.refreshIP();
		verify(model).notifyIPAddressObservers();
	}
	
	

}
