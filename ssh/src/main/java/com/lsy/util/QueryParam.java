package com.lsy.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class QueryParam {
    private String type;
    private String propertyName;
    private Object value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static List<QueryParam> builderQueryParamRequest(HttpServletRequest request){
        List<QueryParam> list= Lists.newArrayList();
        Enumeration<String> paramNames=request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String paramName=paramNames.nextElement();
            String value=request.getParameter(paramName);
            if (paramName.startsWith("q_")&& StringUtils.isNotEmpty(value)){
                try {
                    value=new String(value.getBytes("ISO8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String[] array=paramName.split("_");
                if(array.length!=3){
                    throw new IllegalArgumentException("查询参数异常");
                }
                QueryParam queryParam=new QueryParam();
                queryParam.setPropertyName(array[2]);
                queryParam.setType(array[1]);
                queryParam.setValue(value);
                list.add(queryParam);
            }
        }
        return list;
    }
}
