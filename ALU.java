/*
 * 陈欢  141250010
 * */
public class ALU {
	static ALU ALU = new ALU();
	// 1
	public String calculation(String formula) {
		String[] split=formula.split("");
		String[] s=new String[5];
		for(int j=0;j<5;j++){
			s[j]="";
		}
		int count=0;
		boolean isf=false;
		boolean p=true;
		for(int j=0;j<split.length;j++){
			if(split[j].equals(".")){
				 isf=true;
			}
		}
		for(int j=0;j<split.length;j++){	
			if (split[j].equals("/")) {
				p=false;
				String[] split1 = new String[2];
				split1[0]=formula.substring(0, j);
				split1[1]=formula.substring(j, split.length);
				split1[1] = split1[1].substring(1, split1[1].length() - 1);
				// 判断有无括号，并去掉
				if (split1[0].substring(0, 1).equals("(")) {
					if(split1[0].length() - 1>0)
					split1[0] = split1[0].substring(1, split1[0].length() - 1);
				}

				if (split1[1].substring(0, 1).equals("(")) {
					if(split1[1].length() - 1>0)
					split1[1] = split1[1].substring(1, split1[1].length() - 1);
				}

				if (isf) {
					s[0] = ALU.floatDivision(ALU.floatRepresentation(split1[0], 23, 8),
							ALU.floatRepresentation(split1[1], 23, 8), 23, 8);
					return ALU.floatTrueValue(s[0], 23, 8);
				} else {
					s[0] = ALU.integerDivision(ALU.integerRepresentation(split1[0], 32), 
							ALU.integerRepresentation(split1[1], 32), 32);
					return ALU.integerTrueValue(s[0].substring(0, 32));
				}

			} else if(split[j].equals("*")){
				p=false;
				String[] split1 = new String[2];
				split1[0]=formula.substring(0, j);
				split1[1]=formula.substring(j, split.length);
				split1[1] = split1[1].substring(1, split1[1].length() - 1);
				// 判断有无括号，并去掉
				if (split1[0].substring(0, 1).equals("(")) {
					if(split1[0].length() - 1>0)
					split1[0] = split1[0].substring(1, split1[0].length() - 1);
				}

				if (split1[1].substring(0, 1).equals("(")) {
					if(split1[1].length() - 1>0)
					split1[1] = split1[1].substring(1, split1[1].length() - 1);
				}
				
				if (isf) {
					s[0] = ALU.floatMultiplication(ALU.floatRepresentation(split1[0], 23, 8),
							ALU.floatRepresentation(split1[1], 23, 8), 23, 8);
					return ALU.floatTrueValue(s[0], 23, 8);
				} else {
					s[0] = ALU.integerMultiplication(ALU.integerRepresentation(split1[0], 32), 
							ALU.integerRepresentation(split1[1], 32), 32);
					return ALU.integerTrueValue(s[0]);
				}
				
			}else if(split[j].equals("+")){
				p=false;
				String[] split1 = new String[2];
				split1[0]=formula.substring(0, j);
				split1[1]=formula.substring(j, split.length);
				split1[1] = split1[1].substring(1, split1[1].length() - 1);
				// 判断有无括号，并去掉
				if (split1[0].substring(0, 1).equals("(")) {
					if(split1[0].length() - 1>0)
					split1[0] = split1[0].substring(1, split1[0].length() - 1);
				}

				if (split1[1].substring(0, 1).equals("(")) {
					if(split1[1].length() - 1>0)
					split1[1] = split1[1].substring(1, split1[1].length() - 1);
				}
				
				if (isf) {
					s[0] = ALU.floatAddition(ALU.floatRepresentation(split1[0], 23, 8),
							ALU.floatRepresentation(split1[1], 23, 8), 23, 8,0);
					return ALU.floatTrueValue(s[0].substring(0, 32), 23, 8);
				} else {
					s[0] = ALU.integerAddition(ALU.integerRepresentation(split1[0], 32), 
							ALU.integerRepresentation(split1[1], 32),'0', 32);
					return ALU.integerTrueValue(s[0].substring(0, 32));
				}
			}else if(split[j].equals("-")){
				count++;
		}

			
		}//close the for
			if(p){
				if(count==1){
					String[] split1=formula.split("-");
					if (isf) {
						s[0] = ALU.floatSubtraction(ALU.floatRepresentation(split1[0], 23, 8),
								ALU.floatRepresentation(split1[1], 23, 8), 23, 8,0);
						return ALU.floatTrueValue(s[0].substring(0, 32), 23, 8);
					} else {
						s[0] = ALU.integerSubtraction(ALU.integerRepresentation(split1[0], 32), 
								ALU.integerRepresentation(split1[1], 32), 32);
						return ALU.integerTrueValue(s[0].substring(0, 32));
					}
				}
			}
		
		return null;
	}
	// 2
	public String integerRepresentation(String number, int length) {
		// 返回值：number 的补码表示，长度为 length
		int n = Integer.parseInt(number);
		String s = "";
		String s1 = "";

		int k = Math.abs(n);
		do {
			if (k % 2 == 0) {
				s = "0" + s;
			} else {
				s = "1" + s;
			}
			k = k / 2;
		} while (k != 0);

		if (length - s.length() > 0) {
			for (int m = 0; m < length - s.length(); m++) {
				s1 += "0";
			}
			s = s1 + s;
		}

		if (n < 0) {
			s = ALU.BinaryComplement(s);
		}

		return s;
	}
	// 3
	public String floatRepresentation(String number, int sLength, int eLength) {
		assert sLength >= 8;
		assert eLength >= 8;
		String str1="";
		String str2="";
		String str3="";
		String str4="";
		for(int j=0;j<eLength;j++){
			str1=str1+"0";
			str3=str3+"1";
		}
		for(int j=0;j<sLength;j++){
			str2=str2+"0";
		}
		if(number.equals("0")){
			str4="0"+str1+str2;
			return str4;
		}else if(number.equals("Inf")){
			str4="0"+str3+str2;
			return str4;
		}
		else if(number.equals("NaN")){
			str4="0"+str3+str2.substring(0, sLength-1)+"1";
			return str4;
		}
		String[] s = new String[6];
		double n = Double.parseDouble(number);
		double d = Math.abs(n);
		int i = (int) Math.floor(d);
		double dec = d - i;
		int exponent1 = 0;
		int exponent = (int) Math.pow(2, eLength - 1);
		// long MAX_NUMBER=(long) Math.pow(2-Math.pow(2,sLength),exponent);
		// long MIN_NUMBER=(long) Math.pow(2,exponent-1);

		for (int j = 0; j < 6; j++) {
			s[j] = "";
		}
		if (n < 0) {
			s[0] = "1";
		} else {
			s[0] = "0";
		}

		for (int j = 0; j < sLength+4; j++) {
			dec = dec * 2;
			if (dec >= 1) {
				s[2] = s[2] + "1";
				dec = dec - 1;

			} else if (dec < 1) {
				s[2] = s[2] + "0";
			}

		}// 小数部分

		s[1] = ALU.integerRepresentation((i + "").toString(), sLength);
		if (i == 0) {
			s[1] = "";
			String[] split1 = s[2].split("");
			for (int j = 0; j < sLength; j++) {
				if (split1[j].equals("1")) {
					exponent1 = exponent - 1 - (j + 1);
					s[2] = ALU.leftShift(s[2], j + 1);// 小数部分
					break;
				}
			}
			//就近舍入
			String[] str=s[2].split("");
			if(sLength - s[1].length()<str.length){
				if(str[sLength - s[1].length()].equals("1")){
					for(int j=sLength - s[1].length()-1;j>=0;j--){
						if(str[j].equals("0")){
							str[j]="1";
							break;
						}else{
							str[j]="0";
						}
					}
				}
			}
			
			for(int j=0;j<str.length;j++){
				s[5]=s[5]+str[j];
			}
			
			s[3] = ALU.integerRepresentation(("" + exponent1).toString(),
					eLength);// 指数部分
			s[4] = s[0] + s[3] + s[5].substring(0, sLength);
			return s[4];
		} else {
			String[] split = s[1].split("");
			for (int j = 0; j < sLength; j++) {
				if (split[j].equals("1")) {
					s[1] = s[1].substring(j + 1, sLength);
					exponent1 = sLength - j - 1 + exponent - 1;
					break;
				}
			}
			//就近舍入
			String[] str=s[2].split("");
			if(sLength - s[1].length()<str.length){
				if(str[sLength - s[1].length()].equals("1")){
					for(int j=sLength - s[1].length()-1;j>=0;j--){
						if(str[j].equals("0")){
							str[j]="1";
							break;
						}else{
							str[j]="0";
						}
					}
				}
			}
			
			for(int j=0;j<str.length;j++){
				s[5]=s[5]+str[j];
			}
			s[3] = ALU.integerRepresentation(("" + exponent1).toString(),
					eLength);// 指数部分
			s[1] = s[1] + s[5].substring(0, sLength - s[1].length());// 小数部分
			s[4] = s[0] + s[3] + s[1];
			return s[4];
		}
	}

