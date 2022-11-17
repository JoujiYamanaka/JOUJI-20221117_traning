package kenshu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KenshuApplication {

	public static void main(String[] args) {
		SpringApplication.run(KenshuApplication.class, args);
		// ‰Û‘è1-1
		Kadai1.convertBinaryDigits(57.39);
		// ‰Û‘è1-2
		Kadai1.convertBinaryDigits(113.06);
		// ‰Û‘è2-1
		Kadai2.convertHexadecimal((float) 36.375);
		// ‰Û‘è2-2
		Kadai2.convertHexadecimal((float) -123.06);
	}
}
