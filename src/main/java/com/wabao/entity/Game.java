package com.wabao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 游戏实体
 * 
 * @since 2015年11月24日 下午6:24:02
 * @author yaowenhao
 */
@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue
	private int id; // 游戏ID
	private String name; // 游戏名
	private String url; // 游戏的账户服务器地址

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", url=" + url + "]";
	}

}