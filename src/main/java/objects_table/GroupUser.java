package objects_table;

import interfaces.ObjectInterface;

public class GroupUser implements ObjectInterface {
	long group, user;

	public GroupUser() {
		
	}
	
	public long getGroup() {
		return group;
	}



	public void setGroup(long group) {
		this.group = group;
	}



	public long getUser() {
		return user;
	}



	public void setUser(long user) {
		this.user = user;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
