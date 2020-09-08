package com.mercury.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.grade.Grade;
import com.mercury.jpa.model.mileage.Mileage;
import com.mercury.jpa.model.mileage.MileageRequest;
import com.mercury.jpa.model.role.Role;
import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.user.UserGrade;
import com.mercury.jpa.model.user.UserNotice;
import com.mercury.jpa.model.user.UserRole;
import com.mercury.jpa.repository.role.RoleRepository;
import com.mercury.jpa.repository.user.UserGradeRepository;
import com.mercury.jpa.repository.user.UserNoticeRepository;
import com.mercury.jpa.repository.user.UserRepository;
import com.mercury.jpa.repository.user.UserRoleRepository;
import com.mercury.process.user.UserProcess;
import com.mercury.service.grade.GradeService;
import com.mercury.service.mail.MailService;
import com.mercury.service.mileage.MileageRequestService;
import com.mercury.service.mileage.MileageService;
import com.mercury.service.token.TokenRefreshService;
import com.mercury.user.CustomUserDetails;
import com.mercury.util.DateUtil;
import com.mercury.util.EncodingUtil;
import com.mercury.util.ObjectUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserService implements UserDetailsService {

	@Autowired
	private UserProcess userProcess;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private MileageService mileageService;
	@Autowired
	private MileageRequestService mileageRequestService;
	@Autowired
	private TokenRefreshService refreshTokenService;
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

	public <T extends Object> T findPW(String username, String email)
			throws Exception {
		CustomUserDetails user = (CustomUserDetails) loadUserByUsername(
				username);

		if (user == null) {
			return (T) Boolean.FALSE;
		}

		return (T) mailService.findPW(user.getUser(), email);
	}

	public <T extends Object> T findId(String email) throws Exception {
		return (T) mailService.findId(email);
	}

	public <T extends Object> T seUserByEmail(String email) throws Exception {
		return (T) userProcess.seUserByEmail(email);
	}

	public <T extends Object> T checkUserEmail(String email) throws Exception {
		User user = userProcess.seUserByEmail(email);
		if (user != null)
			return (T) Boolean.TRUE;

		return (T) Boolean.FALSE;
	}

	public <T extends Object> T checkUserName(String userName)
			throws Exception {
		CustomUserDetails user = (CustomUserDetails) loadUserByUsername(
				userName);

		if (user.getUser() != null)
			return (T) Boolean.TRUE;

		return (T) Boolean.FALSE;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
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
		return (T) userProcess.seUserByIdx(idx);
	}

	public <T extends Object> T signUp(User user) throws Exception {
		String userIdx = user.getIdx() == null
				? UUID.randomUUID().toString().replace("-", "")
				: user.getIdx();
		user.setIdx(userIdx);
		user.setInsertDate(DateUtil.now());
		user.setMileage(1000);
		user.setSns(user.getSns() == null ? "DEFUALT" : user.getSns());
		Mileage mileage = new Mileage();

		mileage.setUserName(user.getUsername());
		mileage.setPaymentMileage(1000);
		mileage.setContent("가입 축하 1000 포인트 지급");

		mileageService.inMileage(mileage);

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
	}

	public <T extends Object> T upUser(User user, UserRole role)
			throws Exception {
		user.setChangeDate(DateUtil.now());

		if (user.getNewPassword() != null && user.getNewPassword() != "") {
			// 사용자가 입력한 현재 비밀번호가 DB에 있는 비밀번호와 맞는지 확인
			// DB에 암호화 되어 있어서 확인하는 방법 필요
			User userInfo = userRepository.findByUsername(user.getUsername());
			Boolean matched = EncodingUtil.matches(user.getPassword(),
					userInfo.getPassword());
			if (!matched) {
				return null;
			} else {
				String newPwEncode = EncodingUtil
						.encodingPW(user.getNewPassword());
				user.setPassword(newPwEncode);
				user.setNewPassword(null);
			}
		}

		if (role != null) {
			List<UserRole> userRole = userRoleRepository
					.findByUserName(user.getUsername());
			for (UserRole r : userRole) {
				UserRole sumRole = ObjectUtil.toObj(role, r);
				userRoleRepository.save(sumRole);
			}
		}

		CustomUserDetails cu = (CustomUserDetails) loadUserByUsername(
				user.getUsername());

		User nu = ObjectUtil.toObj(user, cu.getUser());

		return (T) userProcess.upUser(nu);
	}

	/**
	 * 
	 * Three, Two는 제외 평생 기록 남겨 놓는다. 사용자 권한, 사용자 공지사항 확인 여부, 사용자 등급, 사용자 리프레시 토큰,
	 * 마일리지 이력, 사용자를 삭제한다.
	 * 
	 * @param <T>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T destoryUser(User user) throws Exception {
		// User Role Delete All By UserName
		List<UserRole> roles = userRoleRepository
				.findByUserName(user.getUsername());
		userRoleRepository.deleteAll(roles);

		// User Notice Delete All By UserName
		List<UserNotice> notices = userNoticeRepository
				.findByUserName(user.getUsername());
		userNoticeRepository.deleteAll(notices);

		// User Grade Delete All By UserName
		UserGrade grade = userGradeRepository
				.findByUserName(user.getUsername());
		userGradeRepository.delete(grade);

		// Refresh Token Delete All By UserName
		List<TokenRefresh> refresh = refreshTokenService
				.seRefreshTokenByUsername(user.getUsername());
		refreshTokenService.deRefreshTokenAllEntities(refresh);

		// Mileage History Delete All By UserName
		List<Mileage> mileage = mileageService
				.seMileageByUserName(user.getUsername());
		mileageService.deMileageAllEntities(mileage);

		// Mileage Request Delete All By User Name
		List<MileageRequest> mileageRequest = mileageRequestService
				.seMileageByUserName(user.getUsername());
		mileageRequestService.deMileageAllEntities(mileageRequest);

		// User Delete By User
		userProcess.destoryUser(user);
		return (T) Boolean.TRUE;
	}
}
