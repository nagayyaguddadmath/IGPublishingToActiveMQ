package igorders.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name = "Orders")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Orders {

    private List<Order> Orders = null;
    public List<Order> getOrders() {
        return Orders;
    }

    @XmlElement(name = "Order")
    public void setOrders(List<Order> Orders) {
        this.Orders = Orders;
    }
    public void add(Order Order) {
        if (this.Orders == null) {
            this.Orders = new ArrayList<Order>();
        }
        this.Orders.add(Order);
    }
}
