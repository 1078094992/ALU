
public class ALU {
	public enum Operation {
		ADDITION, 
		SUBTRACTION, 
		MULTIPLICATION, 
		DIVISION
	};
	public enum Type {
		INTEGER, 		
		FLOAT, 
		DECIMAL
	};

public static String Complement(String number, int length) {
		int n = Integer.parseInt(number);
		String s = "";
		String s1 = "";

		int k = Math.abs(n);
		do {
			if (k % 2 == 0) {
				s = "0"+s;
			} else {
				s = "1"+s;
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
public static String IntegerTrueValue (String operand){
	String[] split=operand.split("");
	int n=1;
	int m=0;
	String s="";
	if(split[0].equals("1")){
		s+="-";
		operand=ALU.BinaryComplement(operand);
	}
	String[] split1=operand.split("");
	for(int j=split1.length-2;j>=0;j--){
		n=n*2;
		if(split1[j].equals("1")){
			m=m+n;
		}
	}
	if(split1[split1.length-1].equals("1")){
		m=m+1;
	}

	s=s+Integer.toString(m);
	return s;
	}
public static String rightLogShift(String operand, int n) {
		String s = "";
		String[] split = operand.split("");
		for (int i = 0; i < n; i++) {
			s = s + "0";
		}
		for (int l = 0; l < split.length-n; l++) {
			s += split[l];
		}
		return s;
	}
public static String FullAdder (char x, char y, char c){
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
		}//transfer the char to integer
		
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
public static String rightAriShift(String operand, int n) {
	String[] split=operand.split("");
	String s="";
	for (int i = 0; i < n; i++) {
		s = s + split[0];
	}
	for (int l = 0; l < split.length-n; l++) {
		s += split[l];
	}
	return s;
	
	
}
public static String leftShift(String operand, int n) {
	String s="";
	String[] split=operand.split("");
	for(int j=n;j<split.length;j++){
		s+=split[j];
	}
	for(int k=split.length-n;k<split.length;k++){
		s+="0";
	}
	
	return s;
	
}
public static String negation(String operand) {
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

public static String claAdder(String operand1, String operand2, char c) {
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
			s += ALU.FullAdder(p1[i], g1[i], c2[j - 2]);
		}

		String[] split3 = s.split("");

		s = "";
		for (int m = 14; m >= 0; m = m - 2) {
			s += split3[m];
		}
		s += split3[15];
		return s;

	}
//calculate the complement of a negative binary number
public static String BinaryComplement(String s){
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
public static  String IntegerAddition (String operand1, String operand2, char c, int length){ 
		//return length+1
		operand1=ALU.IncreseString(operand1, length);
		operand2=ALU.IncreseString(operand2, length);
		String[] split1 = operand1.split("");
		String[] split2 = operand2.split("");
		String s2 = "";
		String s3="0";
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
				s3= "1";
			} 
		}else if (split1[0].equals("1") && split2[0].equals("1")) {
			if (s2.substring(0, 1).equals("0")) {
				s3="1";
			} 
		}
		s2=s2+s3;
		return s2;
	}

public static String IntegerSubtraction (String operand1, String operand2, int length){
	//return length+1
	operand1=ALU.IncreseString(operand1, length);
	operand2=ALU.IncreseString(operand2, length);
	operand2=ALU.BinaryComplement(operand2);
	String s=ALU.IntegerAddition(operand1, operand2, '0', length);
	return s;
	}
public static String IntegerMultiplication (String operand1, String operand2, int length){
	operand1=ALU.IncreseString(operand1, length);
	operand2=ALU.IncreseString(operand2, length);
	String s="";
	String[] split2=operand2.split("");
	String s1=ALU.BinaryComplement(operand1);
	int[] n=new int[length+1];
	n[length]=0;
	
	for(int j=0;j<length;j++){
		n[j]=Integer.parseInt(split2[j]);
		s+="0";
	}
	s=s+operand2;
	for(int j=length;j>0;j--){
		if(n[j]-n[j-1]==1){
			s=(ALU.IntegerAddition(operand1, s.substring(0, length), '0', length)).substring(0, length)
				+s.substring(length, 2*length);
		}else if(n[j]-n[j-1]==-1){
			s=(ALU.IntegerAddition(s1, s.substring(0, length), '0', length)).substring(0, length)
					+s.substring(length, 2*length);
		}
		s=ALU.rightAriShift(s, 1);
	}
	return s;
	}
//when operand1.length() less than length,extend operand1.length() to length
public static String IncreseString(String operand1,int length){
	String[] split=operand1.split("");
	String s=operand1;
	if(split.length<length){
	
			for(int j=split.length;j<length;j++){
				if(split[0].equals("1")){
					s="1"+s;
			}else{
					s="0"+s;}
		}
	}
	
	return s;
	
}	
public static String IntegerDivision (String operand1, String operand2, int length){
		operand1 = ALU.IncreseString(operand1, length);
		operand2 = ALU.IncreseString(operand2, length);
		String s = "";
		String remainder = "";

		int n1 = Integer.parseInt(ALU.IntegerTrueValue(operand1));
		int n2 = Integer.parseInt(ALU.IntegerTrueValue(operand2));
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
				s = operand1.substring(1, 2 * length );
				remainder = s.substring(0, length);
				String[] split3 = remainder.split("");
				String[] split2 = operand2.split("");
				String s1 = "";

				if (split3[0].equals("0") && split2[0].equals("0")) {
					s1 = ALU.IntegerSubtraction(remainder, operand2,
							remainder.length());
					if (s1.substring(0, 1).equals("1")) {
						isEnough = false;
					}
				} else if (split3[0].equals("1") && split2[0].equals("1")) {
					s1 = ALU.IntegerSubtraction(remainder, operand2,
							remainder.length());
					if (s1.substring(0, 1).equals("0")) {
						isEnough = false;
					}
				} else if (split3[0].equals("1") && split2[0].equals("0")) {
					s1 = ALU.IntegerAddition(remainder, operand2, '0',
							remainder.length());
					if (s1.substring(0, 1).equals("0")) {
						isEnough = false;
					}
				} else if (split3[0].equals("0") && split2[0].equals("1")) {
					s1 = ALU.IntegerAddition(remainder, operand2, '0',
							remainder.length());
					if (s1.substring(0, 1).equals("1")) {
						isEnough = false;
					}
				}
		
				
				if (isEnough) {
					s = s1.substring(0, length)
							+ s.substring(length, 2 * length - 1)
							+ "1";
					
				} else {
					s = s + "0";
				}
					operand1=s;
				
			}// finish the for

			if (split1[0].equals(operand2.substring(0, 1))) {
				s=s.substring(length, 2*length)+s.substring(0, length);
			} else {
				s = ALU.BinaryComplement(s.substring(length, 2*length))+s.substring(0, length);
			}

		}

			return s;// return quotient +remainder
	}
	public String Calculation (String number1, String number2,Type type, Operation operation, int length){
		return null;
	}
// to judge whether remainder is large enough
public static boolean IsEnough(String remainder,String divisor){
	boolean isEnough=true;
	String[] split1=remainder.split(""); 
	String[] split2=divisor.split(""); 
	String s="";
	
	if(split1[0].equals("0")&&split2[0].equals("0")){
		s=ALU.IntegerSubtraction(remainder, divisor, remainder.length());
		if(s.substring(0, 1).equals("1")){
			isEnough=false;
		}
	}else if(split1[0].equals("1")&&split2[0].equals("1")){
		s=ALU.IntegerSubtraction(remainder, divisor, remainder.length());
		if(s.substring(0, 1).equals("0")){
			isEnough=false;
		}
	}else if(split1[0].equals("1")&&split2[0].equals("0")){
		s=ALU.IntegerAddition(remainder, divisor, '0', remainder.length());
		if(s.substring(0, 1).equals("0")){
			isEnough=false;
		}
	}else if(split1[0].equals("0")&&split2[0].equals("1")){
		s=ALU.IntegerAddition(remainder, divisor, '0', remainder.length());
		if(s.substring(0, 1).equals("1")){
			isEnough=false;
		}
	}
	
	return isEnough;
	
}	
	
public static void main(String[] args){
	//	String s=ALU.claAdder("10000001", "10011111", '1');
	//	String s=ALU.FullAdder('1', '1', '0');
	//	String s=ALU.BinaryComplement("1001");
	//	String s=ALU.Complement("-16", 8);
	//	String s=ALU.IntegerTrueValue("0");
	//	String s=ALU.IntegerAddition("0011","1000", '0', 4);
	//	String s=ALU.claAdder("10001111", "10000001", '0');
	//	String s=ALU.IntegerSubtraction("0000000000000001", "0000000000000010", 16);
	//	String s=ALU.IntegerMultiplication("1001", "1010", 4);
	//	String s=ALU.IncreseString("100000011", 15);
	//	boolean s=ALU.IsEnough("1110", "0011");
		String s=ALU.IntegerDivision("1001", "0011", 4);//bug
		System.out.print(s);
		
	}
}
