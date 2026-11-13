package io.vishnuvardhan.urlshortener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Base62Test {
	
	static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	  static long decode(String code) {
	        long n = 0;
	        for (char c : code.toCharArray()) {
	            n = n * 62 + DIGITS.indexOf(c);
	        }
	        return n;
	    }

	
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
        
        System.out.println("code.reverse(): "+code.reverse());
        return code.reverse().toString();
    }

    @Test
    void encode() {
    	
    	String encoded =encode(1000);
    	System.out.println("Encoded------------: "+encoded);
    	
    	long decoded =decode("g8");
    	System.out.println("Encoded------------: "+decoded);
    	
       // assertThat(Base62.encode(0)).isEqualTo("0");
       // assertThat(Base62.encode(61)).isEqualTo("Z");
      //  assertThat(Base62.encode(62)).isEqualTo("10");
      //  assertThat(Base62.encode(1_000_000)).isEqualTo("4c92");
    }

   // @Test
    void decodeReversesEncode() {
        for (long n : new long[] {0, 61, 62, 1_000_000, 987_654_321_012L}) {
            assertThat(Base62.decode(Base62.encode(n))).isEqualTo(n);
        }
    }
}