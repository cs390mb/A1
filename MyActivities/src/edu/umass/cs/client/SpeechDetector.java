package edu.umass.cs.client;

public class SpeechDetector {

	  public static double classify(Object[] i) {
	    double [] sums = new double [2];
	    sums[(int) SpeechDetector_0.classify(i)] += 2.2487000044990664;
	    sums[(int) SpeechDetector_1.classify(i)] += 1.84147671915979;
	    sums[(int) SpeechDetector_2.classify(i)] += 1.8054876846342034;
	    sums[(int) SpeechDetector_3.classify(i)] += 1.3475430887111046;
	    sums[(int) SpeechDetector_4.classify(i)] += 0.8947150100972475;
	    sums[(int) SpeechDetector_5.classify(i)] += 1.1026658260761804;
	    sums[(int) SpeechDetector_6.classify(i)] += 0.6946645643390655;
	    sums[(int) SpeechDetector_7.classify(i)] += 1.006182312395212;
	    sums[(int) SpeechDetector_8.classify(i)] += 0.9071663141808212;
	    sums[(int) SpeechDetector_9.classify(i)] += 1.0757704346770716;
	    double maxV = sums[0];
	    int maxI = 0;
	    for (int j = 1; j < 2; j++) {
	      if (sums[j] > maxV) { maxV = sums[j]; maxI = j; }
	    }
	    return (double) maxI;
	  }
	}
	class SpeechDetector_0 {
	  public static double classify(Object[] i) {
	    /* mfcc2 */
	    if (i[1] == null) { return 1; } else if (((Double)i[1]).doubleValue() <= 3.449737443093722) { return 1; } else { return 0; }
	  }
	}
	class SpeechDetector_1 {
	  public static double classify(Object[] i) {
	    /* mfcc5 */
	    if (i[4] == null) { return 0; } else if (((Double)i[4]).doubleValue() <= -1.2934940412612834) { return 0; } else { return 1; }
	  }
	}
	class SpeechDetector_2 {
	  public static double classify(Object[] i) {
	    /* mfcc1 */
	    if (i[0] == null) { return 0; } else if (((Double)i[0]).doubleValue() <= 95.59751226048644) { return 0; } else { return 1; }
	  }
	}
	class SpeechDetector_3 {
	  public static double classify(Object[] i) {
	    /* mfcc4 */
	    if (i[3] == null) { return 0; } else if (((Double)i[3]).doubleValue() <= -1.753571697543412) { return 0; } else { return 1; }
	  }
	}
	class SpeechDetector_4 {
	  public static double classify(Object[] i) {
	    /* mfcc1 */
	    if (i[0] == null) { return 0; } else if (((Double)i[0]).doubleValue() <= 73.87131239120947) { return 0; } else { return 0; }
	  }
	}
	class SpeechDetector_5 {
	  public static double classify(Object[] i) {
	    /* mfcc1 */
	    if (i[0] == null) { return 1; } else if (((Double)i[0]).doubleValue() <= 73.87131239120947) { return 0; } else { return 1; }
	  }
	}
	class SpeechDetector_6 {
	  public static double classify(Object[] i) {
	    /* mfcc3 */
	    if (i[2] == null) { return 0; } else if (((Double)i[2]).doubleValue() <= -0.19470117233148784) { return 0; } else { return 0; }
	  }
	}
	class SpeechDetector_7 {
	  public static double classify(Object[] i) {
	    /* mfcc3 */
	    if (i[2] == null) { return 1; } else if (((Double)i[2]).doubleValue() <= -0.19470117233148784) { return 0; } else { return 1; }
	  }
	}
	class SpeechDetector_8 {
	  public static double classify(Object[] i) {
	    /* mfcc7 */
	    if (i[6] == null) { return 0; } else if (((Double)i[6]).doubleValue() <= -2.3827994908090986) { return 0; } else { return 1; }
	  }
	}
	class SpeechDetector_9 {
	  public static double classify(Object[] i) {
	    /* mfcc5 */
	    if (i[4] == null) { return 0; } else if (((Double)i[4]).doubleValue() <= -1.9793102353087986) { return 0; } else { return 0; }
	  }
	}
	
