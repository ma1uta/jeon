package io.github.ma1uta.matrix.client.model.device;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DevicesResponse {

    private List<Device> devices;
}
