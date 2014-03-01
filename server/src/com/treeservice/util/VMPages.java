package com.treeservice.util;

public enum VMPages {

	// Сущности
	ENTITIES_LIST, ENTITIES_CREATE;

	public static VMPages cast(String html[]) {
		VMPages out = null;

		if (html[1].equalsIgnoreCase("entities")) {
			if (html[2].equalsIgnoreCase("list")) {
				out = ENTITIES_LIST;
			} else if (html[2].equalsIgnoreCase("create")) {
				out = ENTITIES_CREATE;
			}
		} 

		return out;
	}
}