	// 4
	public String ieee754(String number, int length) {
		String s = "";
		if (length == 32) {
			s = ALU.floatRepresentation(number, 23, 8);
		} else {
			s = ALU.floatRepresentation(number, 52, 11);
		}
		return s;
	}

	// 5
	public String integerTrueValue(String operand) {
		// 操作数的真值。如果是负数，最左边为“-”；如果是正数或 0，不需要符号位
		String[] split = operand.split("");
		int n = 1;
		long m = 0;
		String s = "";
		if (split[0].equals("1")) {
			s += "-";
			operand = ALU.BinaryComplement(operand);
		}
		String[] split1 = operand.split("");
		for (int j = split1.length - 2; j >= 0; j--) {
			n = n * 2;
			if (split1[j].equals("1")) {
				m = m + n;
			}
		}
		if (split1[split1.length - 1].equals("1")) {
			m = m + 1;
		}

		s = (s + m).toString();
		return s;
	}

	public String BinaryComplement(String s) {
		String s1 = "";
		s = ALU.negation(s);
		String[] split = s.split("");

		if (split[s.length() - 1].equals("0")) {
			split[s.length() - 1] = "1";
		} else if (split[s.length() - 1].equals("1")) {
			System.out.println(split[s.length() - 1]);
			for (int j = s.length() - 1; j >= 0; j--) {
				if (split[j].equals("1")) {
					split[j] = "0";

				} else {
					split[j] = "1";
					break;
				}
			}
		}

		for (int l = 0; l < s.length(); l++) {
			s1 = s1 + split[l];
		}
		return s1;
	}

