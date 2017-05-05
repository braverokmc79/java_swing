package com.mcaronics.controller;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.AbstractTableModel;

public class SampleTableModel extends AbstractTableModel { // 출력할 테이블 구현 클래스
	public final static String[] columnNames = { "Name", "Address", "Phone", "Postal Code", "Date" };

	public final static Object[][] values = {
			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" },

			{ "Janet Abel", "1117 Sampson Street", "3184397830", "70669-0531", "2009/10/3" },

			{ "Debbie Anderson", "3420 Davis Road", "3184941888", "70669-0645", "1999/7/15" },

			{ "Teri White", "2516 WestWood Road", "4194040262", "80669-8884", "2005/6/12" } };

	public int getRowCount() {
		return values.length;
	}

	public int getColumnCount() {
		return 5;
	}

	public Object getValueAt(int row, int column) {
		return values[row][column];
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}
}
