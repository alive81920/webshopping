package dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StoreDao;
import entity.store;
import util.DBSQL;

public class StoreImpl implements StoreDao{

	@Override
	public boolean Add(store user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			DBSQL.init();
			String sql1="select * from store where UserName='"+user.getUserName()+"';";//判断登录名是否重复
			ResultSet rSet=DBSQL.SelectSQL(sql1);
			if(rSet.next())
				flag=false;
			else {
				String sql2="insert into store(UserName,UserPwd,UserEmail,UserPhone,UserAdress) values('"
					+ user.getUserName()+"','"
					+ user.getUserPwd()+"','" 
					+ user.getUserEmail()+"','"
					+ user.getUserPhone() +"','"
					+ user.getUserAdress()+"');";
				int i = 0;
				i = DBSQL.AddDelUp(sql2);
				if(i>0)
					flag=true;
			}
		rSet.close();
		DBSQL.closeSQL();
		return flag;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String name) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBSQL.init();
		String sql="delete from store where UserName='"+name+"';";
		int i = 0;
		i=DBSQL.AddDelUp(sql);
		if (i>0) {
			flag=true;
		}
		DBSQL.closeSQL();
		return flag;
	}

	@Override
	public boolean updata(String name, String pwd, String phone, String email, String adress) {
		// TODO Auto-generated method stub
		boolean flag=false;
		DBSQL.init();
		String sql="update store set "
				+"UserPwd='"+pwd+"', "
				+"UserEmail='"+email+"', "
				+"UserPhone='"+phone+"', "
				+"UserAdress='"+adress+"' "
				+"where UserName='"+name+"';";
		int i = 0;
		i = DBSQL.AddDelUp(sql);
		if (i>0) {
			flag=true;
		}
		DBSQL.closeSQL();
		return flag;
	}

	@Override
	public boolean updata_login(String name, String pwd, String phone, String email, String adress)
			throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			DBSQL.init();
			String sql1="select * from store where UserName='"+name+"';";//判断登录名是否重复
			ResultSet rSet=DBSQL.SelectSQL(sql1);
			if(rSet!=null)
				flag=false;
			else {
				String sql2="update store set "
				+"UserName='"+name+"'"
				+"where UserPwd='"+pwd+"', "
				+"UserEmail='"+email+"', "
				+"UserPhone='"+phone+"', "
				+"UserAdress='"+adress+"';";
				int i = 0;
				i = DBSQL.AddDelUp(sql2);
				if(i>0)
					flag=true;
			}
			rSet.close();
			DBSQL.closeSQL();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public store select(String name) {
		// TODO Auto-generated method stub
		store cus=null;
		try {
			DBSQL.init();
			String sql1="select * from store where UserName='"+name+"';";
			ResultSet rSet=DBSQL.SelectSQL(sql1);
			if(rSet.next()) {
				cus=new store();
				cus.setID(rSet.getInt("ID"));
				cus.setUserName(rSet.getString("UserName"));
				cus.setUserPwd(rSet.getString("UserPwd"));
				cus.setUserEmail(rSet.getString("UserEmail"));
				cus.setUserPhone(rSet.getString("UserPhone"));
				cus.setUserAdress(rSet.getString("UserAdress"));
			}
			rSet.close();
			DBSQL.closeSQL();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cus;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			String sql="select UserName from store;";
			ResultSet rSet=DBSQL.SelectSQL(sql);
			while (rSet.next()) {
				i=i+1;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<store> SelectAll(String name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		List<store> list = new ArrayList<store>();
		store user=null;
		String sql="";
		if(name!=null)
			sql="select * from store where UserName='"+name+"' limit "+(pageNumber-1)*pageSize+","+pageSize+";";
		else 
			sql="select * from store limit "+(pageNumber-1)*pageSize+","+pageSize+";";
		try {
			DBSQL.init();
			ResultSet rSet=DBSQL.SelectSQL(sql);
			while (rSet.next()) {
				user=new store();
				user.setID(rSet.getInt("ID"));
				user.setUserName(rSet.getString("UserName"));
				user.setUserPwd(rSet.getString("UserPwd"));
				user.setUserEmail(rSet.getString("UserEmail"));
				user.setUserPhone(rSet.getString("UserPhone"));
				user.setUserAdress(rSet.getString("UserAdress"));
				list.add(user);
			}
			rSet.close();
			DBSQL.closeSQL();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public store selectbyid(int id) {
		store cus=null;
		try {
			DBSQL.init();
			String sql1="select * from store where ID='"+id+"';";
			ResultSet rSet=DBSQL.SelectSQL(sql1);
			if(rSet.next()) {
				cus=new store();
				cus.setID(rSet.getInt("ID"));
				cus.setUserName(rSet.getString("UserName"));
				cus.setUserPwd(rSet.getString("UserPwd"));
				cus.setUserEmail(rSet.getString("UserEmail"));
				cus.setUserPhone(rSet.getString("UserPhone"));
				cus.setUserAdress(rSet.getString("UserAdress"));
			}
			rSet.close();
			DBSQL.closeSQL();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cus;
	}
}