	// 6
	public String floatTrueValue(String operand, int sLength, int eLength) {
		// 返回值：操作数的真值。如果是负数，最左边为“-”；如果是正数或 0，不需要符号 位。
		// 正负无穷分别表示为“+Inf”和“-Inf”，NaN 表示为“NaN”。
		assert sLength >= 8;
		assert eLength >= 8;
		int exponent = (int) Math.pow(2, eLength) - 1;
		int exponent1 = (int) Math.pow(2, eLength - 1) - 1;
		double d = 0;
		double d1 = 0.5;
		String s ;
		String s1 = ALU.integerTrueValue("0"+operand.substring(1, eLength + 1));// 指数部分
		String s2 = ALU.integerTrueValue("0"+operand.substring(eLength + 1,
				operand.length()));// 分数部分
		
		exponent1 = Integer.parseInt(s1) - exponent1;
		String s3 = "1" + operand.substring(eLength + 1, operand.length());//分数部份
		//System.out.println(exponent1); 
		if (operand.substring(0, 1).equals("1")) {
			s = "-";
		}else{
			s="+";
		}
		if (s1.equals("0")) {
			if (s2.equals("0")) {
				s = "0";
			} else {
				s = "NaN";
			}
		} else if (s1.equals((exponent + "").toString())) {
			if (s2.equals("0")) {
				s = s + "Inf";
			} else {
				s = "NaN";
			}
		} else {
			if(s.equals("+")){
				s="";
			}
			if (exponent1 >= 0) {
				int n = Integer.parseInt(ALU.integerTrueValue("0"+s3.substring(0,
						exponent1 + 1)));
				int n1 = s3.length() - exponent1 - 1;
				String[] split = s3.substring(exponent1 + 1, s3.length()).split("");
				for (int j = 0; j < n1; j++) {
					if (split[j].equals("1")) {
						d = d + d1;
					}
					d1 = d1 / 2;
				}
				s = s + Double.toString(d + n);
			} else {
				exponent1 = Math.abs(exponent1);
				s3 = ALU.rightLogShift(s3, exponent1 - 1);
				String[] split = s3.split("");
				for (int j = 0; j < s3.length(); j++) {
					if (split[j].equals("1")) {
						d = d + d1;
					}
					d1 = d1 / 2;
				}
				s = s + Double.toString(d);
			}
		}

			return s;
	}

	// 7
	public String negation(String operand) {
		String s = "";
		String[] split = operand.split("");
		for (int j = 0; j < split.length; j++) {
			switch (split[j]) {
			case "1":
				split[j] = "0";
				break;
			case "0":
				split[j] = "1";
				break;
			}
			s += split[j];
		}
		return s;
	}

	// 8
	public String leftShift(String operand, int n) {
		String s = "";
		String[] split = operand.split("");
		for (int j = n; j < split.length; j++) {
			s += split[j];
		}
		for (int k = split.length - n; k < split.length; k++) {
			s += "0";
		}

		return s;

	}

