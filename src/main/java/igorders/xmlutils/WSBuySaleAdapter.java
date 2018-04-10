package igorders.xmlutils;

import igorders.model.BuySale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class WSBuySaleAdapter extends XmlAdapter<String, BuySale> {
    @Override
    public String marshal(BuySale buysale) throws Exception {
        if (buysale == null) return "";
        return buysale.getBuySale();
    }

    @Override
    public BuySale unmarshal(String buysale) throws Exception {
        return BuySale.getByName(buysale);
    }
}
