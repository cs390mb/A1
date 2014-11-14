package edu.umass.cs.client;

public class SpeechDetector {

	  public static double classify(Object[] i) {
		    double [] sums = new double [2];
		    sums[(int) SpeechDetector_0.classify(i)] += 2.4389969484839225;
		    sums[(int) SpeechDetector_1.classify(i)] += 2.0838049028904218;
		    sums[(int) SpeechDetector_2.classify(i)] += 1.4157116194698511;
		    sums[(int) SpeechDetector_3.classify(i)] += 1.5654292551949422;
		    sums[(int) SpeechDetector_4.classify(i)] += 1.7057062275249277;
		    sums[(int) SpeechDetector_5.classify(i)] += 0.8395253386088793;
		    sums[(int) SpeechDetector_6.classify(i)] += 1.6059426004061517;
		    sums[(int) SpeechDetector_7.classify(i)] += 0.7971049363136686;
		    sums[(int) SpeechDetector_8.classify(i)] += 0.9947522633656506;
		    sums[(int) SpeechDetector_9.classify(i)] += 1.1573418574786276;
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
		    /* mfcc1 */
		    if (i[0] == null) { return 1; } else if (((Double)i[0]).doubleValue() <= 95.44919117330878) { return 0; } else { return 1; }
		  }
		}
		class SpeechDetector_1 {
		  public static double classify(Object[] i) {
		    /* mfcc2 */
		    if (i[1] == null) { return 1; } else if (((Double)i[1]).doubleValue() <= 4.0901803514289945) { return 1; } else { return 0; }
		  }
		}
		class SpeechDetector_2 {
		  public static double classify(Object[] i) {
		    /* mfcc1 */
		    if (i[0] == null) { return 0; } else if (((Double)i[0]).doubleValue() <= 31.363409796164483) { return 1; } else { return 0; }
		  }
		}
		class SpeechDetector_3 {
		  public static double classify(Object[] i) {
		    /* mfcc5 */
		    if (i[4] == null) { return 1; } else if (((Double)i[4]).doubleValue() <= -2.4699833618034486) { return 0; } else { return 1; }
		  }
		}
		class SpeechDetector_4 {
		  public static double classify(Object[] i) {
		    /* mfcc1 */
		    if (i[0] == null) { return 0; } else if (((Double)i[0]).doubleValue() <= 94.0112696525685) { return 0; } else { return 1; }
		  }
		}
		class SpeechDetector_5 {
		  public static double classify(Object[] i) {
		    /* mfcc2 */
		    if (i[1] == null) { return 1; } else if (((Double)i[1]).doubleValue() <= -7.722713732732339) { return 1; } else { return 0; }
		  }
		}
		class SpeechDetector_6 {
		  public static double classify(Object[] i) {
		    /* mfcc11 */
		    if (i[10] == null) { return 1; } else if (((Double)i[10]).doubleValue() <= -2.548829568571599) { return 0; } else { return 1; }
		  }
		}
		class SpeechDetector_7 {
		  public static double classify(Object[] i) {
		    /* mfcc1 */
		    if (i[0] == null) { return 0; } else if (((Double)i[0]).doubleValue() <= 31.363409796164483) { return 1; } else { return 0; }
		  }
		}
		class SpeechDetector_8 {
		  public static double classify(Object[] i) {
		    /* mfcc3 */
		    if (i[2] == null) { return 1; } else if (((Double)i[2]).doubleValue() <= -2.3032514269326465) { return 0; } else { return 1; }
		  }
		}
		class SpeechDetector_9 {
		  public static double classify(Object[] i) {
		    /* mfcc1 */
		    if (i[0] == null) { return 0; } else if (((Double)i[0]).doubleValue() <= 94.0112696525685) { return 0; } else { return 1; }
		  }
		}	
	
