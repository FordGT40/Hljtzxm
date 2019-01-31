package com.wisdom.hljtzxm;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//import com.lidroid.xutils.BitmapUtils;
//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.http.RequestParams;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
//import com.lidroid.xutils.util.LogUtils;
//import com.wisdom.hljtzxm.R;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

/**
 * @author go ������
 * 
 */
public class U {
	private static Toast toast;
	/**************************** ��������ӿڵ�ַ���忪ʼ ****************************/
	// �ٵ� ʡ����������
	public static final String AREA_CODE = "230000";
	//����ʡ���������� 210000
	//�������������� 230000
	//
	// �ٵ� ʡ��
	// public static final String AREA_NAME = "������ʡ";
	public static final String APP_KEY = "4E96A006-2F41-452C-A929-5C2153C6F221";
	// �����ַ
	public static final String URl_GENGGAI_CITY = "http://192.168.1.73:8030/tzxm_service";//�ߺ��ײ��Ե�ַ
	//http://192.168.1.73:8030/tzxm_service �ߺ�����Ե�ַ
	//public static final String URl_GENGGAI_CITY = "http://1.189.195.101/tzxm_service";
	//��ʽ������http://1.189.195.101/tzxm_service/
	// public static final String URl_GENGGAI_CITY =
	// http://218.60.145.40/tzxm_service/v1/";
	//http://192.168.1.250:8090/tzxm_service  �������Ե�ַ
	//http://192.168.1.132:8087/tzxm_service/  ���������Ե�ַ
	// ����ְ��
	public static final String URl_PARTMENT_DUTY = "/v1/departments/responsibility";
	// ���ɷ���
	public static final String URl_LAWS_REGULATIONS = "/v1/laws/laws";
	// ��¼�ӿ�
	public static final String URL_LOGIN = "/v1/account/login";
	public static final int ACCOUNT_PWD_NOT_CORRECT = 30604;
	public static final int UP_LIMIT = 30606;
	//��֤��¼
	public static final String CHECK_TOKEN = "/v1/profile/check_token";
	
	// ע��ӿ�
	public static final String URL_REGISTER = "/v1/account/register";
	public static final int ACCOUNT_HAS_BEEN_USED = 30607;
	// ��ȡ�ֻ���֤��
	public static final String URL_AUTHENTICODE = "/v1/extra/authenticode";
	public static final int AUTHENTICODE_SYSTEM_ERROR = 10301;
	public static final int AUTHENTICODE_BEEN_OCCUPIED = 30602;
	public static final int AUTHENTICODE_REACHED_MAX = 30801;
	public static final int AUTHENTICODE_FREQUENT = 30802;
	public static final int AUTHENTICODE_NOT_MATCH = 30608;
	public static final int AUTHENTICODE_NOT_PHONE = 20107;
	// �޸�����
	public static final String URL_EDIT_PWD = "/v1/account/change_password";
	public static final int EDIT_PWD_ERROR = 30605;
	// ��ȡ��Ŀ����Ŀ¼
	public static final String URL_HQ_XM_SPML = "/v1/projects/catalogs";
	public static final String HQ_XM_SPML_SP = "A00001";
	public static final String HQ_XM_SPML_HZ = "A00002";
	public static final String HQ_XM_SPML_BA = "A00003";

	// 4.��ȡʡ����������
	public static final String URL_HQ_XMSPMU_SJ = "/v1/areas/states";
	// 5.��ȡ���м���������
	public static final String URL_HQ_XMSPMU_SHIJ = "/v1/areas/cities";
	// 6.��ȡ���ؼ���������
	public static final String URL_HQ_XMSPMU_QXJ = "/v1/areas/districts";
	
	public static final String URL_HQ_XMJSD_GUOBIE = "";

	// ��������ʾ
	public static final String URL_BL_JG_GS = "/v1/projects/publicity";
	// ��������ʾ �������
	public static final String BL_JG_GS_PFBJ = "A0501";
	// ��������ʾ ��������
	public static final String BL_JG_GS_BYSL = "A0300";
	// ��Ŀ����ָ��
	public static final String URL_BA_ZN = "/v1/projects/item_guide";
	// ��Ҫ����
	public static final String URL_ZY_GG = "/v1/extra/announcement";
	// �һ�����1
	public static final String URL_FIND_PWD_ONE = "/v1/account/forget_password1";
	public static final int FIND_PWD_ONE_AUTHENTICODE_SYSTEM_ERROR = 30603;
	// �����ֻ�1
	public static final String URL_BANG_DING_TEL_ONE = "/v1/account/untie_phone";
	// �����ֻ�2
	public static final String URL_BANG_DING_TEL_TWO = "/v1/account/bind_phone";
	// ���뼯·��
	public static final String URL_DAI_MA_JI = "/v1/codes/general_code";
	// ������ҵ
	public static final String URL_GUOBIAO_HANGYE = "/v1/codes/national_industry";
	// ����ݸ�
	public static final String URL_SAVE_BASIC_FORM = "/v1/approvals/save_basic_form";
	/*************************************/

