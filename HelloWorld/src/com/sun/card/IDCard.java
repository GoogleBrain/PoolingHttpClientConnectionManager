package com.sun.card;

public class IDCard {
	int[] weight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	char[] validate = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

	public char getValidateCode(String id17) {
		int sum = 0;
		int mode = 0;
		for (int i = 0; i < id17.length(); i++) {
			sum = sum + Integer.parseInt(String.valueOf(id17.charAt(i)))
					* weight[i];
		}
		mode = sum % 11;
		return validate[mode];
	}

	public static void main(String[] args) {
		IDCard car = new IDCard();
		// 身份证前17位；
		String a = "";
		System.out.print(car.getValidateCode(a));
	}
}
