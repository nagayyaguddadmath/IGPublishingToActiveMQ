package igorders.model;

public enum BuySale {
    BUY ("BUY"),
    SALE("SALE"),
    SELL("SELL");

    private String strbuySale;

    private BuySale(String buySale) {
        strbuySale = buySale;
    }

    public String getBuySale() {
        return strbuySale;
    }

    public static BuySale getByName(String buysaleName) {
        for (BuySale buysale: values()) {
            if (buysale.getBuySale().equals(buysaleName)) {
                return buysale;
            }
        }
        return null;
    }

}