//	  public static double classify(Object[] i)
//	    throws Exception {
//
//	    double p = Double.NaN;
//	    p = SpeechDetector.N4db6bcac0(i);
//	    return p;
//	  }
//	  static double N4db6bcac0(Object []i) {
//	    double p = Double.NaN;
//	    if (i[0] == null) {
//	      p = 0;
//	    } else if (((Double) i[0]).doubleValue() <= 95.36548369342854) {
//	    p = SpeechDetector.N181ce8101(i);
//	    } else if (((Double) i[0]).doubleValue() > 95.36548369342854) {
//	    p = SpeechDetector.N207a010b6(i);
//	    } 
//	    return p;
//	  }
//	  static double N181ce8101(Object []i) {
//	    double p = Double.NaN;
//	    if (i[0] == null) {
//	      p = 1;
//	    } else if (((Double) i[0]).doubleValue() <= 17.521677119296452) {
//	      p = 1;
//	    } else if (((Double) i[0]).doubleValue() > 17.521677119296452) {
//	    p = SpeechDetector.N11c77ece2(i);
//	    } 
//	    return p;
//	  }
//	  static double N11c77ece2(Object []i) {
//	    double p = Double.NaN;
//	    if (i[0] == null) {
//	      p = 0;
//	    } else if (((Double) i[0]).doubleValue() <= 93.99540545704477) {
//	    p = SpeechDetector.N40560f803(i);
//	    } else if (((Double) i[0]).doubleValue() > 93.99540545704477) {
//	    p = SpeechDetector.Ndfe06f25(i);
//	    } 
//	    return p;
//	  }
//	  static double N40560f803(Object []i) {
//	    double p = Double.NaN;
//	    if (i[0] == null) {
//	      p = 0;
//	    } else if (((Double) i[0]).doubleValue() <= 54.86936715716707) {
//	    p = SpeechDetector.N5213bd7f4(i);
//	    } else if (((Double) i[0]).doubleValue() > 54.86936715716707) {
//	      p = 0;
//	    } 
//	    return p;
//	  }
//	  static double N5213bd7f4(Object []i) {
//	    double p = Double.NaN;
//	    if (i[0] == null) {
//	      p = 0;
//	    } else if (((Double) i[0]).doubleValue() <= 54.82694227707181) {
//	      p = 0;
//	    } else if (((Double) i[0]).doubleValue() > 54.82694227707181) {
//	      p = 1;
//	    } 
//	    return p;
//	  }
//	  static double Ndfe06f25(Object []i) {
//	    double p = Double.NaN;
//	    if (i[1] == null) {
//	      p = 1;
//	    } else if (((Double) i[1]).doubleValue() <= 3.3381363602532312) {
//	      p = 1;
//	    } else if (((Double) i[1]).doubleValue() > 3.3381363602532312) {
//	      p = 0;
//	    } 
//	    return p;
//	  }
//	  static double N207a010b6(Object []i) {
//	    double p = Double.NaN;
//	    if (i[2] == null) {
//	      p = 0;
//	    } else if (((Double) i[2]).doubleValue() <= -3.5447243660319545) {
//	      p = 0;
//	    } else if (((Double) i[2]).doubleValue() > -3.5447243660319545) {
//	    p = SpeechDetector.N29da89d57(i);
//	    } 
//	    return p;
//	  }
//	  static double N29da89d57(Object []i) {
//	    double p = Double.NaN;
//	    if (i[7] == null) {
//	      p = 1;
//	    } else if (((Double) i[7]).doubleValue() <= 1.9033402909648842) {
//	      p = 1;
//	    } else if (((Double) i[7]).doubleValue() > 1.9033402909648842) {
//	    p = SpeechDetector.N4a8f146a8(i);
//	    } 
//	    return p;
//	  }
//	  static double N4a8f146a8(Object []i) {
//	    double p = Double.NaN;
//	    if (i[1] == null) {
//	      p = 0;
//	    } else if (((Double) i[1]).doubleValue() <= 1.444530199325155) {
//	      p = 0;
//	    } else if (((Double) i[1]).doubleValue() > 1.444530199325155) {
//	      p = 1;
//	    } 
//	    return p;
//	  }
//	}
//
