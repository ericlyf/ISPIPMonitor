package org.ip.client;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IPAddressTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<String> data;
	private String[] columnName = { "Log IP Address" };

	public IPAddressTableModel(List<String> data) {
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex);
	}

	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

}
