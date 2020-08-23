package objects;

import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.ObjectInterface;
import utilities.JSONUtility;

public class User implements ObjectInterface{
	long id; 
	String username, icon_url, profile_card, profile_page, custom_url;
	long[] staff_roles, trade_roles;
	JSONObject settings, social_media, payment_info, login_history;
	Timestamp join_date;
	
	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long user_id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUserame(String name) {
		this.username = name;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getProfile_card() {
		return profile_card;
	}

	public void setProfile_card(String profile_card) {
		this.profile_card = profile_card;
	}

	public String getProfile_page() {
		return profile_page;
	}

	public void setProfile_page(String profile_page) {
		this.profile_page = profile_page;
	}

	public String getCustom_url() {
		return custom_url;
	}

	public void setCustom_url(String custom_url) {
		this.custom_url = custom_url;
	}

	public long[] getStaff_roles() {
		return staff_roles;
	}

	public void setStaff_roles(long[] staff_roles) {
		this.staff_roles = staff_roles;
	}

	public long[] getTrade_roles() {
		return trade_roles;
	}

	public void setTrade_roles(long[] trade_roles) {
		this.trade_roles = trade_roles;
	}

	public JSONObject getSettings() {
		return settings;
	}

	public void setSettings(JSONObject settings) {
		this.settings = settings;
	}

	public JSONObject getSocial_media() {
		return social_media;
	}

	public void setSocial_media(JSONObject social_media) {
		this.social_media = social_media;
	}
	
	public JSONObject getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(JSONObject payment_info) {
		this.payment_info = payment_info;
	}

	public JSONObject getLogin_history() {
		return login_history;
	}

	public void setLogin_history(JSONObject login_history) {
		this.login_history = login_history;
	}

	public Timestamp getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
}

