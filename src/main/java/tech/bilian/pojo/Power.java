package tech.bilian.pojo;

import java.util.Date;

/**
 * 授权记录
 */
public class Power {
    //private Long powerId;

    private Long songId;

    private Long userId;

    private Date time;

    private String  songName;

    private Song song;

//    public Long getPowerId() {
//        return powerId;
//    }
//
//    public void setPowerId(Long powerId) {
//        this.powerId = powerId;
//    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
