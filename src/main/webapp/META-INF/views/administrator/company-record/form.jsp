<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.companyRecord.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.ceo" path="ceo"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.companyRecord.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.companyRecord.form.label.isIncorporated" path="isIncorporated"/>
	<acme:form-integer code="administrator.companyRecord.form.label.numberStars" path="numberStars" placeholder="1 2 3 4 5"/>
	
	<acme:form-submit test="${command == 'create'}"
			code="administrator.companyRecord.form.button.create"
			action="/administrator/company-record/create"/>
	
	<acme:form-return code="administrator.companyRecord.form.button.return"/>
</acme:form>