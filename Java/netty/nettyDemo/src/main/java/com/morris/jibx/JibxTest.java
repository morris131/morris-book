package com.morris.jibx;

import io.netty.util.CharsetUtil;
import org.jibx.runtime.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JibxTest {

    public static void main(String[] args) throws JiBXException, IOException {
        User user = new User();
        user.setName("morris");
        user.setAge(18);
        String s = encode2Xml(user);
        System.out.println("encode2Xml: " + s);

        user = decode2User(s);
        System.out.println("decode2User" + user);

    }

    private static String encode2Xml(User user) throws JiBXException, IOException {
        IBindingFactory factory = BindingDirectory.getFactory(User.class);
        StringWriter writer = new StringWriter();
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(user, CharsetUtil.UTF_8.name(), null, writer);
        String xmlStr = writer.toString();
        writer.close();
        return xmlStr;
    }

    private static User decode2User(String xmlBody) throws JiBXException {
        IBindingFactory factory = BindingDirectory.getFactory(User.class);
        StringReader reader = new StringReader(xmlBody);
        IUnmarshallingContext uctx = factory.createUnmarshallingContext();
        User user = (User) uctx.unmarshalDocument(reader);
        return user;
    }

}
