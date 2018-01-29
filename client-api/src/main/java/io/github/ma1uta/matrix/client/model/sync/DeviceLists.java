package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class DeviceLists {

    private List<String> changed;
}
