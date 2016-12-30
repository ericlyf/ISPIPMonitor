package org.ip.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.ip.service.IPAddressMonitorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class IPAddressModelTest {
	
	
	@Mock IPAddressMonitorService service ;
	private IPAddressModel model;
	
	@Before
	public void setUp(){
		mockService();
		model  = new IPAddressModel();
		model.setIPAddressMonitorService(service);
	}

	private void mockService() {
		service = mock(IPAddressMonitorService.class);
	}

	@Test
	public void testStartLogIPAddresses() {
		model.startLogIpAddresses();
		verify(service).startLogIPAddresses();
	}
	
	@Test
	public void testStopLogIPAddresses() {
		model.stopLogIPAddresses();
		verify(service).stopLogIPAddresses();;
	}
	
	@Test
	public void testGetLogIPAddresses() {
		List<String> mockList = mock(List.class);
		when(service.getLogIPAddresses()).thenReturn(mockList);
		List<String> ipLogs = model.getLogIPAddresses();
		verify(service).getLogIPAddresses();
		assertEquals(mockList,ipLogs);
	}
	
	@Test
	public void testNotifyObserverIPAddress(){
		IPAddressObserver mockObserver = mock(IPAddressObserver.class);
		List<String> mockLogIPAddresses = mock(List.class);
		when(service.getLogIPAddresses()).thenReturn(mockLogIPAddresses);
		model.registerIPAddressObserver(mockObserver);
		model.notifyIPAddressObservers();
		model.removeIPAddressObserver(mockObserver);
		verify(mockObserver).updateIPAddress(mockLogIPAddresses);
		
	}

}
