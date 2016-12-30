package org.ip.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IPAddressMonitorServiceTest {
	
	private IPAddressMonitorService service;
	
	@Before
	public void setUp(){
		service = IPAddressMonitorService.instance ;
		service.setIPAddressService(IPAddressService.instance());
	}

	@Test
	public void testCreateObject() {
		assertNotNull(service);
	}
	
	@Test
	public void testLogIPAddresses_IPNotChange_ShouldLogOneIP(){
		service.setIPAddressService(IPAddressService.instance());
		service.startLogIPAddresses();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<String> ipAddressesLog = service.getLogIPAddresses();
		assertTrue(ipAddressesLog.size() == 1);
	}
	
	@Test
	public void testStopLogIPAddresses_StartAndStopAtTheSameTime_ShouldNotIPLog(){
		service.startLogIPAddresses();
		service.stopLogIPAddresses();
		List<String> logIPAddresses = service.getLogIPAddresses();
		assertTrue(logIPAddresses.size() == 0);
	}
	
	@Test
	public void testIsLogingIPAddress_StartLogIPAddress_ShoudlReturnTrue(){
		service.startLogIPAddresses();
		assertTrue(service.isLogingIPAddress());
	}
	
	@Test
	public void testIsLogingIPAddress_StopOrHavingStartLogIPAddress_ShoudlReturnFalse(){
		assertFalse(service.isLogingIPAddress());
		service.startLogIPAddresses();
		service.stopLogIPAddresses();
		assertFalse(service.isLogingIPAddress());
	}

}
