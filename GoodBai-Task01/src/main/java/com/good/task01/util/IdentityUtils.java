package com.good.task01.util;

import java.util.UUID;

public class IdentityUtils {
	public static String generatorUUID(String name){
		return UUID.randomUUID().toString();
	}
}
