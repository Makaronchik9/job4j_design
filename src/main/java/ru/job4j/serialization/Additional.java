package ru.job4j.serialization;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "additional")
@XmlAccessorType(XmlAccessType.FIELD)
public class Additional {

    @XmlAttribute
    private String attachment;

    @XmlAttribute
    private String bonus;

    public Additional() {
    }

    public Additional(String attachment, String bonus) {
        this.attachment = attachment;
        this.bonus = bonus;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "Additional{"
                + "attachment='" + attachment + '\''
                + ", bonus='" + bonus + '\''
                + '}';
    }
}
