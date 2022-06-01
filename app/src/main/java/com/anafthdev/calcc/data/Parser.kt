package com.anafthdev.calcc.data

import com.anafthdev.calcc.foundation.extension.find
import com.anafthdev.calcc.foundation.extension.indexAll
import com.anafthdev.calcc.foundation.extension.replaceAB
import java.lang.Exception

object Parser {
	
	fun parsePowTwo(exp: String): String {
		var result = exp
		val reversedExp = exp.reversed().replaceAB("(", ")")
		val hashtags = reversedExp.indexAll('#').map { it + 1 }
		val powResult = arrayListOf<Pair<String, String>>()
		
		hashtags.forEach { i ->
			var hashtagV = ""
			
			if (reversedExp[i] == '(') {
				var totalOpenBrackets = 1
				var totalCloseBrackets = 0
				val closeBrackets = reversedExp.find(')', i, reversedExp.length)
				
				for (c in reversedExp.substring(i).also { println(it) }) {
					if (c == '(') {
						totalOpenBrackets++
					} else if (c == ')') {
						totalCloseBrackets++
						if (totalOpenBrackets == totalCloseBrackets) break
					}
				}
				
				if (closeBrackets.isNotEmpty()) {
					hashtagV += "${reversedExp.substring(i, closeBrackets[totalCloseBrackets-1] + 1)})".also {
						println(it)
					}
				}
			} else {
				for (c in reversedExp.substring(i)) {
					if (c.isDigit()) {
						hashtagV += c
					} else break
				}
			}
			
			hashtagV = hashtagV.reversed().replaceAB("(", ")")
			
			powResult.add(
				"$hashtagV#" to "twopow$hashtagV"
			)
		}
		
		powResult.forEach {
			result = result.replace(it.first, it.second)
		}
		
		return result
	}
	
	fun parseSqrt(exp: String): String {
		return try {
			var result = exp
			val sqrtIndex = result.indexAll('√').map { it + 1 }
			val sqrtList = arrayListOf<String>()
			
			sqrtIndex.forEach { i ->
				var sqrtV = "sqrt("
				
				if (result[i] == '(') {
					var totalOpenBrackets = 0
					val closeBrackets = result.find(')', i, result.length)
					
					for (c in result.substring(i)) {
						if (c == '(') {
							totalOpenBrackets++
						} else if (c == ')') {
							break
						}
					}
					
					if (closeBrackets.isNotEmpty()) {
						sqrtV += "${result.substring(i, closeBrackets[totalOpenBrackets - 1] + 1)})"
					}
				} else {
					val subResult = result.substring(i)
					for ((iC, c) in subResult.withIndex()) {
						if (c.isDigit()) {
							sqrtV += c
							if (iC == subResult.length-1) sqrtV += ')'
						} else {
							sqrtV += ')'
							break
						}
					}
				}
				
				sqrtList.add(
					sqrtV
				)
			}
			
			sqrtList.forEach { s ->
				result = result.replace(
					oldValue = "√" + s.replace("sqrt(", "").let {
						it.substring(0, it.length - 1)
					},
					newValue = s
				)
			}
			
			return result
		} catch (e: Exception) {
			exp
		}
	}
	
}