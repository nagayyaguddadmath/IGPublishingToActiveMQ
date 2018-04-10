package igorders.model;

import igorders.xmlutils.WSBuySaleAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/*
 * Order model for Order details
 */
@XmlRootElement(name = "Order")
public class Order {


    public Order() {

    }

    private String accountName;
    private long submittedAt;
    private long receivedAt;
    private String marketName;
    private BuySale buySale;
    private int size;


    public String getAccountName() {
        return accountName;
    }

    @XmlElement(name = "accont")
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getSubmittedAt() {
        return submittedAt;
    }

    @XmlElement(type = Long.class, name = "SubmittedAt")
    public void setSubmittedAt(long submittedAt) {
        this.submittedAt = submittedAt;
    }

    public long getReceivedAt() {
        return receivedAt;
    }

    @XmlElement(type = Long.class, name = "ReceivedAt")
    public void setReceivedAt(long receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getMarketName() {
        return marketName;
    }

    @XmlElement(name = "market")
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public int getSize() {
        return size;
    }

    @XmlElement(type = Integer.class, name = "size")
    public void setSize(int size) {
        this.size = size;
    }


    public igorders.model.BuySale getBuySale() {
        return buySale;
    }

    @XmlElement(name = "action")
    @XmlJavaTypeAdapter(WSBuySaleAdapter.class)
    public void setBuySale(igorders.model.BuySale buySale) {
        this.buySale = buySale;
    }


    public Order(String accountName, long submittedAt, long receivedAt, String marketName, BuySale buySale, int size) {
        this.accountName = accountName;
        this.submittedAt = submittedAt;
        this.receivedAt = receivedAt;
        this.marketName = marketName;
        this.buySale = buySale;
        this.size = size;
    }

    ;

    @Override
    public String toString() {
        return "accountName:" + accountName + ", submittedAt: " + submittedAt + ", receivedAt: " + receivedAt +
                ", marketName: " + marketName + ", BuySale: " + buySale.getBuySale() + ", size: " + size;
    }
}
