package com.example.abhinav.endlessrv;

/**
 * Created by ABHINAV on 26-07-2016.
 */
public class user {
    String id;
    String largeimage;
    String smallimageleft;
    String smallimageright;
    String largetitle;
    String smalltitleleft;
    String smalltitleright;
    String largedate;
    String smalldateleft;
    String smalldateright;
    String largecategory;
    String smallcategoryleft;
    String smallcategoryright;

    public user(String id, String largecategory, String largedate, String largeimage, String largetitle, String smallcategoryleft, String smallcategoryright, String smalldateleft, String smalldateright, String smallimageleft, String smallimageright, String smalltitleleft, String smalltitleright) {
        this.id = id;
        this.largecategory = largecategory;
        this.largedate = largedate;
        this.largeimage = largeimage;
        this.largetitle = largetitle;
        this.smallcategoryleft = smallcategoryleft;
        this.smallcategoryright = smallcategoryright;
        this.smalldateleft = smalldateleft;
        this.smalldateright = smalldateright;
        this.smallimageleft = smallimageleft;
        this.smallimageright = smallimageright;
        this.smalltitleleft = smalltitleleft;
        this.smalltitleright = smalltitleright;
    }
    public user() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLargecategory() {
        return largecategory;
    }

    public void setLargecategory(String largecategory) {
        this.largecategory = largecategory;
    }

    public String getLargedate() {
        return largedate;
    }

    public void setLargedate(String largedate) {
        this.largedate = largedate;
    }

    public String getLargetitle() {
        return largetitle;
    }

    public void setLargetitle(String largetitle) {
        this.largetitle = largetitle;
    }

    public String getLargeimage() {
        return largeimage;
    }

    public void setLargeimage(String largeimage) {
        this.largeimage = largeimage;
    }

    public String getSmallcategoryright() {
        return smallcategoryright;
    }

    public void setSmallcategoryright(String smallcategoryright) {
        this.smallcategoryright = smallcategoryright;
    }

    public String getSmallcategoryleft() {
        return smallcategoryleft;
    }

    public void setSmallcategoryleft(String smallcategoryleft) {
        this.smallcategoryleft = smallcategoryleft;
    }

    public String getSmalldateleft() {
        return smalldateleft;
    }

    public void setSmalldateleft(String smalldateleft) {
        this.smalldateleft = smalldateleft;
    }

    public String getSmalldateright() {
        return smalldateright;
    }

    public void setSmalldateright(String smalldateright) {
        this.smalldateright = smalldateright;
    }

    public String getSmallimageleft() {
        return smallimageleft;
    }

    public void setSmallimageleft(String smallimageleft) {
        this.smallimageleft = smallimageleft;
    }

    public String getSmallimageright() {
        return smallimageright;
    }

    public void setSmallimageright(String smallimageright) {
        this.smallimageright = smallimageright;
    }

    public String getSmalltitleleft() {
        return smalltitleleft;
    }

    public void setSmalltitleleft(String smalltitleleft) {
        this.smalltitleleft = smalltitleleft;
    }

    public String getSmalltitleright() {
        return smalltitleright;
    }

    public void setSmalltitleright(String smalltitleright) {
        this.smalltitleright = smalltitleright;
    }





}
