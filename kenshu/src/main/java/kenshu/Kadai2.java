package kenshu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class Kadai2 {
	public static void convertHexadecimal(float number) {
		float convBinaryDigit = convertBinaryDigits(number);
		// 16進数に変換
		String convHexadecimal =  Float.toHexString(convBinaryDigit);
		log.info("16進数：" + convHexadecimal);
	}

	public static float convertBinaryDigits(float number) {
		log.info("10進数:" + number);

		// 整数部分と小数部分に分割
		int integerPortion = getIntegerPortion(number);
		float decimalPortion = getDecimalPortion(number, integerPortion);

		// 整数部分と小数部分を計算
		List<Integer> integerPortionList = calcIntegerPortion(integerPortion);
		List<Integer> decimalPortionList = calcdecimalPortion(decimalPortion);

		// 整数部分の余りを最後から並べ直す
		List<Integer> couplingList = new ArrayList<Integer>();
		for (int countBack = integerPortionList.size() - 1; countBack >= 0; countBack--) {
			couplingList.add(integerPortionList.get(countBack));
		}

		// 整数部分と小数部分を結合
		String couplingIntegerPortion = couplingList.stream().map(String::valueOf).collect(Collectors.joining(""));
		String couplingdecimalPortion = decimalPortionList.stream().map(String::valueOf)
				.collect(Collectors.joining(""));
		log.info("2進数：" + (couplingIntegerPortion + "." + couplingdecimalPortion));
		return Float.parseFloat(couplingIntegerPortion + "." + couplingdecimalPortion);
	}

	/**
	 * 整数部分の値を取得
	 * 
	 * @param number 入力値
	 * @return 整数部分の値
	 */
	private static int getIntegerPortion(float number) {
		return (int) number;
	}

	/**
	 * 小数部分の値を取得
	 * 
	 * @param number         入力値
	 * @param integerPortion 整数部分の値
	 * @return 小数部分の値
	 */
	private static float getDecimalPortion(float number, int integerPortion) {
		return (10 * number - 10 * integerPortion) / 10;
	}

	/**
	 * 小数部分に2を掛け続ける。 途中で1が出たら無視して、小数部の値のみを使用する
	 * 
	 * @param decimalPortion 小数部分の値
	 * @return 小数部分の余り
	 */
	private static List<Integer> calcdecimalPortion(float decimalPortion) {
		float calcDecimal = decimalPortion;
		List<Integer> excessNumberList = new ArrayList<Integer>();

		int count = 0;
		while (count < 11) {
			calcDecimal = calcDecimal * 2;
			excessNumberList.add(getIntegerPortion(calcDecimal));
			if (calcDecimal >= 1) {
				calcDecimal = getDecimalPortion(calcDecimal, excessNumberList.get(count));
			}
			count++;
		}
		return excessNumberList;
	}

	/**
	 * 整数部分が0になるまで2を割り続けて、その余りを取得
	 * 
	 * @param integerPortion 整数部分の値
	 * @return 整数部分の余り
	 */
	private static List<Integer> calcIntegerPortion(int integerPortion) {
		int calcInteger = integerPortion;
		List<Integer> excessNumberList = new ArrayList<Integer>();

		while (calcInteger > 0) {
			excessNumberList.add(calcInteger % 2);
			calcInteger = calcInteger / 2;
		}
		return excessNumberList;
	}

}