	// 9
	public String rightAriShift(String operand, int n) {
	// 返回值：右移的结果，高位补符号位。 
		String[] split = operand.split("");
		String s = "";
		for (int i = 0; i < n; i++) {
			s = s + split[0];
		}
		for (int l = 0; l < split.length - n; l++) {
			s += split[l];
		}
		return s;
	}

	// 10
	public String rightLogShift(String operand, int n) {
		String s = "";
		String[] split = operand.split("");
		for (int i = 0; i < n; i++) {
			s = s + "0";
		}
		for (int l = 0; l < split.length - n; l++) {
			s += split[l];
		}
		return s;
	}

	// 11
	public String fullAdder(char x, char y, char c) {
		String[] s = new String[2];
		int x1 = 0, y1 = 0, c1 = 0;
		int n = 0;
		for (int j = 0; j < 2; j++) {
			s[j] = "";
		}

		if (x == '1') {
			x1 = 1;
		}
		if (y == '1') {
			y1 = 1;
		}
		if (c == '1') {
			c1 = 1;
		}// transfer the char to integer

		if (x1 == y1) {
			s[0] = "0";
		} else {
			s[0] = "1";
		}
		n = Integer.parseInt(s[0]);
		if (n == c1) {
			s[0] = "0";
		} else {
			s[0] = "1";
		}
		if ((x1 & y1) == 1 || (x1 & c1) == 1 || (y1 & c1) == 1) {
			s[1] = "1";
		} else {
			s[1] = "0";
		}
		return s[0] + s[1];
	}

	// 12
	public String claAdder(String operand1, String operand2, char c) {
		String[] split1 = operand1.split("");
		String[] split2 = operand2.split("");
		int[] p = new int[8];
		int[] g = new int[8];
		int[] c1 = new int[9];
		char[] c2 = new char[9];
		char[] p1 = new char[9];
		char[] g1 = new char[9];
		int j = 1;
		String s = "";

		if (c == '1') {
			c1[0] = 1;
		} else {
			c1[0] = 0;
		}

		for (int i = 7; i >= 0; i--) {
			p[i] = (Integer.parseInt(split1[i]))
					| (Integer.parseInt(split2[i]));
			g[i] = (Integer.parseInt(split1[i]))
					& (Integer.parseInt(split2[i]));
			if (true) {
				c1[j] = g[i] | (p[i] & c1[j - 1]);
				j = j + 1;
			}

			if (c1[j - 2] == 0) {
				c2[j - 2] = '0';
			} else {
				c2[j - 2] = '1';
			}

			if (p[i] == 0) {
				p1[i] = '0';
			} else {
				p1[i] = '1';
			}

			if (g[i] == 0) {
				g1[i] = '0';
			} else {
				g1[i] = '1';
			}
			s += ALU.fullAdder(p1[i], g1[i], c2[j - 2]);
		}

		String[] split3 = s.split("");

		s = "";
		for (int m = 14; m >= 0; m = m - 2) {
			s += split3[m];
		}
		s += split3[15];
		return s;
	}

	// 13
	public String integerAddition(String operand1, String operand2, char c,
			int length) {
		// return length+1
		operand1 = ALU.IncreseString(operand1, length);
		operand2 = ALU.IncreseString(operand2, length);
		String[] split1 = operand1.split("");
		String[] split2 = operand2.split("");
		String s2 = "";
		String s3 = "0";
		int n = length / 8;

		if (length % 8 != 0) {
			for (int j = 8 - (length % 8); j > 0; j--) {
				if (split1[0].equals("0")) {
					operand1 = "0" + operand1;
				} else {
					operand1 = "1" + operand1;
				}

				if (split2[0].equals("0")) {
					operand2 = "0" + operand2;
				} else {
					operand2 = "1" + operand2;
				}
			}
			n = n + 1;
		}

		String[] Sub1 = new String[n];
		String[] Sub2 = new String[n];
		String[] s = new String[n];

		for (int j = n; j > 0; j--) {
			Sub1[j - 1] = operand1.substring(8 * j - 8, 8 * j);
			Sub2[j - 1] = operand2.substring(8 * j - 8, 8 * j);
			s[j - 1] = ALU.claAdder(Sub1[j - 1], Sub2[j - 1], c);

			if (s[j - 1].substring(8, 9).equals("0")) {
				c = '0';
			} else {
				c = '1';
			}
			s2 = s[j - 1].substring(0, 8) + s2;
		}
		s2 = s2.substring((8 * n) - length, 8 * n);

		if (split1[0].equals("0") && split2[0].equals("0")) {
			if (s2.substring(0, 1).equals("1")) {
				s3 = "1";
			}
		} else if (split1[0].equals("1") && split2[0].equals("1")) {
			if (s2.substring(0, 1).equals("0")) {
				s3 = "1";
			}
		}
		s2 = s2 + s3;
		return s2;
	}

