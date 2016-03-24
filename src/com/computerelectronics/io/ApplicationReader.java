package com.computerelectronics.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// Skeleton class representing a reader for the application.
// Any error is represented by a message and is thrown in the form of an exception.
public abstract class ApplicationReader {

	// Maximum read limit after marking the current reader's position.
	protected static final int READ_LIMIT = 100000;
	
	// Main reader object.
	private BufferedReader reader = null;
	
	protected final BufferedReader getReader() {
	
		return this.reader;
	
	}
	
	// Helper method that initializes the reader with a specific file.
	public final void initialize(String file) throws Exception {
	
		if (this.reader == null) {
		
			try {
			
				this.reader = new BufferedReader(new FileReader(file));
			
			} catch (FileNotFoundException fileNotFoundError) {
			
				throw new Exception(String.format("ERROR\n\nThere was an I/O error while attempting to initialize the reader.\nFile \"%s\" not found.", file));
			
			}
		
		}
	
	}
	
	// Helper method that closes the reader.
	public final void close() throws Exception {
	
		if (this.reader != null) {
		
			try {
			
				this.reader.close();
			
			} catch (IOException ioError) {
			
				throw new Exception("ERROR\n\nThere was an I/O error while attempting to close the reader.");
			
			}
		
		}
	
	}
	
	// Helper method for searching a tag.
	//
	// -> If "scan" value is true.
	//
	// 		Then the method marks the current position of the reader and then simply searches for the specified tag. If
	//		found, the method returns true, otherwise false. The reader is then reset to its original position inside the file.
	//
	// -> If "scan" value is false.
	//
	//		Then the method starts scanning the file for the specified tag, while moving the position of the reader. If the tag
	//		is found, then the current position of the reader is marked and the method returns true. If the end of file is reached
	//		without success, the method returns false.
	//
	// In any case, any I/O error or general error is reported by throwing an empty IOException. The caller method is responsible
	// for handling the error with its own error message. See the implemetations of the "read()" functions in classes ProductReader,
	// OrdersReader and SalesReader for more information.
	protected final boolean tagSearch(String tag, boolean scan) throws IOException {
	
		if (this.reader != null) {
		
			if (!(scan)) this.reader.mark(ApplicationReader.READ_LIMIT);
			
			while (true) {
			
				if (this.reader.ready()) {
				
					if (this.reader.readLine().trim().equalsIgnoreCase(tag)) {
					
						if (scan) {
						
							this.reader.mark(ApplicationReader.READ_LIMIT);
						
						} else {
						
							this.reader.reset();
						
						}
						
						return true;
					
					}
					
					continue;
				
				}
				
				if (!(scan)) this.reader.reset();
				
				return false;
			
			}
		
		}
		
		return false;
	
	}
	
	// Helper method for parsing a string value from a tagged line (must be trimmed).
	// If "value" is a quoted string, it is returned without the leading and trailing quotation marks.
	// If an error occurs, the return value is null.
	protected final String parseStringValue(String line, String tag) {
	
		// The delimiter needs to be returned, for better control over the parsing process.
		StringTokenizer tokenizer = new StringTokenizer(line, " ", true);
		
		String value = null;
		
		try {
		
			if (tokenizer.nextToken().equalsIgnoreCase(tag)) {
			
				// Bypass the first space character and continue parsing with the rest of the line, as a whole.
				tokenizer.nextToken();
				value = tokenizer.nextToken("\n");
			
			} else throw new Exception();     // The line does not contain the tag specified, error.
			
			// Check if the value is a quoted string.
			if (value.startsWith("\"") && value.endsWith("\"")) {
			
				value = value.substring(value.indexOf("\"") + 1, value.lastIndexOf("\""));
				return value;
			
			}
			
			// If the value is not a quoted string, return it as is.
			return value;
		
		} catch (Exception parseError) {
		
			return null;
		
		}
	
	}
	
	// Helper method for parsing an integer number value from a tagged line (must be trimmed).
	// If an error occurs, the return value is -1.
	protected final int parseIntValue(String line, String tag) {
	
		// The delimiter needs to be returned, for better control over the parsing process.
		StringTokenizer tokenizer = new StringTokenizer(line, " ", true);
		
		String value = null;
		
		try {
		
			if (tokenizer.nextToken().equalsIgnoreCase(tag)) {
			
				// Bypass the first space character and continue parsing with the rest of the line, as a whole.
				tokenizer.nextToken();
				value = tokenizer.nextToken("\n");
			
			} else throw new Exception();     // The line does not contain the tag specified, error.
			
			// Try to parse the integer number.
			int parsedValue = Integer.parseInt(value);
			
			return parsedValue;
		
		} catch (Exception parseError) {
		
			return -1;
		
		}
	
	}
	
	// Helper method for parsing a double-precision floating-point number value from a tagged line (must be trimmed).
	// If an error occurs, the return value is -1.
	protected final double parseDoubleValue(String line, String tag) {
	
		// The delimiter needs to be returned, for better control over the parsing process.
		StringTokenizer tokenizer = new StringTokenizer(line, " ", true);
		
		String value = null;
		
		try {
		
			if (tokenizer.nextToken().equalsIgnoreCase(tag)) {
			
				// Bypass the first space character and continue parsing with the rest of the line, as a whole.
				tokenizer.nextToken();
				value = tokenizer.nextToken("\n");
			
			} else throw new Exception();     // The line does not contain the tag specified, error.
			
			// Fix precision character, as the "parseDouble()" method fails if the precision character is not "." .
			value = value.replace(',', '.');
			
			// Try to parse the double-precision floating-point number.
			double parsedValue = Double.parseDouble(value);
			
			return parsedValue;
		
		} catch (Exception parseError) {
		
			return -1;
		
		}
	
	}

}