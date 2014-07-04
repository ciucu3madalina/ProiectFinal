package com.ossvirtualshop.onlinemegazine.model;

public class Product {
	private String id;
	private String name;
	private int  price;
	private int count;
	
	public Product(String id,String name, int price,int count)
	{
		this.id=id;
		this.name=name;
		this.price=price;
		this.count=count;
		
		
	}
	public String getId()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}

	public int getPrice()
	{
		return this.price;
	}
	public int getCount()
	{
		return this.count;
	}
}