//		  public static double classify(Object[] i)
//		    throws Exception {
//
//		    double p = Double.NaN;
//		    p = SpeechDetector.N5f097d4c0(i);
//		    return p;
//		  }
//		  static double N5f097d4c0(Object []i) {
//		    double p = Double.NaN;
//		    if (i[1] == null) {
//		      p = 1;
//		    } else if (((Double) i[1]).doubleValue() <= 3.416972722489125) {
//		    p = SpeechDetector.N680139521(i);
//		    } else if (((Double) i[1]).doubleValue() > 3.416972722489125) {
//		    p = SpeechDetector.N552fae2e9(i);
//		    } 
//		    return p;
//		  }
//		  static double N680139521(Object []i) {
//		    double p = Double.NaN;
//		    if (i[4] == null) {
//		      p = 0;
//		    } else if (((Double) i[4]).doubleValue() <= -1.322155680864438) {
//		    p = SpeechDetector.N285a6bd62(i);
//		    } else if (((Double) i[4]).doubleValue() > -1.322155680864438) {
//		    p = SpeechDetector.N6990e90a5(i);
//		    } 
//		    return p;
//		  }
//		  static double N285a6bd62(Object []i) {
//		    double p = Double.NaN;
//		    if (i[4] == null) {
//		      p = 0;
//		    } else if (((Double) i[4]).doubleValue() <= -2.710063597441838) {
//		      p = 0;
//		    } else if (((Double) i[4]).doubleValue() > -2.710063597441838) {
//		    p = SpeechDetector.N485dc34c3(i);
//		    } 
//		    return p;
//		  }
//		  static double N485dc34c3(Object []i) {
//		    double p = Double.NaN;
//		    if (i[0] == null) {
//		      p = 0;
//		    } else if (((Double) i[0]).doubleValue() <= 71.79289742833795) {
//		      p = 0;
//		    } else if (((Double) i[0]).doubleValue() > 71.79289742833795) {
//		    p = SpeechDetector.N3c0d7b24(i);
//		    } 
//		    return p;
//		  }
//		  static double N3c0d7b24(Object []i) {
//		    double p = Double.NaN;
//		    if (i[1] == null) {
//		      p = 1;
//		    } else if (((Double) i[1]).doubleValue() <= 2.7213352628188696) {
//		      p = 1;
//		    } else if (((Double) i[1]).doubleValue() > 2.7213352628188696) {
//		      p = 0;
//		    } 
//		    return p;
//		  }
//		  static double N6990e90a5(Object []i) {
//		    double p = Double.NaN;
//		    if (i[0] == null) {
//		      p = 0;
//		    } else if (((Double) i[0]).doubleValue() <= 71.79289742833795) {
//		      p = 0;
//		    } else if (((Double) i[0]).doubleValue() > 71.79289742833795) {
//		    p = SpeechDetector.N28b3c5ab6(i);
//		    } 
//		    return p;
//		  }
//		  static double N28b3c5ab6(Object []i) {
//		    double p = Double.NaN;
//		    if (i[2] == null) {
//		      p = 0;
//		    } else if (((Double) i[2]).doubleValue() <= -0.7168356975970223) {
//		      p = 0;
//		    } else if (((Double) i[2]).doubleValue() > -0.7168356975970223) {
//		    p = SpeechDetector.N3276726d7(i);
//		    } 
//		    return p;
//		  }
//		  static double N3276726d7(Object []i) {
//		    double p = Double.NaN;
//		    if (i[6] == null) {
//		      p = 1;
//		    } else if (((Double) i[6]).doubleValue() <= -2.56395532927719) {
//		    p = SpeechDetector.N7e526bfc8(i);
//		    } else if (((Double) i[6]).doubleValue() > -2.56395532927719) {
//		      p = 1;
//		    } 
//		    return p;
//		  }
//		  static double N7e526bfc8(Object []i) {
//		    double p = Double.NaN;
//		    if (i[6] == null) {
//		      p = 1;
//		    } else if (((Double) i[6]).doubleValue() <= -2.7114966409858896) {
//		      p = 1;
//		    } else if (((Double) i[6]).doubleValue() > -2.7114966409858896) {
//		      p = 0;
//		    } 
//		    return p;
//		  }
//		  static double N552fae2e9(Object []i) {
//		    double p = Double.NaN;
//		    if (i[0] == null) {
//		      p = 0;
//		    } else if (((Double) i[0]).doubleValue() <= 99.6803022027993) {
//		      p = 0;
//		    } else if (((Double) i[0]).doubleValue() > 99.6803022027993) {
//		      p = 1;
//		    } 
//		    return p;
//		  }
//		}
