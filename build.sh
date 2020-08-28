#!/usr/bin/env bash

mvn clean package -U

docker build -t registry.cn-hangzhou.aliyuncs.com/springcloud-baron/order .

docker push registry.cn-hangzhou.aliyuncs.com/springcloud-baron/order
