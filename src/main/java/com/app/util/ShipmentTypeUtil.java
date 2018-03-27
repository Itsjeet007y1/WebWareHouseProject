package com.app.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtil {
	
	public List<String> getShipmentModes() {
		List<String> list=new LinkedList<String>();
		list.add("Air");
		list.add("Truck");
		list.add("Ship");
		list.add("Train");
		Collections.sort(list);
		return list;
	}
	
	public List<String> getShimpentGrades() {
		List<String> list=new LinkedList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		Collections.sort(list);
		return list;
	}
}
