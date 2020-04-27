package objects;

import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONUtility;

public class User {
	long user_id; 
	String name, icon_url, profile_card, profile_page, custom_url;
	long[] staff_roles, trade_roles;
	PGobject settings, social_media, payment_info;
	PGobject[] login_history;
	Timestamp join_date;
	
	public User() {
		super();
	}

	public User(long user_id, String name, String icon_url, String profile_card, String profile_page, String custom_url,
			long[] staff_roles, long[] trade_roles, PGobject settings, PGobject social_media, PGobject payment_info,
			PGobject[] login_history) {
		this(name, icon_url, profile_card, profile_page, custom_url, staff_roles, trade_roles, settings, social_media, 
				payment_info, login_history);
		this.user_id = user_id;
	}

	public User(String name, String icon_url, String profile_card, String profile_page, String custom_url,
			long[] staff_roles, long[] trade_roles, PGobject settings, PGobject social_media, PGobject payment_info,
			PGobject[] login_history) {
		super();
		this.name = name;
		this.icon_url = icon_url;
		this.profile_card = profile_card;
		this.profile_page = profile_page;
		this.custom_url = custom_url;
		this.staff_roles = staff_roles;
		this.trade_roles = trade_roles;
		this.settings = settings;
		this.social_media = social_media;
		this.payment_info = payment_info;
		this.login_history = login_history;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public PGobject getSettings() {
		return settings;
	}

	public void setSettings(PGobject settings) {
		this.settings = settings;
	}

	public PGobject getSocial_media() {
		return social_media;
	}

	public void setSocial_media(PGobject social_media) {
		this.social_media = social_media;
	}
	
	public PGobject getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(PGobject payment_info) {
		this.payment_info = payment_info;
	}

	public PGobject[] getLogin_history() {
		return login_history;
	}

	public JSONObject[] getLogin_historyAsJSONObject() {
		return JSONUtility.PGtoJSON(login_history);
	}

	public void setLogin_history(PGobject[] login_history) {
		this.login_history = login_history;
	}

	
	public void setLogin_history(JSONObject[] login_history) {
		this.login_history = JSONUtility.JSONtoPG(login_history);
	}
	

	public Timestamp getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", icon_url=" + icon_url + ", profile_card="
				+ profile_card + ", profile_page=" + profile_page + ", custom_url=" + custom_url + ", staff_roles="
				+ Arrays.toString(staff_roles) + ", trade_roles=" + Arrays.toString(trade_roles) + ", settings="
				+ settings + ", social_media=" + social_media + ", payment_info=" + payment_info + ", login_history="
				+ Arrays.toString(login_history) + "]";
	}

}

