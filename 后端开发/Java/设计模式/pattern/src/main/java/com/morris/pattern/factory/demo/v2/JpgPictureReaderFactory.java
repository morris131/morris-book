package com.morris.pattern.factory.demo.v2;

public class JpgPictureReaderFactory implements PictureReaderFactory {
    @Override
    public PictureReader createPictureReader() {
        return new JpgPictureReader();
    }
}
