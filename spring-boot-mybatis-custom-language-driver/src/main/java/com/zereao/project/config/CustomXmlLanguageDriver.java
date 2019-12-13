package com.zereao.project.config;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zereao
 * @version 2019/12/13 14:09
 */
public class CustomXmlLanguageDriver extends XMLLanguageDriver {
    /**
     * 自定义 IN 查询语法规则 正则 (#{xxCollection})
     */
    private static final Pattern CUSTOM_IN_QUERY_RULE = Pattern.compile("\\(#\\{(\\w+)}\\)");
    /**
     * 自定义 if 条件查询 if(name != null and someTable.column != "" ) {a.name = #{name} and [other expressions] }
     */
    private static final Pattern CUSTOM_IF_NULL_RULE = Pattern.compile("if\\s*\\((.*)\\)\\s*\\[(.*)]", Pattern.CASE_INSENSITIVE);

    private static final Pattern SCRIPT_TAG_PATTERN = Pattern.compile("^<script>.*</script>$");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        boolean scriptFlag = false;
        Matcher inRuleMatcher = CUSTOM_IN_QUERY_RULE.matcher(script);
        if (inRuleMatcher.find()) {
            script = inRuleMatcher.replaceAll(" (<foreach collection='$1' item='element' separator=','> #{element} </foreach>) ");
            scriptFlag = true;
        }
        Matcher ifRuleMatcher = CUSTOM_IF_NULL_RULE.matcher(script);
        if (ifRuleMatcher.find()) {
            script = ifRuleMatcher.replaceAll(" <if test = \"$1\"> $2 </if> ");
            scriptFlag = true;
        }
        if (scriptFlag) {
            // 检测SQL是否被<script>包裹
            Matcher scriptTagPattern = SCRIPT_TAG_PATTERN.matcher(script);
            if (!scriptTagPattern.find()) {
                script = "<script>" + script + "</script>";
            }
        }
        return super.createSqlSource(configuration, script, parameterType);
    }
}
