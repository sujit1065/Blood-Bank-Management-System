package in.co.blood.bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import in.co.blood.bank.bean.BaseBean;
import in.co.blood.bank.bean.BloodBankBean;
import in.co.blood.bank.bean.HospitalBean;
import in.co.blood.bank.bean.RoleBean;
import in.co.blood.bank.bean.UserBean;
import in.co.blood.bank.exception.ApplicationException;
import in.co.blood.bank.exception.DuplicateRecordException;
import in.co.blood.bank.model.BloodBankModel;
import in.co.blood.bank.model.HospitalModel;
import in.co.blood.bank.model.UserModel;
import in.co.blood.bank.util.DataUtility;
import in.co.blood.bank.util.DataValidator;
import in.co.blood.bank.util.PropertyReader;
import in.co.blood.bank.util.ServletUtility;

/**
 * Servlet implementation class BloodBankCtl
 */
@WebServlet(name = "BloodBankCtl", urlPatterns = { "/ctl/bloodBank" })
public class BloodBankCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BloodBankCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("BloodBankCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("bGroup"))) {
			request.setAttribute("bGroup", PropertyReader.getValue("error.require", "Blood Group"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid", "Mobile No"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		log.debug("BloodBankCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BloodBankCtl Method populatebean Started");

		BloodBankBean bean = new BloodBankBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bGroup")));

		bean.setContactNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);

		log.debug("BloodBankCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BloodBankCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		BloodBankModel model = new BloodBankModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
   
			BloodBankBean bean;
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
        log.debug("BloodBankCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BloodBankCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        BloodBankModel model = new BloodBankModel();
        HttpSession session=request.getSession();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            BloodBankBean bean = (BloodBankBean) populateBean(request);
            UserBean uBean=(UserBean)session.getAttribute("user");
           
            if(uBean.getRoleId()==1) {
            	bean.setUploadBy(uBean.getFirstName()+" "+uBean.getLastName()+" "+"Admin");
            	bean.setLogin(uBean.getLogin());
            }else if(uBean.getRoleId()==2) {
            	bean.setUploadBy(uBean.getFirstName()+" "+"Hospital");
            	bean.setLogin(uBean.getLogin());
            }else if(uBean.getRoleId()==3) {
            	bean.setUploadBy(uBean.getFirstName()+" "+"Organization");
            	bean.setLogin(uBean.getLogin());
            }else if(uBean.getRoleId()==4) {
            	bean.setUploadBy(uBean.getFirstName()+" "+uBean.getLastName()+" "+"It Self");
            	bean.setLogin(uBean.getLogin());
            }
            try {
                if (id > 0) {
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {	
                    long pk = model.add(bean);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }
              
               
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
        	ServletUtility.redirect(BBMView.BLOODBANK_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(BBMView.BLOODBANK_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("BloodBankCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return BBMView.BLOODBANK_VIEW;
	}

}
