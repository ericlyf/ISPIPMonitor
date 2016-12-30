package org.ip.client;


public class IPAddressController {
	
	private IPAddressModel model;
	private IPAddressView view;

	public IPAddressController(IPAddressModel model) {
		this.model = model;
		view = new IPAddressView(this, model);
	}

	public void startLogIPAddresses() {
		view.enableViewAndRefreshButton();
		view.showMonitoringISPIP();
		model.startLogIpAddresses();
	}

	public void stopLogIPAddresses() {
		view.disableViewAndRefreshButton();
		view.showStopMonitoringISPIP();
		model.stopLogIPAddresses();		
	}

	public void refreshIP() {
		model.notifyIPAddressObservers();
	}

}
