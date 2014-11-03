package com.ailife.uip.doc.util;

import com.ailife.uip.core.util.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;

/**
 * Created by chenmm6 on 2014/11/3.
 */
public class ID {

	@Autowired
	private static IdGenerator idGenerator;

	public static String getNewId() {
		return idGenerator.generateId().toString().replaceAll(Symbol.MINUS, Symbol.EMPTY);
	}

}
