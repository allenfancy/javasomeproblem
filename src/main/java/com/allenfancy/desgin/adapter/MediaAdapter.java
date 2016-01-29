package com.allenfancy.desgin.adapter;

/**
 * 适配器
 * @author allen
 *
 */
public class MediaAdapter implements MediaPlayer{
	
	AdvancedMediaPlayer advancedMediaPlayer;
	
	public MediaAdapter(String audioType){
		if(audioType.equalsIgnoreCase("vlc")){
			advancedMediaPlayer = new VlcPlayer();
		}else if(audioType.equalsIgnoreCase("mp4")){
			advancedMediaPlayer = new Mp4Player();
		}
	}
	
	public void play(String audioType, String fileName) {
		if(audioType.equals("vlc")){
			advancedMediaPlayer.playVlc(fileName);
		}else if(audioType.equals("mp4")){
			advancedMediaPlayer.playMp4(fileName);
		}
	}

}
