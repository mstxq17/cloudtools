package com.xq17.cloudtools.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xq17.cloudtools.bean.FileStore;
import com.xq17.cloudtools.bean.User;
import com.xq17.cloudtools.service.FileStoreService;
import com.xq17.cloudtools.service.UserService;
import com.xq17.cloudtools.utils.SendEmailUtil;
import com.xq17.cloudtools.vo.ExceptionMsg;
import com.xq17.cloudtools.vo.ResultVo;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户注册类
 * @author xq17
 * @date 2020年9月21日
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	FileStoreService fsService;

	@Autowired
	private SendEmailUtil sendEmailUtil;

	@PostMapping("/api/login")
	public ResultVo login(@RequestParam Map<String, Object> map, HttpSession session) {
		if (map == null || map.equals("")) {
			return new ResultVo(301, "输入信息有误!", "");
		}
		// 先判断验证码
		String rightCode = String.valueOf(session.getAttribute("vCode"));
		String code = (String) map.get("code");
		if (code.equals("")) {
			return new ResultVo(301, "输入信息有误!", "");
		}
		if (code.equalsIgnoreCase(rightCode)) {
			// 及时清空验证码
			session.removeAttribute("vCode");
			String adminPath = "admin.html";
			User _user = userService.login(map);
			Map<String, Object> rsp = new HashMap<String, Object>();
			map.put("adminPath", adminPath);
			// 验证码正确
			if (_user != null) {
				if(_user.getRole() == 1) {
					//设置登录标识
					session.setAttribute("role", 1);
					session.setAttribute("userId", _user.getUserId());
					session.setAttribute("isLogin", true);
					return new ResultVo(200, "登录成功,跳转到管理界面", rsp);
				}else {
					session.setAttribute("role", 0);
					session.setAttribute("userId", _user.getUserId());
					session.setAttribute("isLogin", true);
					return new ResultVo(200, "登录成功,跳转到用户界面", "");
				}
			} else {
				return new ResultVo(301, "用户名或密码错误!", "");
			}
		} else {
			return new ResultVo(501, "验证码错误！", "");
		}
	}

	/**
	 * 
	    * @Title: register  
	    * @Description: 注册新用户
	    * @param @param user
	    * @param @param session
	    * @param @return    参数  
	    * @return ResultVo    返回类型  
	    * @throws
	 */
	@PostMapping("/api/register")
	public ResultVo register(User user, String regCode, HttpSession session) {
		Object object = session.getAttribute("regCode");
		Map<String, Object> map = (Map<String, Object>) object;
		String rightRegCode = "";
		String RightRegEmail = "";
		String email = user.getEmail();
		String username = user.getUserName();
		user.setImagePath("../static/image/who.jpg");
		// 普通新用户注册
		user.setRole(0);
		user.setRegisterTime(new Date());
		// 判断用户名是否已经存在
		if (userService.findByName(username) != null) {
			return new ResultVo(301, "用户名已经被注册,请更换...", "");
		}

		if (map != null)
		{
			rightRegCode = (String) map.get("regCode");
			RightRegEmail = (String) map.get("email");
			// System.out.println(rightRegCode);
			// System.out.println(RightRegEmail);
			// System.out.println(regCode);
			// System.out.println(email);
			if (rightRegCode.equals(regCode) && RightRegEmail.equals(email)) {
				// 邮箱注册码检验通过
				// 清除掉session
				if (userService.insert(user) > 0) {
					// 为User分配一个文件仓库

					try {
						FileStore store = new FileStore();
						store.setUserId(user.getUserId());
						fsService.addFileStore(store);
						user.setFileStoreId(store.getFileStoreId());
						userService.update(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ResultVo(301, "为用户分配仓库失败!", "");
					}
					session.removeAttribute("regCode");
					session.removeAttribute("vCode");
					return new ResultVo(200, "注册成功,即将跳转至登录页面!", "");
				}


			} else {
				// 防止爆破一定要及时清空session,刷新验证码
				session.removeAttribute("vCode");
				return new ResultVo(301, "注册失败,注册码与邮箱不匹配!", "");
			}
		} else {
			return new ResultVo(301, "注册码已经过期!", "");
		}
		return null;
	}
	

	/**
	 * 
	    * @Title: sendEmail  
	    * @Description: 发送邮件获取注册码
	    * @param @param session
	    * @param @param email
	    * @param @param userName
	    * @param @param code
	    * @param @return    参数  
	    * @return ResultVo    返回类型  
	    * @throws
	 */
	@PostMapping("/api/sendEmail")
	public ResultVo sendEmail(HttpSession session, String email, String userName, String code) {
		if ("".equals(email) || "".equals(userName) || "".equals(code)) {
			return new ResultVo(301, "邮箱 ||用户名||验证码 不允许为空!", "");
		}
		if (session.getAttribute("regCode") != null) {
			return new ResultVo(301, "请求过于频繁!", "");
		}
		// 判读验证码是否正确
		Object obj = session.getAttribute("vCode");
		if (obj == null) {
			new ResultVo(501, "验证码已经过期!", "");
		}
		// 判断邮箱是否已经存在
		if (userService.findByEmail(email) != null) {
			session.removeAttribute("vCode");
			return new ResultVo(501, "该邮箱已经存在!", "");
		}

		String vCode = String.valueOf(obj);
		// System.out.println(code);
		// 如果验证码正确,则向邮箱发送注册码
		if (code.equalsIgnoreCase(vCode)) {
			//生成随机的6位注册码
			String regCode = "";
			Random rd = new Random();
			while(regCode.length() < 6) {
				regCode += rd.nextInt(10);
			}

			boolean flag = false;
			try {
				flag = sendEmailUtil.sendHtmlMail(email, userName, regCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag) {
				// 邮件发送成功
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("email", email);
				map.put("regCode", regCode);
				// 存储session,然后开始计时清楚
				session.setAttribute("regCode", map);
				TimerTask task = new TimerTask() {

					@Override
					public void run() {
						// 清除session
						session.removeAttribute("regCode");
					}
				};
				// 启用一个定时器,清空session
				Timer timer = new Timer();
				// 3分钟清除session
				timer.schedule(task, 180000);
				return new ResultVo(200, "成功发送注册码到指定邮箱!", "");
			} else {
				return new ResultVo(ExceptionMsg.FAILED);
			}
		} else {
			// 清空验证码
			session.removeAttribute("vCode");
			return new ResultVo(501, "验证码错误!", "");
		}
	}
	
}
