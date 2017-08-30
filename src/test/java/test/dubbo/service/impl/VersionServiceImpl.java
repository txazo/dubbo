package test.dubbo.service.impl;

import test.dubbo.service.VersionService;

public class VersionServiceImpl implements VersionService {

    @Override
    public String getVersion(int time) {
        return "1.0.0";
    }

}
