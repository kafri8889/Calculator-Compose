package com.anafthdev.calcc.foundation.extension

inline fun String.ifNotBlank(defaultValue: () -> String): String =
	if (isNotBlank()) defaultValue() else this

/**
 * ex:
 * strings = ["ab", "cd"]
 * "cd".equalsOr(strings) => true
 * "ab".equalsOr(strings) => true
 * "ef".equalsOr(strings) => false
 */
fun String.equalsOr(strings: List<String>, ignoreCase: Boolean = false): Boolean {
	strings.forEach {
		if (contentEquals(it, ignoreCase)) return true
	}
	
	return false
}

/**
 * replace char, A to B, B to A
 * @author kafri8889
 */
fun String.replaceAB(a: String, b: String): String {
	val temp = replace(b, "~|~")
	return temp.replace(a, b).replace("~|~", a)
}

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
