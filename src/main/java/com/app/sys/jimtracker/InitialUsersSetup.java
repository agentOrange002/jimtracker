package com.app.sys.jimtracker;

/*
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.stereotype.Component;
 * 
 * import com.app.sys.jimtracker.repository.AuthorityRepository; import
 * com.app.sys.jimtracker.repository.RoleRepository; import
 * com.app.sys.jimtracker.repository.UserRepository; import
 * com.app.sys.jimtracker.tool.Utils;
 * 
 * import java.util.Arrays; import java.util.Collection;
 * 
 * import org.springframework.boot.context.event.ApplicationReadyEvent; import
 * org.springframework.context.event.EventListener;
 * 
 * import org.springframework.transaction.annotation.Transactional;
 * 
 * import com.app.sys.jimtracker.entity.AuthorityEntity; import
 * com.app.sys.jimtracker.entity.RoleEntity; import
 * com.app.sys.jimtracker.entity.UserEntity;
 * 
 * import com.app.sys.jimtracker.tool.Roles;
 */

/*
 * @Component public class InitialUsersSetup {
 * 
 * @Autowired UserRepository userRepository;
 * 
 * @Autowired AuthorityRepository authorityRepository;
 * 
 * @Autowired RoleRepository roleRepository;
 * 
 * @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
 * 
 * @Autowired Utils utils;
 * 
 * @EventListener
 */

/*	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("From Application ready event...");

		AuthorityEntity readAuthority = createAuthority("GET_USER_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("SAVE_USER_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_USER_AUTHORITY");
		AuthorityEntity updateAuthority = createAuthority("UPDATE_USER_AUTHORITY");

		AuthorityEntity a = createAuthority("DASHBOARD");
		AuthorityEntity b = createAuthority("SUPPORT_DASHBOARD");
		AuthorityEntity c = createAuthority("ISSUE_MAINTENEANCE");
		AuthorityEntity d = createAuthority("TICKET_MAINTENANCE");
		AuthorityEntity e = createAuthority("TASK_MAINTENANCE");
		AuthorityEntity f = createAuthority("USER_MAINTENANCE");
		AuthorityEntity g = createAuthority("ADMIN TOOLS");

		
		 * RoleEntity roleAdmin = createRole(Roles.ROLE_USER.name(),
		 * Arrays.asList(readAuthority, writeAuthority, deleteAuthority,
		 * updateAuthority));
		 
		
		 * RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(),
		 * Arrays.asList(readAuthority, writeAuthority, deleteAuthority,
		 * updateAuthority));
		 

		
		 * if (roleAdmin == null) return;
		 
		
		  createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority,
					 updateAuthority, a, b, c, d, e, f,g));
		  
		  RoleEntity role = roleRepository.findByName(Roles.ROLE_USER.name());
		 // role.getAuthorities().addAll(Arrays.asList(a, b, c, d, e, f,g));
		  role.getAuthorities().removeAll(Arrays.asList(a, b, c, d, e, f,g));
		  roleRepository.saveAndFlush(role);
		  
			
			 * RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(),
			 * Arrays.asList(readAuthority, writeAuthority, deleteAuthority,
			 * updateAuthority, a, b, c, d, e, f));
			 * 
			 * if (roleAdmin == null) return;
			 
		 

		
		 * UserEntity adminUser = new UserEntity();
		 * 
		 * adminUser.setFirstName("USER"); adminUser.setMiddleName("USER");
		 * adminUser.setLastName("USER"); adminUser.setFullName("USER");
		 * adminUser.setEmail("user@user.com");
		 * adminUser.setEmailVerificationStatus(true);
		 * adminUser.setUserId(utils.generateUserId(30));
		 * adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("password123"));
		 * 
		 * adminUser.setRoles(Arrays.asList(roleAdmin)); userRepository.save(adminUser);
		 
		
		 * RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(),
		 * Arrays.asList(readAuthority, writeAuthority, deleteAuthority,
		 * updateAuthority, a, b, c, d, e));
		 * 
		 * RoleEntity role = roleRepository.findByName("ROLE_ADMIN");
		 * 
		 * UserEntity user =
		 * userRepository.findByUserId("JHivcNsy7HLTexVlszCbRwfVNYEjCp");
		 * user.setRoles(Arrays.asList(role)); userRepository.save(user);
		 

	}

	@Transactional
	private AuthorityEntity createAuthority(String name) {
		AuthorityEntity authority = authorityRepository.findByName(name);
		if (authority == null) {
			authority = new AuthorityEntity(name);
			authorityRepository.saveAndFlush(authority);
		}
		return authority;
	}

	@Transactional
	private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
		RoleEntity resultEntity = new RoleEntity();
		RoleEntity role = roleRepository.findByName(name);		
		if (role == null) {
			for(AuthorityEntity auth: authorities){
				if(!role.getAuthorities().contains(auth)){
					role.getAuthorities().add(auth);
				}				
			}			 
			resultEntity = roleRepository.save(role);			
		}
		return resultEntity;
	}

}}*/
