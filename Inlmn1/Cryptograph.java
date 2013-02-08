import se.lth.cs.pt.io.Keyboard;
import se.lth.cs.pt.util.CSMath;

class Cryptograph {
	final int CI_S = 32; // Chiffer Interval Start
	final int CI_E = 126; // Chiffer interval end

    public static void main(String[] args) {
        new Cryptograph().run();
    }

	enum MenuAction { 
		QUIT("Avsluta"), CHIFFER("Chiffrera"), DECHIFFER("Dechiffrera");
		
		private final String text;

		MenuAction(final String text) {
			this.text = text;
		} 

		@Override
		public String toString() {
			return text;
		}
	}
	
	void run() {
		MenuAction action = showMenuAndGetAction();
		performAction(action);
		
		run();
	}
	
	void makeChiffer() {
		String str = Keyboard.nextLine("Meddelande: ");
		String key = Keyboard.nextLine("Nyckel: ");
		
		System.out.println("Chiffreradtext: " + chiffer(str,key));
	}
	
	void solveChiffer() {
		String chif = Keyboard.nextLine("Chiffertext: ");
		String key = Keyboard.nextLine("Nyckel: ");
		
		System.out.println("Meddelande: " + dechiffer(chif,key));
	}
	
	String chiffer2(String str, String key, boolean dechiffer) {
		StringBuilder output = new StringBuilder();
		int interval = (CI_E-CI_S+1);
		
		for(int i = 0, k=0; i < str.length(); i++, k++) {
			if(k >= key.length()) k = 0;
			
			int code = dechiffer ? str.charAt(i)-key.charAt(k) : str.charAt(i)+key.charAt(k);
			
			char newChar = (char) (CSMath.mod((code-CI_S), interval) + CI_S);
			
			output.append(newChar);
		}
		
		return output.toString();
	}
	
	String chiffer(String str, String key, boolean dechiffer) {
		StringBuilder output = new StringBuilder();
		int interval = (CI_E-CI_S+1);
		
		for(int i = 0; i < str.length(); i++) {
			//if(k >= key.length()) k = 0;
			
			int k = i % key.length();
			
			int code = dechiffer ? str.charAt(i)-key.charAt(k) : str.charAt(i)+key.charAt(k);
			
			char newChar = (char) (CSMath.mod((code-CI_S), interval) + CI_S);
			
			output.append(newChar);
		}
		
		return output.toString();
	}
	
	
	String chiffer(String str, String key) {
		return chiffer(str, key, false);
	}
	
	String dechiffer(String chif, String key) {
		return chiffer(chif, key, true);
	}
		
	void performAction(MenuAction a) {
		switch(a) {
			case QUIT: 
				System.out.println("Tack för idag!");
				System.exit(0);
				break; // unreachable..
			case CHIFFER:
				makeChiffer();
				break;
			case DECHIFFER:
				solveChiffer();
				break;
		}
	}
	
	MenuAction showMenuAndGetAction() {
		int alternatives = MenuAction.values().length; 
		MenuAction[] values = MenuAction.values();
		
		System.out.println("");
		
		// Print menu based on Enum
		for(int i = 1; i < alternatives; i++) {
			System.out.println(i + ". " + values[i]);
		}
		
		System.out.println("0. " + values[0]); // We want Quit to list last
		
		String userPrompt = "Vad vill du göra (0-" + (alternatives-1) + "): ";
		int val = Keyboard.nextInt(userPrompt);
		
		while(!(val < alternatives && val >= 0)) {
			val = Keyboard.nextInt("Ogiltigt val, " + userPrompt);
		}

		System.out.println("");
		
		return values[val];
    }
}