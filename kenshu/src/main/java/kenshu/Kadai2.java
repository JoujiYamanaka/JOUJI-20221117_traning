package kenshu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class Kadai2 {
	public static void convertHexadecimal(float number) {
		float convBinaryDigit = convertBinaryDigits(number);
		// 16�i���ɕϊ�
		String convHexadecimal =  Float.toHexString(convBinaryDigit);
		log.info("16�i���F" + convHexadecimal);
	}

	public static float convertBinaryDigits(float number) {
		log.info("10�i��:" + number);

		// ���������Ə��������ɕ���
		int integerPortion = getIntegerPortion(number);
		float decimalPortion = getDecimalPortion(number, integerPortion);

		// ���������Ə����������v�Z
		List<Integer> integerPortionList = calcIntegerPortion(integerPortion);
		List<Integer> decimalPortionList = calcdecimalPortion(decimalPortion);

		// ���������̗]����Ōォ����ג���
		List<Integer> couplingList = new ArrayList<Integer>();
		for (int countBack = integerPortionList.size() - 1; countBack >= 0; countBack--) {
			couplingList.add(integerPortionList.get(countBack));
		}

		// ���������Ə�������������
		String couplingIntegerPortion = couplingList.stream().map(String::valueOf).collect(Collectors.joining(""));
		String couplingdecimalPortion = decimalPortionList.stream().map(String::valueOf)
				.collect(Collectors.joining(""));
		log.info("2�i���F" + (couplingIntegerPortion + "." + couplingdecimalPortion));
		return Float.parseFloat(couplingIntegerPortion + "." + couplingdecimalPortion);
	}

	/**
	 * ���������̒l���擾
	 * 
	 * @param number ���͒l
	 * @return ���������̒l
	 */
	private static int getIntegerPortion(float number) {
		return (int) number;
	}

	/**
	 * ���������̒l���擾
	 * 
	 * @param number         ���͒l
	 * @param integerPortion ���������̒l
	 * @return ���������̒l
	 */
	private static float getDecimalPortion(float number, int integerPortion) {
		return (10 * number - 10 * integerPortion) / 10;
	}

	/**
	 * ����������2���|��������B �r����1���o���疳�����āA�������̒l�݂̂��g�p����
	 * 
	 * @param decimalPortion ���������̒l
	 * @return ���������̗]��
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
	 * ����������0�ɂȂ�܂�2�����葱���āA���̗]����擾
	 * 
	 * @param integerPortion ���������̒l
	 * @return ���������̗]��
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
