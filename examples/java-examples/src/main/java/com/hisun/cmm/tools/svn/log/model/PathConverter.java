package com.hisun.cmm.tools.svn.log.model;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PathConverter implements Converter {

    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
        Path path = (Path) value;
        writer.addAttribute("action", path.getAction());
        writer.addAttribute("kind", path.getKind());
        writer.setValue(path.getValue());
    }

    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        Path path = new Path();
        path.setValue(reader.getValue());
        path.setAction(reader.getAttribute("action"));
        path.setKind(reader.getAttribute("kind"));
        return path;
    }

    public boolean canConvert(Class clazz) {
        return clazz.equals(Path.class);
    }
}