	//��Ŀ����
	public static final String URL_PROGRESS_QUERY  = "http://192.168.1.73:8030/tzxm_service/v1/itemReply/xmjd";
	
	
	// ��������ͼƬ
	public static final String URL_NEWS_PICTURE = "/mpc/indexImagesUrl";

	/**************************** ��������ӿڵ�ַ������� ****************************/
	public static String USERNAME = "";
	public static String PASSWORD = "";
	public static String ACCESS_TOKEN = "";
	public static String EXPIRES_IN = "";
	public static String UID = "";
	public static String TEL = "";
	public static String EMALL = "";
	public static String NAME = "";// �洢�û���
	public static int LOGIN_STATE = 0;// 0-δ��¼
	public static int GO_HOME;
	public static int REGISTER_TYPE;
	public static int ISME = 0;// 1-"�ҵ�"ҳ����ת
	public static int ISDECLARE = 0;// 1-"�걨--����"ҳ����ת 2-"�걨--��׼"
									// 3-"�걨--���� 4-"ע��֮���¼ҳ��"

	public static String nameGBHY1 = "";// ������ҵ1
	public static String nameGBHY2 = "";// ������ҵ2
	public static String nameGBHY3 = "";// ������ҵ3
	public static String nameGBHY4 = "";// ������ҵ4
	public static String codeGBHY = "";// ������ҵ����
	public static String industry_all = "";// ���й�����ҵ����
	public static List<Map<String, String>> list_SBDW_JBXX;

	public static String sshy2 = "";// ������ҵ2
	public static String sshy = "";// ������ҵ
	public static String gbhy = "";// ���й�����ҵ
	public static String sfkxzqy = "";// �Ƿ����������
	public static String xmjsd = "";// ��Ŀ�����
	public static int startYear = 0;// ��Ŀ����ʱ��
	public static int endYear = 0;// ��Ŀ����ʱ��
	public static String kxzqy = "";// ����������
	public static String jsxz = "";// ��������
	public static String NONGKEN = "";
	public static int ssjb;// ��������
	public static String sycyzctmlx = "";
	public static String sycyzctm1 = "";
	public static String sycyzctm2 = "";
	public static String sycyzctm3 = "";
	public static String czfs = "";
	public static String jwtzfs = "";
	// �ļ�·��
	public static String FILE_PATH = "";
	public static String FLOWINSID = "";
	// public static JSONObject wsSbdwObj = new JSONObject();
	// lxd���
	//
	// �༭������Ϣ��״̬ û����ӹ�Ĭ��Ϊ0 operate�ֶ�Ϊadd����ӣ�
	public static String edit_zhuangtai = "0";

