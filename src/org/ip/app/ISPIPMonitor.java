package org.ip.app;

import org.ip.client.IPAddressController;
import org.ip.client.IPAddressModel;

public class ISPIPMonitor {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new IPAddressController(new IPAddressModel());
			}
		});

	}
}
