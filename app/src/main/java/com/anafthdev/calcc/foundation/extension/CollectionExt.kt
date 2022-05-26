package com.anafthdev.calcc.foundation.extension

/**
 * example:
 * a = [1, 2, 3, 4, 5]
 * a.split(3)
 *
 * return Pair(2, [[1, 2, 3], [4, 5, null]])
 *
 * @author kafri8889
 */
fun <T> Collection<T>.split(n: Int): Pair<Int, List<List<T?>>> {
	val result = arrayListOf<List<T?>>()
	
	var splitSize = 0
	var counter = 0
	
	for (i in indices) {
		counter++
		
		if (counter == n) {
			splitSize++
			counter = 0
		}
		
		if ((i == size - 1) and (counter != 0)) splitSize++
	}
	
	repeat(splitSize) { i ->
		val tList = arrayListOf<T?>()
		val range = i.plus(1).times(n).minus(n)..i.plus(1).times(n)
		
		repeat(n) { index ->
			tList.add(
				elementAtOrNull(range.first + index)
			)
		}
		
		result.add(tList)
	}
	
	return splitSize to result
}
