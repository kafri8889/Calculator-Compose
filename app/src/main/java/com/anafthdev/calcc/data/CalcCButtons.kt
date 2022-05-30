package com.anafthdev.calcc.data

object CalcCButtons {
	
	val buttons = listOf(
		CalcCAction.Clear(),
		CalcCAction.Percent(),
		CalcCAction.Delete(),
		CalcCOperation.Div(),
		
		CalcCNumber.Seven(),
		CalcCNumber.Eight(),
		CalcCNumber.Nine(),
		CalcCOperation.Mul(),
		
		CalcCNumber.Four(),
		CalcCNumber.Five(),
		CalcCNumber.Six(),
		CalcCOperation.Sub(),
		
		CalcCNumber.One(),
		CalcCNumber.Two(),
		CalcCNumber.Three(),
		CalcCOperation.Add(),
		
		CalcCNumber.DoubleZero(),
		CalcCNumber.Zero(),
		CalcCAction.Decimal(),
		CalcCAction.Calculate()
		
	)
	
	val advancedButtons: List<Pair<Calc, Calc>> = listOf(
		CalcCOperation.Sin() to CalcCOperation.ArcSin(),
		CalcCOperation.Cos() to CalcCOperation.ArcCos(),
		CalcCOperation.Tan() to CalcCOperation.ArcTan(),
		CalcCOperation.Log() to CalcCOperation.InvLog(),
		CalcCOperation.NaturalLogarithm() to CalcCOperation.InvNaturalLogarithm(),
		
		CalcCAdvancedButton.OpenParenthesis() to CalcCAdvancedButton.OpenParenthesis(),
		CalcCAdvancedButton.CloseParenthesis() to CalcCAdvancedButton.CloseParenthesis(),
		CalcCOperation.Pow() to CalcCOperation.Pow(),
		CalcCOperation.Sqrt() to CalcCOperation.InvSqrt(),
		CalcCOperation.Factorial() to CalcCOperation.Factorial(),
		
		CalcCOperation.Pi() to CalcCOperation.Pi(),
		CalcCOperation.Exp() to CalcCOperation.Exp(),
		CalcCAdvancedButton.Inverse() to CalcCAdvancedButton.Inverse(),
		CalcCAdvancedButton.Rad() to CalcCAdvancedButton.Rad(),
		CalcCAdvancedButton.Deg() to CalcCAdvancedButton.Deg()
	)
}