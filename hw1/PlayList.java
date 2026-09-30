/*The Playlist class implements an abstract data type for a playlist of audio files 
  supporting common operations on such lists of audio files.
*/

package com.tao;

import java.util.Arrays;

public class PlayList implements Cloneable{
//	The SongRecord objects are stored in an array.
	public final int Max_Size = 50;
	private SongRecord[] oneSong = new SongRecord[Max_Size];
	private static int size =0;
	
//	Construct an instance of the Playlist class with no SongRecord objects in it.
	public PlayList() {
		
	}

	/*
	 * This method generate a copy of this Playlist.
	 * Subsequent changes to the copy will not affect the original.
	 * 
	 * @return 
	 * a copy of this Playlist
	 * 
	 * @throws CloneNotSupportedException
	 * when the class lacks Cloneable interface
	 * 
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		PlayList sample = (PlayList) super.clone();
		sample.oneSong = sample.oneSong.clone();
		
		return sample;
	}

	//This method return the array oneSong
	public SongRecord[] getList() {
		return oneSong;
	}
	
	/*Compare this Playlist to another object for equality.
	 * 
	 * @param obj 
	 * an object in which this Playlist is compared
	 * 
	 * @return
	 * If they are equal, return true,  else return false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayList other = (PlayList) obj;
		return Max_Size == other.Max_Size && Arrays.equals(oneSong, other.oneSong);
	}

	/*
	 * Determines the number of SongRecords currently in this Playlist.
	 * 
	 * @return 
	 * the number of SongRecords in this Playlist.
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Adds a SongRecord to the Playlist at a specific position.
	 * 
	 * @param  song
	 * the new SongRecord object to add.
	 * 
	 * @param position 
	 * the position in the playlist where the song will be inserted.
	 * 
	 * @throws IllegalArgumentException 
	 * when position is not within the valid range.
	 * 
	 * @throws FullPlayListException 
	 * when there is no more room in the Playlist.
	 */
	
	public void addSong(SongRecord song, int position) {
	    if (size >= Max_Size) {
	        throw new FullPlayListExcrption("There is no more room.");
	    }
	    if (position > 0 && position <= size + 1 && position < Max_Size) {
	        // Shift songs to make room for the new song
	        for (int i = size; i >= position; i--) {
	            this.oneSong[i] = this.oneSong[i - 1];
	        }
	        // Add a new instance of SongRecord to ensure a unique object is added
	        this.oneSong[position - 1] = new SongRecord(song.getTitle(), song.getArtist(), song.getMinutes(), song.getSeconds());
	        size++;
	    } else {
	        throw new IllegalArgumentException("Position is not within the valid range.");
	    }
	}

	/*
	 * Removes a SongRecord from the Playlist at a specific position.
	 * 
	 * @param position 
	 * the position in the playlist where the song will be removed from.
	 * 
	 * @throws IllegalArgumentException 
	 * when position is not within the valid range.
	 */
	public void removeSong(int position) {
		int length = this.size();
		if(position>0 && position<= length) {
			for(int i=0;i< length-position;i++) {
				this.oneSong[position-1+i]=this.oneSong[position+i];
			}
			this.oneSong[length - 1] = null;
			size--;
		}else {
			throw new IllegalArgumentException("Position is not within the valid range.");
		}
	}

	/*
	 * Retrieves the SongRecord at a given position in the Playlist.
	 * 
	 * @param position 
	 * the position of the SongRecord to retrieve.
	 * 
	 * @return 
	 * the SongRecord at the specified position.
	 * 
	 * @throws IllegalArgumentException 
	 * when position is not within the valid range.
	 */	
	public SongRecord getSong(int position) {
	    int length = this.size();
	    if (position > 0 && position <= length) {
	        return this.oneSong[position - 1];
	    } else {
	        throw new IllegalArgumentException("Position is not within the valid range.");
	    }
	}
//	Prints a neatly formatted table of each SongRecord in the Playlist.
	public void printAllSongs() {
		System.out.printf("%5s  %-25s%-33s%-10s\n","Song#","Title","Artist","Length");
		for(int i=0;i<this.size();i++) {
			System.out.printf("%-8d",i+1);
			if(this.oneSong[i] != null) {
			System.out.println(this.oneSong[i].toString());
			}else {
				System.out.println("null");
			}
		}
	}
	
	/*
	 * Generates a new Playlist containing all SongRecords performed by the specified artist.
	 * 
	 * @param artist 
	 * the name of the artist.
	 * 
	 * @return 
	 * a new Playlist object containing all SongRecords performed by the specified artist.
	 */
	public void getSongsByArtist(String artist) {
		int index =0;
		for(int i=0;i<this.size();i++) {
			String s=this.oneSong[i].getArtist();
			if(s.equals(artist)) {
				index++;
				System.out.printf("%-8d",index);
				System.out.println(this.oneSong[i].toString());
			}
		}
	}
	
	
	public void show() {
		System.out.printf("%5s  %-25s%-33s%-10s\n","Song#","Title","Artist","Length");
		for(int i=0;i<this.size();i++) {
			System.out.printf("%-8d",i+1);
			System.out.println(this.oneSong[i].toString());
		}
	}
	
	/*
	 * Gets the String presentation of this Playlist object.
	 * 
	 * @return 
	 * a formatted string representing the Playlist.
	 */
	@Override
	    public String toString() {
	        StringBuilder s = new StringBuilder();
	        for (int i = 0; i < this.size(); i++) {
	            s.append(this.oneSong[i].toString());
	            s.append("\n");
	        }
	        return s.toString();
	    }

}
