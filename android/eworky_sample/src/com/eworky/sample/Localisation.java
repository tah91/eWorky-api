package com.eworky.sample;

import java.io.Serializable;

public class Localisation implements Serializable{
	
	private static final long serialVersionUID = 6794898677027141412L;
	
	public int id;
	public String name;
	public double latitude;
	public double longitude;
	public String description;
	public String image;
	public String address;
	public String city;
	public String type;
	public String url;
    public double rating;
	//public List<CommentJson> comments;
    //public List<MemberJson> fans;
    
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Loc [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
