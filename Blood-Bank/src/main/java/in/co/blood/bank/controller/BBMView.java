package in.co.blood.bank.controller;

public interface BBMView {
	
	public String APP_CONTEXT = "/Blood-Bank";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";


	
	public String HOSPITAL_VIEW = PAGE_FOLDER + "/HospitalView.jsp";	
	public String HOSPITAL_LIST_VIEW = PAGE_FOLDER + "/HospitalListView.jsp";
	
	
	public String BLOODBANK_VIEW = PAGE_FOLDER + "/BloodBankView.jsp";	
	public String BLOODBANK_LIST_VIEW = PAGE_FOLDER + "/BloodBankListView.jsp";
	
	
	public String ORGNIZATION_VIEW = PAGE_FOLDER + "/OrganizationView.jsp";	
	public String ORGNIZATION_LIST_VIEW = PAGE_FOLDER + "/OrganizationListView.jsp";
	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	
	public String SEARCH_DONOR_VIEW = PAGE_FOLDER + "/SearchDonorView.jsp";
	
		
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/error";

	
	
	public String USER_CTL = APP_CONTEXT + "/ctl/user";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/userList";
	
	public String BLOODBANK_CTL = APP_CONTEXT + "/ctl/bloodBank";
	public String BLOODBANK_LIST_CTL = APP_CONTEXT + "/ctl/bloodBankList";
	
	
	public String SEARCH_DONOR_CTL = APP_CONTEXT + "/searchDonor";
	
	public String HOSPITAL_CTL = APP_CONTEXT + "/ctl/hospital";
	public String HOSPITAL_LIST_CTL = APP_CONTEXT + "/ctl/hospitalList";
	
	public String ORGNIZATION_CTL = APP_CONTEXT + "/ctl/organization";
	public String ORGNIZATION_LIST_CTL = APP_CONTEXT + "/ctl/organizationList";
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/register";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGOUT_CTL = APP_CONTEXT + "/login";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/getMarksheet";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/marksheetMeritList";



}
