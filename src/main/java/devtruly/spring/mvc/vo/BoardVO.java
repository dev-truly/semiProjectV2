package devtruly.spring.mvc.vo;

import org.springframework.stereotype.Repository;

public class BoardVO {
    private String bno;
    private String title;
    private String contents;
    private String userid;
    private String regdate;
    private String views;

    public BoardVO() { }

    public BoardVO(String bno, String title, String contents, String userid, String regdate, String views) {
        this.bno = bno;
        this.title = title;
        this.contents = contents;
        this.userid = userid;
        this.regdate = regdate;
        this.views = views;
    }

    @Override
    public String toString() {
        String frm = "BoardVO{bno=%s, title=%s, contents=%s, userid=%s, regdate=%s, views=%s}";

        return String.format(frm, bno, title, contents, userid, regdate, views);
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }


}