	// 14
	public String integerSubtraction(String operand1, String operand2,
			int length) {
		// return length+1
		operand1 = ALU.IncreseString(operand1, length);
		operand2 = ALU.IncreseString(operand2, length);
		operand2 = ALU.BinaryComplement(operand2);
		String s = ALU.integerAddition(operand1, operand2, '0', length);
		return s;
	}

	// 15
	public String integerMultiplication(String operand1, String operand2,
			int length) {
		operand1 = ALU.IncreseString(operand1, length);
		operand2 = ALU.IncreseString(operand2, length);
		String s = "";
		String[] split2 = operand2.split("");
		String s1 = ALU.BinaryComplement(operand1);
		int[] n = new int[length + 1];
		n[length] = 0;

		for (int j = 0; j < length; j++) {
			n[j] = Integer.parseInt(split2[j]);
			s += "0";
		}
		s = s + operand2;
		for (int j = length; j > 0; j--) {
			if (n[j] - n[j - 1] == 1) {
				s = (ALU.integerAddition(operand1, s.substring(0, length), '0',
						length)).substring(0, length)
						+ s.substring(length, 2 * length);
			} else if (n[j] - n[j - 1] == -1) {
				s = (ALU.integerAddition(s1, s.substring(0, length), '0',
						length)).substring(0, length)
						+ s.substring(length, 2 * length);
			}
			s = ALU.rightAriShift(s, 1);
		}
		return s;
	}

	// 16
	public String integerDivision(String operand1, String operand2, int length) {
		operand1 = ALU.IncreseString(operand1, length);
		operand2 = ALU.IncreseString(operand2, length);
		String s = "";
		String remainder = "";

		int n1 = Integer.parseInt(ALU.integerTrueValue(operand1));
		int n2 = Integer.parseInt(ALU.integerTrueValue(operand2));
		String[] split1 = operand1.split("");

		for (int j = 0; j < length; j++) {
			operand1 = split1[0] + operand1;
		}
		// String[] split2=operand1.split("");
		// for(int j=0;j<2*length;j++){
		// quotient.add(split2[j]);
		// }
		// extend the operand1.length() to 2*length
		if (n1 == 0 && n2 != 0) {
			s = "0";
		} else if (n1 != 0 && n2 == 0) {
			s = "exception";
		} else if (n1 == 0 && n2 == 0) {
			s = "NaN";
		} else {

			for (int j = 1; j <= length; j++) {
				boolean isEnough = true;
				s = operand1.substring(1, 2 * length);
				remainder = s.substring(0, length);
				String[] split3 = remainder.split("");
				String[] split2 = operand2.split("");
				String s1 = "";

				if (split3[0].equals("0") && split2[0].equals("0")) {
					s1 = ALU.integerSubtraction(remainder, operand2,
							remainder.length());
					if (s1.substring(0, 1).equals("1")) {
						isEnough = false;
					}
				} else if (split3[0].equals("1") && split2[0].equals("1")) {
					s1 = ALU.integerSubtraction(remainder, operand2,
							remainder.length());
					if (s1.substring(0, 1).equals("0")) {
						isEnough = false;
					}
				} else if (split3[0].equals("1") && split2[0].equals("0")) {
					s1 = ALU.integerAddition(remainder, operand2, '0',
							remainder.length());
					if (s1.substring(0, 1).equals("0")) {
						isEnough = false;
					}
				} else if (split3[0].equals("0") && split2[0].equals("1")) {
					s1 = ALU.integerAddition(remainder, operand2, '0',
							remainder.length());
					if (s1.substring(0, 1).equals("1")) {
						isEnough = false;
					}
				}

				if (isEnough) {
					s = s1.substring(0, length)
							+ s.substring(length, 2 * length - 1) + "1";

				} else {
					s = s + "0";
				}
				operand1 = s;

			}// finish the for

			if (split1[0].equals(operand2.substring(0, 1))) {
				s = s.substring(length, 2 * length) + s.substring(0, length);
			} else {
				s = ALU.BinaryComplement(s.substring(length, 2 * length))
						+ s.substring(0, length);
			}

		}

		return s;// return quotient +remainder
	}

