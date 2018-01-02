package geek.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class PushRule {

    private List<Object> actions;
    @XmlAttribute(name = "default")
    private Boolean isDefault;
    private Boolean enabled;
    private String ruleId;
    private List<PushCondition> conditions;
    private String pattern;
}
