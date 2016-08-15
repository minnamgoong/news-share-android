package me.frsunny.sharenews.retrofit.response;

/**
 * Created by min.namgoong@sk.com on 2016. 8. 15..
 */
public class ShortenUrlResponse {
    private String kind;
    private String id;
    private String longUrl;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
