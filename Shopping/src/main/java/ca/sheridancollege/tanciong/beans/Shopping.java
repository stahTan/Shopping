package ca.sheridancollege.tanciong.beans;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shopping {
	private Long id;
	private String itemName;
	private int qty;
	private BigDecimal price;
	private String storeName;
	private String[] storeNames = { "WalMart", "Superstore", "HomeDepot", "T&T", "Amazon", "Shoppers", "SportChek"};
	private String url;
	private String itemDescription;
}
