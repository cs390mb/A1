package edu.umass.cs.client;

public class SpeechDetector {

	  public static double classify(Object[] i)
	    throws Exception {

	    double p = Double.NaN;
	    p = SpeechDetector.N4db6bcac0(i);
	    return p;
	  }
	  static double N4db6bcac0(Object []i) {
	    double p = Double.NaN;
	    if (i[0] == null) {
	      p = 0;
	    } else if (((Double) i[0]).doubleValue() <= 95.36548369342854) {
	    p = SpeechDetector.N181ce8101(i);
	    } else if (((Double) i[0]).doubleValue() > 95.36548369342854) {
	    p = SpeechDetector.N207a010b6(i);
	    } 
	    return p;
	  }
	  static double N181ce8101(Object []i) {
	    double p = Double.NaN;
	    if (i[0] == null) {
	      p = 1;
	    } else if (((Double) i[0]).doubleValue() <= 17.521677119296452) {
	      p = 1;
	    } else if (((Double) i[0]).doubleValue() > 17.521677119296452) {
	    p = SpeechDetector.N11c77ece2(i);
	    } 
	    return p;
	  }
	  static double N11c77ece2(Object []i) {
	    double p = Double.NaN;
	    if (i[0] == null) {
	      p = 0;
	    } else if (((Double) i[0]).doubleValue() <= 93.99540545704477) {
	    p = SpeechDetector.N40560f803(i);
	    } else if (((Double) i[0]).doubleValue() > 93.99540545704477) {
	    p = SpeechDetector.Ndfe06f25(i);
	    } 
	    return p;
	  }
	  static double N40560f803(Object []i) {
	    double p = Double.NaN;
	    if (i[0] == null) {
	      p = 0;
	    } else if (((Double) i[0]).doubleValue() <= 54.86936715716707) {
	    p = SpeechDetector.N5213bd7f4(i);
	    } else if (((Double) i[0]).doubleValue() > 54.86936715716707) {
	      p = 0;
	    } 
	    return p;
	  }
	  static double N5213bd7f4(Object []i) {
	    double p = Double.NaN;
	    if (i[0] == null) {
	      p = 0;
	    } else if (((Double) i[0]).doubleValue() <= 54.82694227707181) {
	      p = 0;
	    } else if (((Double) i[0]).doubleValue() > 54.82694227707181) {
	      p = 1;
	    } 
	    return p;
	  }
	  static double Ndfe06f25(Object []i) {
	    double p = Double.NaN;
	    if (i[1] == null) {
	      p = 1;
	    } else if (((Double) i[1]).doubleValue() <= 3.3381363602532312) {
	      p = 1;
	    } else if (((Double) i[1]).doubleValue() > 3.3381363602532312) {
	      p = 0;
	    } 
	    return p;
	  }
	  static double N207a010b6(Object []i) {
	    double p = Double.NaN;
	    if (i[2] == null) {
	      p = 0;
	    } else if (((Double) i[2]).doubleValue() <= -3.5447243660319545) {
	      p = 0;
	    } else if (((Double) i[2]).doubleValue() > -3.5447243660319545) {
	    p = SpeechDetector.N29da89d57(i);
	    } 
	    return p;
	  }
	  static double N29da89d57(Object []i) {
	    double p = Double.NaN;
	    if (i[7] == null) {
	      p = 1;
	    } else if (((Double) i[7]).doubleValue() <= 1.9033402909648842) {
	      p = 1;
	    } else if (((Double) i[7]).doubleValue() > 1.9033402909648842) {
	    p = SpeechDetector.N4a8f146a8(i);
	    } 
	    return p;
	  }
	  static double N4a8f146a8(Object []i) {
	    double p = Double.NaN;
	    if (i[1] == null) {
	      p = 0;
	    } else if (((Double) i[1]).doubleValue() <= 1.444530199325155) {
	      p = 0;
	    } else if (((Double) i[1]).doubleValue() > 1.444530199325155) {
	      p = 1;
	    } 
	    return p;
	  }
	}

