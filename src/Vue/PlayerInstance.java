package Vue;

import java.awt.Canvas;
import java.awt.Color;

import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class PlayerInstance extends MediaPlayerEventAdapter {

    private final EmbeddedMediaPlayer mediaPlayer;

    private final Canvas videoSurface;

    public PlayerInstance(EmbeddedMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.videoSurface = new Canvas();
        this.videoSurface.setBackground(Color.black);

        mediaPlayer.addMediaPlayerEventListener(this);
    }

    public EmbeddedMediaPlayer mediaPlayer() {
        return mediaPlayer;
    }

    public Canvas videoSurface() {
        return videoSurface;
    }

    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t media, String mrl) {
        System.out.println("mediaChanged");
    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
        System.out.println("playing");
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {
        System.out.println("paused");
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        System.out.println("stopped");
    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
        System.out.println("finished");
    }

    @Override
    public void error(MediaPlayer mediaPlayer) {
        System.out.println("error");
    }

    @Override
    public void opening(MediaPlayer mediaPlayer) {
        System.out.println("opening");
    }
}