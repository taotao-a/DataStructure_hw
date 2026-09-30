//This class maintains simple information about each audio file. 


package com.tao;

public class SongRecord {
//	basic information of the song
	private String title;
	private String artist;
	private int minutes;
	private int seconds;
	
//	a default constructor
	public SongRecord() {
		
	}
	
	public SongRecord(String title, String artist, int minutes, int seconds) {
		super();
		this.title = title;
		this.artist = artist;
		this.minutes = minutes;
		this.seconds = seconds;
}



//	accessor and mutator methods for each variable
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getMinutes() {
		return minutes;
	}

	/*
	 * This method sets the minutes of the song
	 * 
	 * @param minutes
	 * The minutes user entered
	 * 
	 * @throws IllegalArgumentException
	 * when the minutes isn't positive  
	 */
	public void setMinutes(int minutes) {
		if(minutes < 0) {
			throw new IllegalArgumentException("Minutes should be positive");
		}else {
			this.minutes = minutes;
		}	
	}
	public int getSeconds() {
		return seconds;
	}
	/*
	 * This method sets the seconds of the song
	 * 
	 * @param seconds
	 * The minutes user entered
	 * 
	 * @throws IllegalArgumentException
	 * when the seconds isn't between 0 and 59  
	 */
	public void setSeconds(int seconds) {
		if(seconds<0 || seconds>59) {
			throw new IllegalArgumentException("Seconds should be between 0 and 59");
		}else {
			this.seconds = seconds;
		}
	}
		
//	print the information about the audio file
	@Override
	public String toString() {
		return String.format("%-25s%-25s%10d:%02d",this.title,this.artist,this.minutes,this.seconds);
	}


	
	
	

}
