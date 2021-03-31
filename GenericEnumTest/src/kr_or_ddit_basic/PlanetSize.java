package kr_or_ddit_basic;

/**
 *  행성의 반지름
 * 	수성(2439)
 *	금성(6052)
 *	지구(6371)
 *	성(3390)
 *	목성(69911)
 *	토성(58232)
 *	천왕성(25362)
 *	해왕성(24622)
 */
public class PlanetSize {

	public enum Planet {
			수성(2439),
		 	금성(6052),
		 	지구(6371),
		 	성(3390),
		 	목성(69911),
		 	토성(58232),
		 	천왕성(25362),
		 	해왕성(24622);
		
		private int str;
		
		Planet(int data) {
			str = data;
		}
		
		public int getStr() {
			return str;
		}
	}
	
	public static void main(String[] args) {
		
		Planet[] eumArr = Planet.values();
		
		float pi = 3.141592f;
		for (Planet pla : Planet.values()) {
			System.out.println("==================================");
			System.out.println(pla + "의 면적 : " + (double)(4 * pi * Math.pow(pla.getStr(), 2)) + " km^2");
			System.out.println("==================================");
		}
	}
}

