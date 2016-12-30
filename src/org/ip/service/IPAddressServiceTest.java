package org.ip.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class IPAddressServiceTest {

	@Test
	public void testGetPublicIPAddress() {
		IPAddressService service = IPAddressService.instance();
		String publicIP = service.getPublicIP();
		assertTrue(publicIP.length() > 0 );
	}
	
	@Test
	public void testGetPrivateIP() {
		IPAddressService service = IPAddressService.instance();
		String privateIP = service.getPrivateIP();
		assertTrue(privateIP.length() > 0 );
	}

}
