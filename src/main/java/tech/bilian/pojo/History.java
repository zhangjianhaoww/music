package tech.bilian.pojo;

import java.util.Date;

public class History {

    private Long historyId;

    private Long userId;

    private Long songId;

    private String songName;

    private Integer state;

    private Shangjia shangjia;

    private Song song;


    private Date time;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public Shangjia getShangjia() {
        return shangjia;
    }

    public void setShangjia(Shangjia shangjia) {
        this.shangjia = shangjia;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