	// 17
	public String floatAddition(String operand1, String operand2, int sLength,
			int eLength, int gLength) {
		assert eLength>=8;assert sLength>=8;
		String[] s=new String[5];
		String f1=ALU.floatTrueValue(operand1, sLength, eLength);
		String f2=ALU.floatTrueValue(operand2, sLength, eLength);
		String sign1=operand1.substring(0, 1);//符号位
		String sign2=operand2.substring(0, 1);
		String dec1="1"+operand1.substring(1+eLength, 1+eLength+sLength);//尾数部分
		String dec2="1"+operand2.substring(1+eLength, 1+eLength+sLength);
		String s1=operand1.substring(1, eLength+1);//指数部分
		String s2=operand2.substring(1, eLength+1);//指数部分
		boolean isEnough=false;
		
		int exponent1=Integer.parseInt(ALU.integerTrueValue("0"+s1));//指数大小
		int exponent2=Integer.parseInt(ALU.integerTrueValue("0"+s2));
		int exponent3=exponent1;
		int exponent4=(int) (Math.pow(2, eLength)-1);
		//在分数后面加上保护位
		for(int j=0;j<gLength;j++){
			dec1=dec1+"0";
			dec2=dec2+"0";
		}
		 
		if(f1.equals("0")){
			s[0]=operand2+"0";
			return s[0];
		}else if (f2.equals("0")){
			s[0]=operand1+"0";
			return s[0];
		}else{
			
			
			if (exponent1 < exponent2) {
				exponent3 = exponent2;
				dec1 = ALU.rightLogShift(dec1, exponent2 - exponent1);
				if (integerTrueValue(dec1).equals("0")) {
					s[0] = operand2 + "0";
					return s[0];
				}
			}
			else if (exponent1 > exponent2) {
				dec2 = ALU.rightLogShift(dec2, exponent1 - exponent2);
				if (integerTrueValue(dec2).equals("0")) {
					s[0] = operand1 + "0";
					return s[0];
				}
			}
			if (sign1.equals(sign2)) {
				isEnough = true;
				s[1] = ALU.integerAddition(dec1, dec2, '0', dec1.length());
			} else {
				dec2 = ALU.BinaryComplement(dec2);
				s[1] = ALU.integerAddition(dec1, dec2, '0', dec1.length());
				if (s[1].substring(s[1].length() - 1, s[1].length())
						.equals("0")) {
					// 没进位，符号与被减数相反
					sign1 = sign2;
				}
			}//判断符号结束
			
			if (integerTrueValue(s[1].substring(0, s[1].length() - 1)).equals(
					"0")) {
				s[0] = floatRepresentation("0", sLength, eLength) + "0";
				return s[0];
			} else {// 尾数不等于0
				if (s[1].substring(s[1].length() - 1, s[1].length())
							.equals("1")) {
						// 尾数溢出,符号相同时才执行
					if (isEnough) {
						s[1] = ALU.rightLogShift(s[1], 1);
						exponent3 = exponent3 + 1;
						if (exponent3 >= exponent4) {
							// 阶码上溢出
							s[2] = "";
							s[3] = "";
							for (int j = 0; j < eLength; j++) {
								s[2] = s[2] + "1";
							}
							for (int j = 0; j < sLength; j++) {
								s[3] = s[3] + "0";
							}
							s[0] = sign1 + s[2] + s[3] + "1";
							return s[0];
						} else{
							// 阶码不溢出
							String[] split = s[1].split("");
							for (int j = 0; j < split.length; j++) {
								if (split[j].equals(1)) {
									s[1] = ALU.leftShift(s[1], j);
									exponent3 = exponent3 - j;
									break;
								}
							}// close the for

							if (exponent3 == 0) {
								// 阶码下溢
								s[2] = "";
								s[3] = "";
								for (int j = 0; j < eLength; j++) {
									s[2] = s[2] + "0";
								}
								for (int j = 0; j < sLength; j++) {
									s[3] = s[3] + "0";
								}
								s[0] = sign1 + s[2] + s[3] + "1";
								return s[0];
							} else {
								s[2] = ALU.integerRepresentation(
										(exponent3 + "").toString(),
										eLength + 1).substring(1, eLength + 1);
								s[0] = sign1 + s[2]
										+ s[1].substring(1, sLength + 1) + "0";
								return s[0];
							}
						}
					}
				}//执行尾数溢出的操作，结束	
					
				// 尾数不上溢，结果是否规格化
				String[] split = s[1].split("");
				for (int j = 0; j < split.length; j++) {
					if (split[j].equals(1)) {
						s[1] = ALU.leftShift(s[1], j);
						exponent3 = exponent3 - j;
						break;
					}
				}// close the for

				if (exponent3 == 0) {
					// 阶码下溢
					s[2] = "";
					s[3] = "";
					for (int j = 0; j < eLength; j++) {
						s[2] = s[2] + "0";
					}
					for (int j = 0; j < sLength; j++) {
						s[3] = s[3] + "0";
					}
					s[0] = sign1 + s[2] + s[3] + "1";
					return s[0];
				} else {
					s[2] = ALU.integerRepresentation(
							(exponent3 + "").toString(), eLength + 1);
					s[0] = sign1 + s[2].substring(1, eLength + 1)
							+ s[1].substring(1, sLength + 1) + "0";
					return s[0];
				}

			}
		}
	}

