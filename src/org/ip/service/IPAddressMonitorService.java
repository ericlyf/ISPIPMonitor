package org.ip.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IPAddressMonitorService {
	private final static Logger logger = LogManager.getLogger(IPAddressMonitorService.class.getName());
	private final static String logIPFormat = "%s --- %s";
	
	public static IPAddressMonitorService instance = new IPAddressMonitorService();
	private IPAddressService service;
	private final List<String> ipAddressesLog;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();

	private IPAddressMonitorService() {
		service = IPAddressService.instance();
		ipAddressesLog = new ArrayList<String>();
	}

	public void setIPAddressService(IPAddressService service) {
		this.service = service;
	}
	
	public void startLogIPAddresses() {
		if (!logIPFlag) {
			logIPFlag = true;
			new Thread(monitorIPThread).start();
		}
	}
	private volatile boolean logIPFlag = false;
	public boolean isLogingIPAddress() {
		return logIPFlag;
	}
	
	private Runnable monitorIPThread = () -> monitorIPAddresses();
	private void monitorIPAddresses() {
		String previousIP = "0";
		while (logIPFlag) {
			String ip = service.getPublicIP();
			if (!ip.equals(previousIP)) {
				logger.info(ip);
				lock.writeLock().lock();
				try {
					ipAddressesLog.add(String.format(logIPFormat,new Date().toString(), ip));
				} finally {
					lock.writeLock().unlock();
				}
				previousIP = ip;
			}
		}
	}
	
	public void stopLogIPAddresses() {
		if (logIPFlag) {
			logIPFlag = false;
			lock.writeLock().lock();
			try {
				ipAddressesLog.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}
	}

	

	public List<String> getLogIPAddresses() {
		List<String> unmodifLogIPs = Collections.emptyList();
		lock.readLock().lock();
		try {
			unmodifLogIPs = Collections.unmodifiableList(ipAddressesLog);
		} finally {
			lock.readLock().unlock();
		}
		return unmodifLogIPs;

	}

	

}
