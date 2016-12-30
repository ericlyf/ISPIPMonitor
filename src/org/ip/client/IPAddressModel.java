package org.ip.client;

import java.util.ArrayList;
import java.util.List;

import org.ip.service.IPAddressMonitorService;

public class IPAddressModel {

	private IPAddressMonitorService service;
	private List<IPAddressObserver> observers;

	public IPAddressModel() {
		this.service = IPAddressMonitorService.instance;
		observers = new ArrayList<IPAddressObserver>();
	}

	public void startLogIpAddresses() {
		service.startLogIPAddresses();
	}

	public void stopLogIPAddresses() {
		service.stopLogIPAddresses();
	}

	public List<String> getLogIPAddresses() {
		return service.getLogIPAddresses();
	}

	public void notifyIPAddressObservers() {
		for (IPAddressObserver observer : observers) {
			observer.updateIPAddress(service.getLogIPAddresses());
		}
	}

	public void registerIPAddressObserver(IPAddressObserver observer) {
		observers.add(observer);

	}

	public void removeIPAddressObserver(IPAddressObserver observer) {
		int i = observers.indexOf(observer);
		if (i >= 0) {
			observers.remove(i);
		}

	}

	public void setIPAddressMonitorService(IPAddressMonitorService service) {
		this.service = service;
	}

}
