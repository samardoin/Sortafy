package jukebox;

public interface Playable {
    public String getStatus();
    public void start();
    public void stop();
    public void pause();

    public void setSpeed(long speed);
    public long getSpeed();
}
