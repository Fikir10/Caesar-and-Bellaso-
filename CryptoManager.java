public class CryptoManager {
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		//checks to see if plainText is within the acceptable bounds
		for(int i=0;i<plainText.length();i++) {
			//if plainText is outside the bound range, returns false
			if(plainText.charAt(i)>UPPER_BOUND||plainText.charAt(i)<LOWER_BOUND) {
				return false;
			}
		}
		//else returns true
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryptedWord="";
		//if plainText is within the stringInBounds
		if(stringInBounds(plainText)) {
			//performs the Caesar encryption
			for(int i=0;i<plainText.length();i++) {
				char ch=plainText.charAt(i);
				//wrap around the key
				int character=((int)ch+key);
				//if character is > upper bound, subtract range from it 
				while(character>UPPER_BOUND) {
					character-=RANGE;
				}
				//add encryptedWord to character
				encryptedWord+=(char)character;
			}
		}
		return encryptedWord;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encryptedWrd="";
		int lengthOfBellaso=bellasoStr.length();
		//performs the Bellaso encryption
		for(int i=0;i<plainText.length();i++) {
			char ch=plainText.charAt(i);
			//adjusts the length of bellasoStr to plainText
			int character=((int)ch+(int)bellasoStr.charAt(i%lengthOfBellaso));
			//if character is > upper bound, subtract range from it
		    while(character>UPPER_BOUND) {
		    	character-=RANGE;
		    }
		    //adds encryptedWrd to character
		    encryptedWrd+=(char)character;
		}
		return encryptedWrd;	
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String wordDecrypted="";
		//performs the Caesar decryption 
		for(int i=0;i<encryptedText.length();i++) {
			char ch=encryptedText.charAt(i);
			int character=((int)ch-key);
			//if character is < Lower bound, subtracts Range from it
			while(character<LOWER_BOUND) {
				character+=RANGE;
			}
			//adds workDecrypted to character
			wordDecrypted+=(char)character;
		}
		return wordDecrypted;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String wordDecrypted="";
		String wordKey="";
		int i=0;
		//adjusts the length of bellasStr to encryptedText
		while(wordKey.length()!=encryptedText.length()) {
			wordKey+=bellasoStr.charAt(i);
			i++;
			if(i==bellasoStr.length())
				i=0;
		}
		//peforms the decrypt for Bellaso
		for(int j=0;j<encryptedText.length();j++) {
			int result=encryptedText.charAt(j)-wordKey.charAt(j);
			//if result is < Lower bound, add Range to it
			if(result<LOWER_BOUND) {
				while(result<LOWER_BOUND)
					result+=RANGE;
			}
			//add wordDecrypted to result
			wordDecrypted+=(char)result;
		}
		return wordDecrypted;
	}

}
