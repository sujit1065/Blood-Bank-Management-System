package in.co.blood.bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.blood.bank.bean.BaseBean;
import in.co.blood.bank.bean.HospitalBean;
import in.co.blood.bank.bean.OrgnizationBean;
import in.co.blood.bank.bean.RoleBean;
import in.co.blood.bank.bean.UserBean;
import in.co.blood.bank.exception.ApplicationException;
import in.co.blood.bank.exception.DuplicateRecordException;
import in.co.blood.bank.model.HospitalModel;
import in.co.blood.bank.model.OrgnizationModel;
import in.co.blood.bank.model.UserModel;
import in.co.blood.bank.util.DataUtility;
import in.co.blood.bank.util.DataValidator;
import in.co.blood.bank.util.PropertyReader;
import in.co.blood.bank.util.ServletUtility;

/**
 * Servlet implementation class OrganizationCtl
 */
@WebServlet(name = "OrganizationCtl", urlPatterns = { "/ctl/organization" })
public class OrganizationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(OrganizationCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("OrganizationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} 
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(id==0) {
		
		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		

		

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		
		}
		
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid","Mobile No"));
			pass=false;
		} 
		
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require","City"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require","Address"));
			pass = false;
		}
		
			log.debug("OrganizationCtl Method validate Ended");
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("OrganizationCtl Method populatebean Started");

		OrgnizationBean bean = new OrgnizationBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		
		bean.setName(DataUtility.getString(request
				.getParameter("name")));

		if(bean.getId()==0) {
			bean.setLogin(DataUtility.getString(request.getParameter("login")));
	
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
	
			bean.setConfirmPassword(DataUtility.getString(request
					.getParameter("confirmPassword")));
		}	
			
			
			bean.setContectNo(DataUtility.getString(request.getParameter("mobile")));
			
			bean.setAddress(DataUtility.getString(request.getParameter("address")));
			bean.setCity(DataUtility.getString(request.getParameter("city")));
			
			populateDTO(bean, request);
	
			log.debug("OrganizationCtl Method populatebean Ended");
	
			return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("OrganizationCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		OrgnizationModel model = new OrgnizationModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			OrgnizationBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("OrganizationCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("OrganizationCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        OrgnizationModel model = new OrgnizationModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            OrgnizationBean bean = (OrgnizationBean) populateBean(request);
            
            try {
                if (id > 0) {
                	OrgnizationBean hBean=model.findByPK(id);
                	bean.setLogin(hBean.getLogin());
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                	
                	UserBean uBean=new UserBean();
                	UserModel uModel=new UserModel();
                	uBean.setRoleId(RoleBean.ORGANIZATION);
                	uBean.setFirstName(bean.getName());
                	uBean.setLastName(bean.getName());
                	uBean.setLogin(bean.getLogin());
                	uBean.setPassword(bean.getPassword());
                	uBean.setMobileNo(bean.getContectNo());
                	uModel.registerUser(uBean);
                    long pk = model.add(bean);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }
              
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage(e.getMessage(), request);
            }
            ServletUtility.forward(getView(), request, response);
        }else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(BBMView.ORGNIZATION_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(BBMView.ORGNIZATION_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("OrganizationCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return BBMView.ORGNIZATION_VIEW;
	}

}
