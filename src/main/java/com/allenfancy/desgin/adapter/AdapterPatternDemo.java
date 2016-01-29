package com.allenfancy.desgin.adapter;

public class AdapterPatternDemo {

	public static void main(String[] args){
		AudioPlayer audioPlayer = new AudioPlayer();
		
		audioPlayer.play("mp3", "this is mp3");
		audioPlayer.play("mp4", "this is mp4");
		audioPlayer.play("vlc", "this is vlc");
		audioPlayer.play("others", "this is others");
	}
}
