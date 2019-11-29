package eu.accesa.hackit.th.model.dto;

public class Vid2ImageSplitReqDto {
    private String mp4Path;
    private String imagePath;
    private String imgType;
    private int frameJump;

    public String getMp4Path() {
        return mp4Path;
    }

    public void setMp4Path(String mp4Path) {
        this.mp4Path = mp4Path;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public int getFrameJump() {
        return frameJump;
    }

    public void setFrameJump(int frameJump) {
        this.frameJump = frameJump;
    }
}
