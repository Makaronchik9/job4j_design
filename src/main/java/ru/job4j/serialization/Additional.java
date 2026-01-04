package ru.job4j.serialization;

public class Additional {

    private String attachment;
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

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Additional{"
                + "attachment='" + attachment + '\''
                + ", bonus='" + bonus + '\''
                + '}';
    }
}
