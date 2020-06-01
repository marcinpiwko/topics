package com.siwz.app.api.translators;

import com.siwz.app.api.forms.topic.TopicGetResponse;
import com.siwz.app.persistence.dto.Topic;

public class TopicTranslator {

    public TopicGetResponse translateToTopicGetResponse(Topic topic) {
        TopicGetResponse topicGetResponse = new TopicGetResponse();
        topicGetResponse.setName(topic.getName());
        topicGetResponse.setDescription(topic.getDescription());
        topicGetResponse.setCreationDate(topic.getCreationDate());
        topicGetResponse.setLimit(topic.getLimit());
        topicGetResponse.setDeadlineDate(topic.getDeadlineDate());
        return topicGetResponse;
    }
}
