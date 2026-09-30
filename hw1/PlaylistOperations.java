/*This project represents a playlist of audio files
   
@author R32314004 Tao Yumiao*/

package com.tao;

import java.util.Scanner;

//This class is set for the operating page.

public class PlaylistOperations {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PlayList list = new PlayList();   //to receive 
		SongRecord song = new SongRecord(); 
		
		//using introduction of menu
		System.out.println("A) Add Song");
		System.out.println("B) Print Songs by Artist ");
		System.out.println("G) Get Song ");
		System.out.println("R) Remove Song");
		System.out.println("P) Print All Songs");
		System.out.println("S) Size");
		System.out.println("Q) Quit");
		
		//loop to let user enter constantly
		while(true) {
			
			//receive option
			System.out.print("Select a menu option:");
			String option= scan.nextLine();
			option=option.toUpperCase();
			
			try {
				//match
				switch(option){
					case "A" :
						System.out.print("Enter the song title:");
						song.setTitle(scan.nextLine());
						System.out.print("Enter the song artist:");
						song.setArtist(scan.nextLine());
						System.out.print("Enter the song length(minutes):");
						song.setMinutes(scan.nextInt());
						scan.nextLine();
						System.out.print("Enter the song length(seconds):");
						song.setSeconds(scan.nextInt());
						scan.nextLine();
						System.out.print("Enter the position:");
						list.addSong(song,scan.nextInt());
						scan.nextLine();
						System.out.println("Song Added: "+song.getTitle()+" By "+song.getArtist());
						break;
						
					case "B" :
						System.out.print("Enter the artist:");
						String artist=scan.nextLine();
						System.out.printf("%5s  %-25s%-33s%-10s\n","Song#","Title","Artist","Length");
						list.getSongsByArtist(artist);
						break;
						
					case "G" :
						System.out.print("Enter the position:");
						int p1 = scan.nextInt();
						scan.nextLine();
						System.out.printf("%5s  %-25s%-33s%-10s\n","Song#","Title","Artist","Length");
						System.out.printf("%-5d  ",1);
						System.out.println(list.getSong(p1).toString());
						break;
						
					case "R" :
						System.out.print("Enter the position:");
						int p2 =scan.nextInt();
						scan.nextLine();
						list.removeSong(p2);
						System.out.println("Song Removed at position "+p2);
						break;
						
					case "P" :
						list.printAllSongs();
						break;
						
					case "S" :
						System.out.println("There are "+list.size()+" song(s) in the current playlist.");
						break;
					
					//drop out of the loop	
					case "Q" :
						System.out.println("Program terminatingg normally...");
						return;
						
					default:
						System.out.println("False!Enter again!");
						break;
				}
				
			} catch (IllegalArgumentException | FullPlayListExcrption e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
			 
			
			
			
			
		}

	}

}
