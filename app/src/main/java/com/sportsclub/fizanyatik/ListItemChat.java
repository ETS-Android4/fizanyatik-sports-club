package com.sportsclub.fizanyatik;

public class ListItemChat {
    private String message;
    private String userchatname;
    private String objectId;
    private String type;
    private String root;
    private String time;
    private String imageURL;
    private String imgname;

    public ListItemChat(String message, String userchatname, String objectId, String type, String root, String time,String imageURL, String imgname) {
        this.message = message;
        this. objectId = objectId;
        this.userchatname = userchatname;
        this.type = type;
        this.root = root;
        this.time = time;
        this.imageURL = imageURL;
        this.imgname = imgname;
    }

    public String getMessage() {
        return message;
    }

    public String getUserchatname() {
        return userchatname;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getType(){
        return type;
    }

    public String getRoot() {
        return root;
    }

    public String getImgname() {
        return imgname;
    }

    public String getTime() {
        return time;
    }

    public String getImageURL() {
        return imageURL;
    }
}
