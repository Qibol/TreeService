package com.treeservice.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.velocity.context.Context;

/**
 * �����, ������� �������� ������ ��� ������������� ��������� ������� ����������
 * 
 * 
 */
public class ContextFiller {

	/**
	 * �������� ������ ������ �����<br />
	 * ������ ���������� ������, ��������������� vm-���
	 * 
	 * @param context
	 *            Context vm-��
	 * @param companyId
	 *            �� ������������
	 * @param userId
	 * @param params
	 *            ������ ����������� �������� ���������� ��� vm-��
	 * @param rules
	 */
	public static void fillContext(final VMPages page, final Context context,
			final BigInteger companyId, final BigInteger userId,
			final HashMap<String, Object> params) {
		prepareIncludesByPage(page, context);

		switch (page) {
		case ENTITIES_LIST:
			fillEntitiesList(context, companyId, params);
			break;
		case ENTITIES_CREATE:
			fillEntitiesCreate(context, companyId, params);
			break;
		}
	}

	/**
	 * ������������� ���������� ��������� (������� ������ ���� � �.�.)
	 * 
	 * @param context
	 */
	public static void fillTemplateParams(Context context, BigInteger companyId) {
		try {
			context.put("hasRoof", true);
			context.put("hasLeftMenu", true);
			context.put("cssBase", Configuration.getInstance().get("cssBase"));
			context.put("jsBase", Configuration.getInstance().get("jsBase"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fillError(Context context, BigInteger companyId,
			HashMap<String, Object> params) {
		fillTemplateParams(context, companyId);
	}

	/**
	 * JS � CSS � ����������� �� ���������
	 * 
	 * @param page
	 * @param context
	 */
	private static void prepareIncludesByPage(VMPages page, Context context) {
		try {
			ArrayList<String> jsList = new ArrayList<String>();
			ArrayList<String> cssList = new ArrayList<String>();

			// Every fucking page
			jsList.add("dhtmlxcommon.js");
			jsList.add("dhtmlxtree.js");

			cssList.add("style.css");

			context.put("jsList", jsList);
			context.put("cssList", cssList);
		} catch (Exception e) {
			System.out.println("sun of a bitch");
			e.printStackTrace();
		}
	}

	private static void fillEntitiesCreate(Context context,
			BigInteger companyId, HashMap<String, Object> params) {
		context.put("leftMenuAct", "��� ��������");

		Object pageCountParam = params.get("pageCount");
		Object pageLimitParam = params.get("pageLimit");
		Object currentPageParam = params.get("currentPage");
		Object sortingParam = params.get("sorting");

		int pageLimit = (pageLimitParam == null) ? 10 : Integer
				.parseInt(pageLimitParam.toString());

		int currentPage = (currentPageParam == null) ? 1 : Integer
				.parseInt(currentPageParam.toString());
	}

	private static void fillEntitiesList(Context context, BigInteger companyId,
			HashMap<String, Object> params) {
		context.put("leftMenuAct", "��� ��������");

		Object pageCountParam = params.get("pageCount");
		Object pageLimitParam = params.get("pageLimit");
		Object currentPageParam = params.get("currentPage");
		Object sortingParam = params.get("sorting");

		int pageLimit = (pageLimitParam == null) ? 10 : Integer
				.parseInt(pageLimitParam.toString());

		int currentPage = (currentPageParam == null) ? 1 : Integer
				.parseInt(currentPageParam.toString());
	}
}