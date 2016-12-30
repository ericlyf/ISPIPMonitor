package org.ip.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.AbstractTableModel;

public class IPAddressView implements IPAddressObserver{

	private JFrame viewFrame;
	private IPAddressController controller;
	private JToggleButton startAndStopButton;
	private JButton viewAndRefreshButton;
	private JLabel infoLabel;
	private AbstractTableModel tableModel;
	private List<String> ipLog;

	public IPAddressView(IPAddressController controller, IPAddressModel model) {
		this.controller = controller;
		model.registerIPAddressObserver(this);
		ipLog = new ArrayList<String>();
		 createView();
	}
	
	public void createView(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		viewFrame = new JFrame("Monitor ISP's IP");
		addComponentsToPane(this.viewFrame.getContentPane());
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 viewFrame.setSize(new Dimension(800, 600));
//		viewFrame.pack();
	    viewFrame.setVisible(true);
	}

	private void addComponentsToPane(Container pane) {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(getStartAndStopButton(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(getViewIPLogButton(), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		pane.add(getInfoLabel(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		pane.add(getLogTable(), c);

	}

	private ItemListener toggleAction = (ItemEvent ev) -> {
		if (ev.getStateChange() == ItemEvent.SELECTED) {
			controller.startLogIPAddresses();
		} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
			controller.stopLogIPAddresses();
		}

	};
	private JToggleButton getStartAndStopButton() {
		this.startAndStopButton = new JToggleButton("Start/Stop Monitor IP");
		this.startAndStopButton.addItemListener(toggleAction);
		return this.startAndStopButton;
	}

	private ActionListener viewIPLogAction = (ActionEvent e) -> {
		controller.refreshIP();;
	};

	private JComponent getViewIPLogButton() {
		viewAndRefreshButton = new JButton("View Log IP Address");
		viewAndRefreshButton.addActionListener(viewIPLogAction);
		return viewAndRefreshButton;
	}
	
	@Override
	public void updateIPAddress(List<String> logIPAddresses) {
		this.ipLog.clear();
		this.ipLog.addAll(logIPAddresses);
		this.tableModel.fireTableDataChanged();
		
	}
	
	public void disableViewAndRefreshButton(){
		this.viewAndRefreshButton.setEnabled(false);
	}
	
	public void enableViewAndRefreshButton(){
		this.viewAndRefreshButton.setEnabled(true);
	}
	
	private JLabel getInfoLabel(){
		infoLabel = new JLabel();
		infoLabel.setBackground(Color.blue);
		infoLabel.setForeground(Color.white);
		infoLabel.setOpaque(true);
		this.showStopMonitoringISPIP();
		return infoLabel;
	}
	
	public void showMonitoringISPIP(){
		this.infoLabel.setText("The application is monitoring ISP's IP, To stop monitoring ISP's IP, plesae press the Start/Stop Monitor IP button");
	}
	
	public void showStopMonitoringISPIP(){
		this.infoLabel.setText("To monitoring ISP's IP, please press the Start/Stop Monitor IP button");
	}

	private JComponent getLogTable() {
		this.tableModel = new IPAddressTableModel(ipLog);
		JTable table = new JTable();
		table.setModel(this.tableModel);
		JScrollPane tableScrollPane = new JScrollPane(table);
		return tableScrollPane;

	}


}
