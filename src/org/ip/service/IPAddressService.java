package org.ip.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class IPAddressService {
	private static IPAddressService ipAddress = new IPAddressService();

	public static IPAddressService instance() {
		return ipAddress;
	}

	public String getPublicIP() {
		URL whatismyip;
		String ip = "";
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					whatismyip.openStream()));
			ip = in.readLine();
			return ip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}

	public String getPrivateIP() {
		String ip = "";
		try {
			InetAddress thisIp = InetAddress.getLocalHost();
			ip = thisIp.getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

}
