package efub.insta.domain;

import lombok.Getter;

@Getter
public class Image {
    private Long fileSize;

    private String originalFileName;

    private String filePath;

    public Image(Long fileSize, String originalFileName, String filePath){
        this.fileSize = fileSize;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
    }

}