	// ��Ŀ��λ����
	public static final String ENTERPRISE_NATURE = "/v1/codes/enterprise_nature";
	// ���������ύ�ӿ�
	public static final String sp_tijiao = "/v1/approvals/submit_basic_form";
	// ������������Ŀ¼�ӿ�
	public static final String sp_banlishixiangmulu = "/v1/approvals/selectable_catalogs";
	// �����ڶ���ѡ��׶λ�ȡ��ĿĿ¼
	public static final String sp_xuanzemulu = "/v1/approvals/select_catalog";
	// ������������Ŀ�걨��ť�ӿ� ��ȡ��ѡ�����ĿĿ¼
	public static final String sp_shenbaoxiangmu = "/v1/approvals/select_items";
	// ѡ����������İ���ʽ
	public static final String SP_BANLIFANGSHI = "/v1/approvals/select_hand_mode";
	// ��ȡ������Ϣ��
	public static final String sp_getjbxx = "/v1/approvals/basic_form";
	// ��ȡͶ�ʷ�ʽ
	public static final String investment_mode = "/v1/codes/investment_mode";
	// ���ò�ҵ������Ŀ����
	public static final String industrial_policy = "/v1/codes/industrial_policy";
	// ����/�޸����������
	public static final String SAVE_FROM = "/v1/approvals/save_item_from";
	// ��ȡ�ҵ���Ŀ�ӿ�
	public static final String MY_PROJECT = "/v1/profile/my_events";
	// ɾ����Ŀ�ӿ�
	public static final String REMOVE = "/v1/projects/remove";
	// �鿴��ѡ������������б�
	public static final String SELECTITEM = "/v1/approvals/items";
	// ������Ŀ�ӿ�
	public static final String REVOKE = "/v1/projects/revoke";
	// �鿴�Ѿ��ϴ��ĸ���
	public static final String ATTACHS = "/v1/approvals/attachs";
	// �ϴ�����
	public static final String UP_ATTACHS = "/v1/approvals/upload_attach";
	// ɾ������
	public static final String REMOVE_ATTACHS = "/v1/approvals/remove_attach";
	// 16�鿴������Ϣ
	public static final String ITEM_INFO = "/v1/approvals/item_info";
	// �鿴ԭ��
	public static final String REASON = "/v1/approvals/item_refused_reason";
	// �鿴���������
	public static final String ITEM_FORM = "/v1/approvals/item_form";
	// �ύ
	public static final String SUBMIT_ITEM = "/v1/approvals/submit_item";
	// �鿴����ԭ��
	public static final String REVOKE_REASON = "/v1/projects/revoke_reason";
	// 18.��ȡ��������Ŀ�Ľ׶���Ϣ
	public static final String CATALOG_STEPS = "/v1/approvals/catalog_steps";
	// 6.��ȡ��ѡ�����ĿĿ¼
	public static final String CATALOG = "/v1/approvals/catalog";
	// ��ȡ��������
	public static final String MY_PROFILE = "/v1/profile/my_profile";
	// �޸ĸ�������
	public static final String MODIFY_PROFILE = "/v1/profile/modify_profile";
	// ע���õ�ʡ���������
	public static final String PROVINCES = "/v1/reg_areas/provinces";
	// ע���õĵ��м��������
	public static final String CITIES = "/v1/reg_areas/cities";
	// ע���õ����ؼ��������
	public static final String DISTRICTS = "/v1/reg_areas/districts";
	// ���ϵ�ַ
	// public static final String CZZY = "http://218.60.145.40/hz_tzxm_root/";
	//http://192.168.1.250:8090/tzxm_service
	//http://1.189.195.101/tzxm_service/  ��ʽ����
	// ����ָ��
	public static final String CZZY = "http://1.189.195.101/hz_tzxm_root_hlj/tzxmindex/serviceCzzy";
	// ȫʡͳ��
	public static final String QSTJ = "http://1.189.195.101/hz_tzxm_root_hlj/regionStatistics/basic";

	
	/**
	 * �����ӿ�
	 */
	//���۽ӿ�
	public static final String EVALUATE = "/v1/itemReply/pingjia";
	//��ȡ��Ŀ����
	public static final String PTPROJECT = "/v1/itemReply/PTproject";
	//��ȡ��Ŀ����
	public static final String PTITEMS = "/v1/itemReply/ptItems";
	//��ȡ��Ŀ����
	public static final String COMPLAINT = "/v1/itemReply/tousu";

	
	
	/**
	 * ����ͨ��
	 */
	public static HttpUtils httpUtils;
	static {
		httpUtils = new HttpUtils();
		httpUtils.configSoTimeout(3000);
		httpUtils.configTimeout(3000);
		httpUtils.configCurrentHttpCacheExpiry(1000);

	}

//	/**
//	 * ͼ��������
//	 */
//	public static BitmapUtils bitmapUtils;
//
//	public static BitmapUtils getBitmapUtils(Context context) {
//		if (bitmapUtils == null) {
//			bitmapUtils = new BitmapUtils(context);
//			bitmapUtils.configDefaultLoadingImage(R.drawable.refresh);
//		}
//		return bitmapUtils;
//	}

	/**
	 * @param context
	 * @param view
	 *            ���ؼ���
	 */
	public static void closeKeyBoard(Context context, View view) {
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}


	/**
	 * @param context
	 * @param msg
	 *            ��Ϣ��ʾ
	 */
	public static void toast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}



	/**
	 * @param url
	 *            ��ȡ���ݵ�url
	 * @param requestCallBack
	 *            �ص��ӿ�
	 */
	public static void get(String url, RequestCallBack<String> requestCallBack) {
		httpUtils.send(HttpRequest.HttpMethod.GET, url, requestCallBack);

	}

	public static void getfile(String url, RequestCallBack<Object> requestCallBack) {
		httpUtils.send(HttpRequest.HttpMethod.GET, url, requestCallBack);
	}

	/**
	 * post
	 * 
	 * @param url
	 *            ��ȡ���ݵ�url
	 * @param requestCallBack
	 *            �ص��ӿ�
	 * @return
	 * @throws IOException

	 */
	public static void postForm(String url, RequestParams params, RequestCallBack<String> requestCallBack) {
		httpUtils.send(HttpRequest.HttpMethod.POST, url, params, requestCallBack);
	}






}
