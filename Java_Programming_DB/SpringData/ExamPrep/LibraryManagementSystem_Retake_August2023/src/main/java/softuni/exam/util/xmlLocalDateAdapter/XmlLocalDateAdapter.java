package softuni.exam.util.xmlLocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class XmlLocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String time) throws Exception {
        return LocalDate.parse(time);
    }

    @Override
    public String marshal(LocalDate localTime) throws Exception {
        return localTime.toString();
    }
}