package model;

/**
 * This class contains a method which will determine the shipping charge of an order.
 * 
 * @author Nick Pinzon
 *
 */

public class ShippingCost {

	private static double costPerPound;
	private static double costPerMile = 1.15;
	private static double totalDistance;

	/*Shipping charge = (cost per pounds * total weight) * (cost per miles * total distance)*/
	
	/**
	 * Assigns an arbitrary value of 1 for NY State because that is where the store is located.
	 * This value slowly increases the further you travel from NY. In a nutshell, these values represent
	 * the distance away from the restore.
	 * 
	 * @param state The State to where the order is being shipped.
	 * @param shippingMethod The order's shipping method
	 * @param weight The weight of the item.
	 * @return
	 */
	
	public static double calculate(String state, String shippingMethod, double weight) {
		double shippingCharge = 0;
		if (shippingMethod == "Standard Ground") {
			costPerPound = 1;
			if (state == "NY") {
				totalDistance = 1;
			} else if (state == "NJ") {
				totalDistance = 1.05;
			} else if (state == "CT") {
				totalDistance = 1.1;
			} else if (state == "MA") {
				totalDistance = 1.15;
			} else if (state == "PA") {
				totalDistance = 1.2;
			} else if (state == "RI") {
				totalDistance = 1.25;
			} else if (state == "VT") {
				totalDistance = 1.30;
			} else if (state == "PA") {
				totalDistance = 1.35;
			} else if (state == "MD") {
				totalDistance = 1.40;
			} else if (state == "NH") {
				totalDistance = 1.45;
			} else if (state == "ME") {
				totalDistance = 1.5;
			} else if (state == "DE") {
				totalDistance = 1.55;
			} else if (state == "VA") {
				totalDistance = 1.6;
			} else if (state == "WV") {
				totalDistance = 1.65;
			} else if (state == "OH") {
				totalDistance = 1.7;
			} else if (state == "NC") {
				totalDistance = 1.75;
			} else if (state == "KY") {
				totalDistance = 1.8;
			} else if (state == "MI") {
				totalDistance = 1.85;
			} else if (state == "IN") {
				totalDistance = 1.9;
			} else if (state == "TN") {
				totalDistance = 1.95;
			} else if (state == "WI") {
				totalDistance = 2.0;
			} else if (state == "IL") {
				totalDistance = 2.05;
			} else if (state == "SC") {
				totalDistance = 2.1;
			} else if (state == "GA") {
				totalDistance = 2.15;
			} else if (state == "AL") {
				totalDistance = 2.2;
			} else if (state == "FL") {
				totalDistance = 2.25;
			} else if (state == "MN") {
				totalDistance = 2.3;
			} else if (state == "IA") {
				totalDistance = 2.35;
			} else if (state == "MO") {
				totalDistance = 2.4;
			} else if (state == "AR") {
				totalDistance = 2.45;
			} else if (state == "LA") {
				totalDistance = 2.5;
			} else if (state == "ND") {
				totalDistance = 2.55;
			} else if (state == "SD") {
				totalDistance = 2.6;
			} else if (state == "NE") {
				totalDistance = 2.65;
			} else if (state == "KS") {
				totalDistance = 2.7;
			} else if (state == "OK") {
				totalDistance = 2.75;
			} else if (state == "TX") {
				totalDistance = 2.8;
			} else if (state == "MT") {
				totalDistance = 2.85;
			} else if (state == "WY") {
				totalDistance = 2.9;
			} else if (state == "CO") {
				totalDistance = 3.0;
			} else if (state == "NM") {
				totalDistance = 3.1;
			} else if (state == "ID") {
				totalDistance = 3.15;
			} else if (state == "UT") {
				totalDistance = 3.2;
			} else if (state == "AZ") {
				totalDistance = 3.25;
			} else if (state == "WA") {
				totalDistance = 3.3;
			} else if (state == "OR") {
				totalDistance = 3.35;
			} else if (state == "NV") {
				totalDistance = 3.4;
			} else if (state == "CA") {
				totalDistance = 3.45;
			} else if (state == "HI") {
				totalDistance = 4.5;
			} else if (state == "AK") {
				totalDistance = 4.5;
			}
			shippingCharge = (costPerPound*weight)*(costPerMile*totalDistance);
		} else if (shippingMethod == "Second Day Express") {
			costPerPound = 2;
			if (state == "NY") {
				totalDistance = 1;
			} else if (state == "NJ") {
				totalDistance = 1.05;
			} else if (state == "CT") {
				totalDistance = 1.1;
			} else if (state == "MA") {
				totalDistance = 1.15;
			} else if (state == "PA") {
				totalDistance = 1.2;
			} else if (state == "RI") {
				totalDistance = 1.25;
			} else if (state == "VT") {
				totalDistance = 1.30;
			} else if (state == "PA") {
				totalDistance = 1.35;
			} else if (state == "MD") {
				totalDistance = 1.40;
			} else if (state == "NH") {
				totalDistance = 1.45;
			} else if (state == "ME") {
				totalDistance = 1.5;
			} else if (state == "DE") {
				totalDistance = 1.55;
			} else if (state == "VA") {
				totalDistance = 1.6;
			} else if (state == "WV") {
				totalDistance = 1.65;
			} else if (state == "OH") {
				totalDistance = 1.7;
			} else if (state == "NC") {
				totalDistance = 1.75;
			} else if (state == "KY") {
				totalDistance = 1.8;
			} else if (state == "MI") {
				totalDistance = 1.85;
			} else if (state == "IN") {
				totalDistance = 1.9;
			} else if (state == "TN") {
				totalDistance = 1.95;
			} else if (state == "WI") {
				totalDistance = 2.0;
			} else if (state == "IL") {
				totalDistance = 2.05;
			} else if (state == "SC") {
				totalDistance = 2.1;
			} else if (state == "GA") {
				totalDistance = 2.15;
			} else if (state == "AL") {
				totalDistance = 2.2;
			} else if (state == "FL") {
				totalDistance = 2.25;
			} else if (state == "MN") {
				totalDistance = 2.3;
			} else if (state == "IA") {
				totalDistance = 2.35;
			} else if (state == "MO") {
				totalDistance = 2.4;
			} else if (state == "AR") {
				totalDistance = 2.45;
			} else if (state == "LA") {
				totalDistance = 2.5;
			} else if (state == "ND") {
				totalDistance = 2.55;
			} else if (state == "SD") {
				totalDistance = 2.6;
			} else if (state == "NE") {
				totalDistance = 2.65;
			} else if (state == "KS") {
				totalDistance = 2.7;
			} else if (state == "OK") {
				totalDistance = 2.75;
			} else if (state == "TX") {
				totalDistance = 2.8;
			} else if (state == "MT") {
				totalDistance = 2.85;
			} else if (state == "WY") {
				totalDistance = 2.9;
			} else if (state == "CO") {
				totalDistance = 3.0;
			} else if (state == "NM") {
				totalDistance = 3.1;
			} else if (state == "ID") {
				totalDistance = 3.15;
			} else if (state == "UT") {
				totalDistance = 3.2;
			} else if (state == "AZ") {
				totalDistance = 3.25;
			} else if (state == "WA") {
				totalDistance = 3.3;
			} else if (state == "OR") {
				totalDistance = 3.35;
			} else if (state == "NV") {
				totalDistance = 3.4;
			} else if (state == "CA") {
				totalDistance = 3.45;
			} else if (state == "HI") {
				totalDistance = 4.5;
			} else if (state == "AK") {
				totalDistance = 4.5;
			}
			shippingCharge = (costPerPound*weight)*(costPerMile*totalDistance);
		} else if (shippingMethod == "Next Day Air") {
			costPerPound = 3;
			if (state == "NY") {
				totalDistance = 1;
			} else if (state == "NJ") {
				totalDistance = 1.05;
			} else if (state == "CT") {
				totalDistance = 1.1;
			} else if (state == "MA") {
				totalDistance = 1.15;
			} else if (state == "PA") {
				totalDistance = 1.2;
			} else if (state == "RI") {
				totalDistance = 1.25;
			} else if (state == "VT") {
				totalDistance = 1.30;
			} else if (state == "PA") {
				totalDistance = 1.35;
			} else if (state == "MD") {
				totalDistance = 1.40;
			} else if (state == "NH") {
				totalDistance = 1.45;
			} else if (state == "ME") {
				totalDistance = 1.5;
			} else if (state == "DE") {
				totalDistance = 1.55;
			} else if (state == "VA") {
				totalDistance = 1.6;
			} else if (state == "WV") {
				totalDistance = 1.65;
			} else if (state == "OH") {
				totalDistance = 1.7;
			} else if (state == "NC") {
				totalDistance = 1.75;
			} else if (state == "KY") {
				totalDistance = 1.8;
			} else if (state == "MI") {
				totalDistance = 1.85;
			} else if (state == "IN") {
				totalDistance = 1.9;
			} else if (state == "TN") {
				totalDistance = 1.95;
			} else if (state == "WI") {
				totalDistance = 2.0;
			} else if (state == "IL") {
				totalDistance = 2.05;
			} else if (state == "SC") {
				totalDistance = 2.1;
			} else if (state == "GA") {
				totalDistance = 2.15;
			} else if (state == "AL") {
				totalDistance = 2.2;
			} else if (state == "FL") {
				totalDistance = 2.25;
			} else if (state == "MN") {
				totalDistance = 2.3;
			} else if (state == "IA") {
				totalDistance = 2.35;
			} else if (state == "MO") {
				totalDistance = 2.4;
			} else if (state == "AR") {
				totalDistance = 2.45;
			} else if (state == "LA") {
				totalDistance = 2.5;
			} else if (state == "ND") {
				totalDistance = 2.55;
			} else if (state == "SD") {
				totalDistance = 2.6;
			} else if (state == "NE") {
				totalDistance = 2.65;
			} else if (state == "KS") {
				totalDistance = 2.7;
			} else if (state == "OK") {
				totalDistance = 2.75;
			} else if (state == "TX") {
				totalDistance = 2.8;
			} else if (state == "MT") {
				totalDistance = 2.85;
			} else if (state == "WY") {
				totalDistance = 2.9;
			} else if (state == "CO") {
				totalDistance = 3.0;
			} else if (state == "NM") {
				totalDistance = 3.1;
			} else if (state == "ID") {
				totalDistance = 3.15;
			} else if (state == "UT") {
				totalDistance = 3.2;
			} else if (state == "AZ") {
				totalDistance = 3.25;
			} else if (state == "WA") {
				totalDistance = 3.3;
			} else if (state == "OR") {
				totalDistance = 3.35;
			} else if (state == "NV") {
				totalDistance = 3.4;
			} else if (state == "CA") {
				totalDistance = 3.45;
			} else if (state == "HI") {
				totalDistance = 4.5;
			} else if (state == "AK") {
				totalDistance = 4.5;
			}
			shippingCharge = (costPerPound*weight)*(costPerMile*totalDistance);
		}
		return shippingCharge;
	}

}
