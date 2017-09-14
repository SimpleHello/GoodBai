<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="../css/main.css">
<script src="jquery/jquery-1.9.1.js" type="text/javascript" ></script>
<script src="index.js" type="text/javascript" ></script>
<script src="js/url.js" type="text/javascript" ></script>
</head>
<body>
<template>
    <div class="layout">
        <Row type="flex">
            <Col span="5" class="layout-menu-left">
                <Menu active-name="1-2" theme="dark" width="auto" :open-names="['1']">
                    <div class="layout-logo-left"></div>
                    <Submenu name="1">
                        <template slot="title">
                            <Icon type="ios-navigate"></Icon>
                        		   导航一
                        </template>
                        <MenuItem name="1-1">选项 1</MenuItem>
                        <MenuItem name="1-2">选项 2</MenuItem>
                        <MenuItem name="1-3">选项 3</MenuItem>
                    </Submenu>
                    <Submenu name="2">
                        <template slot="title">
                            <Icon type="ios-keypad"></Icon>
                            导航二
                        </template>
                        <MenuItem name="2-1">选项 1</MenuItem>
                        <MenuItem name="2-2">选项 2</MenuItem>
                    </Submenu>
                    <Submenu name="3">
                        <template slot="title">
                            <Icon type="ios-analytics"></Icon>
                            导航三
                        </template>
                        <MenuItem name="3-1">选项 1</MenuItem>
                        <MenuItem name="3-2">选项 2</MenuItem>
                    </Submenu>
                </Menu>
            </Col>
            <Col span="19">
                <div class="layout-header"></div>
                <div class="layout-breadcrumb">
                    <Breadcrumb>
                        <BreadcrumbItem href="#">首页</BreadcrumbItem>
                        <BreadcrumbItem href="#">应用中心</BreadcrumbItem>
                        <BreadcrumbItem>某应用</BreadcrumbItem>
                    </Breadcrumb>
                </div>
                <div class="layout-content">
                    <div class="layout-content-main">内容区域</div>
                </div>
                <div class="layout-copy">
                    2011-2016 &copy; TalkingData
                </div>
            </Col>
        </Row>
    </div>
</template>
<script>
    export default {
        
    }
</script>
</body>

</body>


</html>