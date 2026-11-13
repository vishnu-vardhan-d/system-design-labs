package io.vishnuvardhan.urlshortener;

public class Base62 {
	
	static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	static String encode(long n) {
        StringBuilder code = new StringBuilder();
        do {
        System.out.println("--------------BEGIN--------------------- ");
        	int remainder = (int) (n%62);
        	int dividedBy = (int) (n/62);
        	char digitIndex = DIGITS.charAt(remainder);
        	System.out.println("n: "+n+" remainder: "+remainder+" dividedBy: "+dividedBy+" digitIndex: "+digitIndex);
        	code.append(digitIndex);
        	n = dividedBy;
        	 System.out.println("------------END----------------------- ");
        	
        } while (n > 0);
        
        return code.reverse().toString();
    }

    static long decode(String code) {
        long n = 0;
        for (char c : code.toCharArray()) {
            n = n * 62 + DIGITS.indexOf(c);
        }
        return n;
    }

}
