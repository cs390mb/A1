package edu.umass.cs.client;

public class ActivityClassifier {

	public static double classify(Object[] i)
			throws Exception {

		double p = Double.NaN;
		p = ActivityClassifier.N49803f680(i);
		return p;
	}
	static double N49803f680(Object []i) {
		double p = Double.NaN;
		if (i[27] == null) {
			p = 1;
		} else if (((Double) i[27]).doubleValue() <= 0.8821813218331622) {
			p = ActivityClassifier.N2c2ea8b1(i);
		} else if (((Double) i[27]).doubleValue() > 0.8821813218331622) {
			p = ActivityClassifier.N4ab44d172(i);
		} 
		return p;
	}
	static double N2c2ea8b1(Object []i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 5.1538240662660275) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 5.1538240662660275) {
			p = 2;
		} 
		return p;
	}
	static double N4ab44d172(Object []i) {
		double p = Double.NaN;
		if (i[19] == null) {
			p = 0;
		} else if (((Double) i[19]).doubleValue() <= 4.501140008882644) {
			p = ActivityClassifier.N33147523(i);
		} else if (((Double) i[19]).doubleValue() > 4.501140008882644) {
			p = 2;
		} 
		return p;
	}
	static double N33147523(Object []i) {
		double p = Double.NaN;
		if (i[34] == null) {
			p = 0;
		} else if (((Double) i[34]).doubleValue() <= 1.1438910288811146) {
			p = ActivityClassifier.N18415a214(i);
		} else if (((Double) i[34]).doubleValue() > 1.1438910288811146) {
			p = 0;
		} 
		return p;
	}
	static double N18415a214(Object []i) {
		double p = Double.NaN;
		if (i[12] == null) {
			p = 1;
		} else if (((Double) i[12]).doubleValue() <= 105.80585369455022) {
			p = 1;
		} else if (((Double) i[12]).doubleValue() > 105.80585369455022) {
			p = 0;
		} 
		return p;
	}
}


//public class ActivityClassifier {
//
//	public static double classify(Object[] i)
//			throws Exception {
//
//		double p = Double.NaN;
//		p = ActivityClassifier.N65c990650(i);
//		return p;
//	}
//	public static double N65c990650(Object []i) {
//		double p = Double.NaN;
//		if (i[40] == null) {
//			p = 1;
//		} else if (((Double) i[40]).doubleValue() <= 3.722201436357011) {
//			p = 1;
//		} else if (((Double) i[40]).doubleValue() > 3.722201436357011) {
//			p = ActivityClassifier.N14270b181(i);
//		} 
//		return p;
//	}
//	public static double N14270b181(Object []i) {
//		double p = Double.NaN;
//		if (i[36] == null) {
//			p = 0;
//		} else if (((Double) i[36]).doubleValue() <= 782.5350336108571) {
//			p = 0;
//		} else if (((Double) i[36]).doubleValue() > 782.5350336108571) {
//			p = 2;
//		} 
//		return p;
//	}
//}
