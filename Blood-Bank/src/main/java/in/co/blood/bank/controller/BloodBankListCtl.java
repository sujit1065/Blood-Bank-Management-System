package in.co.blood.bank.controller;

import java.io.IOException;
import java.util.List;

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
import in.co.blood.bank.bean.UserBean;
import in.co.blood.bank.exception.ApplicationException;
import in.co.blood.bank.model.BloodBankModel;
import in.co.blood.bank.model.HospitalModel;
import in.co.blood.bank.util.DataUtility;
import in.co.blood.bank.util.PropertyReader;
import in.co.blood.bank.util.ServletUtility;

/**
 * Servlet implementation class BloodBankListCtl
 */
@WebServlet(name = "BloodBankListCtl", urlPatterns = { "/ctl/bloodBankList" })
public class BloodBankListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	
	private static Logger log = Logger.getLogger(BloodBankListCtl.class);
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BloodBankListCtl populateBean method start");
		BloodBankBean bean = new BloodBankBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		log.debug("BloodBankListCtl populateBean method end");
		return bean;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BloodBankListCtl doGet Start");
		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		BloodBankBean bean = (BloodBankBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		BloodBankModel model = new BloodBankModel();
		
		try {
			
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			bean.setLogin(uBean.getLogin());
		
			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("BloodBankListCtl doPOst End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BloodBankListCtl doPost Start");

		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BloodBankBean bean = (BloodBankBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		BloodBankModel model = new BloodBankModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(BBMView.BLOODBANK_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					BloodBankBean deletebean = new BloodBankBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(BBMView.BLOODBANK_LIST_CTL, request, response);
				return;

			}
			
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			bean.setLogin(uBean.getLogin());

			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("BloodBankListCtl doGet End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return BBMView.BLOODBANK_LIST_VIEW;
	}

}
