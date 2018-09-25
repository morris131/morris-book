package com.morris.pattern.factory.demo.v1;

public class PictureReaderFactory {

    public static PictureReader createPictureReader(String type) {
        if("gif".equals(type)) {
            return new GifPictureReader();
        } else if("jpg".equals(type)) {
            return new JpgPictureReader();
        }
        return null;
    }

}
