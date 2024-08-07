//package com.voatingsys.service;
//
//import java.util.List;
//
//import com.voatingsys.entity.Admin;
//
//public interface AdminService {
//				
//	public void saveAdmin(Admin admin);
//	
//	public static final List<Admin> adminlist = null;
//
//}
package com.voatingsys.service;

import java.util.List;
import com.voatingsys.entity.Admin;

public interface AdminService {
    void saveAdmin(Admin admin);
    List<Admin> adminlist();
    Admin findByUsernameAndPassword(String username, String password);
	
}