	// 18
	
	public String floatSubtraction(String operand1, String operand2,
			int sLength, int eLength, int gLength) {
		String s="";
		String [] str=operand2.split("");
		if(str[0].equals("0")){
			str[0]="1";
		}else{
			str[0]="0";
		}
		for(int j=0;j<str.length;j++){
			s=s+str[j];
		}
		s=floatAddition(operand1, s, sLength, eLength, gLength);
		return s;
	}

	// 19
	
	//19
	
	public String floatMultiplication(String operand1, String operand2,
			int sLength, int eLength) {
		String[] str1=operand1.split("");
		String[] str2=operand2.split("");
		String[] s=new String[5];
		String dec1="1"+operand1.substring(1+eLength, 1+eLength+sLength);//尾数部分
		String dec2="1"+operand2.substring(1+eLength, 1+eLength+sLength);
		String s1=operand1.substring(1, eLength+1);//指数部分
		String s2=operand2.substring(1, eLength+1);//指数部分
		for(int j=0;j<5;j++){
			s[j]="";
		}
		
		int exponent1=Integer.parseInt(ALU.integerTrueValue("0"+s1));//指数大小
		int exponent2=Integer.parseInt(ALU.integerTrueValue("0"+s2));
		int exponent3=(int) (Math.pow(2, eLength)-1);//最大指数
		int exponent4=(int) (Math.pow(2, eLength-1)-1);;
		//符号位
		if(str1[0].equals(str2[0])){
			s[0]="0";
		}else{
			s[0]="1";
		}
		String x=ALU.floatTrueValue(operand1, sLength, eLength);
		String y=ALU.floatTrueValue(operand1, sLength, eLength);
		if(x.equals("0")){
			s[0]=operand1;
		}else if(y.equals("0")){
			s[0]=operand2;
		}else{
			exponent4=exponent1+exponent2-exponent4;
			System.out.println(exponent1);
			System.out.println(exponent2);
			System.out.println(exponent4);
			if(exponent4>exponent3){
			//阶码上溢
				s[0]=s[0]+integerRepresentation("-1", eLength)+
					integerRepresentation("0", sLength);
			}
			else if(exponent4<0){
			////阶码下溢
				s[0]=s[0]+integerRepresentation("0", eLength)+
							integerRepresentation("0", sLength);		
				}
			else{
			//尾数相乘
				s[1]=integerRepresentation("0", 2*dec2.length());
				String[] split=dec2.split("");
		
				for(int j=dec2.length()-1;j>=0;j--){
					if(split[j].equals("1")){
						s[3]=integerAddition(s[1].substring(0, dec1.length()), dec1, '0',
								dec1.length()).substring(0, dec1.length());
						s[1]=s[3]+s[1].substring(dec1.length(), dec1.length()*2);
					}
					s[1]=ALU.rightLogShift(s[1], 1);
					
				}
			}
		//	System.out.println(s[1]);
			//判断是否规格化
			String[] split1=s[1].split("");
			for(int j=0;j<sLength;j++){
				if(split1[j].equals("1")){
				//	exponent4=exponent4-j;
					s[1]=ALU.leftShift(s[1], j+1);
					break;
				}
			}
		//	System.out.println(exponent4);
			String[] str=s[1].split("");	
				if(str[sLength].equals("1")){
					for(int j=sLength -1;j>=0;j--){
						if(str[j].equals("0")){
							str[j]="1";
							break;
						}else{
							str[j]="0";
						}
					}
				}
			
			for(int j=0;j<str.length;j++){
				s[4]=s[4]+str[j];
			}
			
		}
		s[0]=s[0]+ALU.integerRepresentation(""+exponent4, eLength+1)
				.substring(1, eLength+1)+s[4].substring(0, sLength);
		return s[0];
	}

	// 20
	
