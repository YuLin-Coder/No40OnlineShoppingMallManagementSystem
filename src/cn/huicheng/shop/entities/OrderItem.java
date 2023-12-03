package cn.huicheng.shop.entities;

public class OrderItem {
    private Integer itemid;

    private Integer count;

    private Double subtotal;

    private Integer pid;

    private Integer oid;
    
    private Product product;
    
    private Orders orders;

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
    
    public void setProduct(Product product) {
		this.product = product;
	}
    
    public Product getProduct() {
		return product;
	}

    public void setOrders(Orders orders) {
		this.orders = orders;
	}
    
    public Orders getOrders() {
		return orders;
	}
    
	@Override
	public String toString() {
		return "OrderItem [itemid=" + itemid + ", count=" + count + ", subtotal=" + subtotal + ", pid=" + pid + ", oid="
				+ oid + ", product=" + product + "]";
	}
    
}