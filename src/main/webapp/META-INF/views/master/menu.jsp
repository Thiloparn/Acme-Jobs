<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		
		<%-- COSAS DE ANONIMO --%>
		
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.announcement.list" action="/anonymous/announcement/list"/>
      <acme:menu-suboption code="master.menu.anonymous.company-record.list" action="/anonymous/company-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investor-record.list" action="/anonymous/investor-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investor-record.five-stars" action="/anonymous/investor-record/five-stars"/>
			<acme:menu-suboption code="master.menu.anonymous.company-record.five-stars" action="/anonymous/company-record/five-stars"/>
		</acme:menu-option>
		
		<%-- Yo pondria aqui otro desplegable que sea para los Bulletin, para que no se llene anonimo --%>
		
		
		<%-- COSAS DE AUTENTICADO --%>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()" >
			<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.company-record.list" action="/authenticated/company-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.investor-record.list" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.offer.list" action="/authenticated/offer/list"/>
			<acme:menu-suboption code="master.menu.authenticated.solicit.list" action="/authenticated/solicit/list"/>
		</acme:menu-option>
		
		<%-- COSAS DE ADMIN --%>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.announcement.list" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard.show" action="/administrator/dashboard/show"/>			
			<acme:menu-separator/>
      <acme:menu-suboption code="master.menu.administrator.customisation-parameter" action="/administrator/customisation-parameter/show"/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<%-- COSAS DE PROVIDER --%>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<%-- COSAS DE CONSUMER --%>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	</acme:menu-left>
		
		<%-- AQUI COMIENZA LA PARTE DERECHA DEL MENU (DONDE ESTA EL LOGIN Y DEMAS) --%>
		
	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>
