package me.frsunny.sharenews.retrofit.param;

/**
 * Created by min.namgoong@sk.com on 2016. 8. 15..
 */
public class ShortenUrlParam {
    private String longUrl;

    private ShortenUrlParam(Builder builder) {
        setLongUrl(builder.longUrl);
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public static final class Builder {
        private String longUrl;

        public Builder() {
        }

        public Builder longUrl(String val) {
            longUrl = val;
            return this;
        }

        public ShortenUrlParam build() {
            return new ShortenUrlParam(this);
        }
    }
}
