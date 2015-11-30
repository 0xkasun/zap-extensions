package org.zaproxy.zap.extension.jiraIssueCreater;

import org.zaproxy.zap.extension.api.ApiImplementor;

/**
 * Created by kausn on 11/30/15.
 */
public class JiraIssueCreaterAPI extends ApiImplementor {

    private static final String PREFIX = "jiraIssueCreater";
    private ExtensionJiraIssueCreater extension;

    public JiraIssueCreaterAPI(ExtensionJiraIssueCreater extension){
        super();
        this.extension=extension;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }
}
