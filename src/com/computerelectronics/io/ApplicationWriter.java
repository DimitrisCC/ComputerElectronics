package com.computerelectronics.io;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Skeleton class representing a writer for the application.
// Any error is represented by a message and it is thrown in the form of an exception.
public abstract class ApplicationWriter {

	// Main writer object.
	private BufferedWriter writer = null;
	
	protected final BufferedWriter getWriter() {
	
		return this.writer;
	
	}
	
	// Helper method that initializes the writer with a specific file.
	public final void initialize(String file) throws Exception {
	
		if (this.writer == null) {
		
			try {
			
				this.writer = new BufferedWriter(new FileWriter(file));
			
			} catch (IOException ioError) {
			
				throw new Exception("\nThere was an I/O error while attempting to initialize the writer.");
			
			}
		
		}
	
	}
	
	// Helper method that closes the writer.
	public final void close() throws Exception {
	
		if (this.writer != null) {
		
			try {
			
				this.writer.close();
			
			} catch (IOException ioError) {
			
				throw new Exception("\nThere was an I/O error while attempting to close the writer.");
			
			}
		
		}
	
	}

}