package com.anafthdev.calcc.foundation.extension

/**
 * @author kafri8889
 */
fun String.indexAll(char: Char): List<Int> {
	val result = arrayListOf<Int>()
	
	forEachIndexed { i, c ->
		if (c == char) result.add(i)
	}
	
	return result
}

/**
 * @author kafri8889
 */
fun String.find(char: Char, from: Int, to: Int): List<Int> {
	val result = arrayListOf<Int>()
	
	substring(from, to).forEachIndexed { i, c ->
		if (c == char) result.add(i + from)
	}
	
	return result
}
