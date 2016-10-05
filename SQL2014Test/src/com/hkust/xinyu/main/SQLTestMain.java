package com.hkust.xinyu.main;

import com.hkust.xinyu.util.sqlutil.SpecialCommand;

public class SQLTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SpecialCommand.excuteSQL("SELECT * FROM Tb_Apply"));

	}

}
