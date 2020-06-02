package com.siwz.app.api.translators;

import com.siwz.app.api.forms.topic.TopicCreateRequest;
import com.siwz.app.api.forms.topic.TopicGetResponse;
import com.siwz.app.api.forms.topic.TopicUpdateRequest;
import com.siwz.app.persistence.dto.Topic;
import com.siwz.app.utils.DateTimeUtil;

public class TopicTranslator {

    public TopicGetResponse translateToTopicGetResponse(Topic topic) {
        TopicGetResponse topicGetResponse = new TopicGetResponse();
        topicGetResponse.setId(topic.getId());
        topicGetResponse.setName(topic.getName());
        topicGetResponse.setDescription(topic.getDescription());
        topicGetResponse.setCreationDate(topic.getCreationDate());
        topicGetResponse.setLimit(topic.getLimit());
        topicGetResponse.setDeadlineDate(topic.getDeadlineDate());
        return topicGetResponse;
    }

    public Topic translateToService(TopicCreateRequest topicCreateRequest) {
        Topic topic = new Topic();
        topic.setName(topicCreateRequest.getName());
        topic.setLimit(topicCreateRequest.getLimit());
        topic.setDescription(topicCreateRequest.getDescription());
        topic.setDeadlineDate(topicCreateRequest.getDeadlineDate());
        topic.setCreationDate(DateTimeUtil.getCurrentDate());
        return topic;
    }

    public Topic translateToService(TopicUpdateRequest topicUpdateRequest) {
        Topic topic = new Topic();
        topic.setName(topicUpdateRequest.getName());
        topic.setLimit(topicUpdateRequest.getLimit());
        topic.setDescription(topicUpdateRequest.getDescription());
        topic.setDeadlineDate(topicUpdateRequest.getDeadlineDate());
        return topic;
    }
}
