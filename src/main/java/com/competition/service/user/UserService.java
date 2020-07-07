package com.competition.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.cash.Cash;
import com.competition.jpa.model.grade.Grade;
import com.competition.jpa.model.role.Role;
import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserGrade;
import com.competition.jpa.model.user.UserNotice;
import com.competition.jpa.model.user.UserRole;
import com.competition.jpa.repository.role.RoleRepository;
import com.competition.jpa.repository.user.UserGradeRepository;
import com.competition.jpa.repository.user.UserNoticeRepository;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.user.UserRoleRepository;
import com.competition.process.user.UserProcess;
import com.competition.service.cash.CashService;
import com.competition.service.grade.GradeService;
import com.competition.service.mail.MailService;
import com.competition.service.token.refresh.RefreshTokenService;
import com.competition.user.CustomUserDetails;
import com.competition.util.DateUtil;
import com.competition.util.ObjectUtil;

@Service
@SuppressWarnings("unchecked")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserProcess userProcess;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private CashService cashService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private UserGradeRepository userGradeRepository;
	@Autowired
	private UserNoticeRepository userNoticeRepository;
	
	public <T extends Object> T findPW(String username, String email) throws Exception {
		try {
			
			CustomUserDetails user = (CustomUserDetails) loadUserByUsername(username);
			
			if(user == null) {
				return (T) Boolean.FALSE;
			}
			
			return (T) mailService.findPW(user.getUser(), email);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T findId(String email) throws Exception {
		try {
			return (T) mailService.findId(email);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seUserByEmail(String email) throws Exception {
		try {
			return (T) userProcess.seUserByEmail(email);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T checkUserEmail(String email) throws Exception {
		try {
			User user = userProcess.seUserByEmail(email);
			if(user != null) return (T) Boolean.TRUE;
			
			return (T) Boolean.FALSE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T checkUserName(String userName) throws Exception {
		try {
			
			CustomUserDetails user = (CustomUserDetails) loadUserByUsername(userName);
			
			if(user.getUser() != null) return (T) Boolean.TRUE;
			
			return (T) Boolean.FALSE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		List<UserRole> roles = userRoleRepository.findByUserName(username);
		UserGrade grade = userGradeRepository.findByUserName(username);
		
		CustomUserDetails ud = new CustomUserDetails();
		ud.setUser(user);
		ud.setRoles(roles);
		ud.setGrade(grade);
		return ud;
	}
	
	public <T extends Object> T getLists() throws Exception {
		return (T) userProcess.getLists();
	}
	
	public <T extends Object> T seUserByIdx(String idx) throws Exception {
		try {
			return (T) userProcess.seUserByIdx(idx);
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
	public <T extends Object> T signUp(User user) throws Exception {
		try {
			String userIdx = user.getIdx() == null ? UUID.randomUUID().toString().replace("-", ""):user.getIdx(); 
			user.setIdx(userIdx);
			user.setInsertDate(DateUtil.now());
			user.setMileage(1000);
			user.setSns(user.getSns() == null ? "DEFUALT" : user.getSns());
			Cash cash = new Cash();

			cash.setIdx(UUID.randomUUID().toString().replace("-", ""));
			cash.setUserName(user.getUsername());
			cash.setWithDrawCash(0);
			cash.setWithDrawDate(DateUtil.now());
			cash.setPrevCash(0);
			cash.setAfterCash(1000);
			cash.setPaymentDate(null);
			cash.setApproval("N");
			cash.setWhy("가입 축하 1000 포인트 지급");
			
			cashService.inCash(cash);
			
			
			Role role_user = roleRepository.findByRoleName("ROLE_USER");
			
			UserRole userRole = new UserRole();
			userRole.setIdx(UUID.randomUUID().toString().replace("-", ""));
			userRole.setUserIdx(userIdx);
			userRole.setUserName(user.getUsername());
			userRole.setRoleIdx(role_user.getIdx());
			userRole.setRoleName(role_user.getRoleName());
			
			
			Grade gradeInfo = gradeService.seGrade("RED");
			
			UserGrade userGrade = new UserGrade();
			userGrade.setIdx(UUID.randomUUID().toString().replace("-", ""));
			userGrade.setUserIdx(userIdx);
			userGrade.setUserName(user.getUsername());
			userGrade.setGradeIdx(gradeInfo.getIdx());
			userGrade.setGradeName(gradeInfo.getGradeName());
			
			userRoleRepository.save(userRole);
			userGradeRepository.save(userGrade);
			
			return (T) userProcess.signUp(user);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
		
	}
	
	public <T extends Object> T upUser(User user, UserRole role) throws Exception {
		try {
			
			user.setChangeDate(DateUtil.now());
			
			if(role != null) {
				UserRole userRole = userRoleRepository.findByRoleName(role.getRoleName());
				UserRole sumRole = ObjectUtil.toObj(role, userRole);
				userRoleRepository.save(sumRole);
			}

			CustomUserDetails cu = (CustomUserDetails) loadUserByUsername(user.getUsername());
			
			User nu =  ObjectUtil.toObj(user, cu.getUser());
			
			return (T) userProcess.upUser(nu); 
		}catch(Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	/**
	 * 
	 * Three, Two는 제외 평생 기록 남겨 놓는다.
	 * 사용자 권한, 사용자 공지사항 확인 여부, 사용자 등급, 사용자 리프레시 토큰, 마일리지 이력, 사용자를 삭제한다.
	 * 
	 * @param <T>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T destoryUser(User user) throws Exception {
		try {
			// User Role Delete All By UserName
			List<UserRole> roles = userRoleRepository.findByUserName(user.getUsername());
			userRoleRepository.deleteAll(roles);
			
			// User Notice Delete All By UserName
			List<UserNotice> notices = userNoticeRepository.findByUserName(user.getUsername());
			userNoticeRepository.deleteAll(notices);
			
			// User Grade Delete All By UserName
			UserGrade grade = userGradeRepository.findByUserName(user.getUsername());
			userGradeRepository.delete(grade);
			
			// Refresh Token Delete All By UserName
			List<RefreshToken> refresh = refreshTokenService.seRefreshTokenByUsername(user.getUsername());
			refreshTokenService.deRefreshTokenAllEntities(refresh);
			
			// Cash History Delete All By UserName
			List<Cash> cash = cashService.seCashByUserName(user.getUsername());
			cashService.deCashAllEntities(cash);
			
			// User Delete By User
			userProcess.destoryUser(user);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
	}
}
