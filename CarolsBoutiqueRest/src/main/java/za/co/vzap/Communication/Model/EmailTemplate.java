package za.co.vzap.Communication.Model;

import za.co.vzap.Interface.Model.IEntity;

public class EmailTemplate implements IEntity {
    public int Id;
    private EmailTypeEnum type;
    private String bodyTemplate;
    private String subjectTemplate;

    public EmailTemplate(EmailTypeEnum type, String bodyTemplate, String subjectTemplate) {
        this.type = type;
        this.bodyTemplate = bodyTemplate;
        this.subjectTemplate = subjectTemplate;
    }

    public EmailTemplate() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public EmailTypeEnum getType() {
        return type;
    }

    public void setType(EmailTypeEnum type) {
        this.type = type;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
    }

    public String getSubjectTemplate() {
        return subjectTemplate;
    }

    public void setSubjectTemplate(String subjectTemplate) {
        this.subjectTemplate = subjectTemplate;
    }

    @Override
    public String toString() {
        return "EmailTemplate{" + "Id=" + Id + ", type=" + type + ", bodyTemplate=" + bodyTemplate + ", subjectTemplate=" + subjectTemplate + '}';
    }
    
}
