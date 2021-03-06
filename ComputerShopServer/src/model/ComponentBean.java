package model;

import java.io.Serializable;

public class ComponentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5048481196452533521L;

	private int id;
	private String name; // unique
	private double price;
	private static int availableQuantity = 0;
	private String description;
	private CategoryBean category;
	private String homeURL;
	private String pictureURL;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static int getAvailableQuantity() {
		return availableQuantity;
	}

	public static void setAvailableQuantity(int availableQuantity) {
		ComponentBean.availableQuantity = availableQuantity;
	}
	
	public static void increaseAvailableQuantity(int added) {
		ComponentBean.availableQuantity = (ComponentBean.availableQuantity + added);
	}
	
	public static void decreaseAvailableQuantity(int removed) {
		ComponentBean.availableQuantity = (ComponentBean.availableQuantity - removed);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	public String getHomeURL() {
		return homeURL;
	}

	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public ComponentBean() {
	}

}