	//20
	
	public String floatDivision(String operand1, String operand2, int sLength,
			int eLength) {
		String f1=ALU.floatTrueValue(operand1, sLength, eLength);
		String f2=ALU.floatTrueValue(operand2, sLength, eLength);
		String[] str1=operand1.split("");
		String[] str2=operand2.split("");
		String[] s=new String[5];
		String dec1="1"+operand1.substring(1+eLength, 1+eLength+sLength);//尾数部分
		String dec2="1"+operand2.substring(1+eLength, 1+eLength+sLength);
		String s1=operand1.substring(1, eLength+1);//指数部分
		String s2=operand2.substring(1, eLength+1);//指数部分
		for(int j=0;j<5;j++){
			s[j]="";
		}
		
		int exponent1=Integer.parseInt(ALU.integerTrueValue("0"+s1));//指数大小
		int exponent2=Integer.parseInt(ALU.integerTrueValue("0"+s2));
		int exponent3=(int) (Math.pow(2, eLength)-1);//最大指数
		int exponent4=(int) (Math.pow(2, eLength-1)-1);;
		//	//判断符号
		if(str1[0].equals(str2[0])){
			s[0]="0";
		}else{
			s[0]="1";
		}
		
		if ( f2 .equals("0")) {
			s[0]=s[0]+ALU.floatRepresentation("Inf", sLength, eLength).
					substring(1, sLength+eLength+1);
			return s[0];
		
		} else if(f1.equals("0")){
			s[0]=ALU.floatRepresentation("0", sLength, eLength);
			return s[0];
		}else{
			exponent4=exponent1-exponent2+exponent4;
			
			if(exponent4>exponent3){
				//阶码上溢
					s[0]=s[0]+integerRepresentation("-1", eLength)+
						integerRepresentation("0", sLength);
				}
				else if(exponent4<0){
				//阶码下溢
					s[0]=s[0]+integerRepresentation("0", eLength)+
								integerRepresentation("0", sLength);		
					}
				else{
			
				//尾数相除
					s[1]=ALU.integerDivision(dec1, dec2,dec1.length());
					s[1]=s[1].substring(dec1.length(), 2*dec1.length());
					String[] split1=s[1].split("");
					for(int j=0;j<sLength;j++){
						if(split1[j].equals("1")){
						//	exponent4=exponent4-j;
							s[1]=ALU.leftShift(s[1], j+1);
							break;
						}
					}
				//	System.out.println(exponent4);
					String[] str=s[1].split("");	
						if(str[sLength].equals("1")){
							for(int j=sLength -1;j>=0;j--){
								if(str[j].equals("0")){
									str[j]="1";
									break;
								}else{
									str[j]="0";
								}
							}
						}
					
					for(int j=0;j<str.length;j++){
						s[4]=s[4]+str[j];
					}
					
				}
		//	System.out.println(dec1);
		//	System.out.println(dec2);
				s[0]=s[0]+ALU.integerRepresentation(""+exponent4, eLength+1)
						.substring(1, eLength+1)+s[4].substring(0, sLength);
				return s[0];
			}
	}

	public boolean IsEnough(String remainder, String divisor) {
		boolean isEnough = true;
		String[] split1 = remainder.split("");
		String[] split2 = divisor.split("");
		String s = "";

		if (split1[0].equals("0") && split2[0].equals("0")) {
			s = ALU.integerSubtraction(remainder, divisor, remainder.length());
			if (s.substring(0, 1).equals("1")) {
				isEnough = false;
			}
		} else if (split1[0].equals("1") && split2[0].equals("1")) {
			s = ALU.integerSubtraction(remainder, divisor, remainder.length());
			if (s.substring(0, 1).equals("0")) {
				isEnough = false;
			}
		} else if (split1[0].equals("1") && split2[0].equals("0")) {
			s = ALU.integerAddition(remainder, divisor, '0', remainder.length());
			if (s.substring(0, 1).equals("0")) {
				isEnough = false;
			}
		} else if (split1[0].equals("0") && split2[0].equals("1")) {
			s = ALU.integerAddition(remainder, divisor, '0', remainder.length());
			if (s.substring(0, 1).equals("1")) {
				isEnough = false;
			}
		}

		return isEnough;

	}

	public String IncreseString(String operand1, int length) {
		String[] split = operand1.split("");
		String s = operand1;
		if (split.length < length) {

			for (int j = split.length; j < length; j++) {
				if (split[0].equals("1")) {
					s = "1" + s;
				} else {
					s = "0" + s;
				}
			}
		}

		return s;

	}
}
